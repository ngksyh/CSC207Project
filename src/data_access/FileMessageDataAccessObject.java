package data_access;

import entity.Message;
import entity.MessageFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FileMessageDataAccessObject {

    private final File csvFile;

    private final ArrayList<Message> messages = new ArrayList<>();

    private MessageFactory messageFactory;

    private FileClearanceDataAccessObject fileClearanceDataAccessObject;

    private FileUserDataAccessObject fileUserDataAccessObject;

    public FileMessageDataAccessObject(String csvPath, MessageFactory messageFactory,
                                       FileClearanceDataAccessObject fileClearanceDataAccessObject,
                                       FileUserDataAccessObject fileUserDataAccessObject) throws IOException {
        this.messageFactory = messageFactory;
        this.fileClearanceDataAccessObject = fileClearanceDataAccessObject;
        this.fileUserDataAccessObject = fileUserDataAccessObject;

        csvFile = new File(csvPath);

        if (csvFile.length() == 0) {
            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String row;
                while ((row = reader.readLine()) != null) {
                    //In assumption that everything works properly, username and clearance name should not include any comma
                    int ind1 = row.indexOf(",",0);
                    int ind2 = row.indexOf(",",ind1 + 1);

                    String sentBy = row.substring(0, ind1);
                    String clearance = row.substring(ind1+1, ind2);
                    String text = row.substring(ind2+1);
                    Message msg = messageFactory.create(fileUserDataAccessObject.get(sentBy),
                            fileClearanceDataAccessObject.get(clearance), text);

                    messages.add(msg);
                }
            }
        }
    }


    @Override
    public void save(Message message) {
        messages.add(message);
        this.save();
    }



    public ArrayList<Message> getMessages(){
        return messages;
    }



    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));

            for (Message msg : messages) {
                String line = String.format("%s,%s,%s",
                        msg.getSentBy().getName(), msg.getClearance().getName(), msg.getText());
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
        return clearances.containsKey(identifier);
    }

}
