package application;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class NLPGui {
	
	public JFrame frame;
	public JPanel panel;
	
	
	public NLPGui() {
		initialize();
	}
	
	private void initialize() {
		constructJFrame();
		constructJPanel();
		frame.add(panel);
		finalizeFrame();
		
		
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
		frame.setVisible(true);
	}

}
