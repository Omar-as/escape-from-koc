package utils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;

// Based On:
// https://stackoverflow.com/a/2861125/6835329
public final class AccountManager {
    private static final String ALGORITHM = "PBKDF2WithHmacSHA512";
    private static final int ITERATIONS = 65536; // 2 ^ 16
    private static final int SECURITY_PARAMETER = 256;
    private static final int BITS_IN_BYTE = 8;

    public static void createNewAccount(String username, char[] password) {
        var salt = generateSalt();
        var hash = generateHash(salt, password);

        var encoder = Base64.getUrlEncoder().withoutPadding();
        var encodedSalt = encoder.encodeToString(salt);
        var encodedHash = encoder.encodeToString(hash);

        var accountRecord = "%s %s %s".formatted(username, encodedSalt, encodedHash);

        ConfigManager.writeLineToConfigFile(Constants.ACCOUNTS_FILE_NAME, accountRecord);
    }

    public static boolean isValidAuthInput(String username, char[] password) {
        var accountRecords = ConfigManager.readConfigFile(Constants.ACCOUNTS_FILE_NAME);

        for (var record : accountRecords) {
            var recordFields = record.split(" ");
            var recordUsername = recordFields[0];
            if (!Objects.equals(username, recordUsername)) continue;

            var decoder = Base64.getUrlDecoder();
            var recordSalt = decoder.decode(recordFields[1]);
            var recordHash = decoder.decode(recordFields[2]);

            var inputHash = generateHash(recordSalt, password);
            return secureEquals(inputHash, recordHash);
        }

        return false;
    }

    public static boolean doesAccountExist(String username) {
        var accountRecords = ConfigManager.readConfigFile(Constants.ACCOUNTS_FILE_NAME);

        for (var record : accountRecords) {
            var recordFields = record.split(" ");
            var recordUsername = recordFields[0];
            if (Objects.equals(username, recordUsername)) return true;
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

    // Assuming that both arrays are equal lengths
    public static boolean secureEquals(byte[] original, byte[] entered) {
        var isEqual = true;

        for (int i = 0; i < original.length; i++) {
            if (original[i] != entered[i]) {
                if (isEqual) {
                    isEqual = false;
                }
            }
        }
        return isEqual;
    }
}