package data_access;

import entity.Clearance;
import entity.Key;
import entity.KeyFactory;
import entity.User;

import java.io.*;
import java.util.*;

public class FileClearanceDataAccessObject {

    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, Clearance> clearances = new HashMap<>();

    private KeyFactory keyFactory;


    public FileClearanceDataAccessObject(String csvPath, entity.KeyFactory keyFactory) throws IOException {
        this.keyFactory = keyFactory;

        csvFile = new File(csvPath);
        headers.put("clrname", 0);
        headers.put("publickey", 1);
        headers.put("privatekey", 2);

        if (csvFile.length() == 0) {
            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                // For later: clean this up by creating a new Exception subclass and handling it in the UI.
                assert header.equals("clrname,publickey,privatekey");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String clrname = String.valueOf(col[headers.get("clrname")]);
                    String publickey = String.valueOf(col[headers.get("publickey")]);
                    String privatekey = String.valueOf(col[headers.get("privatekey")]);

                    //generates clearance
                    Key key = keyFactory.create();


                    clearances.put(new Clearance(clrname, key));
                }
            }
        }
    }


    @Override
    public void save(Clearance clearance) {
        clearances.put(clearance.getName(), clearance);
        this.save();
    }

    @Override
    public Clearance get(String clrname) {
        return clearances.get(clrname);
    }

    public Map<String, Clearance> getClearances(){return this.clearances;}



    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (Clearance clr : clearances.values()) {
                String line = String.format("%s,%s,%s",
                        "the clearance string format");
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
