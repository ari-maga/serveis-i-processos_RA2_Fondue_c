package cat.almata.dam.amarin;

public class Persona implements Runnable{
	
	private int tempsCoccio;
	private GestorForquilles gestorDeForquillesActiu;
	private GestorForquilles gestorDeForquillesInnactiu;
	private int numPers;
	private int nConsumicio;
	private String tipusFoundie;
	
	public Persona(int tempsCarn, GestorForquilles gfActiu,GestorForquilles gfInnactiu,int num,String tipusFoundie) {
		this.tempsCoccio = tempsCarn;
		this.gestorDeForquillesActiu=gfActiu;
		this.gestorDeForquillesInnactiu=gfInnactiu;
		this.numPers=num;
		this.tipusFoundie = tipusFoundie;
		nConsumicio = 0;
	}
	
	
	/*
	//Getters i seters d'alguns atributs per si de cas els faig servir
	public int getTempsCoccio() {
		return tempsCoccio;
	}
	public void setTempsCoccio(int tempsCoccio) {
		this.tempsCoccio = tempsCoccio;
	}
	public GestorForquilles getForquilla() {
		return forquilla;
	}
	public void setForquilla(GestorForquilles forquilla) {
		this.forquilla = forquilla;
	}
	public String getTipusFoundie() {
		return tipusFoundie;
	}
	public void setTipusFoundie(String tipusFoundie) {
		this.tipusFoundie = tipusFoundie;
	}
	public int getNumPers() {
		return numPers;
	}

	*/


	@Override
	public void run() {
		while(true){
			while(!gestorDeForquillesActiu.agafaForquilla(numPers))canviaDeFoundie();
			System.out.println("Persona "+numPers+" agafa una forquilla de la foundie de "+tipusFoundie);
			cuina();
			gestorDeForquillesActiu.tornaForquilla(numPers);
			System.out.println("Persona "+numPers +" torna la forquilla de la foundie de "+tipusFoundie);
			menja();
		}
		
	}
	private void canviaDeFoundie() {
		GestorForquilles gfTemp;
		gfTemp = gestorDeForquillesActiu;
		gestorDeForquillesActiu=gestorDeForquillesInnactiu;
		gestorDeForquillesInnactiu = gfTemp;
		if(tipusFoundie.equals("carn")) {
			tipusFoundie = "formatge";
		}else {
			tipusFoundie = "carn";
		}
	}



	private void cuina() {
		try {
			Thread.sleep(tempsCoccio*1000);
			if(tipusFoundie.equals("carn"))System.out.println("Persona "+numPers+" ha cuinat la carn i ha tardat "+tempsCoccio+" segons.");
			else System.out.println("Persona "+numPers+" ha sucat al formatge i ha tardat "+tempsCoccio+" segons.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	private void menja() {
		nConsumicio++;
		try {
			Thread.sleep(2000);
			System.out.println("Persona "+numPers+" de la foundie de "+tipusFoundie+" ha acabat de menjar la consumició nº "+nConsumicio);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	

}
