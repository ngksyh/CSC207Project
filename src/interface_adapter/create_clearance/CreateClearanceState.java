package interface_adapter.create_clearance;

public class CreateClearanceState {
    private String name = "";
    private String nameError = null;

    public CreateClearanceState(CreateClearanceState copy) {
       name = copy.name;
       nameError = copy.nameError;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public CreateClearanceState() {}

    public String getName() {
        return name;
    }

    public String getNameError() {
        return nameError;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNameError(String nameError) {
        this.nameError = nameError;
    }
}
