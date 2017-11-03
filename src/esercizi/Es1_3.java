package esercizi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

import it.gandinolorenzo.lft.Automa;
import it.gandinolorenzo.lft.Stato;

/*
 * 
 * */
public class Es1_3 extends BaseEsercizio{

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

        q0.addTransizione(P, q1);
        q0.addTransizione(D, q2);

        q1.addTransizione(P, q1);
        q1.addTransizione(D, q2);
        q1.addTransizione(AK, q3);

        q2.addTransizione(D, q2);
        q2.addTransizione(P, q1);
        q2.addTransizione(LZ, q3);
        
        q3.addTransizione(L, q3);
        
        List<Stato> finali = new LinkedList<>();

        finali.add(q3);
        
        Automa automa = new Automa(q0, finali);
        return automa;
	}
    
    public static void main(String[] args) throws FileNotFoundException {
    	new Es1_3().main(new FileInputStream(new File("input-file/es1_3")));
    }

}
