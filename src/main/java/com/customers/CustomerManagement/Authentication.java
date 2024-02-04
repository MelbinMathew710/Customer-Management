package com.customers.CustomerManagement;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class Authentication {

    public static void authenticateUser(String loginId, String password) {
        try {
            // Prepare the request body
            String requestBody = "{\"login_id\":\"" + loginId + "\",\"password\":\"" + password + "\"}";

            // Create an HttpClient
            HttpClient httpClient = HttpClient.newHttpClient();

            // Create a POST request with the specified URI and body
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://qa.sunbasedata.com/sunbase/portal/api/assignment_auth.jsp"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            // Send the request and get the response
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // Check if the response status code is 200 OK
            if (response.statusCode() == 200) {
                // Extract the bearer token from the response header
                List<String> bearerTokens = response.headers().map().get("Authorization");
                if (bearerTokens != null && !bearerTokens.isEmpty()) {
                    String bearerToken = bearerTokens.get(0); // Assuming only one token is returned
                    System.out.println("Bearer Token: " + bearerToken);
                } else {
                    System.out.println("Bearer token not found in the response header.");
                }

                // Parse and handle the response body
                String responseBody = response.body();
                System.out.println("Response Body: " + responseBody);
            } else {
                System.out.println("Failed to authenticate user. Status code: " + response.statusCode());
            }
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }

}
