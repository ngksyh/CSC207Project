package use_case.create_clearance;

public class CreateClearanceOutputData {

    private final String name;
    private boolean useCaseFailed;

    public CreateClearanceOutputData(String username, boolean useCaseFailed) {
        this.name = username;
        this.useCaseFailed = useCaseFailed;
    }

    public String getName() {
        return name;
    }

}
