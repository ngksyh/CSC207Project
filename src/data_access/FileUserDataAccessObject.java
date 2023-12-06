package data_access;

import entity.Clearance;
import entity.User;
import entity.UserFactory;
import use_case.assign_clearance.AssignClearanceUserDataAccessInterface;
import use_case.assign_supervisor.AssignSupervisorUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import java.io.*;
import java.util.*;

public class FileUserDataAccessObject implements LoginUserDataAccessInterface, AssignClearanceUserDataAccessInterface,
        AssignSupervisorUserDataAccessInterface {

    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, User> accounts = new HashMap<>();

    private UserFactory userFactory;

    private FileClearanceDataAccessObject fileClearanceDataAccessObject;


    public FileUserDataAccessObject(String csvPath, UserFactory userFactory,
                                    FileClearanceDataAccessObject fileClearanceDataAccessObject) throws IOException {
        this.userFactory = userFactory;
        this.fileClearanceDataAccessObject = fileClearanceDataAccessObject;

        csvFile = new File(csvPath);
        headers.put("username", 0);
        headers.put("password", 1);
        headers.put("isadmin", 2);
        headers.put("clearance", 3);

        if (csvFile.length() == 0) {
            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();


                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String username = String.valueOf(col[headers.get("username")]);
                    String password = String.valueOf(col[headers.get("password")]);
                    String isAdmin = String.valueOf(col[headers.get("isadmin")]);
                    String clearance = String.valueOf(col[headers.get("clearance")]);

                    Boolean isadmin = false;

                    if (isAdmin.equals("true")){isadmin = true;}



                    User user = userFactory.create(username, password,
                            isadmin,
                            fileClearanceDataAccessObject.get(clearance));

                    accounts.put(username, user);
                }
            }
        }
    }

    @Override
    public void save(User user) {
        accounts.put(user.getName(), user);
        this.save();
    }

    @Override
    public User get(String username) {
        return accounts.get(username);
    }

    public String stringJoinedBySpace(Set<String> s) {
        //to be implemented
        return String.join(" ", s);

    }

    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (User user : accounts.values()) {

                String line = String.format("%s,%s,%s,%s",
                        user.getName(), user.getPassword(),
                        user.getIsadmin().toString(),
                        user.getClearance().getName());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }




    /**
     * Return whether a user exists with username identifier.
     * @param identifier the username to check.
     * @return whether a user exists with username identifier
     */
    @Override
    public boolean existsByName(String identifier) {

        return accounts.containsKey(identifier);
    }

    @Override
    public Map<String, User> getUsers() {
        return this.accounts;
    }

    @Override
    public void updateIsAdmin(String userName) {
        this.accounts.get(userName).setIsAdmin(true);
        save();
    }

    @Override
    public boolean userHasClearanceByName(String user, String clearance) {
        return Objects.equals(get(user).getClearance().getName(), clearance);
    }

    @Override
    public void update(String userName, Clearance clearance) {
       accounts.get(userName).setClearance(clearance);
       save(); // Not the most efficient, but no point in writing another method for this.
    }
}
