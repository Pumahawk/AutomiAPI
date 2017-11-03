package lexer;

public class Pattern {
	
	static public interface ObjBool {
		boolean eq(Character o);
	}
	
	static public class Checker {
		public ObjBool check;
		public Checker(ObjBool check) {
			this.check = check;
		}
		@Override
		public boolean equals(Object o) {
			if(!o.getClass().isInstance(new Character('.')))
				return false;
			else 
				return this.check.eq((char)o);
		}
			
	}
	
	public static final Object SEPARATOR = new Checker((o) -> (
			o.equals(' ') || 
			o.equals('\n')|| 
			o.equals('\t')|| 
			o.equals('\r')
	));
	
	public static final Object NUMBER = new Checker((o) -> '0' <= o && o <= '9');
	public static final Object NOT_NUMBER = new Checker((o) -> (!NUMBER.equals(o)));
	public static final Object LETTER = new Checker((o) -> ('a' <= o && o <= 'z') || ('A' <= o && o <= 'Z'));
	public static final Object NON_IDENTIFICATORE = new Checker((o) ->  
		!('a' <= o && o <= 'z') && 
		!('A' <= o &&	o <= 'Z') && 
		!o.equals('_') && !NUMBER.equals(o)
	);
	public static final Object NOT_EQ = new Checker((o) -> o != '=');
	public static final Object NOT_COM_PAT = new Checker((o) ->  o != '/' && o != '*');
	public static final Object NOT_DIV = new Checker((o) ->  o != '/');
	public static final Object NOT_MULT_DIV = new Checker((o) -> o != '*' && o != '/');
	public static final Object NOT_NEW_LINE = new Checker((o) -> o != '\n');
	public static final Object NOT_MAX = new Checker((c) -> c != '>');
	public static final Object NOT_MAX_EQ = new Checker((c) -> c != '>' && c != '=');
	public static final Object NOT_MIN = new Checker((c) -> c != '<');
}
