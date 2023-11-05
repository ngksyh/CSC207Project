package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.spi.LocaleNameProvider;

public class CommonUser implements User{

    private final String name;
    private String password;
    private final LocalDateTime creationTime;
    private final ArrayList<Integer> channels;

    /**
     * Requires: password is valid.
     * @param name
     * @param password
     */
    CommonUser(String name, String password, LocalDateTime creationTime, ArrayList<Integer> channels) {
        this.name = name;
        this.password = password;
        this.creationTime = creationTime;
        this.channels = channels;
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
    public void addChannel(Integer channel) {
        this.channels.add(channel);
    }

    @Override
    public ArrayList<Integer> getChannel(){return channels;}
}
