package parser;

import java.io.IOException;
import lexer.Tag;
import lexer.Token;
import lexer.UniLexer;

public class UniParser {
	public final UniLexer lexer;
	private Token t;
	public UniParser(UniLexer lexer) {
		this.lexer = lexer;
	}
	
	public void start() throws IOException {
		this.t = lexer.nextToken();
		
		if(t.tag != Tag.EOF) {
			expr();
		}
		if(this.t.tag != Tag.EOF) {
			
			Token[] avT = {new Token(Tag.EOF)};
			throw new ParserException(lexer.getLineCounter(), this.t, avT);
		}
	}
	protected void expr() throws IOException {
		term();exprp();
	}
	public void exprp() throws IOException {
		if(t.tag == Token.plus.tag) {
			this.t = lexer.nextToken();
			term();exprp();
		} else if(t.tag == Token.minus.tag) {
			this.t = lexer.nextToken();
			term();exprp();
		}
	}
	protected void term() throws IOException {
		fact();termp();
	}
	protected void termp() throws IOException {
		if(t.tag == Token.mult.tag) {
			this.t = lexer.nextToken();
			fact();termp();
		} else if(t.tag == Token.div.tag) {
			this.t = lexer.nextToken();
			fact();termp();
		}
	}
	protected void fact() throws IOException {
		if(t.tag == Token.lpt.tag) {
			this.t = lexer.nextToken();
			expr();
			if(t.tag != Token.rpt.tag) {
				Token[] avT = {Token.rpt};
				throw new ParserException(lexer.getLineCounter(), this.t, avT);
			}
		} else if(t.tag == Tag.NUM) {
			this.t = lexer.nextToken();
		}
		else {
			Token[] avT = {new Token(Tag.NUM)};
			throw new ParserException(lexer.getLineCounter(), this.t, avT);
		}
	}
	
}
