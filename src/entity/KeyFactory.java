package entity;

import java.security.PrivateKey;
import java.security.PublicKey;

public interface KeyFactory {

    Key create(int id, PublicKey encryptionKey, PrivateKey decryptionKey);
    Key create(int id);
}
