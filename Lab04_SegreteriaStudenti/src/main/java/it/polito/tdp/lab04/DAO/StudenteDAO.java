package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	public Map<Integer,Studente> getTuttiGliStudenti() {

		final String sql = "SELECT * FROM studente";

		Map<Integer,Studente> studenti = new TreeMap<Integer,Studente>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				int matricola = rs.getInt("matricola");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String CDS = rs.getString("CDS");

				Studente studente=new Studente(matricola,nome,cognome,CDS);
				// Crea un nuovo JAVA Bean Corso
				// Aggiungi il nuovo oggetto Corso alla lista corsi
				studenti.put(matricola,studente);
			}

			conn.close();
			
			return studenti;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	public List<Corso> getCorsiDoveIScritto(Integer matricola) {
		final String sql2 = "SELECT c.codins,c.nome, c.crediti,c.pd "
				+ "FROM  corso c,studente s,iscrizione i "
				+ "WHERE i.codins=c.codins AND s.matricola=i.matricola  AND s.matricola=?";

		List<Corso> corsi = new ArrayList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();

			PreparedStatement st2 = conn.prepareStatement(sql2);
			
			st2.setInt(1,matricola);
			ResultSet rs = st2.executeQuery() ;

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				Corso corso=new Corso(codins,numeroCrediti,nome,periodoDidattico);

				
				// Crea un nuovo JAVA Bean Corso
				// Aggiungi il nuovo oggetto Corso alla lista corsi
				corsi.add(corso);
			}

			conn.close();
			return corsi;
	}catch (SQLException e) {
		// e.printStackTrace();
		throw new RuntimeException("Errore Db", e);
	}
	}
	

}
