package de.erdbeerbaerlp.cfcore.json;

public class CFAuthors {
    public int id = -1;
    public String name = "undefined";
    public String url = "undefined";

    @Override
    public String toString() {
        return "CFAuthors{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
