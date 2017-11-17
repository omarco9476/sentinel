package com.test.openvpn;
/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.8
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */


public class ClientAPI_LLVector {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected ClientAPI_LLVector(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(ClientAPI_LLVector obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        ovpncliJNI.delete_ClientAPI_LLVector(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public ClientAPI_LLVector() {
    this(ovpncliJNI.new_ClientAPI_LLVector__SWIG_0(), true);
  }

  public ClientAPI_LLVector(long n) {
    this(ovpncliJNI.new_ClientAPI_LLVector__SWIG_1(n), true);
  }

  public long size() {
    return ovpncliJNI.ClientAPI_LLVector_size(swigCPtr, this);
  }

  public long capacity() {
    return ovpncliJNI.ClientAPI_LLVector_capacity(swigCPtr, this);
  }

  public void reserve(long n) {
    ovpncliJNI.ClientAPI_LLVector_reserve(swigCPtr, this, n);
  }

  public boolean isEmpty() {
    return ovpncliJNI.ClientAPI_LLVector_isEmpty(swigCPtr, this);
  }

  public void clear() {
    ovpncliJNI.ClientAPI_LLVector_clear(swigCPtr, this);
  }

  public void add(long x) {
    ovpncliJNI.ClientAPI_LLVector_add(swigCPtr, this, x);
  }

  public long get(int i) {
    return ovpncliJNI.ClientAPI_LLVector_get(swigCPtr, this, i);
  }

  public void set(int i, long val) {
    ovpncliJNI.ClientAPI_LLVector_set(swigCPtr, this, i, val);
  }

}
