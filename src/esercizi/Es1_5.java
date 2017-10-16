package esercizi;

import java.util.LinkedList;
import java.util.List;

import it.gandinolorenzo.lft.Automa;
import it.gandinolorenzo.lft.Stato;

/*
 * 
 * */
public class Es1_5 extends BaseEsercizio{

	@Override
	public Automa getAutoma() {

		final Object P = new Object(){
			public boolean equals(Object oggetto){
				if(oggetto.getClass().isInstance(new String())){
					String str = (String) oggetto;
					return (str.length() == 1) ? (
								str.equals("0") ||
								str.equals("2") ||
								str.equals("4") ||
								str.equals("6") ||
								str.equals("8")
							) : false;
				}
				return false;
			}
		};
		final Object D = new Object(){
			public boolean equals(Object oggetto){
				if(oggetto.getClass().isInstance(new String())){
					String str = (String) oggetto;
					return (str.length() == 1) ? (
								str.equals("1") ||
								str.equals("3") ||
								str.equals("5") ||
								str.equals("7") ||
								str.equals("9")
							) : false;
				}
				return false;
			}
		};
		final Object AK = new Object(){
			public boolean equals(Object oggetto){
				if(oggetto.getClass().isInstance(new String())){
					String str = (String) oggetto;
					return (str.length() == 1) ? ('A' <= str.charAt(0) && str.charAt(0) <= 'K') : false;
				}
				return false;
			}
		};
		final Object LZ = new Object(){
			public boolean equals(Object oggetto){
				if(oggetto.getClass().isInstance(new String())){
					String str = (String) oggetto;
					return (str.length() == 1) ? ('L' <= str.charAt(0) && str.charAt(0) <= 'Z') : false;
				}
				return false;
			}
		};
		
		final Object L = new Object(){
			public boolean equals(Object oggetto){
				if(oggetto.getClass().isInstance(new String())){
					String str = (String) oggetto;
					return (str.length() == 1) ? ('a' <= str.charAt(0) && str.charAt(0) <= 'z') || (('A' <= str.charAt(0) && str.charAt(0) <= 'Z')) : false;
				}
				return false;
			}
		};
		
        Stato q0 = new Stato();
        Stato q1 = new Stato();
        Stato q2 = new Stato();
        Stato q3 = new Stato();
        Stato q4 = new Stato();
        Stato q5 = new Stato();
        Stato q6 = new Stato();

        q0.addTransizione(AK, q1);
        q0.addTransizione(LZ, q2);

        q1.addTransizione(L, q1);
        q1.addTransizione(P, q3);
        q1.addTransizione(D, q4);

        q2.addTransizione(L, q2);
        q2.addTransizione(P, q5);
        q2.addTransizione(D, q6);

        q3.addTransizione(P, q3);
        q3.addTransizione(D, q4);

        q4.addTransizione(P, q3);
        q4.addTransizione(D, q4);

        q5.addTransizione(P, q5);
        q5.addTransizione(D, q6);

        q6.addTransizione(P, q5);
        q6.addTransizione(D, q6);
        
        List<Stato> finali = new LinkedList<>();

        finali.add(q3);
        finali.add(q6);
        
        Automa automa = new Automa(q0, finali);
        return automa;
	}
    
    public static void main(String[] args) {
    	new Es1_5().main();
    }

}
