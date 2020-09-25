package org.ir.project;

import org.ir.project.utils.Helper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashSet;
import java.util.Properties;

public class Robots_disallowedUrl_test {

    Helper helper = new Helper();

    HashSet<String> disAllowedPathPattern;
    public static Properties properties;
    static {
        properties = new Properties();
        try (InputStream input = Robots_disallowedUrl_test.class.getClassLoader().getResourceAsStream("Application.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find Application.properties");
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    @Test
    public void allowBasewebsite() throws IOException {
        getDisAllowedPaths();
        disAllowedPathPattern.add("/");
        boolean result = helper.isUrlAllowedtoCrawl(disAllowedPathPattern, "");
        Assertions.assertTrue(result);
        disAllowedPathPattern.remove("/");
    }

    @Test
    public void disAllowAllPaths() throws IOException {
        getDisAllowedPaths();
        disAllowedPathPattern.add("/");
        boolean result = helper.isUrlAllowedtoCrawl(disAllowedPathPattern, "/rgeger/wgewr/");
        Assertions.assertFalse(result);
        disAllowedPathPattern.remove("/");
    }

    @Test
    public void disAllowspecificPaths() throws IOException {

        getDisAllowedPaths();
        boolean result = helper.isUrlAllowedtoCrawl(disAllowedPathPattern, "/cnn_adspaces/hello/php");
        Assertions.assertFalse(result);
    }

    @Test
    public void disAllowspecificPaths_1() throws IOException {
        getDisAllowedPaths();
        boolean result = helper.isUrlAllowedtoCrawl(disAllowedPathPattern, "egherthtr/browsers/hello/php");
        Assertions.assertFalse(result);
    }

    @Test
    public void AllowspecificPaths_1() throws IOException {
        getDisAllowedPaths();
        boolean result = helper.isUrlAllowedtoCrawl(disAllowedPathPattern, "/gsdgerge/hello/php");
        Assertions.assertTrue(result);
    }

    public void getDisAllowedPaths() throws IOException {
        if(disAllowedPathPattern == null){
            URL url = new URL(properties.getProperty("webCrawler.SEED_WEBSITE"));
            disAllowedPathPattern = helper.getDisalowedPaths_From_robotosFile(url.getProtocol()+"://"+url.getHost());
        }
    }

}
