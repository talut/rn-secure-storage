package com.taluttasgiran.rnsecurestorage;

import com.facebook.proguard.annotations.DoNotStrip;
 
@DoNotStrip
public class NoSuchKeyException extends RuntimeException {

  @DoNotStrip
  public NoSuchKeyException(String msg) {
    super(msg);
  }
}