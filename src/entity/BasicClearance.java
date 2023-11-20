package entity;

public class BasicClearance implements Clearance{

    private String name;
    private Key key;

    BasicClearance(String name, Key key) {
        this.name = name;
        this.key = key;
    }
    @Override
    public String getName() {
        return this.name;
    }
    public Key getKey() {
        return this.key;
    }
}
