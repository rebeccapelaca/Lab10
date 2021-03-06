package it.polito.tdp.porto.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.porto.model.Author;
import it.polito.tdp.porto.model.Paper;

public class PortoDAO {

	public List<Author> getAutori() {
		
		List<Author> autori = new ArrayList<Author>();
		
		final String sql = "SELECT * " + 
		                   "FROM author " +
				           "ORDER BY lastname, firstname";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Author autore = new Author(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"));
				autori.add(autore);
				
			}
			
			st.close();
			conn.close();

			return autori;

		} catch (SQLException e) {
			 e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	public List<Paper> getArticoli(int idAutore) {
		
		List<Paper> articoli = new ArrayList<Paper>();
		
		final String sql = "SELECT paper.eprintid, title, issn, publication, paper.type, paper.types " +
				           "FROM creator, paper " +
				           "WHERE creator.eprintid = paper.eprintid AND authorid = ?";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, idAutore);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				Paper articolo = new Paper(rs.getInt("eprintid"), rs.getString("title"), rs.getString("issn"), rs.getString("publication"), rs.getString("type"), rs.getString("types"));
				articoli.add(articolo);
			}
			
			st.close();
			conn.close();

			return articoli;

		} catch (SQLException e) {
			 e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	public List<Author> getCoAutori(int eprintid) {
		
		List<Author> coAutori = new ArrayList<Author>();
		
		final String sql = "SELECT id, lastname, firstname " +
				           "FROM creator, author " +
				           "WHERE creator.authorid = author.id AND eprintid = ?";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, eprintid);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Author autore = new Author(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"));
				coAutori.add(autore);	
			}
			
			st.close();
			conn.close();

			return coAutori;

		} catch (SQLException e) {
			 e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
}