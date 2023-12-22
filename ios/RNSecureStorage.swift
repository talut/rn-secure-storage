import Foundation
import Security
import LocalAuthentication

@objc(RNSecureStorage)
class RNSecureStorage: NSObject {
  let kBiometryTypeTouchID = "TouchID"
  let kBiometryTypeFaceID = "FaceID"
  let helper = RNSecureStorageHelper.init()

  enum RNSecureErrors: Error {
    case keyNotStored
    case keyNotPresent
    case keyNotRemoved
    case keyDoesNotPresent
    case thereAreNoKeys
    case clearNotWorked
    case anErrorOccured
    case unknown
  }

  @objc(setItem:value:options:resolver:rejecter:)
  func setItem(_ key:String, value:String, options:[String:Any], resolver: RCTPromiseResolveBlock, rejecter: RCTPromiseRejectBlock){
    let accessible = options["accessible"] as! String
    let status = helper.createKeychainValue(key: key, value: value, accessible: accessible)
    if status {
      resolver("Key stored successfully")
    }else{
      rejecter("keyNotStored","RNSecureStorage: An error occurred during key storage", RNSecureErrors.keyNotStored)
    }
  }

  @objc(getItem:resolver:rejecter:)
  func getItem(_ key:String, resolver: RCTPromiseResolveBlock, rejecter: RCTPromiseRejectBlock){
    let val = helper.getKeychainValue(key: key)
    if val == nil {
      rejecter("keyDoesNotPresent","RNSecureStorage: Key does not present", RNSecureErrors.keyDoesNotPresent)
    } else {
      let result = String(data: val!, encoding: .utf8)
      resolver(result)
    }
  }

  @objc(exist:resolver:rejecter:)
  func exist(_ key:String, resolver: RCTPromiseResolveBlock, rejecter: RCTPromiseRejectBlock){
    let val = helper.keychainValueExist(key: key)
    resolver(val)
  }

  @objc(getAllKeys:rejecter:)
  func getAllKeys(_ resolver: RCTPromiseResolveBlock, rejecter: RCTPromiseRejectBlock){

    let keys = helper.getAllKeychainKeys()

    if keys.count > 0 {
      resolver(keys)
    }else{
      rejecter("thereAreNoKeys","RNSecureStorage: There are no stored keys.", RNSecureErrors.thereAreNoKeys)
    }
  }

  @objc(multiSet:options:resolver:rejecter:)
  func multiSet(_ keyValuePairs:[String:String], options:[String:Any], resolver: RCTPromiseResolveBlock, rejecter: RCTPromiseRejectBlock){
    let accessible = options["accessible"] as! String
    let status = helper.multiSetKeychainValues(keyValuePairs: keyValuePairs, accessible: accessible)
    if status {
      resolver("Key stored successfully")
    }else{
      rejecter("keyNotStored","RNSecureStorage: An error occurred during key storage", RNSecureErrors.keyNotStored)
    }
  }

  /*
   Multi get values by keys array.
   */
  @objc(multiGet:resolver:rejecter:)
  func multiGet(_ keys:[String], resolver: RCTPromiseResolveBlock, rejecter: RCTPromiseRejectBlock){
    let vals = helper.multiGetKeychainValues(keys: keys)
    if vals.count > 0 {
      resolver(vals)
    }else{
      rejecter("anErrorOccured","RNSecureStorage: An error occurred during multi getting values.", RNSecureErrors.anErrorOccured)
    }
  }

  @objc(removeItem:resolver:rejecter:)
  func removeItem(_ key:String, resolver: RCTPromiseResolveBlock, rejecter: RCTPromiseRejectBlock){
    let status = helper.removeKeychainValue(key: key)
    if status {
      resolver("Key removed successfully")
    }else{
      rejecter("keyNotStored","RNSecureStorage: An error occurred during key remove", RNSecureErrors.keyNotRemoved)
    }
  }

  /*
   Clear all given keys. (On error will return un-removed keys)
   */
  @objc(multiRemove:resolver:rejecter:)
  func multiRemove(_ keys:[String], resolver: RCTPromiseResolveBlock, rejecter: RCTPromiseRejectBlock){
    let unremovedKeys = helper.multiRemoveKeychainValue(keys: keys)
    if unremovedKeys.count>0 {
      resolver(unremovedKeys)
    }else{
      resolver("Keys removed successfully")
    }
  }


  /*
   Clear all stored keys. (On error will return unremoved keys)
   */
  @objc(clear:rejecter:)
  func clear(_ resolver: RCTPromiseResolveBlock, rejecter: RCTPromiseRejectBlock){
    let keys = helper.clearAllStoredValues()
    if keys.count > 0 {
      resolver(keys)
    }else{
      resolver("All stored keys removed.")
    }
  }
  /*
   Get supported biometry type
   */
  @objc(getSupportedBiometryType:rejecter:)
  func getSupportedBiometryType(_ resolve: @escaping RCTPromiseResolveBlock, rejecter reject: RCTPromiseRejectBlock) -> Void {
    var error: NSError?
    let canEvaluatePolicy = LAContext().canEvaluatePolicy(.deviceOwnerAuthenticationWithBiometrics, error: &error)

    if error==nil && canEvaluatePolicy {
      if #available(iOS 11.0, *) {
        if LAContext().biometryType == LABiometryType.faceID{
          return resolve(kBiometryTypeFaceID)
        }
      }
      return resolve(kBiometryTypeTouchID);
    }
    return resolve(nil)
  }

}
