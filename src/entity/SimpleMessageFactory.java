package entity;

import java.time.LocalDateTime;

public class SimpleMessageFactory implements MessageFactory{

    @Override
    public Message create(int id, int sentBy, LocalDateTime sentTime, int clearance, String text) {
        return new SimpleMessage(id, sentBy, sentTime, clearance, text) {
        };
    }
}
