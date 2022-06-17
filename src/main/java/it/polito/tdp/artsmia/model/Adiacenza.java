package it.polito.tdp.artsmia.model;

public class Adiacenza implements Comparable<Adiacenza>{
	int art1;
	int art2;
	double peso;
	
	
	public Adiacenza(int art1, int art2, double peso) {
		this.art1 = art1;
		this.art2 = art2;
		this.peso = peso;
	}


	@Override
	public int compareTo(Adiacenza o) {
		// TODO Auto-generated method stub
		return (int)(this.peso-o.peso);
	}


	@Override
	public String toString() {
		return "Adiacenza [art1=" + art1 + ", art2=" + art2 + ", peso=" + peso + "]";
	}



	
	

}
