package entity;

import java.time.LocalDateTime;

public interface Message {

    int getId();

    int getSentBy();

    LocalDateTime getSentTime();

    int getClearance();

    String getText();

}
