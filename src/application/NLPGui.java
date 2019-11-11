package application;

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
		paperButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

				int returnValue = jfc.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = jfc.getSelectedFile();
					paperPath.setText(selectedFile.getAbsolutePath());
				}
				
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
				}
				
			}
		});
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
