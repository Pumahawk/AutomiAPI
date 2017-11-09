package lexer;

import java.util.List;

public class LexerException extends RuntimeException {
	
	private static final long serialVersionUID = -8673728574812248899L;
	
	public final int line;
	public final int character;
	public final List<Object> avaiableCharacter;
	
	public LexerException(int line, int character, List<Object> avaiableCharacter) {
		this.line = line;
		this.character = character;
		this.avaiableCharacter = avaiableCharacter;
	}

}
