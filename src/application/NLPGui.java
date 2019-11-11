package application;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NLPGui {
	
	public JFrame frame;
	public JPanel panel;
	
	public JLabel paperLabel;
	public JTextField paperPath;
	public JButton paperButton;
	
	public JLabel summaryLabel;
	public JTextField summaryPath;
	public JButton summaryButton;
	
	
	public NLPGui() {
		initialize();
	}
	
	private void initialize() {
		constructJFrame();
		constructJPanel();
		constructJTextFieldHeaders();
		constructJTextFields();
		constructJButtons();
		addComponentsToFrame();	
		finalizeFrame();
	}
	
	private void addComponentsToFrame() {
		frame.add(paperLabel);
		frame.add(paperPath);
		frame.add(paperButton);
		
		frame.add(summaryLabel);
		frame.add(summaryPath);
		frame.add(summaryButton);	
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
		
		summaryButton = new JButton();
		summaryButton.setText("Browse...");
		summaryButton.setBounds(275, 200, 100, 30);
		
	}

	private void constructJTextFields() {
		paperPath = new JTextField("--Research Paper Path--");  
	    paperPath.setBounds(50, 100, 200, 30);  
	    
	    summaryPath = new JTextField("--Summary Path--");  
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
		//frame.pack();
		frame.setLayout(null);
		frame.setVisible(true);
	}

}
