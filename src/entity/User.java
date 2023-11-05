package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public interface User {


    String getName();

    String getPassword();

    LocalDateTime getCreationTime();

    void addChannel(Integer channel);

    ArrayList<Integer> getChannel();


}
