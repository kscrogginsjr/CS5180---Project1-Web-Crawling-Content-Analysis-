package org.ir.project.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.ir.project.Crawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

public class Helper {

    private static final String ACCESS_KEY = Crawler.properties.getProperty("language.detector.access_key");
    private static final String LANGUAGE_DETECTOR = Crawler.properties.getProperty("language.detector.api");

    //read Robots file.
    public HashSet<String> getDisalowedPaths_From_robotosFile(String hostUrl) throws IOException {
        System.out.println("Fetching disallowed paths from robots.txt");
        System.out.println(hostUrl+"/robots.txt");

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(hostUrl+"/robots.txt").method("GET", null).build();
        com.squareup.okhttp.Response response = client.newCall(request).execute();

        //System.out.println(response.getStatusCode());
        if(!response.isSuccessful() || response.code() != 200) {
            System.out.println("Robots.txt not found");
            return  new HashSet<>();
        }

        HashSet<String> diasslowedUrls = new HashSet<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(response.body().byteStream()));
        String line = null;
        while ((line = reader.readLine()) != null){
            if(line.equalsIgnoreCase("user-agent: *")){
                while ((line = reader.readLine()) != null){

                    //Check if we have arrived at different block.
                    if(line.toLowerCase().contains("sitemap:") || line.toLowerCase().contains("user-agent") ||
                            line.toLowerCase().startsWith("#")){
                        break;
                    }

                    if(line.toLowerCase().contains("disallow:")) {
                        //A
                        try {
                            diasslowedUrls.add(line.split(" ")[1]);
                        }catch (Exception e) {
                            diasslowedUrls.add("");
                        }
                    }
                }
            }
        }
        return diasslowedUrls;
    }

    public boolean isUrlAllowedtoCrawl(HashSet<String> listOfdisAllowedPaths, String urlPath) {
        if(urlPath.isEmpty() || listOfdisAllowedPaths == null) return true;

        for (String path: listOfdisAllowedPaths) {
            //Check for not allowed to crawl any page
            //If it contains only / -> no pages allowed to crawl
            if(path.equals("\\/")){
                return false;
            };

            path = path.replace("?", "\\?");
            path = path.replace("*", ".*");

            System.out.println("Path :"+path);

            if(urlPath.matches(".*" + path + ".*"))
                return false;
        }
        return true;
    }

    public String getWebPageLanguage(String webPageTitle) throws IOException, InterruptedException {
        String language = null;
        String url = LANGUAGE_DETECTOR+"?access_key="+ ACCESS_KEY +"&query="+webPageTitle;
        int retry = 2;
        com.squareup.okhttp.Response response;
        do{
            response = getResponseFromLanguaDetectore(url);
            retry--;
        }while (retry > 0 && !response.isSuccessful());

        if(response.isSuccessful()){
            String responseBody = response.body().string();
            ObjectMapper mapper = new ObjectMapper();
            try {
                System.out.println("response Body : "+responseBody);
                LanguageDetector languageDetectorObj = mapper.readValue(responseBody,
                        LanguageDetector.class);
                if(languageDetectorObj.success){
                    language = languageDetectorObj.results.get(0).language_name;
                }else if(languageDetectorObj.error != null){
                    System.err.println("Issue with the Language detector API");
                    if(languageDetectorObj.error.code == 106) {
                        Thread.sleep(60000);
                        System.out.println("Wait for a minute, you have reached max rate 'hit/min' limit ..... ");
                        getWebPageLanguage(webPageTitle);
                    }
                }
            } catch (JsonProcessingException e) {
                System.out.println("Failed to parse Language detector response Body");
            }
        }else{
            System.err.println("Failed to get the Language detector \n response : " + response.body().string() +" \n status code : "+ response.code());
        }
        return language;
    }


    public Response getResponseFromLanguaDetectore(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(10, TimeUnit.SECONDS);
        client.setReadTimeout(10, TimeUnit.SECONDS);
        client.setWriteTimeout(10, TimeUnit.SECONDS);

        com.squareup.okhttp.Response response;
        System.out.println("URL  : "+url);
        Request request = new Request.Builder().url(url).method("GET", null).build();
        response = client.newCall(request).execute();
        return response;
    }

}
