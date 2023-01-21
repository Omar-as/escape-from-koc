package managers;

import models.Account;
import utils.Constants;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.Objects;

/**
 * Account Manager
 * <p>
 * Handles secure login.
 * <p>
 * Patterns:
 * 1. Low Coupling : This class does not have unnecessary dependencies on other classes.
 * 2. High Cohesion: This class has one responsibility and does it well.
 * <p>
 * Based On:
 * <a href="https://stackoverflow.com/a/2861125/6835329">StackOverflow Thread</a>
 */
public final class AccountManager {
    private static final String ALGORITHM = "PBKDF2WithHmacSHA512";
    private static final int ITERATIONS = 65536; // 2 ^ 16
    private static final int SECURITY_PARAMETER = 256;
    private static final int BITS_IN_BYTE = 8;
    private static String username;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        AccountManager.username = username;
    }

    public static void createNewAccount(String username, char[] password) {
        var salt = generateSalt();
        var hash = generateHash(salt, password);

        var encoder = Base64.getUrlEncoder().withoutPadding();
        var encodedHash = encoder.encodeToString(hash);
        var encodedSalt = encoder.encodeToString(salt);

        var account = new Account(username, encodedHash, encodedSalt);

        DataStoreManager.getInstance().addToCollection(Constants.ACCOUNTS_COLLECTION_NAME, account, Account.class);
    }

    public static boolean isValidAuthInput(String username, char[] password) {
        var accounts = DataStoreManager.getInstance().getCollection(Constants.ACCOUNTS_COLLECTION_NAME, Account.class);

        for (var account : accounts) {
            if (!Objects.equals(username, account.getUsername())) continue;

            var decoder = Base64.getUrlDecoder();
            var recordHash = decoder.decode(account.getBase64EncodedHash());
            var recordSalt = decoder.decode(account.getBase64EncodedSalt());

            var inputHash = generateHash(recordSalt, password);
            return secureEquals(inputHash, recordHash);
        }

        return false;
    }

    public static boolean doesAccountExist(String username) {
        var accounts = DataStoreManager.getInstance().getCollection(Constants.ACCOUNTS_COLLECTION_NAME, Account.class);

        for (var account : accounts) {
            if (Objects.equals(username, account.getUsername())) return true;
        }

        return false;
    }

    private static byte[] generateSalt() {
        var salt = new byte[SECURITY_PARAMETER / BITS_IN_BYTE];
        var random = new SecureRandom();
        random.nextBytes(salt); // Set salt using random bytes
        return salt;
    }

    private static byte[] generateHash(byte[] salt, char[] password) {
        var keySpec = new PBEKeySpec(password, salt, ITERATIONS, SECURITY_PARAMETER);
        try {
            var keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
            return keyFactory.generateSecret(keySpec).getEncoded();
        } catch (InvalidKeySpecException e) {
            throw new IllegalStateException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    // Assumes that both arrays are equal lengths
    public static boolean secureEquals(byte[] arr1, byte[] arr2) {
        var isEqual = true;

        for (int i = 0; i < arr1.length; i++) {
            isEqual = isEqual && arr1[i] == arr2[i];
        }
        return isEqual;
    }
}