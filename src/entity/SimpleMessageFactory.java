package entity;

import java.time.LocalDateTime;

public class SimpleMessageFactory implements MessageFactory{

    @Override
    public Message create(int id, LocalDateTime sentTime, Key clearance, String text) {
        return new SimpleMessage(id, sentTime, clearance, text) {
        };
    }
}
