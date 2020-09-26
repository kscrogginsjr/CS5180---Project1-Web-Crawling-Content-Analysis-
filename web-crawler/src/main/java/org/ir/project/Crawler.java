package org.ir.project;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Crawler{
    public static Properties properties;
    static {
        properties = new Properties();
        try (InputStream input = Crawler.class.getClassLoader().getResourceAsStream("Application.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find Application.properties");
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello");
    }
}