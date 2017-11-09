package it.gandinolorenzo.lft;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class Stato{
    
	public static enum Costanti{
		Epsilon
	}
	
	public static class Pair<T,D>{

		protected T key;
		protected D value;
		protected Consumer<Object> consumer;

		public Pair(T key, D value){
			this.key = key;
			this.value = value;
		}
		public Pair(T key, D value, Consumer<Object> consumer){
			this(key, value);
			this.consumer = consumer;
		}
		
		public T getKey(){
			return this.key;
		}
		
		public D getValue(){
			return this.value;
		}
	}
	
    public String name;
    public List<Pair<?, Stato>> transizioni;
    protected Automa automa;

    public Stato(){
    	this("");
    }
    
    public Stato(String name){
        this.name = name;
        this.transizioni = new LinkedList<>();
    }

    public <T> boolean addTransizione(T transazione, Stato stato){
        transizioni.add(new Pair<>(transazione, stato));
        return true;
    }
    public <T> boolean addTransizione(T transazione, Stato stato, Consumer<Object> consumer){
        transizioni.add(new Pair<>(transazione, stato, consumer));
        return true;
    }
    
    public <T> List<Stato> shift(T elemento){

		List<Stato> eclose;
    	if(!Costanti.Epsilon.equals(elemento))
    		eclose = this.eClose();
    	else{
    		eclose = new LinkedList<>();
    		eclose.add(this);
    	}
        List<Stato> findState = new LinkedList<>();
        
        for(Stato p : eclose)
	        for(Pair<?, Stato> tr : p.transizioni){
	            if(tr.getKey().equals(elemento) && !findState.contains(tr.getValue())){
	                findState.add(tr.getValue());
	                if(tr.consumer != null && elemento != Costanti.Epsilon)
	                	tr.consumer.accept(elemento);
	            }
	        }
        return findState;
        
    } 
    
    public List<Stato> eClose(){
    	List<Stato> insieme = new LinkedList<>();
    	List<Stato> daControllare = new LinkedList<>();
    	List<Stato> sigmaE = new LinkedList<>();
    	
    	daControllare.add(this);
    	while(!daControllare.isEmpty()){
    		Stato p = daControllare.remove(0);
        	insieme.add(p);
    		sigmaE = p.shift(Costanti.Epsilon);
        	while(!sigmaE.isEmpty()){
        		Stato p2 = sigmaE.remove(0);
        		if(!insieme.contains(p2))
        			daControllare.add(p2);
        	}
    		
    	}
    	
    	
    	
    	return insieme;
    	
    }
}
