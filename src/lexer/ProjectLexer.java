package lexer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ProjectLexer {
	public static void execute(AbstractLexer lexer)  throws FileNotFoundException {
    	boolean status = true;
    	LexerException error = null;
    	Token c = new Token(0);
    	while(c.tag != Tag.EOF) {
    		try {
    			try {
					c = lexer.nextToken();
				} catch (LexerException e) {
					error = e;
					c = null;
				}
    			if((c == null)) {
    				status = false;
    				break;
    			}
				System.out.println(c);
    			if((c.tag == Tag.EOF))
    				break;
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	if(status) {
        	System.out.println("Terminazione corretta");
    	}
    	else {
        	System.out.println("Terminazione errata");
        	error.printLexerError();
    	}
		
	}
	public static void main(String[] args) throws FileNotFoundException {
		execute(new UniLexer(new BufferedReader(new FileReader(new File("input-file/lexer-test.pers")))));
    }
}
