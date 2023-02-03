package com.linkedin.javacodechallenges.task0302;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Optional;

/**
 * @author Aurelijus Jurkus
 * @since 17-Jan-2023
 */
public class App0302 {

    public static void main(String[] args) throws IOException, InterruptedException {

        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest httpRequest = HttpRequest
                .newBuilder(URI.create("https://icanhazdadjoke.com/"))
                .header("accept", "application/json")
                .build();

        HttpResponse<String> stringHttpResponse = httpClient.send(httpRequest, BodyHandlers.ofString());

        String responseBody = stringHttpResponse.body();
        Optional<String> theJoke = parseJoke(responseBody);
        theJoke.ifPresent(System.out::println);
    }

    static Optional<String> parseJoke(String responseBody) {
        try {
            Gson gson = new Gson();
            JokeResponse jokeResponse = gson.fromJson(responseBody, JokeResponse.class);
            String theJoke = jokeResponse.joke;
            if (theJoke != null) {
                return Optional.of(jokeResponse.joke);
            }
            return Optional.empty();
        } catch (Exception e) {
            System.out.print("Must be out of jokes for now.\n");
            return Optional.empty();
        }
    }

    private record JokeResponse(String id,
                                String joke,
                                int status) {
    }
}
