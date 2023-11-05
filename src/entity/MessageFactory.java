package entity;

import java.time.LocalDateTime;

public interface MessageFactory {
    Message create(int id, LocalDateTime sentTime, Key clearance, String text);
}
