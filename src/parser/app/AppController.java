package parser.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import javax.swing.JOptionPane;

import lexer.LexerException;
import lexer.UniLexer;
import parser.LauncherParser;
import parser.ParserException;
import parser.UniParser;

public class AppController {
	ParserFrame frame;

	public AppController() {
		this.frame = new ParserFrame(this);
	}
	
	public AppController(ParserFrame f) {
		this.frame = f;
	}
	
	public void setFrame(ParserFrame f) {
		this.frame = f;
	}
	
	public void checkCode() {
		String code = frame.getCode();
		StringReader r = new StringReader(code);
		
		BufferedReader bf = new BufferedReader(r);
		
		UniLexer lexer = new UniLexer(bf);
		UniParser p = new UniParser(lexer);
		
		
		try {
			p.start();
			JOptionPane.showMessageDialog(null, "Analisi sintattica avvenuta con successo.");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Impossibile leggete il codice",
					"Errore input", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (LexerException e) {
			String message = "Carattere letto: " + e.character;
			message += "\nRiga: " + (e.line + 1);
			String title = "Errore nell'analisi lessicale.";
			JOptionPane.showMessageDialog(null, message,
					title, JOptionPane.ERROR_MESSAGE);
			
			e.printStackTrace();
		} catch (ParserException e) {
			String message = ("Errore alla riga: " + (e.line + 1));
			message += ("\nToken rilevato: " + e.token);
			String title = "Errore nell'analisi Sintattica.";
			JOptionPane.showMessageDialog(null, message,
					title, JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void visibleFrame(boolean vis) {
		this.frame.setVisible(true);
	}
	
	public static void main(String[] args) throws IOException {
		AppController controller = new AppController();
		controller.visibleFrame(true);
	}
}
