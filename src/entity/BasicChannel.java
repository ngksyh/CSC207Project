package entity;

import java.util.ArrayList;
import java.util.HashMap;

public class BasicChannel implements Channel{

    private final int id;
    private String name;
    private HashMap<Integer, User> members;
    private ArrayList<Message> messages;
    private HashMap<Integer, Key> clearances;
    private HashMap<Integer, User> moderators;

    public BasicChannel(int id, String name){
        this.id = id;
        this.name = name;
        this.members = new HashMap<Integer, User>();
        this.messages = new ArrayList<String>();
        this.clearances = new HashMap<Integer, Key>();
        this.moderators = new HashMap<Integer, User>();
    }

    @Override
    public int getId() {return id;}
    @Override
    public String getName() {return name;}

    @Override
    public void addMember(User user) {this.members.put(user.getId(),user);}

    @Override
    public HashMap<Integer, User> getMembers(){return this.members;}

    @Override
    public void addMessage(Message message) {this.messages.add(message);}

    @Override
    public ArrayList<Message> getMessages(){return this.messages;}

    @Override
    public void addClearance(Key key) {this.clearances.put(key.getId(), key);}

    @Override
    public HashMap<Integer, Key> getClearances() {return clearances;}

    @Override
    public void addModerator(User user) {this.moderators.put(user.getId(), user);}

    @Override
    public HashMap<Integer, User> getModerators() {return moderators;}
}
