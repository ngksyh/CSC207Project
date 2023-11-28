package use_case.login;

public interface LoginOutputBoundary {
    void prepareSuccessView(LoginOutputData user, String viewname);

    void prepareFailView(String error);

    void prepareSignupView();
}