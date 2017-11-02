package lexer;

public class Pattern {
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
			if(!o.getClass().isInstance(new Character('.')))
				return true;
			return (!NUMBER.equals(o));
		}
	};
	public static final Object LETTER = new Object() {
		@Override
		public boolean equals(Object o) {
			char c = '.';
			if(o.getClass().isInstance(new Character('.')))
				c = (char) o;
			else
				return false;
			
			return ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z');
		}
	};
	public static final Object NON_IDENTIFICATORE = new Object() {
		@Override
		public boolean equals(Object o) {
			char c = '.';
			if(o.getClass().isInstance(new Character('.')))
				c = (char) o;
			else
				return false;
			
			return !('a' <= c && c <= 'z') && !('A' <= c && c <= 'Z') && !o.equals('_') && !LETTER.equals(o);
		}
	};
	public static final Object NOT_EQ = new Object() {
		@Override
		public boolean equals(Object o) {
			char c = '.';
			if(o.getClass().isInstance(new Character('.')))
				c = (char) o;
			else
				return false;
			
			return c != '=';
		}
	};
	public static final Object NOT_COM_PAT = new Object() {
		@Override
		public boolean equals(Object o) {
			char c = '.';
			if(o.getClass().isInstance(new Character('.')))
				c = (char) o;
			else
				return false;
			
			return c != '/' && c != '*';
		}
	};
	public static final Object NOT_DIV = new Object() {
		@Override
		public boolean equals(Object o) {
			char c = '.';
			if(o.getClass().isInstance(new Character('.')))
				c = (char) o;
			else
				return false;
			
			return c != '/';
		}
	};
	public static final Object NOT_MULT_DIV = new Object() {
		@Override
		public boolean equals(Object o) {
			char c = '.';
			if(o.getClass().isInstance(new Character('.')))
				c = (char) o;
			else
				return false;
			
			return c != '*' && c != '/';
		}
	};
	public static final Object NOT_NEW_LINE = new Object() {
		@Override
		public boolean equals(Object o) {
			char c = '.';
			if(o.getClass().isInstance(new Character('.')))
				c = (char) o;
			else
				return false;
			
			return c != '\n';
		}
	};
}
