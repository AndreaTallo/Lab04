package it.polito.tdp.lab04.model;
import java.util.*;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	private CorsoDAO corsoDao=new CorsoDAO();
	private StudenteDAO studenteDao=new StudenteDAO();
	
	public String[] getCorsi(){
		String[] s=new String[corsoDao.getTuttiICorsi().size()+1];
		s[0]="";
		int i=1;
		for(Corso cc: corsoDao.getTuttiICorsi()) {
			s[i]=cc.getNome()+" "+cc.getCodins();
			i++;
		}	
		return s;
	}

	public Map<Integer,Studente> getStudenti(){
		return studenteDao.getTuttiGliStudenti();
	}
	
	public String getNome(int matricola) {
		Studente s=studenteDao.getTuttiGliStudenti().get(matricola);
		return s.getNome();
	}
	public String getCognome(int matricola) {
		Studente s=studenteDao.getTuttiGliStudenti().get(matricola);
		return s.getCognome();
	}
	public String  getStudentiIscrittiAlCorso(String corso){
		String s="";
		for(Studente ss: this.corsoDao.getStudentiIscrittiAlCorso(corso).values())
			s=s+ss.toString();
		return s;
		
	}
	public Corso getCorso(String codice) {
		return corsoDao.getCorso(codice);
	}
	public void clear() {
	corsoDao.getTuttiICorsi().clear();
	studenteDao.getTuttiGliStudenti().clear();
	
}
	
	public String getCorsiIscritto(int matricola) {
		String s="";
		for(Corso st:studenteDao.getCorsiDoveIScritto(matricola))
			s=s+st.toString();
		
		return s;
	}
	
	public boolean isIscritto(int matricola, Corso corso) {
		List<Corso> temp= studenteDao.getCorsiDoveIScritto(matricola);
		if(temp.contains(corso))
			return true;
		return false;
		
	}
	public boolean aggiungiStudenteAlCorso(int matricola, Corso corso) {
		return corsoDao.inscriviStudenteACorso(studenteDao.getTuttiGliStudenti().get(matricola), corso);
	}

}
