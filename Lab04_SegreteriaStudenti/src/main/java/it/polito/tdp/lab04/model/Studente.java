package it.polito.tdp.lab04.model;

public class Studente {
	int matricola;
	String nome;
	String cognome;
	String CDS;
	public Studente(int matricola, String nome, String cognome, String CDS) {
		super();
		this.matricola = matricola;
		this.nome = nome;
		this.cognome = cognome;
		this.CDS = CDS;
	}
	public int getMatricola() {
		return matricola;
	}
	public void setMatricola(int matricola) {
		this.matricola = matricola;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getCDS() {
		return CDS;
	}
	public void setCDS(String cDS) {
		CDS = cDS;
	}
	@Override
	public String toString() {
		return matricola + " " + nome + " " + cognome +"\n";
	
	}
	
	

}
