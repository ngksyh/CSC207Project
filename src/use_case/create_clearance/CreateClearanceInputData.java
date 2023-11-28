package use_case.create_clearance;

public class CreateClearanceInputData {

    final private String name;

    final private String level;
    public CreateClearanceInputData(String name, String level) {
        this.name = name; this.level = level;
    }

    String getName() {
        return name;
    }
    String getLevel(){return level;}

}
