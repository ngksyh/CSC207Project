package entity;

import java.time.LocalDateTime;

public interface MessageFactory {
    Message create(int sentBy, Clearance clearance, String text);
}
