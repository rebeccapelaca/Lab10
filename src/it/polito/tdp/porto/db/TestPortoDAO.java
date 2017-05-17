package it.polito.tdp.porto.db;

import it.polito.tdp.porto.model.Author;
import it.polito.tdp.porto.model.Paper;

public class TestPortoDAO {
	
	public static void main(String args[]) {
		PortoDAO pd = new PortoDAO();
		//System.out.println(pd.getAutore(85));
		//System.out.println(pd.getArticolo(2293546));
		//System.out.println(pd.getArticolo(1941144));
		
		System.out.println("STAMPO TUTTI GLI AUTORI: \n");
		for(Author a : pd.getAutori())
			System.out.println(a + "\n");
		
		System.out.println("STAMPO TUTTI GLI ARTICOLI DI UN DATO AUTORE (id = 719): \n");
		for(Paper p : pd.getArticoli(719))
			System.out.println(p + "\n");
		
		System.out.println("DATI UN AUTORE E UN ARTICOLO, STAMPO TUTTI I COAUTORI (escluso l'autore dato, idAutore = 719, eprintid = 2497471) : \n");
		for(Author autore : pd.getCoAutori(2497471, 719))
			System.out.println(autore + "\n");
	}
}
