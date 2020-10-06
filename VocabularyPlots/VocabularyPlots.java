import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

public class VocabularyPlots {
	
	private static HashSet<String> vocabSet = new HashSet<>();
	private static BufferedReader buffer;
	private static FileWriter csvWriter;
	private static int uniqueWords;
	private static int totalWords;
	private static String[] currentLine;
	private static String directoryPath;
	private static String currentFile;
	private static String line;
	
	
	public static void main(String[] args) throws IOException
	{
		directoryPath = "/Users/KScroggins/Desktop/chinese-texts/";
		csvWriter = new FileWriter("/Users/KScroggins/Desktop/chineseWordCount.txt");
		
		uniqueWords = 0;
		totalWords = 0;
		
		File[] files = new File(directoryPath).listFiles();
		Arrays.sort(files);
		
		for(File txtFile : files)
		{
			analysis(txtFile.getName());
		}
		
		buffer.close();
		csvWriter.close();
	}
		
	private static void analysis(String file) throws IOException
	{
		currentFile = directoryPath + file;
		System.out.println(currentFile);
		
		buffer = new BufferedReader(new FileReader(currentFile));
				
		
		while((line = buffer.readLine()) != null)
		{
			currentLine = line.split(" ");
			
			for(String word : currentLine)
			{
				if(!vocabSet.contains(word.toLowerCase()))
				{
					uniqueWords += 1;
					totalWords +=1;	
					vocabSet.add(word.toLowerCase());
				}
				
				else
					totalWords +=1;
				
				csvWriter.append(uniqueWords+ "," + totalWords);
				csvWriter.append("\n");
				
			}
				
		}
		
	}
	
	
	

	
	
	

}
