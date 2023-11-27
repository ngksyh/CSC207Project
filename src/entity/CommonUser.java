package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.spi.LocaleNameProvider;

public class CommonUser implements User{

    private final String name;
    private String password;

    public Boolean isadmin;

    public Clearance clearance;

    public Clearance clearance_0 = new Clearance("basic", 0);


    /**
     * Requires: password is valid.
     * @param name
     * @param password
     */
    CommonUser(String name, String password) {
        this.name = name;
        this.password = password;
        this.isadmin = false;
        this.clearance = clearance_0;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public Clearance getClearance() {
        return clearance;
    }

    public Boolean getIsadmin() {
        return isadmin;
    }


}
