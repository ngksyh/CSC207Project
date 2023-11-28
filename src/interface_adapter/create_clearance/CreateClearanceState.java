package interface_adapter.create_clearance;

public class CreateClearanceState {
    private String name = "";
    private String level = "";
    private String nameError = null;
    private String levelError = null;

    public CreateClearanceState(CreateClearanceState copy) {
       name = copy.name;
       level = copy.level;
       nameError = copy.nameError;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public CreateClearanceState() {}

    public String getName() {
        return name;
    }

    public String getLevel(){return level;}

    public String getNameError() {
        return nameError;
    }
    public String getLevelError() {return levelError;}

    public void setName(String name) {
        this.name = name;
    }
    public void setLevel(String level){this.level = level;}

    public void setNameError(String nameError) {
        this.nameError = nameError;
    }

    public void setLevelError(String levelError) { this.levelError = levelError;}

}
