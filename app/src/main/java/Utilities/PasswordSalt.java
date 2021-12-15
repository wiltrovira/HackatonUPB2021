package Utilities;

import androidx.annotation.NonNull;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

public class PasswordSalt {

    // https://howtodoinjava.com/java/java-security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/

    private static final String MD5 = "MD5";
    private static final String SHA_1 = "SHA_1";
    private static final String SHA_256 = "SHA-256";
    private static final String SHA_384 = "SHA-384";
    private static final String SHA_512 = "SHA-512";


    /**
     * Devuelve un Salt que permite cifrar el password que se guardará en la base de datos
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     */
    public static String getSalt()
            throws NoSuchAlgorithmException, NoSuchProviderException
    {
        // Always use a SecureRandom generator
        SecureRandom sr = new SecureRandom();

        // Create array for salt
        byte[] salt = new byte[16];

        // Get a random salt
        sr.nextBytes(salt);

        // return salt
        return salt.toString();
    }


    /**
     * Devuelve el password cifrado con el Salt y el algoritmo indicado
     * @param passwordToHash
     * @param salt
     * @return
     */
    public static String getSecurePassword(@NonNull String passwordToHash,
                                            @NonNull String salt) {
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
//            MessageDigest md = MessageDigest.getInstance(MD5);
//            MessageDigest md = MessageDigest.getInstance(SHA_512);
            MessageDigest md = MessageDigest.getInstance(SHA_256);

            // Add password bytes to digest
            md.update(salt.getBytes());

            // Get the hash's bytes
            byte[] bytes = md.digest(passwordToHash.getBytes());

            // This bytes[] has bytes in decimal format;
            // Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
                        .substring(1));
            }

            // Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

}
