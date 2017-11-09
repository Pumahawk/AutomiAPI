package parser;

import java.util.List;

import lexer.Tag;
import lexer.Token;

public class ParserException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public final int line;
	public final Token token;
	public final Token[] avaiableTok;
	
	public ParserException(int line, Token tok, Token[] avaiableTok) {
		this.line = line;
		this.token = tok;
		this.avaiableTok = avaiableTok;
	}
}
