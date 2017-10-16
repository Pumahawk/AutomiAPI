package it.gandinolorenzo.lft;

import java.util.LinkedList;
import java.util.List;

public class Automa {
	private Stato iniziale;
	private List<Stato> finali;

	public Automa(Stato iniziale, List<Stato> finali) {
		super();
		this.iniziale = iniziale;
		this.finali = finali;
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
