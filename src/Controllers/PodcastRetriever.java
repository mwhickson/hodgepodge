package Controllers;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class PodcastRetriever {

    public static void GetPodcastFeedByUrl(String url) {
        try {
            HttpRequest request = (HttpRequest) HttpRequest
                .newBuilder(new URI(url))
                .version(HttpClient.Version.HTTP_2)
                .timeout(Duration.of(10, ChronoUnit.SECONDS))
                .GET()
                .build();

            try {
                HttpResponse<String> response = HttpClient
                    .newBuilder()
                    .followRedirects(HttpClient.Redirect.ALWAYS)
                    .build()
                    .send(request, BodyHandlers.ofString());

                if (response.statusCode() == 200)
                {
                    String body = response.body();
                    System.out.print(body);
                }
            } catch (InterruptedException ex) {
                System.err.format("InterruptedException: %s%n", ex);
            } catch (IOException ex) {
                System.err.format("IOException: %s%n", ex);
            }

        } catch (URISyntaxException ex) {
            System.err.format("URISyntaxException: %s%n", ex);
        }
    }
    
}
