package Models;

import java.util.HashMap;

public class Podcast {
    private String url = "";
    private String title = "";

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
