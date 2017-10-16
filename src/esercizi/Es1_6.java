package esercizi;

import java.util.LinkedList;
import java.util.List;

import it.gandinolorenzo.lft.Automa;
import it.gandinolorenzo.lft.Stato;

/*
 * 
 * */
public class Es1_6 extends BaseEsercizio{

	@Override
	public Automa getAutoma() {

        Stato q0 = new Stato();
        Stato q1 = new Stato();
        Stato q2 = new Stato();

        q0.addTransizione("0", q0);
        q0.addTransizione("1", q1);

        q1.addTransizione("0", q2);
        q1.addTransizione("1", q0);

        q2.addTransizione("0", q1);
        q2.addTransizione("1", q2);
        
        List<Stato> finali = new LinkedList<>();

        finali.add(q0);
        
        Automa automa = new Automa(q0, finali);
        return automa;
	}
    
    public static void main(String[] args) {
    	new Es1_6().main();
    }

}
