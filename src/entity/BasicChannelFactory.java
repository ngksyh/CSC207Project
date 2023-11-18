package entity;

import java.util.ArrayList;

public class BasicChannelFactory implements ChannelFactory{

    @Override
    public Channel create(String name) {
        return new BasicChannel(name);
    }
}
