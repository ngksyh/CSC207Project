package entity;

import java.time.LocalDateTime;

public interface MessageFactory {
    Message create(int id, int sentBy, LocalDateTime sentTime, int clearance, String text);
}
