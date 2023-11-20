package data_access;

import entity.User;
import entity.UserFactory;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FileUserDataAccessObject implements SignupUserDataAccessInterface, LoginUserDataAccessInterface{

    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, User> accounts = new HashMap<>();

    private UserFactory userFactory;

    private FileClearanceDataAccessObject fileClearanceDataAccessObject;


    public FileUserDataAccessObject(String csvPath, UserFactory userFactory, FileClearanceDataAccessObject fileClearanceDataAccessObject) throws IOException {
        this.userFactory = userFactory;
        this.fileClearanceDataAccessObject = fileClearanceDataAccessObject;

        csvFile = new File(csvPath);
        headers.put("username", 0);
        headers.put("password", 1);
        headers.put("isAdmin", 2);
        headers.put("clearances", 3);

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
                    String isAdmin = String.valueOf(col[headers.get("isAdmin")]);
                    String clearanceText = String.valueOf(col[headers.get("clearances")]);

                    String[] clearances = clearanceText.split(" ");
                    for (String c: clearances){chs.add(Integer.parseInt(s));}

                    User user = userFactory.create(username, password, );

                    accounts.put(username, user);
                }
            }
        }
    }

    public void addChannelObject(FileChannelDataAccessObject fileChannelDataAccessObject){this.channelDataAccessObject = fileChannelDataAccessObject;}

    @Override
    public void save(User user) {
        accounts.put(user.getName(), user);
        this.save();
    }

    @Override
    public User get(String username) {
        return accounts.get(username);
    }


    private String integerCollectionToString(Collection<Integer> coll){
        String str = "_";
        for (Integer i: coll){
            str.concat(i.toString()).concat("_");
        }
        return str;

    }

    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (User user : accounts.values()) {
                String line = String.format("%s,%s,%s,%s",
                        user.getName(), user.getPassword(), user.getCreationTime(), integerCollectionToString(user.getChannel()));
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

}
