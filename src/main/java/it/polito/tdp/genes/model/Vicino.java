package it.polito.tdp.genes.model;

public class Vicino implements Comparable<Vicino>{
	Genes g;
	Double peso;
	public Vicino(Genes g, double peso) {
		super();
		this.g = g;
		this.peso = peso;
	}
	public Genes getG() {
		return g;
	}
	public void setG(Genes g) {
		this.g = g;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	@Override
	public String toString() {
		return g + " " + peso;
	}
	@Override
	public int compareTo(Vicino o) {
		// TODO Auto-generated method stub
		return o.peso.compareTo(this.peso);
	}
	
}
