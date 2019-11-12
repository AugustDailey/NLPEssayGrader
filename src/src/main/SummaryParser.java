package src.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class SummaryParser {

	private String paper;
	private String summary;

	private StringBuffer summaryBuffer;

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

			String line = buffer.readLine();

			// TODO: read in the title and the authors

			while (line != null) {
				Scanner scanner = new Scanner(line);
				while (scanner.hasNext()) {

					// TODO: place these words into a hashmap of doubles
					System.out.println(scanner.next());

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

	public static void main(String[] args) {
		SummaryParser p = new SummaryParser(
				"C:/Users/dummitrj/OneDrive - Rose-Hulman Institute of Technology/Documents/Artificial Intelligence/AI Eclipse Workspace/NLPEssayGrader/src/papers/Deep Blue Paper.txt",
				"C:/Users/dummitrj/OneDrive - Rose-Hulman Institute of Technology/Documents/Artificial Intelligence/AI Eclipse Workspace/NLPEssayGrader/src/papers/Deep Blue Summary.txt");
		p.loadDocuments();

		System.out.println(p.summaryBuffer.toString());
		
	}

}
