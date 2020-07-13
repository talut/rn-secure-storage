//
//  RNSecureStorageHelper.swift
//  Created by Talut TASGIRAN on 10.03.2020.
//

import Foundation

// TODO: Add keychain share

class RNSecureStorageHelper {
  let appBundleName = Bundle.main.bundleIdentifier!

  /*
   Store an item in keychain.
   */
  func createKeychainValue(key: String, value: String, accessible:String) -> Bool {
    let keyData = appBundleName+"."+key
    let tag = keyData.data(using: .utf8)!
    let valData =  value.data(using: .utf8)
    let query:[String : Any] = [
      kSecClass as String: kSecClassKey,
      kSecAttrApplicationTag as String : tag,
      kSecValueData as String   : valData!,
      kSecAttrAccessible as String: self.accessibleValue(accessible: accessible)
    ]

    SecItemDelete(query as CFDictionary)

    let status = SecItemAdd(query as CFDictionary, nil)

    return status == errSecSuccess

  }

  /*
   Get value from keychain by key.
   */
  func getKeychainValue(key: String) -> Data?  {
    let keyData = appBundleName+"."+key
    let tag = keyData.data(using: .utf8)!

    let query: [String : Any] = [
      kSecClass as String: kSecClassKey,
      kSecAttrApplicationTag as String: tag,
      kSecReturnData as String:  kCFBooleanTrue!,
    ]
    var dataTypeRef: AnyObject? = nil

    let status: OSStatus = SecItemCopyMatching(query as CFDictionary, &dataTypeRef)

    if status == noErr {
      return dataTypeRef as! Data?
    } else {
      return nil
    }

  }
  /*
   Check if key exist in keychain.
   */
  func keychainValueExist(key: String) -> Bool  {
    let keyData = appBundleName+"."+key
    let tag = keyData.data(using: .utf8)!

    let query: [String : Any] = [
      kSecClass as String: kSecClassKey,
      kSecAttrApplicationTag as String: tag,
      kSecReturnData as String:  kCFBooleanTrue!,
    ]

    let status = SecItemCopyMatching(query as CFDictionary, nil)

    return status == errSecSuccess

  }

  /*
   Get all stored keys from keychain.
   */
  func getAllKeychainKeys()  -> [String] {
    var keys = [String]()
    let query: [String: Any] = [
      kSecClass as String : kSecClassKey,
      kSecReturnData as String  : kCFBooleanTrue!,
      kSecReturnAttributes as String : kCFBooleanTrue!,
      kSecMatchLimit as String: kSecMatchLimitAll
    ]

    var result: AnyObject? = nil

    let lastResultCode = withUnsafeMutablePointer(to: &result) {
      SecItemCopyMatching(query as CFDictionary, UnsafeMutablePointer($0))
    }

    if lastResultCode == noErr {
      let array = result as? Array<Dictionary<String, Any>>

      for item in array! {
        let key = item[kSecAttrApplicationTag as String]
        let replaceKey = String(data:key as! Data, encoding: .utf8)!
        let result = replaceKey.replacingOccurrences(of: appBundleName+".", with: "")
        keys.append(result)
      }
    }

    return keys

  }

  /*
   Set multiple values from keychain with keys
   */
  func multiSetKeychainValues(keyValuePairs: [[String]], accessible:String) -> Bool{
    var settedPairs = 0
    for keyValue in keyValuePairs {
      let val = self.createKeychainValue(key: keyValue[0], value: keyValue[1], accessible:accessible)
      if val {
        settedPairs = settedPairs + 1
      }
    }
    return settedPairs == keyValuePairs.count
  }


  /*
   Get multiple values from keychain with keys
   */
  func multiGetKeychainValues(keys: [String]) -> [String:String] {
    var values = [String:String]()
    for key in keys {
      let val = self.getKeychainValue(key: key)
      if val != nil {
        let result = String(data: val!, encoding: .utf8)
        values[key] = result
      }
    }
    return values
  }

  /*
   Remove keychain item.
   */
  func removeKeychainValue(key: String) -> Bool {
    let keyData = appBundleName+"."+key
    let tag = keyData.data(using: .utf8)!

    let query: [String : Any] = [
      kSecClass as String: kSecClassKey,
      kSecAttrApplicationTag as String: tag,
      kSecReturnData as String:  kCFBooleanTrue!,
    ]

    return SecItemDelete(query as CFDictionary) == errSecSuccess
  }

  /*
   Multi remove keychain item.
   */
  func multiRemoveKeychainValue(keys: [String]) -> [String] {
    var unremovedKeys = [String]()
    for key in keys {
      let val = self.removeKeychainValue(key: key)
      if !val {
          unremovedKeys.append(key)
        }
      }
   return unremovedKeys
  }

  /*
   Clear all stored keys
   */
  func clearAllStoredValues() -> [String] {
    var keys = [String]()
    var unremovedKeys = [String]()
    let query: [String: Any] = [
      kSecClass as String : kSecClassKey,
      kSecReturnData as String  : kCFBooleanTrue!,
      kSecReturnAttributes as String : kCFBooleanTrue!,
      kSecMatchLimit as String: kSecMatchLimitAll
    ]

    var result: AnyObject? = nil

    let lastResultCode = withUnsafeMutablePointer(to: &result) {
      SecItemCopyMatching(query as CFDictionary, UnsafeMutablePointer($0))
    }

    if lastResultCode == noErr {
      let array = result as? Array<Dictionary<String, Any>>

      for item in array! {
        let key = item[kSecAttrApplicationTag as String]
        let replaceKey = String(data:key as! Data, encoding: .utf8)!
        let result = replaceKey.replacingOccurrences(of: appBundleName+".", with: "")
        keys.append(result)
      }
    }

    for key in keys {
      let status  = self.removeKeychainValue(key: key)
      if !status {
        unremovedKeys.append(key)
      }
    }
    return unremovedKeys
  }

  func accessibleValue(accessible: String) -> CFString {

    let list = [
      "AccessibleWhenUnlocked": kSecAttrAccessibleWhenUnlocked,
      "AccessibleAfterFirstUnlock": kSecAttrAccessibleAfterFirstUnlock,
      "AccessibleAlways": kSecAttrAccessibleAlways,
      "AccessibleWhenPasscodeSetThisDeviceOnly": kSecAttrAccessibleWhenPasscodeSetThisDeviceOnly,
      "AccessibleWhenUnlockedThisDeviceOnly": kSecAttrAccessibleWhenUnlockedThisDeviceOnly,
      "AccessibleAfterFirstUnlockThisDeviceOnly": kSecAttrAccessibleAfterFirstUnlockThisDeviceOnly,
      "AccessibleAlwaysThisDeviceOnly": kSecAttrAccessibleAlwaysThisDeviceOnly
    ]

    return list[accessible]!

  }


}
