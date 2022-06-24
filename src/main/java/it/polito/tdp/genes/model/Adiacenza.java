package it.polito.tdp.genes.model;

public class Adiacenza {
	private String g1;
	private String g2;
	private Double correlazione;
	public Adiacenza(String g1, String g2, Double correlazione) {
		super();
		this.g1 = g1;
		this.g2 = g2;
		this.correlazione = correlazione;
	}
	public String getG1() {
		return g1;
	}
	public void setG1(String g1) {
		this.g1 = g1;
	}
	public String getG2() {
		return g2;
	}
	public void setG2(String g2) {
		this.g2 = g2;
	}
	public Double getCorrelazione() {
		return correlazione;
	}
	public void setCorrelazione(Double correlazione) {
		this.correlazione = correlazione;
	}
	@Override
	public String toString() {
		return "Adiacenza [g1=" + g1 + ", g2=" + g2 + ", correlazione=" + correlazione + "]";
	}
	
}
