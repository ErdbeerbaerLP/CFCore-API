package de.erdbeerbaerlp.cfcore.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Type;
import java.util.Arrays;

public class CFFile {
    public long id = -1;
    public long gameId = -1;
    public long modId = -1;
    public boolean isAvailable = false;
    public String displayName = "undefined";
    public String fileName = "undefined";
    public CFReleaseType releaseType = CFReleaseType.RELEASE;
    public CFFileStatus fileStatus = CFFileStatus.PROCESSING;
    public CFFileHash[] hashes = new CFFileHash[0];
    public String fileDate = "undefined";
    public long fileLength = -1;
    public long downloadCount = -1;
    public String downloadUrl = "undefined";
    public String[] gameVersions = new String[0];
    public CFSortableGameVersion[] sortableGameVersions = new CFSortableGameVersion[0];
    public CFFileDependency[] dependencies = new CFFileDependency[0];
    public boolean exposeAsAlternative = false;
    public long parentProjectFileId = -1;
    public long alternateFileId = -1;
    public boolean isServerPack = false;
    public long serverPackFileId = -1;
    public long fileFingerprint = -1;
    public CFFileModule[] modules = new CFFileModule[0];


    public static class CFFileHash{
        public String value = "undefined";
        public int algo = -1;

        @Override
        public String toString() {
            return "CFFileHash{" +
                    "value='" + value + '\'' +
                    ", algo=" + algo +
                    '}';
        }
    }

    public static class CFFileModule{
        public String name = "undefined";
        public long fingerprint = -1;

        @Override
        public String toString() {
            return "CFFileModule{" +
                    "name='" + name + '\'' +
                    ", fingerprint=" + fingerprint +
                    '}';
        }
    }

    public static class CFFileDependency{
        public String modId = "undefined";
        public int relationType = -1;

        @Override
        public String toString() {
            return "CFFileDependency{" +
                    "modId='" + modId + '\'' +
                    ", relationType=" + relationType +
                    '}';
        }

        public enum CFFileRelationType{
            UNKNOWN(-1),
            EMBEDDED_LIBRARY(1),
            OPTIONAL_DEPENDENCY(2),
            REQUIRED_DEPENDENCY(3),
            TOOL(4),
            INCOMPATIBLE(5),
            INCLUDE(6);

            public int value;

            CFFileRelationType(int i) {
                this.value = i;
            }
            public static CFFileRelationType fromValue(int val) {
                for(CFFileRelationType type : CFFileRelationType.values()) {
                    if(type.value == val) {
                        return type;
                    }
                }
                return CFFileRelationType.UNKNOWN;
            }

            public static class FileRelationTypeDeserializer implements JsonDeserializer<CFFileRelationType> {
                @Override
                public CFFileRelationType deserialize(JsonElement element, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
                    int value = element.getAsInt();
                    return CFFileRelationType.fromValue(value);
                }
            }
        }
    }

    public enum CFFileStatus {
        UNKNOWN(-1),
        PROCESSING(1),
        CHANGES_REQUIRED(2),
        UNDER_REVIEW(3),
        APPROVED(4),
        REJECTED(5),
        MALWARE_DETECTED(6),
        DELETED(7),
        ARCHIVED(8),
        TESTING(9),
        RELEASED(10),
        READY_FOR_REVIEW(11),
        DEPECRATED(12),
        BAKING(13),
        AWAITING_PUBLISHING(14),
        FAILED_PUBLISHING(15);


        public int value;

        CFFileStatus(int i) {
            this.value = i;
        }
        public static CFFileStatus fromValue(int val) {
            for(CFFileStatus type : CFFileStatus.values()) {
                if(type.value == val) {
                    return type;
                }
            }
            return CFFileStatus.UNKNOWN;
        }

        public static class FileStatusDeserializer implements JsonDeserializer<CFFileStatus> {
            @Override
            public CFFileStatus deserialize(JsonElement element, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
                int value = element.getAsInt();
                return CFFileStatus.fromValue(value);
            }
        }
    }

    @Override
    public String toString() {
        return "CFFile{" +
                "id=" + id +
                ", gameId=" + gameId +
                ", modId=" + modId +
                ", isAvailable=" + isAvailable +
                ", displayName='" + displayName + '\'' +
                ", fileName='" + fileName + '\'' +
                ", releaseType=" + releaseType +
                ", fileStatus=" + fileStatus +
                ", hashes=" + Arrays.toString(hashes) +
                ", fileDate='" + fileDate + '\'' +
                ", fileLength=" + fileLength +
                ", downloadCount=" + downloadCount +
                ", downloadUrl='" + downloadUrl + '\'' +
                ", gameVersions=" + Arrays.toString(gameVersions) +
                ", sortableGameVersions=" + Arrays.toString(sortableGameVersions) +
                ", dependencies=" + Arrays.toString(dependencies) +
                ", exposeAsAlternative=" + exposeAsAlternative +
                ", parentProjectFileId=" + parentProjectFileId +
                ", alternateFileId=" + alternateFileId +
                ", isServerPack=" + isServerPack +
                ", serverPackFileId=" + serverPackFileId +
                ", fileFingerprint=" + fileFingerprint +
                ", modules=" + Arrays.toString(modules) +
                '}';
    }
}
