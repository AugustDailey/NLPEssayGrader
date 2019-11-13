package src.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import application.NLPGui;

public class SummaryParser {

	private String paper;
	private String summary;
	
	private String paperTitle;

	private StringBuffer summaryBuffer;
	
	private boolean nameIncluded;
	private boolean paperTitleIncluded;

	public SummaryParser(String paper, String summary) {
		this.paper = paper;
		this.summary = summary;

		this.summaryBuffer = new StringBuffer();
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

			while (line != null) {
				Scanner scanner = new Scanner(line);
				while (scanner.hasNext()) {

					// TODO: place these words into a hashmap of doubles
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

			//TODO: Do we want to check for your name at the top? 
			String name = buffer.readLine();
			nameIncluded = verifyName(name);
			
			
			String line = "";
			
			while (line != null) {
				this.summaryBuffer.append(line + " ");
				line = buffer.readLine();
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

	private boolean verifyName(String line) {
		String[] words = line.split("[^\\w']+");
		for(int i = 0; i < words.length; i++) {
			System.out.println(words[i]);
		}
		return true;
	}

	public static void main(String[] args) {
		NLPGui gui = new NLPGui();
	
//		SummaryParser p = new SummaryParser(gui.paperPath.getText(), gui.summaryPath.getText());
////				"C:/Users/daileyab/Desktop/CSSE413/NLPEssayGrader/src/papers/Deep Blue Paper.txt",
////				"C:/Users/daileyab/Desktop/CSSE413/NLPEssayGrader/src/papers/Deep Blue Summary.txt");
//		p.loadDocuments();

		//System.out.println(p.summaryBuffer.toString());
		
	}

}
