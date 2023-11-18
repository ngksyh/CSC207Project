package entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class BasicChannel implements Channel{

    private String name;
    private final ArrayList<Message> messages;
    private final HashMap<String, Clearance> clearances;
    private final HashMap<String, User> members;
    private final HashMap<String, User> supervisors;

    public BasicChannel(String name){
        this.name = name;
        this.messages = new ArrayList<Message>();
        this.clearances = new HashMap<String, Clearance>();
        this.members = new HashMap<String, User>();
        this.supervisors = new HashMap<String, User>();
    }

    @Override
    public String getName() {return name;}

    @Override
    public void addMember(User user) {this.members.put(user.getName(), user);}

    @Override
    public void addMember(ArrayList<User> users) {
        for(User user: users){
            addMember(user);
        }
    }

    @Override
    public User getMember(String username) {return members.get(username);}

    @Override
    public boolean existsMember(String username) {return members.containsKey(username);}

    @Override
    public void addMessage(Message message) {this.messages.add(message);}

    @Override
    public void addMessage(ArrayList<Message> messages) {this.messages.addAll(messages);}

    @Override
    public ArrayList<Message> getMessages(){return this.messages;}

    @Override
    public Message getLastMessage(){return this.messages.get(messages.size() - 1);}

    @Override
    public void addClearance(Clearance clr) {this.clearances.put(clr.getName(), clr);}

    @Override
    public void addClearances(ArrayList<Clearance> clrs) {
        for(Clearance c: clrs){
            this.clearances.put(c.getName(), clr);
        }
    }

    @Override
    public HashMap<String, Clearance> getClearances() {return clearances;}

    @Override
    public Clearance getClearance(String clrname){return clearances.get(clrname);}

    @Override
    public boolean existsClearance(String clrname) {return clearances.containsKey(clrname);}

    @Override
    public void addSupervisor(User user) {this.supervisors.put(user.getName(), user);}

    @Override
    public void addSupervisor(ArrayList<User> users) {
        for (User user: users){
            addSupervisor(user);
        }
    }

    @Override
    public User getSupervisor(String username) {
        return this.supervisors.get(username);
    }

    @Override
    public boolean existsSupervisor(String username) {return supervisors.containsKey(username);}
}
