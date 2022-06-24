package it.polito.tdp.genes.model;

public class Studio {
	private Genes gene;
	private int nIngegneri;
	public Studio(Genes gene, int nIngegneri) {
		super();
		this.gene = gene;
		this.nIngegneri = nIngegneri;
	}
	public Genes getGene() {
		return gene;
	}
	public int getnIngegneri() {
		return nIngegneri;
	}
	public void incrementaIngegneri() {
		this.nIngegneri++;
	}
	public void decrementaIngegneri() {
		this.nIngegneri--;
	}
	@Override
	public String toString() {
		return "gene: " + gene + ", " + nIngegneri + " ingegneri";
	}
}
