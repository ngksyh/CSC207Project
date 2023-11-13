package entity;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class RSAKeyFactory implements KeyFactory{

    @Override
    public Key create(int id, String encryptionKey, String decryptionKey) {
        return new RSAKey(id, encryptionKey, decryptionKey);
    }

    public Key create(int id) {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();

            return new RSAKey(id, byteToString(publicKey.getEncoded()), byteToString(privateKey.getEncoded()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }


    }
    public static String byteToString(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }
}
