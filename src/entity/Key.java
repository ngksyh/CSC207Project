package entity;

import java.security.PrivateKey;
import java.security.PublicKey;

public interface Key {

    int getId();

    PublicKey getEncrypt();

    PrivateKey getDecrypt();

}
