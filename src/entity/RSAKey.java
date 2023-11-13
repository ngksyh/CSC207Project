package entity;

import java.security.PrivateKey;
import java.security.PublicKey;

public class RSAKey implements Key{

    private final int id;
    private final PublicKey encryptionKey;
    private final PrivateKey decryptionKey;

    public RSAKey(int id, PublicKey encryptionKey, PrivateKey decryptionKey){
        this.id = id;
        this.encryptionKey = encryptionKey;
        this.decryptionKey = decryptionKey;
    }

    @Override
    public int getId(){return this.id;}

    @Override
    public PublicKey getEncrypt(){return this.encryptionKey;}

    @Override
    public PrivateKey getDecrypt(){return this.decryptionKey;}

}
