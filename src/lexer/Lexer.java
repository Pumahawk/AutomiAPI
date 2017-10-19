package lexer;

import java.io.BufferedReader;
import java.util.LinkedList;
import java.util.List;

import it.gandinolorenzo.lft.Automa;
import it.gandinolorenzo.lft.Stato;

public abstract class Lexer {
	
	public Automa automa;
	public List<Stato> stati = new LinkedList<>();
	public List<Token> tokenList = new LinkedList<>();
	Token lastToken = null;
	boolean tokenTrovato = false;
	
	public Lexer(Automa automa) {
		this.automa = automa;
	}
	
	public void alertToken(Token t)  {
		tokenList.add(t);
		lastToken = t;
		tokenTrovato = true;
	}
	
	public Token nextToken(BufferedReader br) {
		return null;
	}
	
	
}