package entity;

import java.time.LocalDateTime;

public class SimpleMessage implements Message{
    private final int id;
    private final int sentBy;
    private final LocalDateTime sentTime;
    private final int clearance;
    private final String text;

    public SimpleMessage(int id, int sentBy, LocalDateTime sentTime, int clearance, String text){
        this.id = id;
        this.sentBy = sentBy;
        this.sentTime = sentTime;
        this.clearance = clearance;
        this.text = text;
    }

    @Override
    public int getId(){return this.id;}

    @Override
    public LocalDateTime getSentTime() {return sentTime;}

    @Override
    public int getSentBy() {return sentBy;}

    @Override
    public int getClearance() {return clearance;}

    @Override
    public String getText() {return text;}
}
