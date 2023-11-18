package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface ChannelFactory {
    Channel create(String name);
}
