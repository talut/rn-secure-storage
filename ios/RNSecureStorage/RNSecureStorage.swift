//
//  RNSecureStorage.swift
//
//  Created by Talut TASGIRAN on 10.07.2020.
//
import Foundation

@objc(RNSecureStorage)
class RNSecureStorage: NSObject {

  @objc(setItem:value:options:resolver:rejecter:)
  func setItem(_ key:String, value:String, options:NSDictionary, resolver: RCTPromiseResolveBlock, rejecter: RCTPromiseRejectBlock){
    print(key)
    print(value)
  }
  
  @objc(getItem:resolver:rejecter:)
  func getItem(_ key:String, resolver: RCTPromiseResolveBlock, rejecter: RCTPromiseRejectBlock){
    print(key)
  }
  
  @objc(exist:resolver:rejecter:)
  func exist(_ key:String, resolver: RCTPromiseResolveBlock, rejecter: RCTPromiseRejectBlock){
    print(key)
  }
  
  @objc(getAllKeys:rejecter:)
  func getAllKeys(_ resolver: RCTPromiseResolveBlock, rejecter: RCTPromiseRejectBlock){
    print("getAllKeys")
  }
  
  @objc(multiSet:resolver:rejecter:)
  func multiSet(_ keyValuePair:[[String]], resolver: RCTPromiseResolveBlock, rejecter: RCTPromiseRejectBlock){
    print(keyValuePair[0][0])
  }
  
  @objc(multiGet:resolver:rejecter:)
  func multiSet(_ keys:[String], resolver: RCTPromiseResolveBlock, rejecter: RCTPromiseRejectBlock){
    print(keys)
  }
  
  @objc(removeItem:resolver:rejecter:)
  func removeItem(_ key:String, resolver: RCTPromiseResolveBlock, rejecter: RCTPromiseRejectBlock){
    print(key)
  }
  
  @objc(multiRemove:resolver:rejecter:)
  func multiRemove(_ keys:[String], resolver: RCTPromiseResolveBlock, rejecter: RCTPromiseRejectBlock){
    print(keys)
  }
  
  @objc(clear:rejecter:)
  func clear(_ resolver: RCTPromiseResolveBlock, rejecter: RCTPromiseRejectBlock){
    print("Clear")
  }

}
