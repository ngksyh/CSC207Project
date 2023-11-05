package entity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.spi.LocaleNameProvider;

public class CommonUser implements User{

    private final int id;
    private String name;
    private String password;
    private final LocalDateTime creationTime;
    private HashMap<Integer,Channel> channels;

    /**
     * Requires: password is valid.
     * @param name
     * @param password
     */
    CommonUser(int id, String name, String password, LocalDateTime creationTime) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.creationTime = creationTime;
        this.channels = new HashMap<Integer, Channel>();
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    @Override
    public void addChannel(Channel channel) {
        this.channels.put(channel.getId(),channel);
    }

    @Override
    public HashMap<Integer, Channel> getChannel(){return channels;}
}
