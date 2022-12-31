package Models;

import java.time.LocalDateTime;
import java.util.HashMap;

public class Episode {
    private String url = "";
    private String title = "";
    private String description = "";
    private String author = "";
    private String image = "";
    private LocalDateTime published = LocalDateTime.MIN;
    
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDateTime getPublished() {
        return published;
    }

    public void setPublished(LocalDateTime published) {
        this.published = published;
    }

    public Episode() {
        this("", "");
    }

    public Episode(String url) {
        this(url, "");
    }

    public Episode(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public Episode(HashMap data) {
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
