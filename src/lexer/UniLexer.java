package lexer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import it.gandinolorenzo.lft.Automa;
import it.gandinolorenzo.lft.Stato;

public class UniLexer extends Lexer {
	
	private String contenitoreNumero = "";
	
	public static class Pattern {
		public static final Object SEPARATOR = new Object() {
			@Override
			public boolean equals(Object o) {
				
				return (
						o.equals(' ') || 
						o.equals('\n')|| 
						o.equals('\t')|| 
						o.equals('\r')
				);
			}
		};
		public static final Object NUMBER = new Object() {
			@Override
			public boolean equals(Object o) {
				char c = '.';
				if(o.getClass().isInstance(new Character('.')))
					c = (char) o;
				else
					return false;
				
				return ('0' <= c && c <= '9');
			}
		};
		public static final Object NOT_NUMBER = new Object() {
			@Override
			public boolean equals(Object o) {
				char c = '.';
				if(o.getClass().isInstance(new Character('.')))
					c = (char) o;
				else
					return false;
				return (!NUMBER.equals(o));
			}
		};
	}
	
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
		q.addTransizione(endSeparator, first, (cc) -> this.alertToken(t));
		
	}

	private Automa generateAutoma() {
		Stato q0 = new Stato("q0");
		Stato q1 = new Stato("q1");
		Stato q2 = new Stato("q2: Inizio riconoscimento numero");

		q0.addTransizione(Pattern.SEPARATOR, q0);

		// Riconoscimento EOF
		q0.addTransizione((char) -1, q0, (c) -> this.alertToken(new Token(Tag.EOF)));

		// Riconoscimento ! not
		q0.addTransizione('!', q0, (c) -> this.alertToken(Token.not));
		
		// Riconoscimento ! not
		q0.addTransizione('(', q0, (c) -> this.alertToken(Token.lpt));
		
		// Riconoscimento ! not
		q0.addTransizione(')', q0, (c) -> this.alertToken(Token.rpt));
		
		// Riconoscimento ! not
		q0.addTransizione('+', q0, (c) -> this.alertToken(Token.plus));
		
		// Riconoscimento ! not
		q0.addTransizione('-', q0, (c) -> this.alertToken(Token.minus));

		
		// Riconoscimento ! not
		q0.addTransizione('*', q0, (c) -> this.alertToken(Token.mult));
		
		// Riconoscimento ! not
		q0.addTransizione('/', q0, (c) -> this.alertToken(Token.div));
		
		// Riconoscimento ! not
		q0.addTransizione(';', q0, (c) -> this.alertToken(Token.semicolon));
		

		// Riconoscimento && end
		q0.addTransizione('&', q1);
		q1.addTransizione('&', q0, (c) -> this.alertToken(Word.and));
		
		
		// Riconoscimento  ||
		q0.addTransizione('|', q1);
		q1.addTransizione('|', q0, (c) -> this.alertToken(Word.or));
		
		
		// Riconoscimento parole chiave
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
		keyToAutoma(q0, "=", Pattern.SEPARATOR, Word.assign);
		keyToAutoma(q0, "==", Pattern.SEPARATOR, Word.eq);
		keyToAutoma(q0, "<=", Pattern.SEPARATOR, Word.le);
		keyToAutoma(q0, "<>", Pattern.SEPARATOR, Word.ne);
		keyToAutoma(q0, ">=", Pattern.SEPARATOR, Word.ge);
		
		//Riconoscimento numero


		q0.addTransizione(Pattern.NUMBER, q2, (c) ->	contenitoreNumero += c);
		q2.addTransizione(Pattern.NUMBER, q2, (c) ->	contenitoreNumero += c);
		
		q2.addTransizione(Pattern.SEPARATOR, q0, (c) -> {
			this.alertToken(new NumberTok(Integer.parseInt(contenitoreNumero)));
			contenitoreNumero = "";
		});
		
		List<Stato> finali = new LinkedList<>();
		
		finali.add(q0);
		
		Automa automa = new Automa(q0, finali);
		
		return automa;
	}
	public UniLexer(BufferedReader br) {
		super(null, br);
		Automa a = generateAutoma();
		this.automa = a;
		this.stati.add(a.iniziale);
	}

    public static void main(String[] args) throws FileNotFoundException {
    	boolean status = true;
    	//BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    	BufferedReader in = new BufferedReader(new FileReader(new File("input-file/lexer-test.pers")));
    	
    	UniLexer lexer = new UniLexer(in);
    	Token c = new Token(0);
    	while(c.tag != Tag.EOF) {
    		try {
    			c = lexer.nextToken();
    			if((c == null)) {
    				status = false;
    				break;
    			}
				System.out.println(c);
    			if((c.tag == Tag.EOF))
    				break;
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	if(status) {
        	System.out.println("Terminazione corretta");
    	}
    	else

        	System.out.println("Terminazione errata");
    }
}
