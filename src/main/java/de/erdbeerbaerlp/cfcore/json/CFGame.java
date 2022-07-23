package de.erdbeerbaerlp.cfcore.json;

public class CFGame {
    public long id = -1;
    public String name = "undefined";
    public String slug = "undefined";
    public String dateModified = "undefined";
    public int status = -1;
    public int apiStatus = -1;

    @Override
    public String toString() {
        return "CFGame{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", slug='" + slug + '\'' +
                ", dateModified='" + dateModified + '\'' +
                ", status=" + status +
                ", apiStatus=" + apiStatus +
                '}';
    }
}
