package Models;

import java.time.LocalDateTime;
import java.util.HashMap;

public class Subscription {
    public static int DEFAULT_POLLING_MINUTES = 60;

    private Podcast podcast = new Podcast();
    private int pollingMinutes = Subscription.DEFAULT_POLLING_MINUTES;
    private LocalDateTime subscribed = LocalDateTime.MIN;
    private LocalDateTime lastRetrieved = LocalDateTime.MIN;
    private String lastRetrievedResponse = "";
    
    public Podcast getPodcast() {
        return podcast;
    }

    public void setPodcast(Podcast podcast) {
        this.podcast = podcast;
    }

    public int getPollingMinutes() {
        return pollingMinutes;
    }

    public void setPollingMinutes(int pollingMinutes) {
        this.pollingMinutes = pollingMinutes;
    }

    public LocalDateTime getSubscribed() {
        return subscribed;
    }

    public void setSubscribed(LocalDateTime subscribed) {
        this.subscribed = subscribed;
    }

    public LocalDateTime getLastRetrieved() {
        return lastRetrieved;
    }

    public void setLastRetrieved(LocalDateTime lastRetrieved) {
        this.lastRetrieved = lastRetrieved;
    }

    public String getLastRetrievedResponse() {
        return lastRetrievedResponse;
    }

    public void setLastRetrievedResponse(String lastRetrievedResponse) {
        this.lastRetrievedResponse = lastRetrievedResponse;
    }

    public Subscription() {
        this("", "");
    }

    public Subscription(String url) {
        this(url, "");
    }

    public Subscription(String url, String title) {
        this.podcast.setTitle(title);
        this.podcast.setUrl(url);
    }

    public Subscription(HashMap data) {
        this();
        this.podcast.PopulateFromHashMap(data);
    }

    public String toString() {
        StringBuilder format = new StringBuilder();

        format.append("TITLE: %s%n");
        format.append("URL: %s%n");
        format.append("POLL: %d%n");
        format.append("SUBSCRIBED: %s%n");
        format.append("RETRIEVED: %s%n");
        format.append("STATUS: %s%n");

        return String.format(
            format.toString(),
            this.podcast.getTitle(), 
            this.podcast.getUrl(),
            this.pollingMinutes,
            this.subscribed.toString(),
            this.lastRetrieved.toString(),
            this.lastRetrievedResponse
        );
    }

    public String toJSON() {
        StringBuilder jsonFormat = new StringBuilder();

        jsonFormat.append("{");
        jsonFormat.append("\"poll_minutes\": %d,");
        jsonFormat.append("\"subscribed\": \"%s\",");
        jsonFormat.append("\"last_retrieved\": \"%s\",");
        jsonFormat.append("\"last_retrieved_response\": \"%s\",");
        jsonFormat.append("\"podcast\": {");
        jsonFormat.append("\"title\": \"%s\", ");
        jsonFormat.append("\"url\": \"%s\"");
        jsonFormat.append("}");
        jsonFormat.append("}");

        // FIX: less quick 'n dirty with the JSON escaping...
        return String.format(            
            jsonFormat.toString(), 
            this.pollingMinutes,
            this.subscribed.toString(),
            this.lastRetrieved.toString(),
            this.lastRetrievedResponse,
            this.podcast.getTitle().replace("\"", "\\\""), 
            this.podcast.getUrl().replace("\"", "\\\"")
        );
    }

    public void PopulateFromHashMap(HashMap details) {
        this.podcast.PopulateFromHashMap(details);
    }
}
