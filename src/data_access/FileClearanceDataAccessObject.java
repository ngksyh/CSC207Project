package data_access;

import entity.Clearance;
import entity.Key;
import entity.KeyFactory;
import entity.User;
import use_case.assign_clearance.AssignClearanceClearanceDataAccessInterface;

import java.io.*;
import java.util.*;

public class FileClearanceDataAccessObject implements AssignClearanceClearanceDataAccessInterface {

    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, Clearance> clearances = new HashMap<>();

    private KeyFactory keyFactory;


    public FileClearanceDataAccessObject(String csvPath, entity.KeyFactory keyFactory) throws IOException {
        this.keyFactory = keyFactory;

        csvFile = new File(csvPath);
        headers.put("clrname", 0);
        headers.put("encryptkey", 1);
        headers.put("decryptkey", 2);
        headers.put("level", 3);

        if (csvFile.length() == 0) {
            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                // For later: clean this up by creating a new Exception subclass and handling it in the UI.
                assert header.equals("clrname,encryptkey,decryptkey");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String clrname = String.valueOf(col[headers.get("clrname")]);
                    String encryptkey = String.valueOf(col[headers.get("encryptkey")]);
                    String decryptkey = String.valueOf(col[headers.get("decryptkey")]);
                    String level = String.valueOf(col[headers.get("level")]);

                    //generates clearance
                    Key key = keyFactory.create(encryptkey, decryptkey);


                    clearances.put(clrname, new Clearance(clrname, Integer.parseInt(level), key));
                }
            }
        }
    }


    public void save(Clearance clearance) {
        clearances.put(clearance.getName(), clearance);
        this.save();
    }

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
                String line = String.format("%s,%s,%s,%s",
                        clr.getName(),clr.getKey().getEncrypt(),clr.getKey().getDecrypt(), clr.getLevel().toString());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }




    public boolean existsByName(String identifier) {
        return clearances.containsKey(identifier);
    }

}
