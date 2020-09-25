package org.ir.project.utils;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Helper {

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
}
