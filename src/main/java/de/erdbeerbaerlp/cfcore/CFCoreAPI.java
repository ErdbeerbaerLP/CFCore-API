package de.erdbeerbaerlp.cfcore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import de.erdbeerbaerlp.cfcore.exceptions.InvalidAPIKeyException;
import de.erdbeerbaerlp.cfcore.json.*;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class CFCoreAPI {
    private static final String baseURL = "https://api.curseforge.com/v1/";

    private static final Gson gson;
    static {
        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(CFReleaseType.class,new CFReleaseType.ReleaseTypeDeserializer());
        gsonBuilder.registerTypeAdapter(CFFile.CFFileStatus.class,new CFFile.CFFileStatus.FileStatusDeserializer());
        gsonBuilder.registerTypeAdapter(CFFile.CFFileDependency.CFFileRelationType.class,new CFFile.CFFileDependency.CFFileRelationType.FileRelationTypeDeserializer());
        gson = gsonBuilder.create();
    }
    /**
     * CFCore API Key
     */
    private static String apiKey;

    /**
     * Sets the API Key to use to access https://api.curseforge.com<br>
     * Get one from https://console.curseforge.com/?#/api-keys
     * @param apiKey API Key to use
     */
    public static void setApiKey(final String apiKey) {
        CFCoreAPI.apiKey = apiKey;
    }


    /**
     * Fetches a mod from CurseForge
     * @param modId Mod ID to fetch
     * @return {@link CFMod} instance, or null if not found
     * @throws InvalidAPIKeyException if API Key is invalid or not set
     */
    public static CFMod getModFromID(final long modId){
        if(apiKey == null) throw new InvalidAPIKeyException();
        try {
            final URL url = new URL(baseURL+"mods/"+modId);
            final HttpsURLConnection urlConnection = openUrlConnection(url);
            if(urlConnection.getResponseCode() != 200) return null;
            final InputStreamReader reader = new InputStreamReader(urlConnection.getInputStream());
            return gson.fromJson(JsonParser.parseReader(reader).getAsJsonObject().get("data").getAsJsonObject(), CFMod.class);
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
    /**
     * Fetches a mod from CurseForge
     * @param slug Slug to search for, will grab first search result
     * @return {@link CFMod} instance, or null if not found
     * @throws InvalidAPIKeyException if API Key is invalid or not set
     */
    public static CFMod getModFromSlug(final String slug, int gameID){
        if(apiKey == null) throw new InvalidAPIKeyException();
        try {
            final URL url = new URL(baseURL+"mods/search?slug="+URLEncoder.encode(slug,StandardCharsets.UTF_8)+"&gameId="+gameID);
            final HttpsURLConnection urlConnection = openUrlConnection(url);
            if(urlConnection.getResponseCode() != 200) return null;
            final InputStreamReader reader = new InputStreamReader(urlConnection.getInputStream());
            return gson.fromJson(JsonParser.parseReader(reader).getAsJsonObject().get("data").getAsJsonArray().get(0), CFMod.class);
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Fetches (first 50) categories of a specific game
     * @param gameID Game ID to load categories from
     * @return {@link CFCategory} array, or null if not found
     * @throws InvalidAPIKeyException if API Key is invalid or not set
     */
    public static CFCategory[] getCategories(long gameID){
        if(apiKey == null) throw new InvalidAPIKeyException();
        try {
            final URL url = new URL(baseURL+"categories?gameId="+gameID);
            final HttpsURLConnection urlConnection = openUrlConnection(url);
            if(urlConnection.getResponseCode() != 200) return null;
            final InputStreamReader reader = new InputStreamReader(urlConnection.getInputStream());
            return gson.fromJson(JsonParser.parseReader(reader).getAsJsonObject().get("data").getAsJsonArray(), CFCategory[].class);
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
    /**
     * Fetches specific file from a specific mod
     * @param modID ID of the mod to get the file from
     * @param fileID File ID to fetch
     * @return {@link CFFile} instance, or null if not found
     * @throws InvalidAPIKeyException if API Key is invalid or not set
     */
    public static CFFile getFileFromID(long modID, long fileID){
        if(apiKey == null) throw new InvalidAPIKeyException();
        try {
            final URL url = new URL(baseURL+"mods/"+modID+"/files/"+fileID);
            final HttpsURLConnection urlConnection = openUrlConnection(url);
            if(urlConnection.getResponseCode() != 200) return null;
            final InputStreamReader reader = new InputStreamReader(urlConnection.getInputStream());
            return gson.fromJson(JsonParser.parseReader(reader).getAsJsonObject().get("data").getAsJsonObject(), CFFile.class);
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
    /**
     * Fetches (first 50) games from curseforge
     * @return {@link CFGame} array, or null if not found
     * @throws InvalidAPIKeyException if API Key is invalid or not set
     */
    public static CFGame[] getGames() {
        if(apiKey == null) throw new InvalidAPIKeyException();
        try {
            final URL url = new URL(baseURL+"games");
            final HttpsURLConnection urlConnection = openUrlConnection(url);
            if(urlConnection.getResponseCode() != 200) return null;
            final InputStreamReader reader = new InputStreamReader(urlConnection.getInputStream());
            return gson.fromJson(JsonParser.parseReader(reader).getAsJsonObject().get("data").getAsJsonArray(), CFGame[].class);
        } catch (IOException e) {
        e.printStackTrace();
        return null;
    }
    }
    /**
     * Fetches (first 50) search results for a mod
     * @param gameID Game ID of the search result
     * @param term Search term to use
     * @return {@link CFMod} array, or null on error
     * @throws InvalidAPIKeyException if API Key is invalid or not set
     */
    public static CFMod[] searchMod(final String term, int gameID){
        if(apiKey == null) throw new InvalidAPIKeyException();
        final String urlEncodedTerm = URLEncoder.encode(term, StandardCharsets.UTF_8);
        try {
            final URL url = new URL(baseURL+"mods/search?searchFilter="+urlEncodedTerm+"&gameId="+gameID);
            final HttpsURLConnection urlConnection = openUrlConnection(url);
            if(urlConnection.getResponseCode() != 200) return null;
            final InputStreamReader reader = new InputStreamReader(urlConnection.getInputStream());
            return gson.fromJson(JsonParser.parseReader(reader).getAsJsonObject().get("data").getAsJsonArray(), CFMod[].class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static HttpsURLConnection openUrlConnection(URL url) throws IOException {
        final HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setRequestProperty("Accept", "application/json");
        urlConnection.setRequestProperty("User-Agent", "CFCore-API/https://github.com/ErdbeerbaerLP");
        urlConnection.setRequestProperty("x-api-key", apiKey);
        urlConnection.connect();
        if(urlConnection.getResponseCode() == 403) throw new InvalidAPIKeyException();
        return urlConnection;
    }

}
