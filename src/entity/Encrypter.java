package entity;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;

public class Encrypter {

    ArrayList<Message> messages;
    Clearance userClearance;
    HashMap<String, Clearance> clearances;

    public Encrypter(ArrayList<Message> messages, Clearance userClearance, HashMap<String, Clearance> clearances){
        this.messages = messages;
        this.userClearance = userClearance;
        this.clearances = clearances;


    }

    public ArrayList<Message> encrypt() throws Exception {
        String apiUrl = "http://localhost:3000/bake";
        String requestBody;

        ArrayList<Message> encryptedMessages = new ArrayList<Message>();
        for (Message message: messages) {
            if (userClearance.getLevel() < message.getClearance().getLevel()) {
                requestBody = "\n" +
                        "{\"input\":\"" + message + "\",\"recipe\":[{ \"op\": \"PGP Encrypt\",\"args\": [\"" + message.getClearance().getKey().getEncrypt() + "\"] }]}";
                encryptedMessages.add(new EncryptedMessage(message.getSentBy(), message.getClearance(), makeApiCall(apiUrl, requestBody).substring(120).split("-----END PGP MESSAGE-----")[0].substring(0, 40)));
            } else {
                encryptedMessages.add(message);
            }
        }
        return encryptedMessages;
    }
    private static String makeApiCall(String apiUrl, String requestBody) throws Exception {
        // Create an HttpClient
        HttpClient httpClient = HttpClient.newHttpClient();

        // Build the request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        // Send the request and retrieve the response
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // Return the response body
        return response.body();
    }
}
