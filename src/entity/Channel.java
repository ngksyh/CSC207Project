package entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public interface Channel {


    String getName();

    void addMember(User user);

    void addMember(ArrayList<User> users);

    User getMember(String username);

    boolean existsMember(String username);

    void addMessage(Message message);

    void addMessage(ArrayList<Message> messages);

    ArrayList<Message> getMessages();

    Message getLastMessage();

    void addClearance(Clearance clr);

    void addClearances(ArrayList<Clearance> clrs);

    HashMap<String, Clearance> getClearances();

    Clearance getClearance(String clrname);

    boolean existsClearance(String clrname);

    void addSupervisor(User user);

    void addSupervisor(ArrayList<User> users);

    User getSupervisor(String username);

    boolean existsSupervisor(String username);






}
