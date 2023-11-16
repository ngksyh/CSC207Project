package entity;

public interface KeyFactory {

    Key create(int id, String encryptionKey, String decryptionKey);
    Key create(int id);
}
