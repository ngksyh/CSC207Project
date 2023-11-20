package entity;

public class BasicClearanceFactory implements ClearanceFactory{
    public Clearance create(String name, Key key) {
        return new BasicClearance(name, key);
    }
}
