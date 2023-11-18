package entity;

import java.time.LocalDateTime;

public interface Message {

    int getSentBy();

    Clearance getClearance();

    String getText();

}
