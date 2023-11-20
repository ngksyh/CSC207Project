package data_access;

import entity.*;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class FileChannelDataAccessObject {

    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Channel channel;

    private ChannelFactory channelFactory;

    private ClearanceFactory ClearanceFactory;

    private MessageFactory messageFactory;

    private FileUserDataAccessObject userDataAccessObject;

    public FileChannelDataAccessObject(String commonPath, ChannelFactory channelFactory, KeyFactory keyFactory, MessageFactory messageFactory) throws IOException {
        this.commonPath = commonPath;
        this.channelFactory = channelFactory;
        this.keyFactory = keyFactory;
        this.messageFactory = messageFactory;
        this.userDataAccessObject = null;

        LinkedHashMap<String, Integer> l0 = new LinkedHashMap<String, Integer>();
        l0.put("id", 0);
        l0.put("name", 0);
        headers.add(l0);

        LinkedHashMap<String, Integer> l1 = new LinkedHashMap<String, Integer>();
        l1.put("id", 0);
        l1.put("encrypt", 1);
        l1.put("decrypt", 1);
        headers.add(l1);

        naviFile = new File(commonPath.concat("/channel.csv"));

        if (naviFile.length() == 0) {
            saveNavi();
        } else {
            String[] chans = null;
            try (BufferedReader reader = new BufferedReader(new FileReader(naviFile))) {
                String channelText = reader.readLine();
                if (!channelText.isEmpty()) {
                    chans = channelText.split("_");
                }

            }

            if (chans != null){
                for (String s: chans) {
                    String sn = String.valueOf(s);
                    String ss = commonPath.concat("/").concat(sn);
                    File[] f = new File[5];
                    File channel = new File(ss.concat("/channel.csv"));
                    f[0] = channel;
                    File clearances = new File(ss.concat("/clearances.csv"));
                    f[1] = clearances;
                    File messages = new File(ss.concat("/messages.csv"));
                    f[2] = messages;
                    File members = new File(ss.concat("/members.csv"));
                    f[3] = members;
                    File moderators = new File(ss.concat("/moderators.csv"));
                    f[4] = moderators;
                    csvFiles.put(Integer.parseInt(s), f);

                    try (BufferedReader reader = new BufferedReader(new FileReader(channel))) {
                        String header = reader.readLine();
                        assert header.equals("id,name");
                        String[] col = reader.readLine().split(",");
                        int id = Integer.parseInt(String.valueOf(col[headers.get(0).get("id")]));
                        String name = String.valueOf(col[headers.get(0).get("name")]);

                        ArrayList<Integer> mem = readMembers(members);
                        ArrayList<Integer> mod = readMembers(moderators);

                        Channel nchannel = channelFactory.create(id, name, mem, mod);

                        nchannel.addClearance(readClearances(clearances));
                        nchannel.addMessage(readMessages(messages));

                        channels.put(id, nchannel);

                    }
                }
            }

        }
    }

    public Channel get() {return channel;}

    private ArrayList<Key> readClearances(File file) throws IOException{
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String header = reader.readLine();

            assert header.equals("id,encrypt,decrypt");

            ArrayList<Key> re = new ArrayList<>();
            String row;
            while ((row = reader.readLine()) != null) {
                String[] col = row.split(",");
                String id = String.valueOf(col[headers.get(1).get("id")]);
                String encrypt = String.valueOf(col[headers.get(1).get("encrypt")]);
                String decrypt = String.valueOf(col[headers.get(1).get("decrypt")]);

                re.add(keyFactory.create(Integer.parseInt(id), encrypt, decrypt));
            }
            return re;
        }
    }

    private ArrayList<Message> readMessages(File file) throws IOException{
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            ArrayList<Message> re = new ArrayList<>();
            String row;
            while ((row = reader.readLine()) != null) {
                int ind1 = row.indexOf(",",0);
                int ind2 = row.indexOf(",",ind1 + 1);
                int ind3 = row.indexOf(",",ind2 + 1);
                int ind4 = row.indexOf(",",ind3 + 1);
                String id = row.substring(0, ind1);
                String sentBy = row.substring(ind1+1, ind2);
                String sentTime = row.substring(ind2+1, ind3);
                LocalDateTime ldt = LocalDateTime.parse(sentTime);
                String clearance = row.substring(ind3+1, ind4);
                String text = row.substring(ind4+1);

                re.add(messageFactory.create(Integer.parseInt(id),Integer.parseInt(sentBy),ldt,Integer.parseInt(clearance),text));
            }
            return re;
        }
    }

    private ArrayList<Integer> readMembers(File file) throws IOException{
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String me = reader.readLine();
            ArrayList<Integer> mem = new ArrayList<>();
            String[] mems = me.split("_");
            for (String s: mems){mem.add(Integer.parseInt(s));}

            return mem;
        }
    }

    public void addUserObject(FileClearanceDataAccessObject fileUserDataAccessObject){this.userDataAccessObject = fileUserDataAccessObject;}

    private String integerCollectionToString(Collection<Integer> coll){
        String str = "_";
        for (Integer i: coll){
            str.concat(i.toString()).concat("_");
        }
        return str;

    }

    private void saveNavi() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(naviFile));
            writer.write(integerCollectionToString(channels.keySet()));
            writer.newLine();

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveMembers(File file, ArrayList<Integer> members) {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(integerCollectionToString(members));
            writer.newLine();

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveMessages(File file, ArrayList<Message> messages){
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            for (Message message: messages){
                String line = String.format("%s,%s,%s,%s,%s",
                        message.getId(), message.getSentBy(), message.getSentTime(), message.getClearance(), message.getText());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveClearances(File file, HashMap<Integer, Key> clearances){
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(String.join(",", headers.get(1).keySet()));
            writer.newLine();

            for (Key key: clearances.values()){
                String line = String.format("%s,%s,%s",
                        key.getId(), key.getEncrypt(), key.getDecrypt());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //given valid id
    private void saveChannel(int id){
        File[] files = csvFiles.get(id);
        Channel chan = channels.get(id);
        saveClearances(files[1],chan.getClearances());
        saveMessages(files[2],chan.getMessages());
        saveMembers(files[3],chan.getMembers());
        saveMembers(files[4],chan.getModerators());
    }

    //createNewChannel
    private void saveChannel(int id, String name){
        Channel chan = channelFactory.create(id,name,new ArrayList<Integer>(),new ArrayList<Integer>());
        channels.put(id, chan);
        saveNavi();
        String path = commonPath.concat("/").concat(Integer.toString(id));

        File[] f = new File[5];
        File channel = new File(path.concat("/channel.csv"));
        f[0] = channel;
        File clearances = new File(path.concat("/clearances.csv"));
        f[1] = clearances;
        File messages = new File(path.concat("/messages.csv"));
        f[2] = messages;
        File members = new File(path.concat("/members.csv"));
        f[3] = members;
        File moderators = new File(path.concat("/moderators.csv"));
        f[4] = moderators;
        csvFiles.put(id, f);

        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(f[0]));
            writer.write(String.join(",", headers.get(0).keySet()));
            writer.newLine();
            String line = String.format("%s,%s",
                    id, name);
            writer.write(line);
            writer.newLine();
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        saveChannel(id);
    }


}
