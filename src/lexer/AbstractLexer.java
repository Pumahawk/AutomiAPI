package lexer;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import it.gandinolorenzo.lft.Automa;
import it.gandinolorenzo.lft.Stato;
import it.gandinolorenzo.lft.Stato.Pair;

public abstract class AbstractLexer implements Lexer{
	
	private boolean eof = false;;
	public Automa automa;
	public BufferedReader br;
	public List<Stato> stati = new LinkedList<>();
	public List<Token> tokenList = new LinkedList<>();
	public String stringa = "";
	Token lastToken = null;
	private int lastChar;
	boolean tokenTrovato = false;
	private Queue<Character> buffer = new LinkedList<>();
	int lineCounter = 0;
	
	public AbstractLexer(BufferedReader br) {
		this.br = br;
	}

	@Override
	public void updateStringa(char c) {
		this.stringa += c;
	}

	@Override
	public void initStringa(char c)  {
		this.stringa = "" + c;
	}

	@Override
	public void alertToken(Token t)  {
		tokenList.add(t);
		lastToken = t;
		tokenTrovato = true;
	}

	@Override
	public void alertToken(Token t, char buffer)  {
		addBuffer(buffer);
		alertToken(t);
	}

	@Override
	public Token nextToken() throws IOException, LexerException {
		this.tokenTrovato = false;
		int c;
		List<Stato> oldS = new LinkedList<>();
		oldS.addAll(this.stati);
		while(!this.tokenTrovato) {
			if(buffer.isEmpty())
				c = br.read();
			else 
				c = buffer.remove();
			this.lastChar = c;
			if(c == -1)
				if(eof) {
					Object[] pDisp = {Pattern.NOT_EOF};
					throw new LexerException(this.lineCounter, c, pDisp);
				}
				else
					eof = true;
			if(c == '\n')
				lineCounter++;
			process((char)c);
			if(this.stati.isEmpty()) {
				List<Object> pDisp = new LinkedList<>();
				for(Stato s : oldS) {
					for(Pair<?, Stato> o : s.transizioni) {
						pDisp.add(o.getKey());
					}
				}
				throw new LexerException(this.lineCounter, c, pDisp.toArray());
			}
		}
		return this.lastToken;
	}
	

	@Override
	public void process(char c) {
		List<Character> l = new LinkedList<>();
		l.add(c);
		this.stati = this.automa.process(this.stati, l);
		removeDuplicateStati();
	}
	
	void removeDuplicateStati() {
		List<Stato> l = new LinkedList<>();
		
		for(Stato s : this.stati) {
			if(!l.contains(s)) {
				l.add(s);
			}
		}
		this.stati = l;
	}
	
	@Override
	public void addBuffer(char c) {
		buffer.add(c);
	}

	@Override
	public int getLastChar() {
		return this.lastChar;
	}

	@Override
	public int getLineCounter() {
		return this.lineCounter;
	}
	
	
	
}