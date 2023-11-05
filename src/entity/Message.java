package entity;

import java.time.LocalDateTime;

public interface Message {

    int getId();

    LocalDateTime getSentTime();

    Key getClearance();

    String getText();

}
