package de.erdbeerbaerlp.cfcore.json;

public class CFScreenshot {
    public long id = -1;
    public long modId = -1;
    public String title = "undefined";
    public String description = "undefined";
    public String thumbnailUrl = "undefined";
    public String url = "undefined";

    @Override
    public String toString() {
        return "CFScreenshot{" +
                "id=" + id +
                ", modId=" + modId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
