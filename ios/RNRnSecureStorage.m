
#import "RNRnSecureStorage.h"
#import "Valet/Valet.h"
@implementation RNRnSecureStorage

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}
RCT_EXPORT_MODULE()

RCT_EXPORT_METHOD(set:(NSString *)key value:(NSString *)value)
{
    VALValet *myValet = [[VALValet alloc] initWithIdentifier:[[NSBundle mainBundle] bundleIdentifier] accessibility:VALAccessibilityWhenUnlocked];
    [myValet setString:value forKey:key];
}

RCT_EXPORT_METHOD(get: (NSString *)key findEventsWithResolver:(RCTPromiseResolveBlock)resolve
                  rejecter:(RCTPromiseRejectBlock)reject)
{
    VALValet *myValet = [[VALValet alloc] initWithIdentifier:[[NSBundle mainBundle] bundleIdentifier] accessibility:VALAccessibilityWhenUnlocked];
    resolve([myValet stringForKey:key]);
}

RCT_EXPORT_METHOD(clearWithKey:(NSString *)key)
{
    VALValet *myValet = [[VALValet alloc] initWithIdentifier:[[NSBundle mainBundle] bundleIdentifier] accessibility:VALAccessibilityWhenUnlocked];
    [myValet removeObjectForKey:key];
}

RCT_EXPORT_METHOD(clearAll)
{
    VALValet *myValet = [[VALValet alloc] initWithIdentifier:[[NSBundle mainBundle] bundleIdentifier] accessibility:VALAccessibilityWhenUnlocked];
    [myValet removeAllObjects];
}

@end
  
