/*
 * Course: CS 5180 Information Retrival
 * Project Part I: Implementation of Web Crawler
 * Authors: Jayavardhan Patil, Ankita Patil
 * 
 * The project implements a web crawler that starts with a seed and crawls every outlink available on the page.
 * It also takes into consideration the politeness policy of each domain crawled and avoids disallowed links mentioned in its robot.txt file.
 * The final output of the crawler is a report.csv file that contains the URL's crawled and the number of outlinks found in each webpage.
 * 
 */

package org.ir.project;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Properties;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

import org.ir.project.utils.Helper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Crawler{

	private static HashSet<String> visitedLinks = new HashSet<String>();
	private static int crawlLimit = 0;
	private static HashMap<String, Integer> report = new HashMap<String, Integer>();
    private static HashMap<String, HashSet<String>> hostDisallowedUrlPath = new HashMap<>();
	public static Properties properties;
	private Helper helper = new Helper(); 
    
    static {
        properties = new Properties();
        try (InputStream input = Crawler.class.getClassLoader().getResourceAsStream("Application.properties")) {
            if (input == null) {
                System.out.println("Unable to find Application.properties");
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void main(String args[]) throws IOException {
		//readInputFile();
		Crawler crawler = new Crawler();
		String sourceURL = properties.getProperty("webCrawler.SEED_WEBSITE");
		crawlLimit = Integer.parseInt(properties.getProperty("webCrawler.CRAWL_LIMIT"));
		long startTime = System.currentTimeMillis();
		crawler.processPage(sourceURL);
		long endTime = System.currentTimeMillis();

		System.out.println("Total Time to crawl 100 webPages - "+ (endTime - startTime) + " ms");
		crawler.createReport();
	}

	// The input to the crawler(seed) and the crawling limit is set in an external file.
	// We read the file and parse the data to get the starter values for the crawler
//	public static void readInputFile() {
//		try {
//			File inoutFile = new File("src/input.txt");
//			BufferedReader reader = new BufferedReader(new FileReader(inoutFile));
//
//			String fileContent = "";
//			String output = "";
//
//			while ((fileContent = reader.readLine()) != null) {
//				output += fileContent;
//			}
//
//			sourceURL = output.split(",")[0];
//			crawlLimit = Integer.parseInt(output.split(",")[1]);
//			reader.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	// This function processes the URL, checks politeness policy, downloads the
	// content of the page and crawls outlinks of the current page
	public void processPage(String url) {
		// Crawl further pages only if limit is not reached or page is not previously visited
		if (visitedLinks.size() >= crawlLimit || visitedLinks.contains(url)) {
			return;
		}

		Document doc = null;
		try {
			URL uri = new URL(url);
			String host = uri.getHost();
			HashSet<String> disAllowedPaths = null;
			if (!hostDisallowedUrlPath.containsKey(host)) {
				//System.out.println("No robots fetched for this Base URL");
				disAllowedPaths = helper.getDisalowedPaths_From_robotosFile(uri.getProtocol() + "://" + uri.getHost());
				hostDisallowedUrlPath.put(host, disAllowedPaths);
			}
			disAllowedPaths = hostDisallowedUrlPath.get(host);
			if (!helper.isUrlAllowedtoCrawl(disAllowedPaths, url)) {
				System.out.println("Not allowed to Crawl : " + url);
				return;
			}

			//Document doc = Jsoup.connect(url).ignoreHttpErrors(true).get();

			doc = Jsoup.parse(new URL(url).openStream(), "UTF-8", url);
			//System.out.println("Body : " + doc.body().children().text());
			visitedLinks.add(url);
			System.out.println(url);

			// Download content of webpage
			String utf_encoded_webPageContent = URLEncoder.encode(doc.body().children().text(), "UTF-8");
			System.out.println("title : " + doc.title());

			String language = "English";

			language = helper.getWebPageLanguage(utf_encoded_webPageContent.length() > 1000 ? utf_encoded_webPageContent.substring(0, 1000) : utf_encoded_webPageContent);

			File createDirectory = new File("repository/");
			createDirectory.mkdir();
			createDirectory = new File("repository/" + language);
			createDirectory.mkdir();
			File htmlFile = new File(createDirectory.getCanonicalPath() + "/link_" + visitedLinks.size() + ".html");
			//htmlFile.createNewFile();
			String htmlText = doc.html();

			FileWriter fileWriter = new FileWriter(htmlFile);
			fileWriter.write(htmlText);

			fileWriter.flush();
			fileWriter.close();

			// retrieve outlinks of webpage
			Elements sublinks = doc.select("a[href]");

			int outlinkCount = sublinks.size();
			report.put(url, outlinkCount);

			// process each outlink
			for (Element link : sublinks) {
				if (!helper.isValidUrl(link.attr("abs:href"))) {
					//System.out.println(link);
					//System.out.println("Not a valid URL : " + link.attr("abs:href"));
					continue;
				}
				processPage(link.attr("abs:href"));
			}
		}catch (Exception e){
			System.err.println("Exception : "+e.getLocalizedMessage());
			return;
		}

	}


	// Create report.csv to store the final output of urls crawled and outlinks associated with each url
	public void createReport() {
		File reportFile = new File("repository/report.csv");
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(reportFile));
			bufferedWriter.write("URL, Number of Outlinks \n");
			report.forEach((k, v) -> {
				try {
					bufferedWriter.write("\n" + k + ", " + v);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});

			bufferedWriter.close();
		} catch (IOException e) {

		}
	}
}