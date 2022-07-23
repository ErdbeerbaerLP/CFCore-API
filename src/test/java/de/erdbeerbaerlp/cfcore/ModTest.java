package de.erdbeerbaerlp.cfcore;

import de.erdbeerbaerlp.cfcore.exceptions.InvalidAPIKeyException;
import de.erdbeerbaerlp.cfcore.json.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class ModTest {

    @Test
    @BeforeAll
    @DisplayName("API Key set?")
    static void getAPIKey(){
        Assertions.assertNotEquals(null,System.getProperties().getProperty("cf.apikey"));
    }

    @Test
    @DisplayName("Check API Key exception")
    void checkAPIKeyException(){
        Assertions.assertThrows(InvalidAPIKeyException.class,()->{
            CFCoreAPI.getModFromID(1);
        });

        CFCoreAPI.setApiKey("invalid");
        Assertions.assertThrows(InvalidAPIKeyException.class,()->{
            CFCoreAPI.getModFromID(1);
        });
    }

    @Test
    @DisplayName("Get an existing mod's data from ID")
    void getExistingModID(){
        CFCoreAPI.setApiKey(System.getProperties().getProperty("cf.apikey"));
        final CFMod mod = CFCoreAPI.getModFromID(238222);
        Assertions.assertNotNull(mod);
        System.out.println("Output:" + mod.toString());
        Assertions.assertEquals("jei",mod.slug);
    }
    @Test
    @DisplayName("Get an existing mod's data from Slug")
    void getExistingModSlug(){
        CFCoreAPI.setApiKey(System.getProperties().getProperty("cf.apikey"));
        final CFMod mod = CFCoreAPI.getModFromSlug("dcintegration",432);
        Assertions.assertNotNull(mod);
        System.out.println("Output:" + mod.toString());
        Assertions.assertEquals(324952,mod.id);
    }
    @Test
    @DisplayName("Get an nonexisting mod's data")
    void getNonexistentMod(){
        CFCoreAPI.setApiKey(System.getProperties().getProperty("cf.apikey"));
        final CFMod mod = CFCoreAPI.getModFromID(1);
        Assertions.assertNull(mod);
    }


    @Test
    void testGameRetrieval(){
        CFCoreAPI.setApiKey(System.getProperties().getProperty("cf.apikey"));
        final CFGame[] games = CFCoreAPI.getGames();
        System.out.println(Arrays.toString(games));
        Assertions.assertNotNull(games);
        Assertions.assertNotNull(games[0]);
    }
    @Test
    void searchMod(){
        CFCoreAPI.setApiKey(System.getProperties().getProperty("cf.apikey"));
        final CFMod[] results = CFCoreAPI.searchMod("BetterPortals", 432);
        Assertions.assertNotNull(results);
        Assertions.assertNotNull(results[0]);
        Assertions.assertEquals("betterportals",results[0].slug);
        System.out.println(Arrays.toString(results));
    }

    @Test
    void fileRetrievalTest(){
        CFCoreAPI.setApiKey(System.getProperties().getProperty("cf.apikey"));
        final CFFile file = CFCoreAPI.getFileFromID(324952,3846786);
        Assertions.assertNotNull(file);
        Assertions.assertEquals("dcintegration-forge-2.4.5-1.19.jar",file.fileName);
    }

    @Test
    void categoryRetrievalTest(){
        CFCoreAPI.setApiKey(System.getProperties().getProperty("cf.apikey"));
        final CFCategory[] categories = CFCoreAPI.getCategories(432);
        Assertions.assertNotNull(categories);
        Assertions.assertNotNull(categories[0]);
    }

    @Test
    void releaseTypeTest(){
        CFCoreAPI.setApiKey(System.getProperties().getProperty("cf.apikey"));
        final CFFile alphaFile = CFCoreAPI.getFileFromID(238222, 3894194);
        final CFFile betaFile = CFCoreAPI.getFileFromID(238222, 3885885);
        final CFFile releaseFile = CFCoreAPI.getFileFromID(238222, 3847103);
        Assertions.assertNotNull(alphaFile);
        Assertions.assertNotNull(betaFile);
        Assertions.assertNotNull(releaseFile);
        Assertions.assertEquals(CFReleaseType.ALPHA, alphaFile.releaseType);
        Assertions.assertEquals(CFReleaseType.BETA, betaFile.releaseType);
        Assertions.assertEquals(CFReleaseType.RELEASE, releaseFile.releaseType);

    }
}
