package uk.co.jpereira.Marta.utils;

import uk.co.jpereira.Marta.Configuration;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Inject;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;

/**
 * Created by joao on 4/18/16.
 */
public class Crypt {
    @Inject
    Configuration configuration;
    private String encryptValue(String value, String key, String seed) {
        byte[] input = value.getBytes();
        byte[] keyBytes = key.getBytes();
        byte[] ivBytes = "1231asd12easdasd23ddad".getBytes();
        // wrap key data in Key/IV specs to pass to cipher
        SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "DES");
        IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
        // create the cipher with the algorithm you choose
        // see javadoc for Cipher class for more info, e.g.
        Cipher cipher = null;
        byte[] encrypted = null;
        try {
            cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
            SecureRandom secureRandom = new SecureRandom();
            secureRandom.setSeed(seed.getBytes());
            encrypted = new byte[cipher.getOutputSize(input.length)];
            int enc_len = cipher.update(input, 0, input.length, encrypted, 0);
            enc_len += cipher.doFinal(encrypted, enc_len);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (ShortBufferException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }

        return new String(encrypted);
    }

    private String encryptValue(String value) {
        return encryptValue(value, configuration.getKey(), "asdasd");
    }
    private String encryptValue(String value, String seed) {
        return encryptValue(value, configuration.getKey(), seed);
    }

    public static String encrypt(String value) {
        return new Crypt().encryptValue(value);
    }
    public static String encrypt(String value, String seed) {
        return new Crypt().encryptValue(value, seed);
    }
}
