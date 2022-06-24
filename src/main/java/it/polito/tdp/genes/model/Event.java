package it.polito.tdp.genes.model;

public class Event implements Comparable<Event>{
	
	private int time;
//	private int ingegnere;
	private Genes gene;
	

	public Event(int time, Genes gene) {
		super();
		this.time = time;
//		this.ingegnere = ingegnere;
		this.gene = gene;
	}


	public int getTime() {
		return time;
	}


	public void setTime(int time) {
		this.time = time;
	}


	/*public int getIngegnere() {
		return ingegnere;
	}


	public void setIngegnere(int ingegnere) {
		this.ingegnere = ingegnere;
	}*/


	public Genes getGene() {
		return gene;
	}


	public void setGene(Genes gene) {
		this.gene = gene;
	}


	@Override
	public int compareTo(Event o) {
		// TODO Auto-generated method stub
		return this.time-o.time;
	};
}
