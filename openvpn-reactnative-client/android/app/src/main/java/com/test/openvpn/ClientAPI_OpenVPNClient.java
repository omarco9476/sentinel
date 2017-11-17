package com.test.openvpn;
/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.8
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */


public class ClientAPI_OpenVPNClient extends ClientAPI_TunBuilderBase {
  private transient long swigCPtr;

  protected ClientAPI_OpenVPNClient(long cPtr, boolean cMemoryOwn) {
    super(ovpncliJNI.ClientAPI_OpenVPNClient_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  protected static long getCPtr(ClientAPI_OpenVPNClient obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        ovpncliJNI.delete_ClientAPI_OpenVPNClient(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  protected void swigDirectorDisconnect() {
    swigCMemOwn = false;
    delete();
  }

  public void swigReleaseOwnership() {
    swigCMemOwn = false;
    ovpncliJNI.ClientAPI_OpenVPNClient_change_ownership(this, swigCPtr, false);
  }

  public void swigTakeOwnership() {
    swigCMemOwn = true;
    ovpncliJNI.ClientAPI_OpenVPNClient_change_ownership(this, swigCPtr, true);
  }

  public ClientAPI_OpenVPNClient() {
    this(ovpncliJNI.new_ClientAPI_OpenVPNClient(), true);
    ovpncliJNI.ClientAPI_OpenVPNClient_director_connect(this, swigCPtr, swigCMemOwn, true);
  }

  public static void init_process() {
    ovpncliJNI.ClientAPI_OpenVPNClient_init_process();
  }

  public static void uninit_process() {
    ovpncliJNI.ClientAPI_OpenVPNClient_uninit_process();
  }

  public static ClientAPI_MergeConfig merge_config_static(String path, boolean follow_references) {
    return new ClientAPI_MergeConfig(ovpncliJNI.ClientAPI_OpenVPNClient_merge_config_static(path, follow_references), true);
  }

  public static ClientAPI_MergeConfig merge_config_string_static(String config_content) {
    return new ClientAPI_MergeConfig(ovpncliJNI.ClientAPI_OpenVPNClient_merge_config_string_static(config_content), true);
  }

  public static ClientAPI_EvalConfig eval_config_static(ClientAPI_Config config) {
    return new ClientAPI_EvalConfig(ovpncliJNI.ClientAPI_OpenVPNClient_eval_config_static(ClientAPI_Config.getCPtr(config), config), true);
  }

  public static int max_profile_size() {
    return ovpncliJNI.ClientAPI_OpenVPNClient_max_profile_size();
  }

  public static boolean parse_dynamic_challenge(String cookie, ClientAPI_DynamicChallenge dc) {
    return ovpncliJNI.ClientAPI_OpenVPNClient_parse_dynamic_challenge(cookie, ClientAPI_DynamicChallenge.getCPtr(dc), dc);
  }

  public ClientAPI_EvalConfig eval_config(ClientAPI_Config arg0) {
    return new ClientAPI_EvalConfig(ovpncliJNI.ClientAPI_OpenVPNClient_eval_config(swigCPtr, this, ClientAPI_Config.getCPtr(arg0), arg0), true);
  }

  public ClientAPI_Status provide_creds(ClientAPI_ProvideCreds arg0) {
    return new ClientAPI_Status(ovpncliJNI.ClientAPI_OpenVPNClient_provide_creds(swigCPtr, this, ClientAPI_ProvideCreds.getCPtr(arg0), arg0), true);
  }

  public boolean socket_protect(int socket) {
    return ovpncliJNI.ClientAPI_OpenVPNClient_socket_protect(swigCPtr, this, socket);
  }

  public ClientAPI_Status connect() {
    return new ClientAPI_Status(ovpncliJNI.ClientAPI_OpenVPNClient_connect(swigCPtr, this), true);
  }

  public ClientAPI_ConnectionInfo connection_info() {
    return new ClientAPI_ConnectionInfo(ovpncliJNI.ClientAPI_OpenVPNClient_connection_info(swigCPtr, this), true);
  }

  public boolean session_token(ClientAPI_SessionToken tok) {
    return ovpncliJNI.ClientAPI_OpenVPNClient_session_token(swigCPtr, this, ClientAPI_SessionToken.getCPtr(tok), tok);
  }

  public void stop() {
    ovpncliJNI.ClientAPI_OpenVPNClient_stop(swigCPtr, this);
  }

  public void pause(String reason) {
    ovpncliJNI.ClientAPI_OpenVPNClient_pause(swigCPtr, this, reason);
  }

  public void resume() {
    ovpncliJNI.ClientAPI_OpenVPNClient_resume(swigCPtr, this);
  }

  public void reconnect(int seconds) {
    ovpncliJNI.ClientAPI_OpenVPNClient_reconnect(swigCPtr, this, seconds);
  }

  public boolean pause_on_connection_timeout() {
    return ovpncliJNI.ClientAPI_OpenVPNClient_pause_on_connection_timeout(swigCPtr, this);
  }

  public static int stats_n() {
    return ovpncliJNI.ClientAPI_OpenVPNClient_stats_n();
  }

  public static String stats_name(int index) {
    return ovpncliJNI.ClientAPI_OpenVPNClient_stats_name(index);
  }

  public long stats_value(int index) {
    return ovpncliJNI.ClientAPI_OpenVPNClient_stats_value(swigCPtr, this, index);
  }

  public ClientAPI_LLVector stats_bundle() {
    return new ClientAPI_LLVector(ovpncliJNI.ClientAPI_OpenVPNClient_stats_bundle(swigCPtr, this), true);
  }

  public ClientAPI_InterfaceStats tun_stats() {
    return new ClientAPI_InterfaceStats(ovpncliJNI.ClientAPI_OpenVPNClient_tun_stats(swigCPtr, this), true);
  }

  public ClientAPI_TransportStats transport_stats() {
    return new ClientAPI_TransportStats(ovpncliJNI.ClientAPI_OpenVPNClient_transport_stats(swigCPtr, this), true);
  }

  public void post_cc_msg(String msg) {
    ovpncliJNI.ClientAPI_OpenVPNClient_post_cc_msg(swigCPtr, this, msg);
  }

  public void event(ClientAPI_Event arg0) {
    ovpncliJNI.ClientAPI_OpenVPNClient_event(swigCPtr, this, ClientAPI_Event.getCPtr(arg0), arg0);
  }

  public void log(ClientAPI_LogInfo arg0) {
    ovpncliJNI.ClientAPI_OpenVPNClient_log(swigCPtr, this, ClientAPI_LogInfo.getCPtr(arg0), arg0);
  }

  public void external_pki_cert_request(ClientAPI_ExternalPKICertRequest arg0) {
    ovpncliJNI.ClientAPI_OpenVPNClient_external_pki_cert_request(swigCPtr, this, ClientAPI_ExternalPKICertRequest.getCPtr(arg0), arg0);
  }

  public void external_pki_sign_request(ClientAPI_ExternalPKISignRequest arg0) {
    ovpncliJNI.ClientAPI_OpenVPNClient_external_pki_sign_request(swigCPtr, this, ClientAPI_ExternalPKISignRequest.getCPtr(arg0), arg0);
  }

  public boolean remote_override_enabled() {
    return (getClass() == ClientAPI_OpenVPNClient.class) ? ovpncliJNI.ClientAPI_OpenVPNClient_remote_override_enabled(swigCPtr, this) : ovpncliJNI.ClientAPI_OpenVPNClient_remote_override_enabledSwigExplicitClientAPI_OpenVPNClient(swigCPtr, this);
  }

  public void remote_override(ClientAPI_RemoteOverride arg0) {
    if (getClass() == ClientAPI_OpenVPNClient.class) ovpncliJNI.ClientAPI_OpenVPNClient_remote_override(swigCPtr, this, ClientAPI_RemoteOverride.getCPtr(arg0), arg0); else ovpncliJNI.ClientAPI_OpenVPNClient_remote_overrideSwigExplicitClientAPI_OpenVPNClient(swigCPtr, this, ClientAPI_RemoteOverride.getCPtr(arg0), arg0);
  }

  public void clock_tick() {
    if (getClass() == ClientAPI_OpenVPNClient.class) ovpncliJNI.ClientAPI_OpenVPNClient_clock_tick(swigCPtr, this); else ovpncliJNI.ClientAPI_OpenVPNClient_clock_tickSwigExplicitClientAPI_OpenVPNClient(swigCPtr, this);
  }

  public static String crypto_self_test() {
    return ovpncliJNI.ClientAPI_OpenVPNClient_crypto_self_test();
  }

  public static int app_expire() {
    return ovpncliJNI.ClientAPI_OpenVPNClient_app_expire();
  }

  public static String platform() {
    return ovpncliJNI.ClientAPI_OpenVPNClient_platform();
  }

  public static String copyright() {
    return ovpncliJNI.ClientAPI_OpenVPNClient_copyright();
  }

}
