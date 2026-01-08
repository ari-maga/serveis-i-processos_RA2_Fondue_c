
package cat.almata.dam.amarin;

public class GestorForquilles {
	
	private int forquillesTotal;
	private int forquillesOcupades;
	
	public GestorForquilles(int forquillesTotal) {
		this.forquillesTotal=forquillesTotal;
		forquillesOcupades = 0;
	}
	
	public synchronized boolean agafaForquilla(int numPer) {
			
			if(forquillesOcupades>=forquillesTotal)return false;  // no facis cas wait();
			forquillesOcupades++;
			notifyAll();
			/* catch (InterruptedException e) {
				System.out.println("Hi ha hagut un error inesperat i s'ha interromput el thread de la persona "+numPer);
			}*/
			return true;
	}
	public synchronized void tornaForquilla(int numPer) {
		forquillesOcupades--;
		notifyAll();
}

}
