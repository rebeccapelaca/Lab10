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
	}
}