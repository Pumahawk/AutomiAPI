package lexer;

public class Token {
	public final int tag;

	public Token(int t) {
		tag = t;
	}

	public String toString() {
		return "<" + tag + ">";
	}
	
	@Override
	public boolean equals(Object o) {
		if(!o.getClass().isInstance(this))
			return false;
		Token t = (Token) o;
		return t.tag == this.tag;
	}

	public static final Token
		not = new Token('!'),
		lpt = new Token('('),
		rpt = new Token(')'),
		plus = new Token('+'),
		minus = new Token('-'),
		mult = new Token('*'),
		div = new Token('/'),
		semicolon = new Token(';'),
		assign = new Token('=');
	}
