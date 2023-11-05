package entity;

import java.time.LocalDateTime;
import java.util.HashMap;

public interface User {


    int getId();

    String getName();

    String getPassword();

    LocalDateTime getCreationTime();

    void addChannel(Channel channel);

    HashMap<Integer, Channel> getChannel();


}
