package use_case.create_clearance;

public class CreateClearanceOutputData {

    private final String name;
    private final String level;
    private boolean useCaseFailed;

    public CreateClearanceOutputData(String name, String level, boolean useCaseFailed) {
        this.name = name;
        this.level = level;
        this.useCaseFailed = useCaseFailed;
    }

    public String getName() {
        return name;
    }
    public String getLevel(){return level;}

}
