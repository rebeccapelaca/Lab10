package it.polito.tdp.porto.model;

import java.util.Map;
import java.util.TreeMap;

public class Author {

	private int id;
	private String lastname;
	private String firstname;
	private Map<Integer, Paper> articoli;
		
	public Author(int id, String lastname, String firstname) {
		super();
		this.id = id;
		this.lastname = lastname;
		this.firstname = firstname;
		this.articoli = new TreeMap<Integer, Paper>();
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	
	public Map<Integer, Paper> getArticoli() {
		return articoli;
	}

	public void setArticoli(Paper articolo) {
		articoli.put(articolo.getEprintid(), articolo);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Author other = (Author) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Author [id=" + id + ", lastname=" + lastname + ", firstname=" + firstname + "]";
	}
}
