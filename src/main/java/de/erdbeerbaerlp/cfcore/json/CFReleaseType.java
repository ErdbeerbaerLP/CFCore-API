package de.erdbeerbaerlp.cfcore.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Type;

public enum CFReleaseType {
    UNKNOWN(-1),
    ALPHA(3),
    BETA(2),
    RELEASE(1);

    public final int value;

    CFReleaseType(int i) {
        this.value = i;
    }
    public static CFReleaseType fromValue(int val) {
        for(CFReleaseType type : CFReleaseType.values()) {
            if(type.value == val) {
                return type;
            }
        }
        return UNKNOWN;
    }

    public static class ReleaseTypeDeserializer implements JsonDeserializer<CFReleaseType> {
        @Override
        public CFReleaseType deserialize(JsonElement element, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
            int value = element.getAsInt();
            return CFReleaseType.fromValue(value);
        }
    }

}
