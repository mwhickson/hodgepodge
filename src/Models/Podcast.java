package Models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Podcast {
    // REF: https://support.google.com/podcast-publishers/answer/9889544?hl=en#podcast_tags
    private String url = "";
    private String title = "";
    private String owner = "";
    private String website = "";
    private String description = "";
    private String author = "";
    private String category = "";
    private String image = "";
    private LocalDateTime updated = LocalDateTime.MIN;
    private ArrayList<Episode> episodes = new ArrayList<Episode>();

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public ArrayList<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(ArrayList<Episode> episodes) {
        this.episodes = episodes;
    }

    public Podcast() {
        this("", "");
    }

    public Podcast(String url) {
        this(url, "");
    }

    public Podcast(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public Podcast(HashMap data) {
        this();
        PopulateFromHashMap(data);
    }

    public String toString() {
        return String.format("%s (%s)", this.title, this.url);
    }

    public String toJSON() {
        // FIX: less quick 'n dirty with the JSON escaping...
        return String.format(
            "{\"title\": \"%s\", \"url\": \"%s\"}", 
            this.title.replace("\"", "\\\""), 
            this.url.replace("\"", "\\\"")
        );
    }

    public void PopulateFromHashMap(HashMap details) {
        String url = "";
        String title = "";

        if (details != null) {
            if (details.containsKey("title")) {
                title = details.get("title").toString();
            }

            if (details.containsKey("url")) {
                url = details.get("url").toString();
            }

            // OpmlConverter will have a different world view... let's support that...

            if (details.containsKey("text")) {
                title = details.get("text").toString();
            }

            if (details.containsKey("xmlUrl")) {
                url = details.get("xmlUrl").toString();
            }
        }

        this.url = url;
        this.title = title;
    }
}
