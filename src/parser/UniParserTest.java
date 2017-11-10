package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import lexer.LexerException;
import lexer.UniLexer;

public class UniParserTest {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File("input-file/parser-test.pers")));
		UniLexer lexer = new UniLexer(br);
		UniParser p = new UniParser(lexer);
		try {
			p.start();
		} catch(LexerException e) {
			e.printLexerError();
		} catch(ParserException e) {
			System.err.println("Rilevato un errore durante l'analisi sintattica.");
			System.err.println("Errore alla riga: " + (e.line + 1));
			System.err.println("Token rilevato: " + e.token);
			System.err.println("Token accettati: ");
			for(Object o : e.avaiableTok)
				System.err.print(o + " ");
		}
	}
}
