package entity;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RSAKeyFactory implements KeyFactory {

    @Override
    public Key create(String encryptionKey, String decryptionKey) {
        return new RSAKey(encryptionKey, decryptionKey);
    }

    public Key create() {
        String[] a = new String[2];
        try {
            a = RSACreate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new RSAKey(a[0], a[1]);
    }

    public static String[] RSACreate() throws Exception {
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
        return new String[] {publicKey, privateKey};
    }

    private static String makeApiCall(String apiUrl, String requestBody) {
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
        HttpResponse<String> response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Return the response body
        return response.body();
    }
}