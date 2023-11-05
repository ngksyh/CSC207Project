package entity;

public class RSAKeyFactory implements KeyFactory{

    @Override
    public Key create(int id, String encryptionKey, String decryptionKey) {
        return new RSAKey(id, encryptionKey, decryptionKey);
    }
}
