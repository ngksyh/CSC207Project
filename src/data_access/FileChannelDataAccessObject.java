package data_access;

import entity.*;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class FileChannelDataAccessObject {

    private final File csvFile;

    private final Channel channel;

    private ChannelFactory channelFactory;

    private FileClearanceDataAccessObject clearanceDataAccessObject;
    private FileUserDataAccessObject userDataAccessObject;
    private FileMessageDataAccessObject messageDataAccessObject;


    public FileChannelDataAccessObject(String csvPath, ChannelFactory channelFactory,
                                       FileClearanceDataAccessObject clearanceDataAccessObject,
                                       FileUserDataAccessObject userDataAccessObject,
                                       FileMessageDataAccessObject messageDataAccessObject) throws IOException {
        this.channelFactory = channelFactory;
        this.clearanceDataAccessObject = clearanceDataAccessObject;
        this.userDataAccessObject = userDataAccessObject;
        this.messageDataAccessObject = messageDataAccessObject;

        csvFile = new File(csvPath);

        channel = channelFactory.create("BasicChannel");

        if (csvFile.length() == 0) {
            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String row = reader.readLine();
                if (row != null) {
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
                }else{
                    save();
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

    public void addClearance(Clearance clearance){
        channel.addClearance(clearance);
        clearanceDataAccessObject.save(clearance);
    }

    public void addMember(User user){
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
