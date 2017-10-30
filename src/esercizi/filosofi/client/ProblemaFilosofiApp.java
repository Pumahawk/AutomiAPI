package esercizi.filosofi.client;

public class ProblemaFilosofiApp {

	public static void main(String[] args) {
		int numeroFilosofi = 10;
		int time = 10000;

		Filosofo[] filosofi = new Filosofo[numeroFilosofi];
		Chopstick[] chopsticks = new Chopstick[numeroFilosofi];

		for(int i = 0; i < chopsticks.length; i++) {
			chopsticks[i] = new Chopstick();
		}

		filosofi[0] = new Filosofo("0", time, time, chopsticks[0], chopsticks[numeroFilosofi-1]);
		for(int i = 1; i < filosofi.length; i++) {
			filosofi[i] = new Filosofo((i + ""),time, time, chopsticks[i-1], chopsticks[i]);
		}
		
		for(Filosofo f : filosofi)
			f.start();
		
	}

}
