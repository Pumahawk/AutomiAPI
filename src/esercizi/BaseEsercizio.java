package esercizi;

import java.util.Arrays;
import java.util.Scanner;

import it.gandinolorenzo.lft.Automa;

public abstract class BaseEsercizio {
	public abstract Automa getAutoma();
	public void main(){

        Automa automa = this.getAutoma();
        
        Scanner sc = new Scanner(System.in);
        boolean allOk = true;
        
        String s;
        while(sc.hasNext()){
        	s = sc.next();
        	System.out.print("Stringa: " + s + " Verificata: ");
            System.out.println(automa.match(Arrays.asList(s.split(""))));
        }
        sc.close();
	}
}
