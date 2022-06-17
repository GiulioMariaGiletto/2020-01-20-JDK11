package it.polito.tdp.artsmia.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.artsmia.db.ArtsmiaDAO;

public class Model {
	
	ArtsmiaDAO dao;
	Graph<Integer,DefaultWeightedEdge> grafo;
	List<Adiacenza> archi;
	List<Integer> best;
	int partenza;
	double mPeso;
	double pp=0.0;
	double pes=0.0;
	
	public Model() {
		dao=new ArtsmiaDAO();
		
	}
	
	public List<String> getRole(){
		return dao.allRole();
	}
	
	public void creaGrafo(String ruolo) {
		
		this.grafo=new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		//AGGIUNGI VERTICI
		Graphs.addAllVertices(this.grafo, dao.allNodi(ruolo));
		//AGGIUNGO ARCHI
		this.archi=new ArrayList<>(dao.allArch(ruolo));
		for(Adiacenza a:archi) {
			Graphs.addEdge(this.grafo, a.art1, a.art2, a.peso);
		}
	}
	
	public int nArc() {
		return this.grafo.edgeSet().size();
	}
	
	public int nVert() {
		return this.grafo.vertexSet().size();
	}
	
	
	public List<Adiacenza> vicini(){
		List<Adiacenza> result=new ArrayList<>(archi);
		Collections.sort(result);
		return result;
	}
	
	public List<Integer> ricorsione(int artista){
		this.partenza=artista;
		if(!this.grafo.vertexSet().contains(this.partenza)) {
			return null;
		}
		List<Integer> parziale=new LinkedList<>();
		parziale.add(this.partenza);
		this.best=new LinkedList<>();
	
		cerca(parziale);
		return best;
		
		
	}

	private void cerca(List<Integer> parziale) {
		List<Integer> vicini=Graphs.neighborListOf(this.grafo, parziale.get(parziale.size()-1));
		if(parziale.size()>best.size()) {
			best=new LinkedList<>(parziale);
			this.mPeso=pp;
		}
		for(int i:vicini) {
			DefaultWeightedEdge e=this.grafo.getEdge(parziale.get(parziale.size()-1), i);
			if(e!=null) {
				pp=this.grafo.getEdgeWeight(e);
				if(!parziale.contains(i)) {
					if(parziale.size()!=1) {
						if(pp==this.grafo.getEdgeWeight(this.grafo.getEdge(parziale.get(parziale.size()-2),parziale.size()-1))){
							parziale.add(i);
						}
					}
					else if(parziale.size()==1){
						parziale.add(i);
						pes=this.grafo.getEdgeWeight(this.grafo.getEdge(this.partenza,parziale.get(i)));
					}
				}
			
			}
			
		}
		
	}


}
