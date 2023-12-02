package entity;

public class Clearance {
    private final String name;
    private Key key;

    private Integer level;





    /**
     * Requires: password is valid.
     * @param name
     * @param level
     */
    public Clearance(String name, Integer level, Key key) {
        this.name = name;
        this.level = level;
        this.key = key;
    }


    public String getName() {
        return name;
    }

    public Integer getLevel() {return level;}
    public Boolean canRead(Clearance c1) {return level >= c1.level;}

    public Key getKey() {return key;}



}
