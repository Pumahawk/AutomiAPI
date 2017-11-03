package esercizi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

import it.gandinolorenzo.lft.Automa;
import it.gandinolorenzo.lft.Stato;

public class Es1_7 extends BaseEsercizio{

	@Override
	public Automa getAutoma() {

		Stato q0 = new Stato();
		Stato q1 = new Stato();
		Stato q2 = new Stato();
		Stato q3 = new Stato();
		Stato q4 = new Stato();
		Stato q5 = new Stato();

		q0.addTransizione("/", q1);
		
		q1.addTransizione("*", q2);

		q2.addTransizione("a", q3);
		q2.addTransizione("/", q3);
		q2.addTransizione("*", q4);

		q3.addTransizione("a", q3);
		q3.addTransizione("/", q3);
		q3.addTransizione("*", q4);
		

		q4.addTransizione("a", q3);
		q4.addTransizione("/", q5);
		q4.addTransizione("*", q4);
		
		List<Stato> finali = new LinkedList<>();
		finali.add(q5);
		
		Automa automa = new Automa(q0, finali);
		
		return automa;
	}

    public static void main(String[] args) throws FileNotFoundException {
    	new Es1_7().main(new FileInputStream(new File("input-file/es1_7")));
    }

}
