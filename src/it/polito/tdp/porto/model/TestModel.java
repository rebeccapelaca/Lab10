package it.polito.tdp.porto.model;

import org.jgrapht.graph.DefaultEdge;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		System.out.println("TODO: write a Model class and test it!");
		
		System.out.println("STAMPO TUTTI GLI AUTORI (vertici del grafo) : \n");
		for(Author a : model.getAutori())
			System.out.println(a + "\n");
		
		System.out.println("STAMPO GLI ARCHI DEL GRAFO : \n");
		for(DefaultEdge de : model.creaGrafo())
			System.out.println(de + "\n");	
		
		System.out.println("STAMPO COAUTORI DI UN AUTORE DATO: \n");
		for(Author a : model.getCoAutori(model.getAutori().get(45)))
			System.out.println(a + "\n");
		
		System.out.println("STAMPO CAMMINO MINIMO TRA DUE AUTORI NON DIRETTAMENTE CO-AUTORI: \n");
		for(DefaultEdge de : model.getPercorsoMinimo(model.getAutori().get(45), model.getAutori().get(12)))
			System.out.println(de + "\n");
		
		System.out.println("STAMPO PERCORSO ARTICOLI TRA DUE AUTORI: \n");
		System.out.println("Autore di partenza : " + model.getAutori().get(45) + "\n");
		for(Paper paper : model.getPercorsoArticoli(model.getAutori().get(45), model.getAutori().get(12)))
			System.out.println(paper + "\n");
		System.out.println("Autore di destinazione : " + model.getAutori().get(12) + "\n");	
	}
}