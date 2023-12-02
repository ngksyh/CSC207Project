package data_access;

import entity.*;
import use_case.create_clearance.CreateClearanceChannelDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import java.io.*;

public class FileChannelDataAccessObject implements SignupUserDataAccessInterface, CreateClearanceChannelDataAccessInterface {

    private final File csvFile;

    private final Channel channel;

    private ChannelFactory channelFactory;

    private FileClearanceDataAccessObject clearanceDataAccessObject;
    private FileUserDataAccessObject userDataAccessObject;
    private FileMessageDataAccessObject messageDataAccessObject;

    private UserFactory userFactory;
    private ClearanceFactory clearanceFactory;

    private  MessageFactory messageFactory;


    public FileChannelDataAccessObject(String csvPath, ChannelFactory channelFactory,
                                       FileClearanceDataAccessObject clearanceDataAccessObject,
                                       FileUserDataAccessObject userDataAccessObject,
                                       FileMessageDataAccessObject messageDataAccessObject,
                                       UserFactory userFactory,
                                       ClearanceFactory clearanceFactory,
                                       MessageFactory messageFactory) throws IOException {
        this.channelFactory = channelFactory;
        this.clearanceDataAccessObject = clearanceDataAccessObject;
        this.userDataAccessObject = userDataAccessObject;
        this.messageDataAccessObject = messageDataAccessObject;
        this.userFactory = userFactory;
        this.clearanceFactory = clearanceFactory;
        this.messageFactory = messageFactory;

        csvFile = new File(csvPath);

        channel = channelFactory.create("BasicChannel");

        if (csvFile.length() == 0) {
            Key key = new RSAKey("2", "2");
            Clearance basicClearance = clearanceFactory.create("BasicClearance", 0, key);
            User adminUser = userFactory.create("Admin", "1111", true, basicClearance);
            Message message = messageFactory.create(adminUser, basicClearance, "Hello, welcome to the channel!");

            clearanceDataAccessObject.save(basicClearance);
            userDataAccessObject.save(adminUser);
            messageDataAccessObject.save(message);

            channel.addClearance(basicClearance);
            channel.addMember(adminUser);
            channel.addSupervisor(adminUser);
            channel.addMessage(message);

            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String row = reader.readLine();
                String[] col = row.split(",");

                String channelname = String.valueOf(col[0]);
                String memberlisttext = String.valueOf(col[1]);
                String supervisorlisttext = String.valueOf(col[2]);

                channel.setName(channelname);
                channel.addClearances(clearanceDataAccessObject.getClearances().values());
                channel.addMessages(messageDataAccessObject.getMessages());

                String[] members = memberlisttext.split(" ");
                String[] supervisors = supervisorlisttext.split(" ");

                for (String s : members) {
                    channel.addMember(userDataAccessObject.get(s));
                }
                for (String s : supervisors) {
                    channel.addSupervisor(userDataAccessObject.get(s));
                }

            }
        }


    }

    public Channel getChannel() {return channel;}


    public void setName(String name){
        channel.setName(name);
        save();
    }

    public void addMessage(Message message){
        channel.addMessage(message);
        messageDataAccessObject.save(message);
    }

    @Override
    public boolean clearanceExistsByName(String identifier){
        return clearanceDataAccessObject.existsByName(identifier);
    }

    @Override
    public void save(Clearance clearance){
        channel.addClearance(clearance);
        clearanceDataAccessObject.save(clearance);
    }

    @Override
    public Clearance get(String name){
        return clearanceDataAccessObject.get(name);
    }


    public boolean userExistsByName(String identifier){
        return userDataAccessObject.existsByName(identifier);
    }


    public void save(User user){
        userDataAccessObject.save(user);
        addMember(user);
    }

    private void addMember(User user){
        channel.addMember(user);
        save();
    }

    public void addSupervisor(User user){
        channel.addSupervisor(user);
        save();
    }



    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            String line = String.format("%s,%s,%s",
                    channel.getName(),
                    userDataAccessObject.stringJoinedBySpace(channel.getMembers().keySet()),
                    userDataAccessObject.stringJoinedBySpace(channel.getSupervisors().keySet())
            );

            //
            writer.write(line);
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
