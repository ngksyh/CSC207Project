package entity;

import java.util.ArrayList;

public class BasicChannelFactory implements ChannelFactory{

    @Override
    public Channel create(int id, String name, ArrayList<Integer> members, ArrayList<Integer> moderators) {
        return new BasicChannel(id, name, members, moderators);
    }
}
