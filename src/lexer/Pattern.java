package lexer;

public class Pattern {
	
	static public interface ObjBool {
		boolean eq(Character o);
	}
	
	static public class Checker {
		public final String name;
		public final ObjBool check;
		public Checker(String name, ObjBool check) {
			this.name = name;
			this.check = check;
		}
		@Override
		public String toString() {
			return this.name;
		}
		@Override
		public boolean equals(Object o) {
			if(!o.getClass().isInstance(new Character('.')))
				return false;
			else 
				return this.check.eq((char)o);
		}
			
	}
	
	public static final Object SEPARATOR = new Checker("SEPARATOR", (o) -> (
			o.equals(' ') || 
			o.equals('\n')|| 
			o.equals('\t')|| 
			o.equals('\r')
	));
	
	public static final Object NUMBER = new Checker("NUMBER", (o) -> '0' <= o && o <= '9');
	public static final Object NOT_NUMBER = new Checker("NOT_NUMBER", (o) -> (!NUMBER.equals(o)));
	public static final Object LETTER = new Checker("LETTER", (o) -> ('a' <= o && o <= 'z') || ('A' <= o && o <= 'Z'));
	public static final Object NON_IDENTIFICATORE = new Checker("NON_IDENTIFICATORE",(o) ->  
		!('a' <= o && o <= 'z') && 
		!('A' <= o &&	o <= 'Z') && 
		!o.equals('_') && !NUMBER.equals(o)
	);
	public static final Object NOT_EQ = new Checker("NOT_EQ", (o) -> o != '=');
	public static final Object NOT_COM_PAT = new Checker("NOT_COM_PAT", (o) ->  o != '/' && o != '*');
	public static final Object NOT_DIV = new Checker("NOT_DIV", (o) ->  o != '/');
	public static final Object NOT_MULT_DIV = new Checker("NOT_MULT_DIV",(o) -> o != '*' && o != '/');
	public static final Object NOT_NEW_LINE = new Checker("NOT_NEW_LINE",(o) -> o != '\n');
	public static final Object NOT_MAX = new Checker("NOT_MAX", (c) -> c != '>');
	public static final Object NOT_MAX_EQ = new Checker("NOT_MAX_EQ", (c) -> c != '>' && c != '=');
	public static final Object NOT_MIN = new Checker("NOT_MIN", (c) -> c != '<');
}
