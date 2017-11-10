package lexer;

public class LexerException extends Exception {
	
	private static final long serialVersionUID = -8673728574812248899L;
	
	public final int line;
	public final int character;
	public final Object[]  avaiableCharacter;
	
	public LexerException(int line, int character, Object[] avaiableCharacter) {
		this.line = line;
		this.character = character;
		this.avaiableCharacter = avaiableCharacter;
	}
	
	public void printLexerError() {
		System.err.println("Rilevato un errore durante l'analisi lessicale.");
		System.err.println("Errore alla riga: " + (this.line + 1));
		System.err.println("Carattere rilevato: " + this.character);
		System.err.println("Caratteri accettati: ");
		for(Object o : this.avaiableCharacter)
			System.err.print(o + " ");
	}

}
