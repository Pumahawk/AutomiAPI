package lexer;

public class NumberTok extends Token {

	public int value;
	
	public NumberTok(int number) {
		super(Tag.NUM);
		this.value = number;
	}

	@Override
	public boolean equals(Object o) {
		if(!super.equals(o))
			return false;
		NumberTok t = (NumberTok) o;
		return t.value == this.value;
	}
	
	public String toString() {
		String s = "<";
		s += this.tag + ", ";
		s += this.value + ">";
		return s;
	}

}
