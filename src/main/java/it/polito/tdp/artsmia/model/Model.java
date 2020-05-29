package it.polito.tdp.artsmia.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.artsmia.db.ArtsmiaDAO;

public class Model {

	//Integer per gli artisti perchè utilizzo soo gli id
	private Graph<Integer, DefaultWeightedEdge> graph;
	private List<Adiacenza> adiacenze;
	private ArtsmiaDAO dao;
	private List<Integer> bestPath;
	
	public Model() {
		this.dao = new ArtsmiaDAO();
	}
	
	
	public List<String> getRuoli(){
		return this.dao.getRuoli();
	}
	
	
	public void creaGrafo(String ruolo) {
		this.graph = new SimpleWeightedGraph<Integer,DefaultWeightedEdge>(DefaultWeightedEdge.class);
		this.adiacenze = this.dao.getAdiacenze(ruolo);
		// se non richiesta la stampa dei vertici non c'è bisogno di aggiungere tutti i vertici
		// se richiesta conviene inserire tutti i vertici e poi basarsi sulle adiacenze
		//non richiesta la stampa
		Graphs.addAllVertices(this.graph, this.dao.getArtisti(ruolo));
		//creo il grafo
		for(Adiacenza a : this.adiacenze) {
			/*if(!this.graph.containsVertex(a.getA1()))
				this.graph.addVertex(a.getA1());
			if(!this.graph.containsVertex(a.getA2()))
				this.graph.addVertex(a.getA2());*/
			if(this.graph.getEdge(a.getA1(), a.getA2())==null)
				Graphs.addEdgeWithVertices(this.graph, a.getA1(), a.getA2(),a.getPeso());			
		}
		System.out.println("Numero vertici: "+this.graph.vertexSet().size());
		System.out.println("Numero archi: "+this.graph.edgeSet().size());
	}
	
	public Integer nVertici() {
		return this.graph.vertexSet().size();
	}
	public Integer nArchi() {
		return this.graph.edgeSet().size();
	}
	public List<Adiacenza> getAdiacenze(){
		return this.adiacenze;
	}
	
	public List<Integer> trovaPercorso(Integer sorgente){
		this.bestPath = new ArrayList<>();
		List<Integer> parziale = new ArrayList<>();
		parziale.add(sorgente);
		ricorsione(parziale, -1);
		return bestPath;
	}


	private void ricorsione(List<Integer> parziale, int peso) {
		
		if(parziale.size()>this.bestPath.size()) {
			this.bestPath = new ArrayList<>(parziale);
		}
		
		
		Integer last = parziale.get(parziale.size()-1);
		//Prendo tutti i vicini
		List<Integer> vicini = Graphs.neighborListOf(this.graph, last);
		for(Integer vicino : vicini) {
			//fisso il peso al primo passo della ricorsione
			if(!parziale.contains(vicino) && peso == -1) {
				parziale.add(vicino);
				ricorsione(parziale,(int)this.graph.getEdgeWeight(this.graph.getEdge(last, vicino)) );
				parziale.remove(parziale.size()-1);
			}else {
				if(!parziale.contains(vicino) && this.graph.getEdgeWeight(this.graph.getEdge(last, vicino))==peso) {
					parziale.add(vicino);
					ricorsione(parziale,peso);
					parziale.remove(parziale.size()-1);
				}
			}
		}
		
	}
	
	public boolean grafoContiene(Integer id) {
		return this.graph.containsVertex(id);
	}
}
