package lexer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Es2_3 {
	public static void main(String[] args) throws FileNotFoundException {
    	BufferedReader in = new BufferedReader(new FileReader(new File("input-file/esp2_3")));
    	
    	UniLexer lexer = new UniLexer(in);
    	
    	ProjectLexer.execute(lexer);
    }
}
