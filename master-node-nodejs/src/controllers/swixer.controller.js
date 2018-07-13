import async from 'async';
import axios from "axios";
import uuidv4 from 'uuid/v4'

import { SwixerNodes, Swixes } from '../models'
import { tokens } from "../helpers/tokens";

const getSwixerList = (cb) => {
  SwixerNodes.find({
    'swixer.status': 'up'
  }, (err, list) => {
    if (err) {
      cb({
        success: false,
        message: 'error in getting nodes list'
      }, null)
    }
    else {
      cb(list, null);
    }
  })
}

const getAccount = (ip, fromSymbol, toSymbol, clientAddress, destinationAddress, delayInSeconds, cb) => {
  try {
    let url = `http://${ip}:3000/account`
    axios.post(url, {
      'fromSymbol': fromSymbol,
      'toSymbol': toSymbol,
      'clientAddress': clientAddress,
      'destinationAddress': destinationAddress,
      'delayInSeconds': delayInSeconds
    }).then((resp) => {
      let data = resp.data
      if (data['success']) {
        cb(null, {
          swixHash: data['swixHash'],
          address: data['address']
        })
      } else {
        cb({
          message: 'Error in getting account'
        }, null)
      }
    })
  } catch (error) {
    console.log('error', error);
    cb(null, null)
  }
}

const apiGetSwixStatus = (ip, swixHash, cb) => {
  try {
    let url = `http://${ip}:3000/status?swixHash=${swixHash}`
    axios.get(url)
      .then((resp) => {
        let data = resp.data
        if (data['success']) {
          cb({
            'status': data['swixStatus'],
            'txInfos': data['txInfos'],
            'remainingAmount': data['remianingAmount'] || null
          })
        }
      })
  } catch (error) {
    cb(null, null)
  }
}

const getSwixStatus = (req, res) => {
  let swixHash = req.query['hash'];
  let swix = null;
  let node = null;

  async.waterfall([
    (next) => {
      Swixes.findOne({
        'swix_hash': swixHash
      }, (err, resp) => {
        if (err) {
          next({
            success: false,
            message: 'error in getting swix node'
          }, null)
        } else if (!resp) {
          next({
            'success': false,
            'message': 'No swix found.'
          }, null)
        } else {
          swix = resp
          next(null)
        }
      })
    }, (next) => {
      SwixerNodes.findOne({
        'account_addr': swix['node_address'],
        'swixer.status': 'up'
      }, (err, swixNode) => {
        if (err || !swixNode) {
          next({
            'success': false,
            'message': 'Error or No swixer node found.'
          }, null)
        } else {
          node = swixNode
          next();
        }
      })
    }, (next) => {
      apiGetSwixStatus(node['ip'], swixHash, (err, details) => {
        if (err || !resp) {
          next({
            'success': false,
            'message': 'Error occured while getting swix status.'
          })
        } else {
          next(null, {
            'success': true,
            'status': details['status'],
            'txInfos': details['tx_infos'],
            'remainingAmount': details['remaining_amount']
          })
        }
      })
    }
  ], (err, resp) => {
    if (err) res.status(400).send(err)
    else res.status(200).send(resp)
  })
}

const getSwixerNodesList = (req, res) => {
  getSwixerList((err, list) => {
    if (err || !list) {
      res.status(400).send({
        'success': false,
        'list': []
      })
    } else {
      res.status(200).send({
        'success': true,
        'list': list
      })
    }
  })
}

const getSwixDetails = (req, res) => {
  let data = req.body;
  let nodeAddress = req.body['node_address'];
  let fromSymbol = req.body['from_symbol'];
  let toSymbol = req.body['to_symbol']
  let clientAddress = req.body['client_address']
  let destinationAddress = req.body['destination_address']
  let delayInSeconds = req.body['delay_in_seconds']

  let node = null

  async.waterfall([
    (next) => {
      SwixerNodes.findOne({
        'account_addr': nodeAddress,
        'swixer.status': 'up'
      }, (err, swixNode) => {
        if (err || !swixNode) {
          next({
            success: false,
            message: 'Error in finding nodes or No swixer node found.'
          }, null)
        } else {
          node = swixNode
          next(null)
        }
      })
    }, (next) => {
      getAccount(node['ip'], fromSymbol, toSymbol, clientAddress, destinationAddress, delayInSeconds, (err, account) => {
        if (err || !account) {
          next({
            'success': false,
            'message': 'Error occured while getting account.'
          }, null)
        } else {
          let swixerNodeData = new SwixerNodes(data);
          swixerNodeData.save((err) => {
            if (err) {
              next({
                success: false,
                message: 'error in inserting swixer node details'
              })
            } else {
              next(null, {
                'success': true,
                'address': account['address'],
                'swixHash': account['swix_hash'],
              })
            }
          })
        }
      })
    }
  ], (err, resp) => {
    if (err) res.status(400).send(err)
    else res.status(200).send(resp)
  })
}

