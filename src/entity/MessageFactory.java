package entity;

import java.time.LocalDateTime;

public interface MessageFactory {
    Message create(User sentBy, Clearance clearance, String text);
}
