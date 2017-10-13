package esercizi.personali;

import java.util.LinkedList;
import java.util.List;

import esercizi.BaseEsercizio;
import it.gandinolorenzo.lft.Automa;
import it.gandinolorenzo.lft.Stato;

public class Esp_1 extends BaseEsercizio{

	@Override
	public Automa getAutoma() {

		Stato q1 = new Stato("q1");
		Stato q2 = new Stato("q2");
		Stato q3 = new Stato("q3");
		Stato q4 = new Stato("q4");
		Stato q5 = new Stato("q5");
		Stato q6 = new Stato("q6");

		q1.addTransizione(Stato.Costanti.Epsilon, q2);
		q1.addTransizione(Stato.Costanti.Epsilon, q4);
		

		q2.addTransizione("0", q2);
		q2.addTransizione("1", q2);
		q2.addTransizione("2", q2);
		q2.addTransizione("1", q3);
		

		q3.addTransizione(Stato.Costanti.Epsilon, q6);
		

		q4.addTransizione("0", q4);
		q4.addTransizione("1", q4);
		q4.addTransizione("2", q4);
		q4.addTransizione("0", q5);
		

		q5.addTransizione(Stato.Costanti.Epsilon, q6);
		
		List<Stato> finali = new LinkedList<>();
		finali.add(q6);
		
		Automa automa = new Automa(q1, finali);
		
		return automa;
	}

    public static void main(String[] args) {
    	new Esp_1().main();
    }

}
