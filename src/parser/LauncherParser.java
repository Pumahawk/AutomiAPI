package parser;

import java.io.BufferedReader;
import java.io.IOException;

import lexer.LexerException;

public class LauncherParser {

	public static void exec(BufferedReader br, Parser p) throws IOException {
		try {
			p.start();
		} catch(LexerException e) {
			e.printLexerError();
		} catch(ParserException e) {
			e.printParserError();
		}
	}
}
