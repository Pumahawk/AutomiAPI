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
	public String stringa = "";
	Token lastToken = null;
	boolean tokenTrovato = false;
	
	public Lexer(Automa automa, BufferedReader br) {
		this.automa = automa;
		this.br = br;
		if(automa != null)
			this.stati.add(automa.iniziale);
	}
	
	public void updateStringa(char c) {
		this.stringa += c;
	}
	
	public void initStringa(char c)  {
		this.stringa = "" + c;
	}
	
	public void alertToken(Token t)  {
		tokenList.add(t);
		lastToken = t;
		tokenTrovato = true;
	}
	
	public Token nextToken() throws IOException {
		this.tokenTrovato = false;
		int c;
		while(!this.tokenTrovato) {
			if((c = br.read()) == -1)
				alertToken(new Token(Tag.EOF));
			else
				process((char)c);
			if(this.stati.isEmpty())
				return null;
		}
		return this.lastToken;
	}
	
	public void process(char c) {
		List<Character> l = new LinkedList<>();
		l.add(c);
		this.stati = this.automa.process(this.stati, l);
	}
	
	
	
	
}