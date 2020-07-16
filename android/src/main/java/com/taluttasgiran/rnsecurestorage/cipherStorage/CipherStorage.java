package com.taluttasgiran.rnsecurestorage.cipherStorage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.taluttasgiran.rnsecurestorage.SecurityLevel;
import com.taluttasgiran.rnsecurestorage.exceptions.CryptoFailedException;
import com.taluttasgiran.rnsecurestorage.exceptions.KeyStoreAccessException;

import java.security.Key;

@SuppressWarnings({"unused", "WeakerAccess"})
public interface CipherStorage {
    //region Helper classes

    /**
     * basis for storing credentials in different data type formats.
     */
    abstract class CipherResult<T> {
        public final T value;

        public CipherResult(final T value) {
            this.value = value;
        }
    }

    /**
     * Credentials in bytes array, often a result of encryption.
     */
    class EncryptionResult extends CipherResult<byte[]> {
        /**
         * Name of used for encryption cipher storage.
         */
        public final String cipherName;

        /**
         * Main constructor.
         */
        public EncryptionResult(final byte[] value, final String cipherName) {
            super(value);
            this.cipherName = cipherName;
        }

        /**
         * Helper constructor. Simplifies cipher name extraction.
         */
        public EncryptionResult(final byte[] value, @NonNull final CipherStorage cipherStorage) {
            this(value, cipherStorage.getCipherStorageName());
        }
    }

    /**
     * Credentials in string's, often a result of decryption.
     */
    class DecryptionResult extends CipherResult<String> {
        private final SecurityLevel securityLevel;

        public DecryptionResult(final String value) {
            this(value, SecurityLevel.ANY);
        }

        public DecryptionResult(final String value, final SecurityLevel level) {
            super(value);
            securityLevel = level;
        }

        public SecurityLevel getSecurityLevel() {
            return securityLevel;
        }
    }

    /**
     * Ask access permission for decrypting credentials in provided context.
     */
    class DecryptionContext extends CipherResult<byte[]> {
        public final Key key;
        public final String keyAlias;

        public DecryptionContext(@NonNull final String keyAlias,
                                 @NonNull final Key key,
                                 @NonNull final byte[] value) {
            super(value);
            this.keyAlias = keyAlias;
            this.key = key;
        }
    }

    /**
     * Get access to the results of decryption via properties.
     */
    interface WithResults {
        /**
         * Get reference on results.
         */
        @Nullable
        DecryptionResult getResult();

        /**
         * Get reference on capture error.
         */
        @Nullable
        Throwable getError();

        /**
         * Block thread and wait for any result of execution.
         */
        void waitResult();
    }

    /**
     * Handler that allows to inject some actions during decrypt operations.
     */
    interface DecryptionResultHandler extends WithResults {
        /**
         * Ask user for interaction, often its unlock of keystore by biometric data providing.
         */
        void askAccessPermissions(@NonNull final DecryptionContext context);

        /**
         *
         */
        void onDecrypt(@Nullable final DecryptionResult decryptionResult, @Nullable final Throwable error);
    }
    //endregion

    //region API

    /**
     * Encrypt credentials with provided key (by key) and required security level.
     */
    @NonNull
    EncryptionResult encrypt(@NonNull final String key,
                             @NonNull final String value,
                             @NonNull final SecurityLevel level)
            throws CryptoFailedException;

    /**
     * Decrypt credentials with provided key (by key) and required security level.
     * In case of key stored in weaker security level than required will be raised exception.
     * That can happens during migration from one version of library to another.
     */
    @NonNull
    DecryptionResult decrypt(@NonNull final String key,
                             @NonNull final byte[] value,
                             @NonNull final SecurityLevel level)
            throws CryptoFailedException;

    /**
     * Decrypt the credentials but redirect results of operation to handler.
     */
    void decrypt(@NonNull final DecryptionResultHandler handler,
                 @NonNull final String key,
                 @NonNull final byte[] value,
                 @NonNull final SecurityLevel level)
            throws CryptoFailedException;

    /**
     * Remove key (by key) from storage.
     */
    void removeKey(@NonNull final String key) throws KeyStoreAccessException;
    //endregion

    //region Configuration

    /**
     * Storage name.
     */
    String getCipherStorageName();

    /**
     * Minimal API level needed for using the storage.
     */
    int getMinSupportedApiLevel();

    /**
     * Provided security level.
     */
    SecurityLevel securityLevel();

    /**
     * True - based on secured hardware capabilities, otherwise False.
     */
    boolean supportsSecureHardware();

    /**
     * True - based on biometric capabilities, otherwise false.
     */
    boolean isBiometrySupported();

    /**
     * The higher value means better capabilities.
     * Formula:
     * = 1000 * isBiometrySupported() +
     * 100 * isSecureHardware() +
     * minSupportedApiLevel()
     */
    int getCapabilityLevel();

    /**
     * Get default name for key/service.
     */
    String getDefaultAliasServiceName();
    //endregion
}
