package lexer;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.gandinolorenzo.lft.Automa;
import it.gandinolorenzo.lft.Stato;

public class UniLexer extends AbstractLexer {

	private Map<String, Word> keyWords = new HashMap<>();
	
	private String contenitoreNumero = "";
	private String contenitoreIdentificatore = "";
	
	private void keyToAutoma(Stato q, String key, Object endSeparator, Token t) {
		if(key == "")
			return;
		char c;
		Stato next, first = q;
		for(int i = 0; i < key.length(); i++) {
			c = key.charAt(i);
			next = new Stato();
			q.addTransizione(c, next);
			q = next;
		}
		q.addTransizione(endSeparator, first, (cc) -> this.alertToken(t, (char)cc));
		
	}
	
	public UniLexer(BufferedReader br) {
		super(br);
		Stato q0 = new Stato("q0");
		Stato q1 = new Stato("q1");
		Stato q2 = new Stato("q2: Inizio riconoscimento numero");
		Stato qDiv = new Stato("q2: Inizio riconoscimento diviso");

		q0.addTransizione(Pattern.SEPARATOR, q0);

		// Riconoscimento EOF
		q0.addTransizione((char) -1, q0, (c) -> this.alertToken(new Token(Tag.EOF)));

		// Riconoscimento ! not
		q0.addTransizione('!', q0, (c) -> this.alertToken(Token.not));
		
		// Riconoscimento ( not
		q0.addTransizione('(', q0, (c) -> this.alertToken(Token.lpt));
		
		// Riconoscimento ) not
		q0.addTransizione(')', q0, (c) -> this.alertToken(Token.rpt));
		
		// Riconoscimento + not
		q0.addTransizione('+', q0, (c) -> this.alertToken(Token.plus));
		
		// Riconoscimento - not
		q0.addTransizione('-', q0, (c) -> this.alertToken(Token.minus));

		
		// Riconoscimento * not
		q0.addTransizione('*', q0, (c) -> this.alertToken(Token.mult));
		
		// Riconoscimento / not
		q0.addTransizione('/', qDiv);
		qDiv.addTransizione(Pattern.NOT_MULT_DIV, q0, (c) -> this.alertToken(Token.div, (char) c));
		
		//Riconoscimento commento su riga
		{
			Stato qCom = new Stato();
			qDiv.addTransizione('/', qCom);
			qCom.addTransizione(Pattern.NOT_NEW_LINE, qCom);
			qCom.addTransizione('\n', q0);
			qCom.addTransizione((char) -1, q0, (c) -> this.alertToken(new Token(Tag.EOF)));
		}
		
		// Riconoscimento ; not
		q0.addTransizione(';', q0, (c) -> this.alertToken(Token.semicolon));
		

		// Riconoscimento && end
		{
			Stato andS = new Stato();
			q0.addTransizione('&', andS);
			andS.addTransizione('&', q0, (c) -> this.alertToken(Word.and));
		}

		
		// Riconoscimento  ||
		{
			Stato orS = new Stato();
			q0.addTransizione('|', orS);
			orS.addTransizione('|', q0, (c) -> this.alertToken(Word.or));
		}
		
		
		// Riconoscimento  <, <>, <=
		{
			Stato notEq = new Stato();
			q0.addTransizione('<', notEq);
			notEq.addTransizione(Pattern.NOT_MAX_EQ, q0, (c) -> this.alertToken(Word.minus, (char)c));
			notEq.addTransizione('>', q0, (c) -> this.alertToken(Word.ne));
			notEq.addTransizione('=', q0, (c) -> this.alertToken(Word.le));
		}

		// Riconoscimento =, ==
		{
			Stato eqS = new Stato();
			q0.addTransizione('=', eqS);
			eqS.addTransizione(Pattern.NOT_EQ, q0, (c) -> this.alertToken(Word.assign, (char)c));
			eqS.addTransizione('=', q0, (c) -> this.alertToken(Word.eq));
		}
		
		// Riconoscimento >, >=

		{
			Stato maxS = new Stato();
			q0.addTransizione('>', maxS);
			maxS.addTransizione(Pattern.NOT_EQ, q0, (c) -> this.alertToken(Word.gt, (char)c));
			maxS.addTransizione('=', q0, (c) -> this.alertToken(Word.ge));
		}
		
		// Riconoscimento parole chiave
		keyWords.put("if", Word.iftok);
		keyWords.put("then", Word.then);
		keyWords.put("else", Word.elsetok);
		keyWords.put("for", Word.fortok);
		keyWords.put("do", Word.dotok);
		keyWords.put("print", Word.print);
		keyWords.put("read", Word.read);
		keyWords.put("begin", Word.begin);
		keyWords.put("end", Word.end);
		/*
		 * 
		 * Parte sostitita dalla lista contente tutte le parole chiavi
		 * 
		 * 
		keyToAutoma(q0, "if", Pattern.SEPARATOR, Word.iftok);
		keyToAutoma(q0, "then", Pattern.SEPARATOR, Word.then);
		keyToAutoma(q0, "else", Pattern.SEPARATOR, Word.elsetok);
		keyToAutoma(q0, "for", Pattern.SEPARATOR, Word.fortok);
		keyToAutoma(q0, "do", Pattern.SEPARATOR, Word.dotok);
		keyToAutoma(q0, "print", Pattern.SEPARATOR, Word.print);
		keyToAutoma(q0, "read", Pattern.SEPARATOR, Word.read);
		keyToAutoma(q0, "begin", Pattern.SEPARATOR, Word.begin);
		keyToAutoma(q0, "end", Pattern.SEPARATOR, Word.end);
		keyToAutoma(q0, "<", Pattern.SEPARATOR, Word.lt);
		keyToAutoma(q0, ">", Pattern.SEPARATOR, Word.gt);
		keyToAutoma(q0, "=", Pattern.NOT_EQ, Word.assign);
		keyToAutoma(q0, "==", Pattern.SEPARATOR, Word.eq);
		keyToAutoma(q0, "<=", Pattern.SEPARATOR, Word.le);
		keyToAutoma(q0, "<>", Pattern.SEPARATOR, Word.ne);
		keyToAutoma(q0, ">=", Pattern.SEPARATOR, Word.ge);
		*/
		
		// Fine iconoscimento parole chiave
		
		//Riconoscimento numero


		q0.addTransizione(Pattern.NUMBER, q2, (c) -> contenitoreNumero += c);
		q2.addTransizione(Pattern.NUMBER, q2, (c) -> contenitoreNumero += c);
		
		q2.addTransizione(Pattern.NOT_NUMBER, q0, (c) -> {
			this.alertToken(new NumberTok(Integer.parseInt(contenitoreNumero)), (char) c);
			contenitoreNumero = "";
		});

		//Fine riconoscimento numero
		
		//Riconoscimento identificatore.

		Stato rId0 = new Stato("rId0");
		Stato rId1 = new Stato("rId1");

		q0.addTransizione(Pattern.LETTER, rId1, (c) -> contenitoreIdentificatore += c);
		q0.addTransizione('_', rId0, (c) -> contenitoreIdentificatore += c);
		rId0.addTransizione('_', rId0, (c) -> contenitoreIdentificatore += c);
		rId0.addTransizione(Pattern.LETTER, rId1, (c) -> contenitoreIdentificatore += c);
		rId0.addTransizione(Pattern.NUMBER, rId1, (c) -> contenitoreIdentificatore += c);
		rId1.addTransizione('_', rId1, (c) -> contenitoreIdentificatore += c);
		rId1.addTransizione(Pattern.NUMBER, rId1, (c) -> contenitoreIdentificatore += c);
		rId1.addTransizione(Pattern.LETTER, rId1, (c) -> contenitoreIdentificatore += c);
		rId1.addTransizione(Pattern.NON_IDENTIFICATORE, q0, (c) -> {
			if(keyWords.containsKey(contenitoreIdentificatore))
				this.alertToken(keyWords.get(contenitoreIdentificatore));
			else
				this.alertToken(new Word(Tag.ID, contenitoreIdentificatore));
			addBuffer((char)c);
			contenitoreIdentificatore = "";
		});
		
		//Fine riconoscimento identificatore
		
		//Inizio riconoscimento commento
		Stato c1 = new Stato();
		Stato c2 = new Stato();
		Stato c3 = new Stato();
		
		q0.addTransizione('/', c1);

		c1.addTransizione('*', c2);

		c2.addTransizione(Pattern.NOT_COM_PAT, c2);
		c2.addTransizione('/', c2);
		c2.addTransizione('*', c3);

		c3.addTransizione(Pattern.NOT_COM_PAT, c2);
		c3.addTransizione('/', q0);
		c3.addTransizione('*', c3);
		//Fine riconoscimento commento
		
		List<Stato> finali = new LinkedList<>();
		
		finali.add(q0);
		
		Automa a = new Automa(q0, finali);
		this.automa = a;
		this.stati.add(a.iniziale);
	}

}
