package src.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

import application.NLPGui;
import edu.stanford.nlp.util.PriorityQueue;

public class SummaryParser {

	private String paper;
	private String summary;
	
	private String paperTitle;

	public StringBuffer summaryBuffer;
	public int summaryWordCount;
	
	public boolean nameIncluded;
	public boolean paperTitleIncluded;
	
	public Map<String, Integer> bigramCounts;
	public int bigramsUsedInSummary;
	public List<String> wordsToIgnore;
 
	public SummaryParser(String paper, String summary) {
		this.paper = paper;
		this.summary = summary;

		this.summaryBuffer = new StringBuffer();
		this.summaryWordCount = 0;
		this.bigramsUsedInSummary = 0;
		this.bigramCounts = new HashMap<>();
		wordsToIgnore = new ArrayList<>();
		populateWordsToIgnore();
		
		this.nameIncluded = false;
		this.paperTitleIncluded = false;
	}

	private void populateWordsToIgnore() {
		wordsToIgnore.add("the");
		wordsToIgnore.add("to");
		wordsToIgnore.add("from");
		wordsToIgnore.add("and");
		wordsToIgnore.add("of");
		wordsToIgnore.add("and");
		wordsToIgnore.add("in");
		wordsToIgnore.add("on");
		wordsToIgnore.add("but");
		wordsToIgnore.add("or");
		wordsToIgnore.add("nor");
		wordsToIgnore.add("is");
		wordsToIgnore.add("a");
		wordsToIgnore.add("can");
		wordsToIgnore.add("be");
	}

	public boolean loadDocuments() {
		return loadPaper() && loadSummary();
	}

	private boolean loadPaper() {
		// Load paper
		File file = new File(this.paper);
		BufferedReader buffer;
		FileReader fileReader;
		try {
			fileReader = new FileReader(file);
			buffer = new BufferedReader(fileReader);

			//Pull title
			this.paperTitle = buffer.readLine();
			
			String line = buffer.readLine();
			String previous = "";
			String current = "";
			while (line != null) {
				Scanner scanner = new Scanner(line);
				while (scanner.hasNext()) {

					// TODO: place these words into a hashmap of doubles
					previous = current;
					current = scanner.next();
					
					if (!previous.equals("") && !current.equals("")) {
						
						String key = previous + " " + current;
						if (!wordsToIgnore.contains(previous) && !wordsToIgnore.contains(current)){
							if (bigramCounts.containsKey(key)) {
								System.out.println(key);
								bigramCounts.put(key, bigramCounts.get(key) + 1);
							} else {
								
								bigramCounts.put(key, 1);
							}
						}
						
					}

				}
				line = buffer.readLine();
				scanner.close();
			}
			fileReader.close();
			buffer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private boolean loadSummary() {
		// Load paper
		File file = new File(this.summary);
		BufferedReader buffer;
		FileReader fileReader;
		try {
			fileReader = new FileReader(file);
			buffer = new BufferedReader(fileReader);

			//Checks for name at the top
			String line = buffer.readLine();
			this.nameIncluded = verifyName(line);
			
			while (line != null) {
				this.summaryBuffer.append(line + " ");
				Scanner scanner = new Scanner(line);
				while (scanner.hasNext()) {
					this.summaryWordCount++;
					scanner.next();
				}
				line = buffer.readLine();
				scanner.close();				
			}
			fileReader.close();
			buffer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		checkBuzzWords();
		return true;
	}

	private void checkBuzzWords() {

		List<String> topSixBuzzWords = getTopXBuzzWords(6);
		
		for (String buzzWordBigram : topSixBuzzWords) {
			
			String keyWordCombo = buzzWordBigram;
			if (this.summaryBuffer.toString().contains(keyWordCombo)) {
				this.bigramsUsedInSummary++;
			}
		}
		
		
	}

	private List<String> getTopXBuzzWords(int i) {

		List<String> topSixBuzzWords = new ArrayList<>();
		
		for(int x = 0; x < i; x++) {
			topSixBuzzWords.add(getMostOccuringBigram());
		}
		
		return topSixBuzzWords;
	}
	
	private String getMostOccuringBigram() {
		
		int maxCount = 0;
		String maxBigram = null;
		
		for (String bigram : bigramCounts.keySet()) {
			int currentCount = bigramCounts.get(bigram);
			if (currentCount > maxCount) {
				maxBigram = bigram;
				maxCount = currentCount; 
			}
		}
		
		bigramCounts.remove(maxBigram);
		System.out.println("Buzzword Count: " + maxCount + " - " + maxBigram);
		return maxBigram;
	}

	private boolean verifyName(String line) {
		String[] words = line.split("[^\\w']+");
		for(int i = 0; i < words.length; i++) {
//			System.out.println(words[i]);
			String word = words[i].replaceAll(" ", "");
			if(word.charAt(0) != (word.toUpperCase()).charAt(0)){
				//System.out.println(words[i]);
				return false;
			}
		}
		return true;
	}

	public boolean passesWordCount(){
		return this.summaryWordCount <= 500 && this.summaryWordCount >= 300;
	}
	
	public boolean containsPaperTitle(){
		return this.summaryBuffer.toString().contains(this.paperTitle);
	}
	
	public static void main(String[] args) {
		NLPGui gui = new NLPGui();
	}

}
