package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {
	
	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

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
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	
	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public Corso getCorso(String codice) {
		for(Corso cc:this.getTuttiICorsi())
			if(cc.getCodins().equals(codice))
				return cc;
		return null;
	}

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public Map<Integer,Studente> getStudentiIscrittiAlCorso(String codiceCorso) {
		final String sql2 = "SELECT s.matricola, s.cognome, s.nome, s.CDS "
				+ "FROM studente s, iscrizione i, corso c "
				+ "WHERE s.matricola=i.matricola AND i.codins=c.codins AND c.codins=?";

		Map<Integer,Studente> studenti = new TreeMap<Integer,Studente>();

		try {
			Connection conn = ConnectDB.getConnection();

			PreparedStatement st2 = conn.prepareStatement(sql2);
			
			st2.setString(1,codiceCorso);
			ResultSet res2 = st2.executeQuery() ;

			while (res2.next()) {

				int matricola = res2.getInt("matricola");
				String cognome = res2.getString("cognome");
				String nome = res2.getString("nome");
				String CDS = res2.getString("CDS");

				Studente studente=new Studente(matricola,nome,cognome,CDS);
				// Crea un nuovo JAVA Bean Corso
				// Aggiungi il nuovo oggetto Corso alla lista corsi
				studenti.put(matricola,studente);
			}

			conn.close();
			return studenti;
	}catch (SQLException e) {
		// e.printStackTrace();
		throw new RuntimeException("Errore Db", e);
	}
	}

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		// TODO
		// ritorna true se l'iscrizione e' avvenuta con successo
		int matricola=studente.getMatricola();
		String codins=corso.getCodins();
		
		String sql3="insert into iscrizione (matricola,codins) "
				+ "VALUES (?,?)";
		try {
			Connection conn = ConnectDB.getConnection();

			PreparedStatement st2 = conn.prepareStatement(sql3);
			
			st2.setInt(1,matricola);
			st2.setString(2, codins);
			
			int res=st2.executeUpdate();
			conn.close();
		
			if(res==0)
				return false;
			else
				return true;
	}catch (SQLException e) {
		// e.printStackTrace();
		throw new RuntimeException("Errore Db", e);
	
	}
	}

}
