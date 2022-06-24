package it.polito.tdp.genes.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

public class Simulator {
	// dati in ingresso
	private Graph<Genes, DefaultWeightedEdge> grafo;
	private int n;
	private Genes gene;
	
	// dati in uscita
	// valore finale di 'geniInCorsoDiStudio'
	
	// modello del mondo
	List<Studio> ricerca;
	
	// coda degli eventi
	private Queue<Event> queue;
	
	public Simulator() {
		// inizializzo il grafo
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
	}
	
	public void init(int n, Genes gene) {
		// inizializzo i dati in ingresso
		this.n = n;
		this.gene = gene;
			
		// inizializzo il modello del mondo
		this.ricerca = new ArrayList<>();
		this.ricerca.add(new Studio(this.gene, this.n));
		
		// riempio la coda degli eventi
		this.queue = new PriorityQueue<>();
		for(int i=0; i<n; i++) {
			this.queue.add(new Event(1, this.gene));
		}
	}
	
	public void run() {
		while(!this.queue.isEmpty()) {
			
			// estraggo gli eventi dalla coda
			Event e = this.queue.poll();
			
			// processo il singolo evento
			this.processEvent(e);
		}
	}
	
	public void processEvent(Event e) {
		Genes genePrecedente = e.getGene();
		Genes prossimoGene = null;
//		int ingegnere = e.getIngegnere();
		double caso = Math.random();
		
		if(e.getTime() < 36) {	// tre anni			
			if(caso < 0.3) {
				// continuo a studiare lo stesso gene
				prossimoGene = genePrecedente;
			}
			else {
				// passo ad uno dei geni adiacenti
				prossimoGene = this.scegliGene(genePrecedente);
			}
			
			// carico la coda
			this.queue.add(new Event(e.getTime()+1, prossimoGene));
			
			// aggiorno il modello del mondo
			if(!prossimoGene.equals(genePrecedente)) {
				this.ricerca.add(new Studio(prossimoGene, 0));
			}
			
			for(Studio s : this.ricerca) {
				if(s.getGene().equals(genePrecedente)) {
					// studio relativo al gene precedente
					
					// decremento il numero di ingegneri associati al gene precedente
					s.decrementaIngegneri();
				}
				if(s.getGene().equals(prossimoGene)) {
					// studio relativo al prossimo gene

					// incremento il numero di ingegneri associati al prossimo gene
					s.incrementaIngegneri();
				}
			}
		}
		
	}
	
	private Genes scegliGene(Genes precedente) {
		Genes scelto = null;
		double sommaPesi = 0.0;
		List<Vicino> adiacenti = new ArrayList<Vicino>();
		for(DefaultWeightedEdge e : this.grafo.edgesOf(gene)) {
			double peso = this.grafo.getEdgeWeight(e);
			Genes adiacente = Graphs.getOppositeVertex(this.grafo, e, gene);
			adiacenti.add(new Vicino(adiacente, peso));
		}
		Collections.sort(adiacenti);
		double caso = Math.random();
		for(Vicino v : adiacenti) {
			sommaPesi += v.getPeso();
		} 
		double prob = 0.0;
		for(Vicino v : adiacenti) {
			if(caso >= prob && caso < v.getPeso()/sommaPesi) {
				scelto = v.getG();
			}
			prob = v.getPeso()/sommaPesi;
		}
		
		return scelto;
	}
	
	public List<Studio> getRicerca() {
		return this.ricerca;
	}
}
