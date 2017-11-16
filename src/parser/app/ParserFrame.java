package parser.app;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ParserFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	final JTextArea codeArea;
	final AppController controller;
	
	public ParserFrame(AppController controller) {
		
		this.controller = controller;
		
		setTitle("ParserTest");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		codeArea = new JTextArea(15,50);
		JScrollPane sc = new JScrollPane(codeArea);
		
		JButton controlla = new JButton("AVVIA");
		controlla.addActionListener(e -> controller.checkCode());
		
		JPanel south = new JPanel();
		south.add(controlla);
		
		add(sc, BorderLayout.CENTER);
		add(south, BorderLayout.SOUTH);
		pack();
		
	}
	
	public String getCode() {
		return this.codeArea.getText();
	}
}
