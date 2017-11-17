package com.test.openvpn;
/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.8
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */


public class ClientAPI_ProvideCreds {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected ClientAPI_ProvideCreds(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(ClientAPI_ProvideCreds obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        ovpncliJNI.delete_ClientAPI_ProvideCreds(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setUsername(String value) {
    ovpncliJNI.ClientAPI_ProvideCreds_username_set(swigCPtr, this, value);
  }

  public String getUsername() {
    return ovpncliJNI.ClientAPI_ProvideCreds_username_get(swigCPtr, this);
  }

  public void setPassword(String value) {
    ovpncliJNI.ClientAPI_ProvideCreds_password_set(swigCPtr, this, value);
  }

  public String getPassword() {
    return ovpncliJNI.ClientAPI_ProvideCreds_password_get(swigCPtr, this);
  }

  public void setResponse(String value) {
    ovpncliJNI.ClientAPI_ProvideCreds_response_set(swigCPtr, this, value);
  }

  public String getResponse() {
    return ovpncliJNI.ClientAPI_ProvideCreds_response_get(swigCPtr, this);
  }

  public void setDynamicChallengeCookie(String value) {
    ovpncliJNI.ClientAPI_ProvideCreds_dynamicChallengeCookie_set(swigCPtr, this, value);
  }

  public String getDynamicChallengeCookie() {
    return ovpncliJNI.ClientAPI_ProvideCreds_dynamicChallengeCookie_get(swigCPtr, this);
  }

  public void setReplacePasswordWithSessionID(boolean value) {
    ovpncliJNI.ClientAPI_ProvideCreds_replacePasswordWithSessionID_set(swigCPtr, this, value);
  }

  public boolean getReplacePasswordWithSessionID() {
    return ovpncliJNI.ClientAPI_ProvideCreds_replacePasswordWithSessionID_get(swigCPtr, this);
  }

  public void setCachePassword(boolean value) {
    ovpncliJNI.ClientAPI_ProvideCreds_cachePassword_set(swigCPtr, this, value);
  }

  public boolean getCachePassword() {
    return ovpncliJNI.ClientAPI_ProvideCreds_cachePassword_get(swigCPtr, this);
  }

  public ClientAPI_ProvideCreds() {
    this(ovpncliJNI.new_ClientAPI_ProvideCreds(), true);
  }

}
