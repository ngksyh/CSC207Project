package entity;

import java.util.ArrayList;
import java.util.HashMap;

public class BasicChannel implements Channel{

    private final int id;
    private String name;
    private final ArrayList<Message> messages;
    private ArrayList<Clearance> clearances;
    private final ArrayList<Integer> members;
    private final ArrayList<Integer> moderators;

    public BasicChannel(int id, String name, ArrayList<Integer> members, ArrayList<Integer> moderators){
        this.id = id;
        this.name = name;
        this.messages = new ArrayList<Message>();
        this.clearances = new ArrayList<Clearance>();
        this.members = members;
        this.moderators = moderators;
    }

    @Override
    public int getId() {return id;}
    @Override
    public String getName() {return name;}

    @Override
    public void addMember(Integer user) {this.members.add(user);}

    @Override
    public ArrayList<Integer> getMembers(){return this.members;}

    @Override
    public void addMessage(Message message) {this.messages.add(message);}

    @Override
    public void addMessage(ArrayList<Message> messages) {this.messages.addAll(messages);}

    @Override
    public ArrayList<Message> getMessages(){return this.messages;}

    @Override
    public void addClearance(String name, Key key) {this.clearances.add(new BasicClearance(name, key));}

    @Override
    public void addClearance(ArrayList<Clearance> clearances) {
        this.clearances.addAll(clearances);
    }

    @Override
    public ArrayList<Clearance> getClearances() {return clearances;}

    @Override
    public void addModerator(Integer user) {this.moderators.add(user);}

    @Override
    public ArrayList<Integer> getModerators() {return moderators;}
}
