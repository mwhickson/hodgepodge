package Models;

import java.util.HashMap;

public class Subscription {
    private Podcast podcast = new Podcast();
    
    public Podcast getPodcast() {
        return podcast;
    }

    public void setPodcast(Podcast podcast) {
        this.podcast = podcast;
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
        return String.format("%s (%s)", this.podcast.getTitle(), this.podcast.getUrl());
    }

    public String toJSON() {
        // FIX: less quick 'n dirty with the JSON escaping...
        return String.format(
            "{\"title\": \"%s\", \"url\": \"%s\"}", 
            this.podcast.getTitle().replace("\"", "\\\""), 
            this.podcast.getUrl().replace("\"", "\\\"")
        );
    }

    public void PopulateFromHashMap(HashMap details) {
        this.podcast.PopulateFromHashMap(details);
    }
}
