package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.spi.LocaleNameProvider;

public class CommonUser implements User{

    private final String name;
    private String password;

    private Boolean isadmin;

    private Clearance clearance;




    /**
     * Requires: password is valid.
     * @param name
     * @param password
     */
    public CommonUser(String name, String password, Boolean isAdmin, Clearance clearance) {
        this.name = name;
        this.password = password;
        this.isadmin = isAdmin;
        this.clearance = clearance;
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

    public void setClearance(Clearance clearance){this.clearance = clearance;}

    @Override
    public void setIsAdmin(Boolean value) {
        this.isadmin = value;
    }


}
