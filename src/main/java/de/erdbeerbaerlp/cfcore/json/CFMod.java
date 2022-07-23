package de.erdbeerbaerlp.cfcore.json;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class CFMod {
    public long id = -1;
    public int gameId = -1;
    public String name = "Undefined;";
    public String slug = "undefined";
    public CFLinks links = new CFLinks();
    public String summary = "undefined";
    public CFModStatus status = CFModStatus.NEW;
    public long downloadCount = -1;
    public boolean isFeatured = false;
    public int primaryCategoryId = -1;
    public CFCategory[] categories = new CFCategory[0];
    public int classId = -1;
    public CFAuthors[] authors = new CFAuthors[0];
    public CFLogo logo = new CFLogo();
    public CFScreenshot[] screenshots = new CFScreenshot[0];
    public long mainFileId = -1;
    public CFFile[] latestFiles = new CFFile[0];
    public CFFileIndex[] latestFilesIndexes = new CFFileIndex[0];
    public String dateCreated = "undefined";
    public String dateModified = "undefined";
    public String dateReleased = "undefined";
    public boolean allowModDistribution = false;
    public int gamePopularityRank = -1;
    public boolean isAvailable = false;
    public long thumbsUpCount = -1;


    public enum CFModStatus{
        @SerializedName("1")
        NEW(1),
        @SerializedName("2")
        CHANGES_REQUIRED(2),
        @SerializedName("3")
        UNDER_SOFT_REVIEW(3),
        @SerializedName("4")
        APPROVED(4),
        @SerializedName("5")
        REJECTED(5),
        @SerializedName("6")
        CHANGES_MADE(6),
        @SerializedName("7")
        INACTIVE(7),
        @SerializedName("8")
        ABANDONED(8),
        @SerializedName("9")
        DELETED(9),
        @SerializedName("10")
        UNDER_REVIEW(10);

        public final int value;

        CFModStatus(int i) {
            this.value = i;
        }
    }

    @Override
    public String toString() {
        return "CFMod{" +
                "id=" + id +
                ", gameId=" + gameId +
                ", name='" + name + '\'' +
                ", slug='" + slug + '\'' +
                ", links=" + links +
                ", summary='" + summary + '\'' +
                ", status=" + status +
                ", downloadCount=" + downloadCount +
                ", isFeatured=" + isFeatured +
                ", primaryCategoryId=" + primaryCategoryId +
                ", categories=" + Arrays.toString(categories) +
                ", classId=" + classId +
                ", authors=" + Arrays.toString(authors) +
                ", logo=" + logo +
                ", screenshots=" + Arrays.toString(screenshots) +
                ", mainFileId=" + mainFileId +
                ", latestFiles=" + Arrays.toString(latestFiles) +
                ", latestFilesIndexes=" + Arrays.toString(latestFilesIndexes) +
                ", dateCreated='" + dateCreated + '\'' +
                ", dateModified='" + dateModified + '\'' +
                ", dateReleased='" + dateReleased + '\'' +
                ", allowModDistribution=" + allowModDistribution +
                ", gamePopularityRank=" + gamePopularityRank +
                ", isAvailable=" + isAvailable +
                ", thumbsUpCount=" + thumbsUpCount +
                '}';
    }
}
