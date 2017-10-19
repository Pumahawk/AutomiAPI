package it.gandinolorenzo.lft;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Automa {
	private Stato iniziale;
	private Set<Stato> finali;
	protected Set<Object> alfabeto;
	
	private void linkStati(){
		Queue<Stato> collegati, daCollegare;
		collegati = new LinkedList<>();
		daCollegare = new LinkedList<>();
		daCollegare.add(iniziale);
		
		Stato p;
		while(!daCollegare.isEmpty()) {
			p = daCollegare.remove();
			p.automa = this;
			collegati.add(p);
			for(Stato.Pair<?, Stato> s : p.transizioni) {
				if(!collegati.contains(s.value) && !daCollegare.contains(s.value))
					daCollegare.add(s.value);
				if(!alfabeto.contains(s.key))
					alfabeto.add(s.key);
			}
		}
	}

	public Automa(Stato iniziale, Set<Stato> finali) {
		super();
		this.iniziale = iniziale;
		this.finali = finali;
		this.alfabeto = new HashSet<>();
		linkStati();
	}
	public Automa(Stato iniziale, List<Stato> finali) {
		Set<Stato> p = new HashSet<>();
		p.addAll(finali);
	}
	
	public <T> boolean match(List<T> stringa){
		List<Stato> statiIntermedi = new LinkedList<>();
		statiIntermedi.add(this.iniziale);
		List<Stato> transazioni = new LinkedList<>();
		for(T elem : stringa){
			while(!statiIntermedi.isEmpty()){
				Stato p = statiIntermedi.remove(0);
				transazioni.addAll(p.shift(elem));
			}
			statiIntermedi.addAll(transazioni);
			transazioni.clear();
		}

		for (Stato p : statiIntermedi) {
			for (Stato p2 : p.eClose()) {
				if (finali.contains(p2)) {
					return true;
				}
			}
		}
		return false;
	}
	
}
