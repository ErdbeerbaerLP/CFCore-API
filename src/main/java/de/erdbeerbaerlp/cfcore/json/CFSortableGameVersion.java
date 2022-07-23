package de.erdbeerbaerlp.cfcore.json;

public class CFSortableGameVersion {
    public String gameVersionName = "undefined";
    public String gameVersionPadded = "undefined";
    public String gameVersion = "undefined";
    public String gameVersionReleaseDate = "undefined";
    public long gameVersionTypeId = -1;

    @Override
    public String toString() {
        return "CFSortableGameVersion{" +
                "gameVersionName='" + gameVersionName + '\'' +
                ", gameVersionPadded='" + gameVersionPadded + '\'' +
                ", gameVersion='" + gameVersion + '\'' +
                ", gameVersionReleaseDate='" + gameVersionReleaseDate + '\'' +
                ", gameVersionTypeId=" + gameVersionTypeId +
                '}';
    }
}