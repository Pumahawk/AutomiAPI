package esercizi;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import it.gandinolorenzo.lft.Automa;

public abstract class BaseEsercizio {
	
	private class ErrorBox{
		int riga;
		String stringa;
		public ErrorBox(int riga, String stringa) {
			super();
			this.riga = riga;
			this.stringa = stringa;
		}
	}
	public abstract Automa getAutoma();
	
	public void main(){
		main("\n");
	}
	public void main(String delimeter){

        Automa automa = this.getAutoma();
        String s;
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter(delimeter);
        List<ErrorBox> errorList = new LinkedList<ErrorBox>();
    	boolean check;
        for(int i = 0; sc.hasNext(); i++){
        	check = true;
        	s = sc.next();
        	System.out.print(i + " Stringa: " + s + " Verificata: ");
        	if(!(check = automa.match(Arrays.asList(s.split("")))))
        		errorList.add(new ErrorBox(i, s));
        			
            System.out.println(check);
        }
        sc.close();
        
        if(!errorList.isEmpty()){
        	System.out.println("Numero errori: " + errorList.size());
        	int i = 0;
        	for(ErrorBox er : errorList)
        		System.out.println((++i)+" Riga: "+ er.riga +" Stringa: " + er.stringa);
        }
	}
}
