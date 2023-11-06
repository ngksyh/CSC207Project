package interface_adapter;

public class ChannelCreationFailed extends RuntimeException {
    public ChannelCreationFailed(String error) {
        super(error);
    }
}
