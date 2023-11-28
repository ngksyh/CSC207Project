package entity;

import com.sun.source.util.SourcePositions;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TestApiCall {
    public static void main(String[] args) throws Exception {
        // Set the API endpoint URL
        String apiUrl = "http://localhost:3000/bake";

        // Prepare the request body
        String requestBody = "\n" +
                "{\"input\":\" \",\"recipe\":[{ \"op\": \"Generate PGP Key Pair\",\"args\": [\"RSA-1024\", \"\", \"\", \"\"] }]}";

        // Make the API call
        String response = makeApiCall(apiUrl, requestBody);

        String response2 = response.substring(10);

        String privateKey = response2.split("(?<=-----END PGP PRIVATE KEY BLOCK-----)")[0];
        String publicKey = response2.split("(?<=-----END PGP PRIVATE KEY BLOCK-----)")[1].substring(4).split("(?<=-----END PGP PUBLIC KEY BLOCK-----)")[0];
        // Print the API response
        System.out.println("Response is " + response2);
        System.out.println("Privkey is " + privateKey);
        System.out.println("Pubkey is " + publicKey);
        String msg = "messagehere";
        requestBody = "\n" +
                "{\"input\":\"" + msg + "\",\"recipe\":[{ \"op\": \"PGP Encrypt\",\"args\": [\"" + publicKey + "\"] }]}";

        String encryptResponse = makeApiCall(apiUrl, requestBody);
        String encryptedMessage = encryptResponse.substring(10).split("(?<=-----END PGP MESSAGE-----)")[0];
        System.out.println("Encrypt response: " + encryptResponse);
        System.out.println("Encrypted message: " + encryptedMessage);


        requestBody = "\n" +
                "{\"input\":\"" + encryptedMessage + "\",\"recipe\":[{ \"op\": \"PGP Decrypt\",\"args\": [\"" + privateKey + "\", \"\"] }]}";

        String decryptResponse = makeApiCall(apiUrl, requestBody);
        String decryptedMessage = decryptResponse.substring(10).split("\",\"type\":\"string\"}")[0];
        System.out.println("Decrypt response: " + decryptResponse);
        System.out.println("Decryped message: " + decryptedMessage);
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

