package use_case.create_clearance;

import entity.Clearance;
import entity.Key;
import entity.User;
import use_case.login.*;

public class CreateClearanceInteractor implements CreateClearanceInputBoundary {
    final CreateClearanceChannelDataAccessInterface channelDataAccessObject;
    final CreateClearanceOutputBoundary createClearancePresenter;

    public CreateClearanceInteractor(CreateClearanceChannelDataAccessInterface channelDataAccessObject,
                                     CreateClearanceOutputBoundary createClearancePresenter) {
        this.channelDataAccessObject = channelDataAccessObject;
        this.createClearancePresenter = createClearancePresenter;
    }

    @Override
    public void execute(CreateClearanceInputData createClearanceInputData) {
        String name = createClearanceInputData.getName();
        if (!channelDataAccessObject.existsByName(name)) {
            createClearancePresenter.prepareFailView(name + ": Is already used for an existing clearance.");
        } else {
                // Creates New Clearance w name name
                Clearance clearance = null;
                // Logic Above; requires implementation of class

                CreateClearanceOutputData createClearanceOutputData = new CreateClearanceOutputData(clearance.getName(), false);
                createClearancePresenter.prepareSuccessView(createClearanceOutputData);
            }
    }

    @Override
    public void changeToSignUp() {
        createClearancePresenter.prepareSignupView();
    }
}