package esercizi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

import it.gandinolorenzo.lft.Automa;
import it.gandinolorenzo.lft.Stato;

/*
 * AUtoma che rappresenta le stringhe che finiscono 
 * con 01 oppure iniziano con 01.
 * */
public class Es1_1 extends BaseEsercizio{

	@Override
	public Automa getAutoma() {

        Stato q0 = new Stato();
        Stato q1 = new Stato();
        Stato q2 = new Stato();
        Stato q3 = new Stato();
        Stato q4 = new Stato();
        Stato q5 = new Stato();

        q0.addTransizione("0", q1);
        q0.addTransizione("1", q2);

        q1.addTransizione("0", q4);
        q1.addTransizione("1", q3);
        
        q2.addTransizione("0", q4);
        q2.addTransizione("1", q2);

        q3.addTransizione("0", q3);
        q3.addTransizione("1", q3);

        q4.addTransizione("0", q4);
        q4.addTransizione("1", q5);

        q5.addTransizione("0", q4);
        q5.addTransizione("1", q2);
        
        
        List<Stato> finali = new LinkedList<>();

        finali.add(q3);
        finali.add(q5);
        
        Automa automa = new Automa(q0, finali);
        return automa;
	}
    
    public static void main(String[] args) throws FileNotFoundException {
    	new Es1_1().main(new FileInputStream(new File("input-file/es1_1")));
    }

}
