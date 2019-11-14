package application;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

import src.main.SummaryParser;

public class NLPGui {

	public JFrame frame;
	public JPanel panel;

	public JLabel paperLabel;
	public JTextField paperPath;
	public JButton paperButton;

	public JLabel summaryLabel;
	public JTextField summaryPath;
	public JButton summaryButton;

	public JLabel submission_RubricHeader;
	public JLabel name_RubricHeader;
	public JLabel papername_RubricHeader;
	public JLabel wordCount_RubricHeader;
	public JLabel buzzwords_RubricHeader;

	public JLabel totalGrade_RubricHeader;

	public JLabel submission_RubricValue;
	public JLabel name_RubricValue;
	public JLabel papername_RubricValue;
	public JLabel wordCount_RubricValue;
	public JLabel buzzwords_RubricValue;

	public JLabel totalGrade_RubricValue;

	public JButton gradeButton;

	private int score;

	public NLPGui() {
		initialize();
	}

	private void initialize() {
		score = 50; // 50 to start
		constructJFrame();
		constructJPanel();
		constructJTextFieldHeaders();
		constructJTextFields();
		constructJButtons();
		constructRubricJTextFields();
		constructGradeButton();
		addComponentsToFrame();
		finalizeFrame();
	}

	private void constructGradeButton() {

		gradeButton = new JButton();
		gradeButton.setText("Grade Submission");
		gradeButton.setEnabled(false);
		gradeButton.setBounds(150, 250, 150, 30);

		gradeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SummaryParser p = new SummaryParser(paperPath.getText(), summaryPath.getText());
				p.loadDocuments();

				// TODO
				// 50 points for the submission
				score = 50;
				submission_RubricValue.setText("50 / 50");

				// 5 points for the name
				if (p.nameIncluded) {
					name_RubricValue.setText("5 / 5");
					score += 5;
				} else {
					name_RubricValue.setText("0 / 5");
				}

				// 5 points for the article title
				if (p.containsPaperTitle()) {
					papername_RubricValue.setText("5 / 5");
					score += 5;
				} else {
					papername_RubricValue.setText("0 / 5");
				}

				// 10 points for length requirements
				if (p.passesWordCount()) {
					wordCount_RubricValue.setText("10 / 10");
					score += 10;
				} else {
					System.out.println(p.summaryWordCount);
					wordCount_RubricValue.setText("0 / 10");
				}

				// 5 points for the top 6 buzz words
				int buzzWordPoints = p.bigramsUsedInSummary * 5;
				buzzwords_RubricValue.setText("" + buzzWordPoints + " / 30");
				score += buzzWordPoints;
				
				// Total score
				totalGrade_RubricValue.setText(score + " / 100");
			}
		});
	}

	private void constructRubricJTextFields() {

		// Headers
		submission_RubricHeader = new JLabel();
		submission_RubricHeader.setText("Submission");
		submission_RubricHeader.setBounds(50, 300, 200, 30);

		name_RubricHeader = new JLabel();
		name_RubricHeader.setText("Student Name Included");
		name_RubricHeader.setBounds(50, 320, 200, 30);

		papername_RubricHeader = new JLabel();
		papername_RubricHeader.setText("Paper Name Included");
		papername_RubricHeader.setBounds(50, 340, 200, 30);

		wordCount_RubricHeader = new JLabel();
		wordCount_RubricHeader.setText("250 - 300 Words Used");
		wordCount_RubricHeader.setBounds(50, 360, 200, 30);

		buzzwords_RubricHeader = new JLabel();
		buzzwords_RubricHeader.setText("Buzzwords Used");
		buzzwords_RubricHeader.setBounds(50, 380, 200, 30);

		// Grade Values
		submission_RubricValue = new JLabel();
		submission_RubricValue.setText("0 / 50");
		submission_RubricValue.setBounds(275, 300, 200, 30);

		name_RubricValue = new JLabel();
		name_RubricValue.setText("0 / 5");
		name_RubricValue.setBounds(275, 320, 200, 30);

		papername_RubricValue = new JLabel();
		papername_RubricValue.setText("0 / 5");
		papername_RubricValue.setBounds(275, 340, 200, 30);

		wordCount_RubricValue = new JLabel();
		wordCount_RubricValue.setText("0 / 10");
		wordCount_RubricValue.setBounds(275, 360, 200, 30);

		buzzwords_RubricValue = new JLabel();
		buzzwords_RubricValue.setText("0 / 30");
		buzzwords_RubricValue.setBounds(275, 380, 200, 30);

		// Final Grade
		totalGrade_RubricHeader = new JLabel();
		totalGrade_RubricHeader.setText("Final Grade");
		totalGrade_RubricHeader.setBounds(50, 410, 200, 30);

		totalGrade_RubricValue = new JLabel();
		totalGrade_RubricValue.setText("0 / 100");
		totalGrade_RubricValue.setBounds(275, 410, 200, 30);

	}

	private void addComponentsToFrame() {
		frame.add(paperLabel);
		frame.add(paperPath);
		frame.add(paperButton);

		frame.add(summaryLabel);
		frame.add(summaryPath);
		frame.add(summaryButton);

		frame.add(submission_RubricHeader);
		frame.add(name_RubricHeader);
		frame.add(papername_RubricHeader);
		frame.add(wordCount_RubricHeader);
		frame.add(buzzwords_RubricHeader);

		frame.add(submission_RubricValue);
		frame.add(name_RubricValue);
		frame.add(papername_RubricValue);
		frame.add(wordCount_RubricValue);
		frame.add(buzzwords_RubricValue);

		frame.add(totalGrade_RubricHeader);
		frame.add(totalGrade_RubricValue);

		frame.add(gradeButton);
	}

	private void constructJTextFieldHeaders() {
		paperLabel = new JLabel();
		paperLabel.setText("Research Paper");
		paperLabel.setBounds(50, 70, 200, 30);

		summaryLabel = new JLabel();
		summaryLabel.setText("Student Summary Submission");
		summaryLabel.setBounds(50, 170, 200, 30);

	}

	private void constructJButtons() {
		paperButton = new JButton();
		paperButton.setText("Browse...");
		paperButton.setBounds(275, 100, 100, 30);
		paperButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

				int returnValue = jfc.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = jfc.getSelectedFile();
					paperPath.setText(selectedFile.getAbsolutePath());

					if (isValid(selectedFile.getAbsolutePath())) {
						paperPath.setBackground(Color.GREEN);
						if (summaryPath.getBackground().equals(Color.GREEN)) {
							gradeButton.setEnabled(true);
						}
					} else {
						paperPath.setBackground(Color.RED);
						gradeButton.setEnabled(false);
					}

				}

			}

			private boolean isValid(String path) {
				return path.endsWith(".pdf") || path.endsWith(".txt");
			}
		});

		summaryButton = new JButton();
		summaryButton.setText("Browse...");
		summaryButton.setBounds(275, 200, 100, 30);
		summaryButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

				int returnValue = jfc.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = jfc.getSelectedFile();
					summaryPath.setText(selectedFile.getAbsolutePath());

					if (isValid(selectedFile.getAbsolutePath())) {
						summaryPath.setBackground(Color.GREEN);
						if (paperPath.getBackground().equals(Color.GREEN)) {
							gradeButton.setEnabled(true);
						}
					} else {
						summaryPath.setBackground(Color.RED);
						gradeButton.setEnabled(false);
					}

				}

			}

			private boolean isValid(String path) {
				return path.endsWith(".docx") || path.endsWith(".pdf") || path.endsWith(".txt");
			}
		});
	}

	private void constructJTextFields() {
		paperPath = new JTextField("--Research Paper Path--");
		paperPath.setEditable(false);
		paperPath.setBounds(50, 100, 200, 30);

		summaryPath = new JTextField("--Summary Path--");
		summaryPath.setEditable(false);
		summaryPath.setBounds(50, 200, 200, 30);

	}

	private void constructJPanel() {
		panel = new JPanel();
		panel.setSize(new Dimension(500, 500));

	}

	private void constructJFrame() {
		frame = new JFrame();
		frame.setTitle("NLP Essay Grader -- CSSE413");
		frame.setSize(new Dimension(500, 500));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

	}

	private void finalizeFrame() {
		// frame.pack();
		frame.setLayout(null);
		frame.setVisible(true);
	}

}
