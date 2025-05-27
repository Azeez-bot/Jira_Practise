package Section10;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Sec10Example {
    public static void main(String[] args) throws Exception {
        String json = "{ \"user\": { \"id\": 101, \"name\": \"John Doe\", \"email\": \"john@example.com\", \"address\": { \"city\": \"New York\", \"zipcode\": \"10001\" }, \"roles\": [\"admin\", \"editor\"] }, \"status\": \"active\" }";

        ObjectMapper objectMapper = new ObjectMapper();
        getUser response = objectMapper.readValue(json, getUser.class);

        // Access nested fields
        System.out.println("User Name: " + response.getUser().getName());
        System.out.println("City: " + response.getUser().getAddress().getCity());
        System.out.println("First Role: " + response.getUser().getRoles().get(0));
        System.out.println("Status: " + response.getStatus());

    }
}

