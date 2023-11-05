package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface ChannelFactory {
    Channel create(int id, String name, ArrayList<Integer> members, ArrayList<Integer> moderators);
}
