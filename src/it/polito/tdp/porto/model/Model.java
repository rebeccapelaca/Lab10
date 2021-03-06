package it.polito.tdp.porto.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.porto.db.PortoDAO;

public class Model {
	
	private PortoDAO portoDAO;
	private UndirectedGraph<Author, DefaultEdge> graph;
	private List<Author> autori;

	public Model() {

		this.portoDAO = new PortoDAO();
	}

	public List<Author> getAutori() {
		
		if (this.autori == null) {
			this.autori = portoDAO.getAutori();
			for(Author autore : autori) 
				for(Paper articolo : portoDAO.getArticoli(autore.getId()))
					autore.setArticoli(articolo);
		}
		
		return this.autori;
	}
	
	public UndirectedGraph<Author, DefaultEdge> getGrafo() {
		
		if(this.graph==null)
			this.creaGrafo();
		return this.graph ;
	}

	public Set<DefaultEdge> creaGrafo() {
		
		graph = new SimpleGraph<>(DefaultEdge.class);
		
		Graphs.addAllVertices(graph, this.getAutori());
			
		for(Author a1 : graph.vertexSet()) 
			for(Author a2 : graph.vertexSet()) 
				if(!a1.equals(a2)) 
					for(Integer p1 : a1.getArticoli().keySet()) 
						if(a2.getArticoli().containsKey(p1)) {
							graph.addEdge(a1, a2);
							break;
						}
		
		return graph.edgeSet();
	}
	
	public List<Author> getCoAutori(Author autore) {
		
		List<Author> coAutori = Graphs.neighborListOf(this.getGrafo(), autore);
		
		return coAutori;
	}
	
	public List<Paper> getPercorsoArticoli(Author partenza, Author arrivo) {
		
		List<Paper> sequenzaArticoli = new ArrayList<Paper>();
		List<DefaultEdge> percorsoMinimo = this.getPercorsoMinimo(partenza, arrivo);
		
		for(DefaultEdge de : percorsoMinimo) {
			
			Author source = graph.getEdgeSource(de);
			Author target = graph.getEdgeTarget(de);
			
			for(Integer idPaper : source.getArticoli().keySet()) 
				if(target.getArticoli().containsKey(idPaper)) {
					for(Author coAutore : portoDAO.getCoAutori(idPaper)) {
						source.getArticoli().get(idPaper).setCoAutori(coAutore);
					}
					
					sequenzaArticoli.add(source.getArticoli().get(idPaper));
					break;
				}
		}
		
		return sequenzaArticoli;		
	}
	
	public List<DefaultEdge> getPercorsoMinimo(Author partenza, Author arrivo) {
		
		DijkstraShortestPath<Author, DefaultEdge> camminoMinimo = new DijkstraShortestPath<Author, DefaultEdge>(this.getGrafo(), partenza, arrivo);
	
		List<DefaultEdge> percorsoMinimo = camminoMinimo.getPath().getEdgeList();
	
		return percorsoMinimo;
			
	}

	public boolean autoriValidi(Author partenza, Author destinazione) {
		
		if(!partenza.equals(destinazione) && !this.getCoAutori(partenza).contains(destinazione))
			return true;
		return false;
	}
}
