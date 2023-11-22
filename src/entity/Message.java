package entity;

import java.time.LocalDateTime;

public interface Message {

    User getSentBy();

    Clearance getClearance();

    String getText();

}
