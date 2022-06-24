package it.polito.tdp.genes.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.genes.db.GenesDao;

public class Model {
	private Graph<Genes, DefaultWeightedEdge> grafo;
	private GenesDao dao;
	private Map<String, Genes> idMap;
	private Simulator sim;
	
	public Model() {
		this.dao = new GenesDao();
		this.idMap = new HashMap<>();
		this.dao.getAllGenes(this.idMap);
		this.sim = new Simulator();
	}
	
	public void creaGrafo() {
		// inizializzo la struttura del grafo
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		// aggiungo i vertici
		Graphs.addAllVertices(this.grafo, this.dao.getVertici(idMap));
		
		// aggiungo gli archi
		for(Adiacenza a : this.dao.getAdiacenze()) {
			Genes g1 = this.idMap.get(a.getG1());
			Genes g2 = this.idMap.get(a.getG2());
			
			// calcolo il peso del singolo arco
			double peso = Math.abs(a.getCorrelazione());
			if(g1.getChromosome() == g2.getChromosome()) {
				peso = 2*peso;
			}
			
			Graphs.addEdge(this.grafo, g1, g2, peso);
		}
		
		// console
		System.out.println("# vertici: " + this.grafo.vertexSet().size());
		System.out.println("# archi: " + this.grafo.edgeSet().size());

	}
	
	public List<Vicino> getAdiacenti(Genes gene) {
		List<Vicino> adiacenti = new ArrayList<Vicino>();
		for(DefaultWeightedEdge e : this.grafo.edgesOf(gene)) {
			double peso = this.grafo.getEdgeWeight(e);
			Genes adiacente = Graphs.getOppositeVertex(this.grafo, e, gene);
			adiacenti.add(new Vicino(adiacente, peso));
		}
		Collections.sort(adiacenti);
		return adiacenti;
	}
	
	public int nVertici() {
		return this.grafo.vertexSet().size();
	}
	
	public int nArchi() {
		return this.grafo.edgeSet().size();
	}
	
	public List<Genes> getVertici() {
		return new ArrayList<Genes>(this.grafo.vertexSet());
	}
	
	public boolean isGrafoCreato() {
		return this.grafo!=null;
	}
	
	public void simula(int n, Genes gene) {
		this.sim.init(n, gene);
		this.sim.run();
	}
	
	public List<Studio> getRicerca() {
		
	}
}