const getExchangeValue = (req, res) => {
  let node = req.query['node']
  let fromToken = tokens.getToken(req.query['from'])
  let toToken = tokens.getToken(req.query['to'])
  let val = req.query['value'];

  async.waterfall([
    (next) => {
      SwixerNodes.findOne({
        'account_addr': node,
        'swixer.status': 'up'
      }, (err, node) => {
        if (err || !node) {
          next({
            'success': false,
            'message': 'Error at finding node or No swixer node found.'
          }, null)
        } else {
          if (!fromToken || !toToken) {
            next({
              'success': false,
              'message': 'From token OR To token is not found.'
            }, null)
          } else {
            tokens.exchange(fromToken, toToken, val, node['service_charge'], (value) => {
              next(null, {
                'success': true,
                'value': value
              })
            })
          }
        }
      })
    }
  ], (err, resp) => {
    if (err) res.status(400).send(err)
    else res.status(200).send(resp)
  })
}

const registerSwixerNode = (req, res) => {
  let data = req.body;
  let accountAddr = data.account_addr;
  data.token = uuidv4();

  async.waterfall([
    (next) => {
      SwixerNodes.findOne({ account_addr: accountAddr }, (err, node) => {
        if (err) {
          next({
            'success': false,
            'message': 'error in registering node'
          }, null)
        } else {
          next(null, node)
        }
      })
    }, (node, next) => {
      if (!node) {
        data.joined_on = parseInt(Date.now() / 1000);
        let SwixerNodedata = new SwixerNodes(data);
        SwixerNodedata.save((err) => {
          if (err) {
            next({
              'success': false,
              'message': 'error in registering node'
            }, null)
          } else {
            next(null, {
              'success': true,
              'token': data.token,
              'message': 'Node registered successfully.'
            })
          }
        })
      } else {
        SwixerNodes.findOneAndUpdate({ account_addr: accountAddr }, { '$set': data }, (err, resp) => {
          if (err) {
            next({
              'success': false,
              'message': 'error in registering node'
            }, null)
          } else {
            next(null, {
              'success': true,
              'token': data.token,
              'message': 'Node registered successfully.'
            })
          }
        })
      }
    }
  ], (err, resp) => {
    if (err) res.status(400).send(err)
    else res.status(200).send(resp)
  })
}

const deRegisterSwixerNode = (req, res) => {
  let accountAddr = req.body['account_addr'];
  let token = req.body['token'];

  SwixerNodes.findOneAndRemove({
    account_addr: accountAddr,
    token: token
  }, (err, resp) => {
    if (err) {
      res.status(400).send({
        'success': false,
        'message': 'error in deregistering node'
      })
    } else if (!resp) {
      res.status(400).send({
        'success': false,
        'message': 'Node is not registered.'
      })
    } else {
      res.status(200).send({
        'success': true,
        'message': 'Node deregistered successfully.'
      })
    }
  })
}

const updateSwixerNodeInfo = (req, res) => {
  let token = req.body['token'];
  let accountAddr = req.body['account_addr'];
  let info = req.body['info'];

  let node = null
  let initOn = parseInt(Date.now()/1000);

  if (info['type'] === 'swixer') {
    SwixerNodes.findOneAndUpdate({
      'account_addr': accountAddr,
      'token': token
    }, {
        '$set': {
          'swixer.status': 'up',
          'swixer.init_on': initOn,
          'swixer.ping_on': initOn
        }
      }, (err, node) => {
        if (err || !node) {
          res.status(400).send({
            'success': false,
            'message': 'Node is not registered.'
          })
        } else {
          res.status(200).send({
            'success': true,
            'message': 'Node info updated successfully.'
          })
        }
      })
  } else if (info['type'] === 'alive') {
    SwixerNodes.findOneAndUpdate({
      'account_addr': accountAddr,
      'token': token
    }, {
        '$set': {
          'swixer.status': 'up',
          'swixer.ping_on': initOn
        }
      }, (err, node) => {
        if (err || !node) {
          res.status(400).send({
            'success': false,
            'message': 'Node is not registered.'
          })
        } else {
          res.status(200).send({
            'success': true,
            'message': 'Node info updated successfully.'
          })
        }
      })
  }
}

export default {
  getSwixDetails,
  getSwixerNodesList,
  getSwixStatus,
  getExchangeValue,
  registerSwixerNode,
  deRegisterSwixerNode,
  updateSwixerNodeInfo
}