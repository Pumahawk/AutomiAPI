package it.gandinolorenzo.lft;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import it.gandinolorenzo.lft.Stato.Pair;

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
		this(iniziale, new HashSet<>());
		this.finali.addAll(finali);
		linkStati();
	}
	
	public <T> List<Stato> process(List<Stato> stati, List<T> stringa){
		List<Stato> statiIntermedi = new LinkedList<>();
		statiIntermedi.addAll(stati);
		List<Stato> transazioni = new LinkedList<>();
		for(T elem : stringa){
			while(!statiIntermedi.isEmpty()){
				Stato p = statiIntermedi.remove(0);
				transazioni.addAll(p.shift(elem));
			}
			statiIntermedi.addAll(transazioni);
			transazioni.clear();
		}
		
		return statiIntermedi;
	}
	
	public <T> boolean match(List<T> stringa) {
		List<Stato> statiIntermedi = new LinkedList<>();
		statiIntermedi.add(this.iniziale);
		statiIntermedi = process(statiIntermedi, stringa);
		for (Stato p : statiIntermedi) {
			for (Stato p2 : p.eClose()) {
				if (finali.contains(p2)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void printInfo() {
		Set<Stato> stati = new HashSet<>();
		List<Stato> daControllare = new LinkedList<>();
		Stato p;

		daControllare.add(this.iniziale);
		stati.add(this.iniziale);
		while(!daControllare.isEmpty()) {
			p = daControllare.remove(0);
			System.out.println("Stato: " + p.name);
			for(Pair<?, Stato> s : p.transizioni) {
				System.out.println("Transizione: " +s.key + " Nodo: " + s.value.name);
				if(stati.add(s.value)) {
					daControllare.add(s.value);
				}
			}
		}
	}
	
}
