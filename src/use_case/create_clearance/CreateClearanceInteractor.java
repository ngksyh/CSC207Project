package use_case.create_clearance;

import entity.*;

public class CreateClearanceInteractor implements CreateClearanceInputBoundary {
    final CreateClearanceChannelDataAccessInterface channelDataAccessObject;
    final CreateClearanceOutputBoundary createClearancePresenter;

    public CreateClearanceInteractor(CreateClearanceChannelDataAccessInterface channelDataAccessObject,
                                     CreateClearanceOutputBoundary createClearancePresenter) {
        this.channelDataAccessObject = channelDataAccessObject;
        this.createClearancePresenter = createClearancePresenter;
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
    @Override
    public void execute(CreateClearanceInputData createClearanceInputData) {
        String name = createClearanceInputData.getName();
        String level = createClearanceInputData.getLevel();
        if (channelDataAccessObject.clearanceExistsByName(name)) {
            createClearancePresenter.prepareFailView(name + ": Is already used for an existing clearance.");
        }
        else if (!isNumeric(level)){createClearancePresenter.prepareFailView(name + ": Level should be non-negative integers.");}
        else {
                // Creates New Clearance w name
                RSAKeyFactory rsaKeyFactory = new RSAKeyFactory();
                Key key = rsaKeyFactory.create();
                ClearanceFactory clearanceFactory= new ClearanceFactory();
                Clearance clearance = clearanceFactory.create(createClearanceInputData.getName(), Integer.valueOf(createClearanceInputData.getLevel()), key);
                // Logic Above; requires implementation of class

                channelDataAccessObject.save(clearance);

                CreateClearanceOutputData createClearanceOutputData = new CreateClearanceOutputData(clearance.getName(), clearance.getLevel().toString(),false);
                createClearancePresenter.prepareSuccessView(createClearanceOutputData);
            }
    }

    @Override
    public void changeToSignUp() {
        createClearancePresenter.prepareSignupView();
    }
}