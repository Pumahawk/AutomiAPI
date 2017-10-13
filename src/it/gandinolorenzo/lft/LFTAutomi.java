package it.gandinolorenzo.lft;

public class LFTAutomi {
    public static void main(String args[]){
    	Stato a = new Stato();
    	Stato b = new Stato();
    	Stato c = new Stato();
    	Stato d = new Stato();

    	a.addTransizione(Stato.Costanti.Epsilon, b);
    	b.addTransizione(Stato.Costanti.Epsilon, c);
    	c.addTransizione(Stato.Costanti.Epsilon, b);
    	a.addTransizione("a", d);
    	c.addTransizione("b", d);
    	
    	Object list = a.eClose().toArray();
    	
    	return;
    	
    }
    
}