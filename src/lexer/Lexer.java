package lexer;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import it.gandinolorenzo.lft.Automa;
import it.gandinolorenzo.lft.Stato;

public class Lexer {
	
	public Automa automa;
	public BufferedReader br;
	public List<Stato> stati = new LinkedList<>();
	public List<Token> tokenList = new LinkedList<>();
	Token lastToken = null;
	boolean tokenTrovato = false;
	
	public Lexer(Automa automa, BufferedReader br) {
		this.automa = automa;
		this.br = br;
		if(automa != null)
			this.stati.add(automa.iniziale);
	}
	
	public void alertToken(Token t)  {
		tokenList.add(t);
		lastToken = t;
		tokenTrovato = true;
	}
	
	public Token nextToken() throws IOException {
		this.tokenTrovato = false;
		int c;
		while((c = br.read()) != -1 && !this.tokenTrovato) {
			process((char)c);
		}
		if(this.tokenTrovato)
			return this.lastToken;
		else
			return null;
	}
	
	public void process(char c) {
		List<Character> l = new LinkedList<>();
		l.add(c);
		this.stati = this.automa.process(this.stati, l);
	}
	
	
	
	
}