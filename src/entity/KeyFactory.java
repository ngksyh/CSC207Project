package entity;

public interface KeyFactory {

    Key create(String encryptionKey, String decryptionKey);
    Key create();
}
