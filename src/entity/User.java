package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public interface User {


    String getName();

    String getPassword();

    Clearance getClearance();

    Boolean getIsadmin();

    void setIsAdmin(Boolean b);

    void setClearance(Clearance c);





}
