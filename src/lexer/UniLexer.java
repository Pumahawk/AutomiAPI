package lexer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import it.gandinolorenzo.lft.Automa;
import it.gandinolorenzo.lft.Stato;

public class UniLexer extends Lexer {
	
	public static class Pattern {
		public static final Object IGNORE = new Object() {
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
	}

	private Automa generateAutoma() {
		Stato q0 = new Stato("q0");
		Stato q1 = new Stato("q0");

		//q0.addTransizione(Pattern.IGNORE, q0);
		
		//Riconoscimento ! not
		q0.addTransizione('!', q0, (c) -> this.alertToken(Token.not));
		

		//Riconoscimento && end
		q0.addTransizione('&', q1);
		q1.addTransizione('&', q0, (c) -> this.alertToken(Word.and));
		
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

    public static void main(String[] args) {
    	UniLexer lexer = new UniLexer(new BufferedReader(new InputStreamReader(System.in)));
    	while(true) {
    		try {
				System.out.println(lexer.nextToken());
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    }
}
