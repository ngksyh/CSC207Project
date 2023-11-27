package entity;

import java.security.PrivateKey;
import java.security.PublicKey;

public class RSAKey implements Key{
    private final String encryptionKey;
    private final String decryptionKey;

    public RSAKey(String encryptionKey, String decryptionKey) {
        this.encryptionKey = encryptionKey;
        this.decryptionKey = decryptionKey;
    }

    @Override
    public String getEncrypt(){return this.encryptionKey;}

    @Override
    public String getDecrypt(){return this.decryptionKey;}

}
