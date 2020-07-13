// Type definitions for rn-secure-storage 3.0.0
// Project: https://github.com/akiver/rn-secure-storage
// Definitions by: Talut TASGIRAN <https://github.com/talut>
// TypeScript Version: 3.9.6

declare module "rn-secure-storage" {
  export enum ACCESSIBLE {
    /**
     * The data in the keychain item cannot be accessed after a restart until the device
     * has been unlocked once by the user.
     */
    AFTER_FIRST_UNLOCK = "AccessibleAfterFirstUnlock",
    /**
     * The data in the keychain item cannot be accessed after a restart until the device
     * has been unlocked once by the user.
     * Items with this attribute never migrate to a new device.
     */
    AFTER_FIRST_UNLOCK_THIS_DEVICE_ONLY = "AccessibleAfterFirstUnlockThisDeviceOnly",
    /**
     * The data in the keychain item can always be accessed regardless of whether
     * the device is locked.
     */
    ALWAYS = "AccessibleAlways",
    /**
     * The data in the keychain item can always be accessed regardless of whether the
     * device is locked.
     * Items with this attribute never migrate to a new device.
     */
    ALWAYS_THIS_DEVICE_ONLY = "AccessibleAlwaysThisDeviceOnly",
    /**
     * The data in the keychain can only be accessed when the device is unlocked.
     * Only available if a passcode is set on the device.
     * Items with this attribute never migrate to a new device.
     */
    WHEN_PASSCODE_SET_THIS_DEVICE_ONLY = "AccessibleWhenPasscodeSetThisDeviceOnly",
    /**
     * The data in the keychain item can be accessed only while the device is
     * unlocked by the user.
     * This is the default value.
     */
    WHEN_UNLOCKED = "AccessibleWhenUnlocked",
    /**
     * The data in the keychain item can be accessed only while the device is
     * unlocked by the user.
     * Items with this attribute do not migrate to a new device.
     */
    WHEN_UNLOCKED_THIS_DEVICE_ONLY = "AccessibleWhenUnlockedThisDeviceOnly",
  }

  type SetOptions = {
    /**
     * iOS ONLY!
     * This indicates when a keychain item is accessible, see possible values in RNSecureStorage.ACCESSIBLE.
     * Default: ACCESSIBLE.WHEN_UNLOCKED
     */
    accessible: ACCESSIBLE
  }

  const RNSecureStorage: RNSecureStorageStatic

  export interface RNSecureStorageStatic {
    /**
     * Set a value.
     */
    setItem(key: string, value: string, options: SetOptions): Promise<string | null>
    /**
     * Get a value from secure storage.
     */
    getItem(key: string): Promise<string | null>
    /**
     * Checks if a key has been set.
     */
    exist(key: string): Promise<boolean | null>
    /**
     * Get all setted keys from secure storage.
     */
    getAllKeys(): Promise<string[] | null>
    /**
     * Multiple key pair set for secure storage
     */
    multiSet(pairs: Array<[string, string]>, options: SetOptions): Promise<string[] | null>
    /**
     * Get multiple values from secure storage.
     */
    multiGet(keys: string[]): Promise<string[] | null>
    /**
     * Remove a value from secure storage.
     */
    removeItem(key: string): Promise<string | null>
    /**
     * Remove values from secure storage (On error will return unremoved keys)
     */
    multiRemove(keys: string[]): Promise<string | null>
    /**
     * Get supported biometry type (Will return FaceID, TouchID, Fingerprint, Iris, Face or undefined/null)
     */
    getSupportedBiometryType(): Promise<string | null>
    /**
     * Removes whole RNSecureStorage data (On error will return unremoved keys)
     */
    clear(): Promise<string | null>
  }

  export default RNSecureStorage
}
