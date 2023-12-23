import Foundation
import Security
import LocalAuthentication

public enum RNSecureErrors: Error {
    case KEY_NOT_STORED
    case KEY_REQUIRED
    case KEY_NOT_REMOVED
    case NOT_FOUND
    case UNKNOWN_ERROR
}

@objc(RNSecureStorage)
class RNSecureStorage: NSObject {
    let helper = RNSecureStorageHelper.init()
    
    
    @objc(setItem:value:options:resolver:rejecter:)
    func setItem(_ key:String, value:String, options:[String:Any], resolver: RCTPromiseResolveBlock, rejecter: RCTPromiseRejectBlock){
        let accessible = options["accessible"] as! String
        let status = helper.createKeychainValue(key: key, value: value, accessible: accessible)
        if status {
            resolver("Key stored successfully")
        }else{
            rejecter("KEY_NOT_STORED","RNSecureStorage: An error occurred during key storage", RNSecureErrors.KEY_NOT_STORED)
        }
    }
    
    @objc(getItem:resolver:rejecter:)
    func getItem(_ key:String, resolver: RCTPromiseResolveBlock, rejecter: RCTPromiseRejectBlock){
        let val = helper.getKeychainValue(key: key)
        if val == nil {
            rejecter("NOT_FOUND","RNSecureStorage: Key does not present", RNSecureErrors.NOT_FOUND)
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
            rejecter("NOT_FOUND","RNSecureStorage: There are no stored keys.", RNSecureErrors.NOT_FOUND)
        }
    }
    
    @objc(multiSet:options:resolver:rejecter:)
    func multiSet(_ keyValuePairs:[String:String], options:[String:Any], resolver: RCTPromiseResolveBlock, rejecter: RCTPromiseRejectBlock){
        let accessible = options["accessible"] as! String
        let status = helper.multiSetKeychainValues(keyValuePairs: keyValuePairs, accessible: accessible)
        if status {
            resolver("Key stored successfully")
        }else{
            rejecter("KEY_NOT_STORED","RNSecureStorage: An error occurred during key storage", RNSecureErrors.KEY_NOT_STORED)
        }
    }
    
    /*
     Multi get values by keys array.
     */
    @objc(multiGet:resolver:rejecter:)
    func multiGet(_ keys: [String], resolver: RCTPromiseResolveBlock, rejecter: RCTPromiseRejectBlock) {
        do {
            let vals = try helper.multiGetKeychainValues(keys: keys)
            resolver(vals)
        } catch RNSecureErrors.KEY_REQUIRED {
            rejecter("KEY_REQUIRED", "You must provide at least one key to get", RNSecureErrors.KEY_REQUIRED)
        } catch {
            rejecter("UNKNOWN_ERROR", "An unknown error occurred", error)
        }
    }
    
    @objc(removeItem:resolver:rejecter:)
    func removeItem(_ key:String, resolver: RCTPromiseResolveBlock, rejecter: RCTPromiseRejectBlock){
        let status = helper.removeKeychainValue(key: key)
        if status {
            resolver("Key removed successfully")
        }else{
            rejecter("KEY_NOT_REMOVED","RNSecureStorage: An error occurred during key remove", RNSecureErrors.KEY_NOT_REMOVED)
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
}

