package esercizi.filosofi.client;

public class Filosofo extends Thread{
	private String name;
	private Chopstick sinistra;
	private Chopstick destra;
	private int timeThink;
	private int timeEat;

	public void setTimeThink(int time) {
		this.timeThink = time;
	}
	public void setTimeEat(int time) {
		this.timeEat = time;
	}
	
	public int getTimeThink() {
		return timeThink;
	}
	public int getTimeEat() {
		return timeEat;
	}
	public Chopstick getSinistra() {
		return sinistra;
	}

	public void setSinistra(Chopstick sinistra) {
		this.sinistra = sinistra;
	}

	public Chopstick getDestra() {
		return destra;
	}

	public void setDestra(Chopstick destra) {
		this.destra = destra;
	}

	public Filosofo(String nome,int timeEat, int timeThink, Chopstick sinistra, Chopstick destra) {
		this.setSinistra(sinistra);
		this.setDestra(destra);
		this.timeEat = timeEat;
		this.name = nome;
		this.timeThink = timeThink;
	}
	
	public Filosofo(String nome, Chopstick sinistra, Chopstick destra) {
		this(nome, 1000, 1000, sinistra, destra);
	}
	public Filosofo(Chopstick sinistra, Chopstick destra) {
		this("", sinistra, destra);
	}
	
	public void toEat() {
		System.out.println("Filosofo: " + name + " Sta andando a mangiare.");
		synchronized(this.sinistra) {
			System.out.println("Filosofo: " + name + " Occupa posata sinistra.");
			synchronized(this.destra) {
				System.out.println("Filosofo: " + name + " Occupa posata destra.");
				System.out.println(">>>Filosofo: " + name + " Inizia a mangiare.");
				try {
					sleep(timeEat);
					System.out.println("<<<Filosofo: " + name + "  smette di mangiare");
				} catch (InterruptedException e) {
					e.printStackTrace();
					System.exit(-1);
				}
			}
		}
	}
	
	public void toThink() {
		System.out.println("Filosofo: " + name + " Sta pensando.");
		try {
			sleep(timeThink);
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	@Override
	public void run() {
		while(true) {
			toThink();
			toEat();
		}
	}
	
}
