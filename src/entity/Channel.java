package entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public interface Channel {


    String getName();

    void addMember(User user);

    void addMember(Collection<User> users);

    User getMember(String username);

    boolean existsMember(String username);

    void addMessage(Message message);

    void addMessages(ArrayList<Message> messages);

    ArrayList<Message> getMessages();

    Message getLastMessage();

    ArrayList<Message> getUpToLast(int num);

    void addClearance(Clearance clr);

    void addClearances(Collection<Clearance> clrs);

    HashMap<String, Clearance> getClearances();

    Clearance getClearance(String clrname);

    boolean existsClearance(String clrname);

    void addSupervisor(User user);

    void addSupervisor(Collection<User> users);

    User getSupervisor(String username);

    boolean existsSupervisor(String username);






}
