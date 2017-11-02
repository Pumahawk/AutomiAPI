package lexer;

public class NumberTok extends Token {

	public int value;
	
	public NumberTok(int number) {
		super(Tag.NUM);
		this.value = number;
	}
	
	
	public String toString() {
		String s = "<";
		s += this.tag + ",";
		s += this.value + ">";
		return s;
	}

}
