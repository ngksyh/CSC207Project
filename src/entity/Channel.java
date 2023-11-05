package entity;

import java.util.ArrayList;
import java.util.HashMap;

public interface Channel {

    int getId();

    String getName();

    void addMember(Integer user);

    ArrayList<Integer> getMembers();

    void addMessage(Message message);

    void addMessage(ArrayList<Message> messages);

    ArrayList<Message> getMessages();

    void addClearance(Key key);

    void addClearance(ArrayList<Key> keys);

    HashMap<Integer, Key> getClearances();

    void addModerator(Integer user);

    ArrayList<Integer> getModerators();





}
