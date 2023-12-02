package entity;

public class EncryptedMessage implements Message{
    private final User sentBy;
    private final Clearance clearance;
    private final String text;

    public EncryptedMessage(User sentBy, Clearance clearance, String text) {
        this.sentBy = sentBy;
        this.clearance = clearance;
        this.text = text;
    }

    @Override
    public User getSentBy() {return sentBy;}

    @Override
    public Clearance getClearance() {return clearance;}

    @Override
    public String getText() {return text;}
}
