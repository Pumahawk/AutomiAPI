package lexer;

import java.io.IOException;

public interface Lexer {

	public void addBuffer(char c);
	public int getLastChar();
	public int getLineCounter();
	public void alertToken(Token t);
	public void alertToken(Token t, char buffer);
	public void initStringa(char c);
	public Token nextToken() throws IOException, LexerException;
	public void process(char c);
	public void updateStringa(char c);
}
