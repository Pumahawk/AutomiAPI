package lexer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

public class CommandLineLexer {

	/*
	 * To write data in stdin open terminal e write "cat > path/to/fileFilIn"
	 */
	
	static private String fifoFileIn = "input-file/fifo-in";

	public static void main(String[] args) throws IOException {
		PipedReader inP = new PipedReader();
		PipedWriter outP = new PipedWriter(inP);
		new Thread(() -> {
			try {
				ProjectLexer.execute(
						new UniLexer(new BufferedReader(inP)));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		).start();
		
		BufferedReader in = new BufferedReader(new FileReader(new File(fifoFileIn)));

		System.out.println("Start command line version:");
		for(int s = 0; s > -1;) {
			s = in.read();
			outP.write(s);
			outP.flush();
		}
		outP.close();
		in.close();
	}
}
