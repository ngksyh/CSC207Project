package entity;

import java.util.ArrayList;
import java.util.HashMap;

public class BasicChannel implements Channel{

    private final int id;
    private String name;
    private final ArrayList<Integer> members;
    private final ArrayList<Message> messages;
    private final HashMap<Integer, Key> clearances;
    private final ArrayList<Integer> moderators;

    public BasicChannel(int id, String name, ArrayList<Integer> members, ArrayList<Integer> moderators){
        this.id = id;
        this.name = name;
        this.members = members;
        this.messages = new ArrayList<Message>();
        this.clearances = new HashMap<Integer, Key>();
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
    public ArrayList<Message> getMessages(){return this.messages;}

    @Override
    public void addClearance(Key key) {this.clearances.put(key.getId(), key);}

    @Override
    public HashMap<Integer, Key> getClearances() {return clearances;}

    @Override
    public void addModerator(Integer user) {this.moderators.add(user);}

    @Override
    public ArrayList<Integer> getModerators() {return moderators;}
}
