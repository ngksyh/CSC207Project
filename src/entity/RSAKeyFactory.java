package entity;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class RSAKeyFactory implements KeyFactory{

    @Override
    public Key create(String encryptionKey, String decryptionKey) {
        return new RSAKey(encryptionKey, decryptionKey);
    }

    public Key create() {
        String[] a = RSACreate();
        return new RSAKey(a[0], a[1]);
    }

    public static String[] RSACreate() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();
            return new String[]{byteToString(publicKey.getEncoded()), byteToString(privateKey.getEncoded())};
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String byteToString(byte[] bytes) { return Base64.getEncoder().encodeToString(bytes); }
}
