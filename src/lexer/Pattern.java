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
				return false;
			return (!NUMBER.equals(o));
		}
	};
}
