package esercizi;

import java.util.LinkedList;
import java.util.List;

import it.gandinolorenzo.lft.Automa;
import it.gandinolorenzo.lft.Stato;

/*
 * Automa che riconosce il linguaggio formato da 0 e 1 che 
 * non contengono 3 zeri consecutivi.
 * */
public class Es1_1_2 extends BaseEsercizio{

	@Override
	public Automa getAutoma() {

        Stato q0 = new Stato();
        Stato q1 = new Stato();
        Stato q2 = new Stato();

        q0.addTransizione("1", q0);
        q0.addTransizione("0", q1);

        q1.addTransizione("1", q0);
        q1.addTransizione("0", q2);

        q2.addTransizione("1", q1);
        
        List<Stato> finali = new LinkedList<>();

        finali.add(q0);
        finali.add(q1);
        finali.add(q2);
        
        Automa automa = new Automa(q0, finali);
        return automa;
	}
    
    public static void main(String[] args) {
    	new Es1_1_2().main();
    }

}
