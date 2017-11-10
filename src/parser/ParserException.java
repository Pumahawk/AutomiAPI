package parser;

import lexer.Token;

public class ParserException extends Exception {
	private static final long serialVersionUID = 1L;
	public final int line;
	public final Token token;
	public final Token[] avaiableTok;
	
	public ParserException(int line, Token tok, Token[] avaiableTok) {
		this.line = line;
		this.token = tok;
		this.avaiableTok = avaiableTok;
	}
	public void printParserError() {
		System.err.println("Rilevato un errore durante l'analisi sintattica.");
		System.err.println("Errore alla riga: " + (this.line + 1));
		System.err.println("Token rilevato: " + this.token);
		System.err.println("Token accettati: ");
		for(Object o : this.avaiableTok)
			System.err.print(o + " ");
	}
}
