package entity;

import java.time.LocalDateTime;

public class SimpleMessage implements Message{
    private final int id;
    private final LocalDateTime sentTime;
    private final Key clearance;
    private final String text;

    public SimpleMessage(int id, LocalDateTime sentTime, Key clearance, String text){
        this.id = id;
        this.sentTime = sentTime;
        this.clearance = clearance;
        this.text = text;
    }

    @Override
    public int getId(){return this.id;}

    @Override
    public LocalDateTime getSentTime() {return sentTime;}

    @Override
    public Key getClearance() {return clearance;}

    @Override
    public String getText() {return text;}
}
