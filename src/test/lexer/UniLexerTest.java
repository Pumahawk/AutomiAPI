package test.lexer;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import org.junit.jupiter.api.Test;

import lexer.Lexer;
import lexer.NumberTok;
import lexer.Tag;
import lexer.Token;
import lexer.UniLexer;
import lexer.Word;

public class UniLexerTest {
	
	public static UniLexer generateUniLexer(String s) {
		BufferedReader in = new BufferedReader(new StringReader(s));
		return  new UniLexer(in);
	}
	
	@Test
	public void rapidTest() throws IOException {
		String s = "ciao 120 ";
		BufferedReader in = new BufferedReader(new StringReader(s));
		Lexer lexer = new UniLexer(in);
		Token t = lexer.nextToken();
		assertTrue("Errore lettura primo token",((Word)t).tag == 257 && ((Word)t).lexeme.equals("ciao"));
		t = lexer.nextToken();
		assertTrue("Errore lettura secondo token",((NumberTok)t).tag == 256 && ((NumberTok)t).value == 120);
		t = lexer.nextToken();
		assertTrue("Errore lettura token EOF",t.tag == Tag.EOF);
			
	}
}
