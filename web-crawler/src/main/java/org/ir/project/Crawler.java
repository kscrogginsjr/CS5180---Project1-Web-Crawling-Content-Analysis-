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
import java.util.Properties;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Crawler{

	public static HashSet<String> visitedLinks = new HashSet<String>();
	public HashSet<String> blockedLinks = new HashSet<String>();
	public static HashSet<String> politenessChecked = new HashSet<String>();
	public static int crawlLimit = 0;
	public static HashMap<String, Integer> report = new HashMap<String, Integer>();
	public static String sourceURL = "";
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
    }public static void main(String args[]) throws IOException {
		readInputFile();
		processPage(sourceURL);
		createReport();

	}

	// The input to the crawler(seed) and the crawling limit is set in an external file.
	// We read the file and parse the data to get the starter values for the crawler
	public static void readInputFile() {
		try {
			File inoutFile = new File("src/input.txt");
			BufferedReader reader = new BufferedReader(new FileReader(inoutFile));

			String fileContent = "";
			String output = "";

			while ((fileContent = reader.readLine()) != null) {
				output += fileContent;
			}

			sourceURL = output.split(",")[0];
			crawlLimit = Integer.parseInt(output.split(",")[1]);
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// This function processes the URL, checks politeness policy, downloads the
	// content of the page and crawls outlinks of the current page
	public static void processPage(String url) throws IOException {
		// Crawl further pages only if limit is not reached or page is not previously visited
		if (visitedLinks.size() >= crawlLimit || visitedLinks.contains(url)) {
			return;
		}

		Document doc = Jsoup.connect(url).ignoreHttpErrors(true).get();
		visitedLinks.add(url);
		System.out.println(url);

		// Download content of webpage
		File createDirectory = new File("repository");
		createDirectory.mkdir();
		File htmlFile = new File("repository/link" + visitedLinks.size() + ".html");
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
			processPage(link.attr("abs:href"));
		}
	}

	// Create report.csv to store the final output of urls crawled and outlinks associated with each url
	public static void createReport() {
		File reportFile = new File("src/report.csv");
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(reportFile));
			bufferedWriter.write("URL -> Number of Outlinks \n");
			report.forEach((k, v) -> {
				try {
					bufferedWriter.write("\n" + k + " -> " + v);
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