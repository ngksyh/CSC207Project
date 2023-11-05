package entity;

import java.util.ArrayList;
import java.util.HashMap;

public interface Channel {

    int getId();

    String getName();

    void addMember(User user);

    HashMap<Integer, User> getMembers();

    void addMessage(Message message);

    ArrayList<Message> getMessages();

    void addClearance(Key key);

    HashMap<Integer, Key> getClearances();

    void addModerator(User user);

    HashMap<Integer, User> getModerators();





}
