package data_access;

import entity.*;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FileChannelDataAccessObject {

    private final String commonPath;

    private final File naviFile;

    private final HashMap<Integer, File[]> csvFiles = new HashMap<>();

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<Integer, Channel> channels = new HashMap<>();

    private ChannelFactory channelFactory;

    private KeyFactory keyFactory;

    private MessageFactory messageFactory;

    private FileUserDataAccessObject userDataAccessObject;

    public FileChannelDataAccessObject(String commonPath, ChannelFactory channelFactory, KeyFactory keyFactory, MessageFactory messageFactory) throws IOException {
        this.commonPath = commonPath;
        this.channelFactory = channelFactory;
        this.keyFactory = keyFactory;
        this.messageFactory = messageFactory;
        this.userDataAccessObject = null;

        naviFile = new File(commonPath.concat("/channels.csv"));

        if (naviFile.length() == 0) {
            saveNavi();
        } else {
            String[] channels;
            try (BufferedReader reader = new BufferedReader(new FileReader(naviFile))) {
                String channelText = reader.readLine();
                channels = channelText.split(",");

            }

            for (String s: channels){
                String ss =commonPath.concat(s);
                File[] f = new File[5];
                File channel = new File(ss.concat("/channel.csv"));
                f[0] = channel;
                File members = new File(ss.concat("/members.csv"));
                f[1] = members;
                File messages = new File(ss.concat("/messages.csv"));
                f[2] = messages;
                File clearances = new File(ss.concat("/clearances.csv"));
                f[3] = clearances;
                File moderators = new File(ss.concat("/moderators.csv"));
                f[4] = moderators;
            }

        }
    }

    //need methods to save these files and add entiities.


    private void saveNavi() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(naviFile));
            String str = "";
            for (Integer s: channels.keySet()){
                str.concat(str.toString()).concat(",");
            }
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
