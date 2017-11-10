package parser;

import java.io.IOException;

import lexer.LexerException;

public interface Parser {
	public void start()  throws IOException, LexerException, ParserException ;
}
