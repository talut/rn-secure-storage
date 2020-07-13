//
//  RNSecureStorage.m
//
//  Created by Talut TASGIRAN on 10.07.2020.
//

#import <Foundation/Foundation.h>
#import <React/RCTBridgeModule.h>

@interface RCT_EXTERN_MODULE(RNSecureStorage, NSObject)

/**
 * Set a value from secure storage.
 */
RCT_EXTERN_METHOD(setItem:(NSString *)key value:(NSString *)value options:(NSDictionary *)options resolver:(RCTPromiseResolveBlock)resolve rejecter:(RCTPromiseRejectBlock)reject)

/**
 * Get a value from secure storage.
 */
RCT_EXTERN_METHOD(getItem:(NSString *)key resolver:(RCTPromiseResolveBlock)resolve rejecter:(RCTPromiseRejectBlock)reject)

/**
 * Checks if a key has been set.
 */
RCT_EXTERN_METHOD(exist:(NSString *)key resolver:(RCTPromiseResolveBlock)resolve rejecter:(RCTPromiseRejectBlock)reject)

/**
  * Get all setted keys from secure storage.
  */
RCT_EXTERN_METHOD(getAllKeys:(RCTPromiseResolveBlock *)resolver rejecter:(RCTPromiseRejectBlock)reject)

/**
 * Multiple key pair set for secure storage
 */
RCT_EXTERN_METHOD(multiSet:(NSArray *)keyValuePairs options:(NSDictionary *)options resolver:(RCTPromiseResolveBlock)resolver rejecter:(RCTPromiseRejectBlock)reject)

/**
 * Get multiple values from secure storage.
 */
RCT_EXTERN_METHOD(multiGet:(NSArray *)keys resolver:(RCTPromiseResolveBlock)resolver rejecter:(RCTPromiseRejectBlock)reject)

/**
 * Remove a value from secure storage.
 */
RCT_EXTERN_METHOD(removeItem:(NSString *)key resolver:(RCTPromiseResolveBlock)resolve rejecter:(RCTPromiseRejectBlock)reject)

/**
   * Remove values from secure storage
*/
RCT_EXTERN_METHOD(multiRemove:(NSArray *)keys resolver:(RCTPromiseResolveBlock)resolver rejecter:(RCTPromiseRejectBlock)reject)

/**
   Remove all stored keys. (On error will return unremoved keys)
*/
RCT_EXTERN_METHOD(clear:(RCTPromiseResolveBlock *)resolver rejecter:(RCTPromiseRejectBlock)reject)

/**
  Get supported biometry type
*/
RCT_EXTERN_METHOD(getSupportedBiometryType: (RCTPromiseResolveBlock *)resolver rejecter:(RCTPromiseRejectBlock *)reject)


+ (BOOL)requiresMainQueueSetup
{
    return YES;
}
@end
