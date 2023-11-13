package entity;
public class RSAKey implements Key{

    private final int id;
    private final String encryptionKey;
    private final String decryptionKey;

    public RSAKey(int id, String encryptionKey, String decryptionKey){
        this.id = id;
        this.encryptionKey = encryptionKey;
        this.decryptionKey = decryptionKey;
    }

    @Override
    public int getId(){return this.id;}

    @Override
    public String getEncrypt(){return this.encryptionKey;}

    @Override
    public String getDecrypt(){return this.decryptionKey;}

}
