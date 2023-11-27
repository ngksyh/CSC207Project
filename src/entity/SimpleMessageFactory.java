package entity;

import java.time.LocalDateTime;

public class SimpleMessageFactory implements MessageFactory{

    @Override
    public Message create(User sentBy, Clearance clearance, String text) {
        return new SimpleMessage(sentBy, clearance, text);
    }
}
