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

        if (csvFile.length() == 0) {
            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String row = reader.readLine();
                String[] col = row.split(",");

                String channelname = String.valueOf(col[0]);
                String memberlisttext = String.valueOf(col[1]);
                String supervisorlisttext = String.valueOf(col[2]);

                channel = channelFactory.create(channelname);
                channel.addClearances(clearanceDataAccessObject.getClearances().values());
                channel.addMessages(messageDataAccessObject.getMessages());

                String[] members = memberlisttext.split(" ");
                String[] supervisors = supervisorlisttext.split(" ");

                for (String s: members){channel.addMember(userDataAccessObject.get(s));}
                for (String s: supervisors){channel.addSupervisor(userDataAccessObject.get(s));}


            }
        }


    }

    public Channel getChannel() {return channel;}


    //saveclearance
    //save
    ...


    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));

            //

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
