package esercizi;

import java.util.LinkedList;
import java.util.List;

import it.gandinolorenzo.lft.Automa;
import it.gandinolorenzo.lft.Stato;

/*
 * Automa che riconosce un identificatore java: Identificatore è una 
 * sequenza non vuota di lettere e numeri ed il simbolo di _ che non
 * incomincia con un numero e che non può essere composto solo dal
 *  simbolo _.
 * */
public class Es1_2 extends BaseEsercizio{

	@Override
	public Automa getAutoma() {

		final Object lettera = new Object(){
			public boolean equals(Object oggetto){
				if(oggetto.getClass().isInstance(new String())){
					String str = (String) oggetto;
					return (str.length() == 1) ? ('a' <= str.charAt(0) && str.charAt(0) <= 'z') || (('A' <= str.charAt(0) && str.charAt(0) <= 'Z')) : false;
				}
				return false;
			}
		};
		final Object numero = new Object(){
			public boolean equals(Object oggetto){
				if(oggetto.getClass().isInstance(new String())){
					String str = (String) oggetto;
					return (str.length() == 1) ? ('0' <= str.charAt(0) && str.charAt(0) <= '9') : false;
				}
				return false;
			}
		};
		
        Stato q0 = new Stato();
        Stato q1 = new Stato();
        Stato q2 = new Stato();

        q0.addTransizione("_", q1);
        q0.addTransizione(lettera, q2);

        q1.addTransizione("_", q1);
        q1.addTransizione(lettera, q2);
        q1.addTransizione(numero, q2);

        q2.addTransizione("_", q2);
        q2.addTransizione(lettera, q2);
        q2.addTransizione(numero, q2);
        
        List<Stato> finali = new LinkedList<>();

        finali.add(q2);
        
        Automa automa = new Automa(q0, finali);
        return automa;
	}
    
    public static void main(String[] args) {
    	new Es1_2().main();
    }

}
