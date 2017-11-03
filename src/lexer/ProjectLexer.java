package lexer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ProjectLexer {
	public static void execute(Lexer lexer)  throws FileNotFoundException {
    	boolean status = true;
    	Token c = new Token(0);
    	while(c.tag != Tag.EOF) {
    		try {
    			c = lexer.nextToken();
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
    	else

        	System.out.println("Terminazione errata");
		
	}
	public static void main(String[] args) throws FileNotFoundException {
		execute(new UniLexer(new BufferedReader(new FileReader(new File("input-file/lexer-test.pers")))));
    }
}
