/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.lab04;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	Model model;

    @FXML
    private ComboBox<String> corsoTendina;

    @FXML
    private TextField matricola;

    @FXML
    private TextField nome;

    @FXML
    private TextField cognome;

    @FXML
    private TextArea output;

    @FXML
    void clear(ActionEvent event) {
    	output.clear();
    	nome.clear();
    	cognome.clear();
    	matricola.clear();
    	model.clear();
    }

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	try {
       	 Integer matricol=Integer.parseInt(matricola.getText());
         String s=model.getCorsiIscritto(matricol);
         if(s.equals("")) {
        	 output.setText("Inserire una matricola valida");
        	 return;
         }	 
         output.setText(s);
       	}catch(NumberFormatException nfe) {
       		output.setText("Inserire un valore numerico");
       		return;
       
       	}

    }

    @FXML
    void doCercaIscritti(ActionEvent event) {
    	try {
    	String nomeCorso=corsoTendina.getValue();
    	String[] temp=nomeCorso.split(" ");
    	Corso cc=model.getCorso(temp[temp.length-1]);
    	String s=model.getStudentiIscrittiAlCorso(cc.getCodins());
    	output.setText(s);

    	}catch(NullPointerException npe) {
    		output.setText("ERRORE:Selezionare corso");
    	}
    	

    }

    @FXML
    void doCompleta(ActionEvent event){
    	try {
    	 Integer matricol=Integer.parseInt(matricola.getText());
    		nome.setText(model.getNome(Integer.parseInt(matricola.getText())));
        	cognome.setText(model.getCognome(Integer.parseInt(matricola.getText())));
    	}catch(NumberFormatException nfe) {
    		output.setText("Inserire un valore numerico");
    		return;
    	}catch(NullPointerException npe) {
    		output.setText("Inserire una matricola valida");
    	}
    
    	
    
   
    }

    @FXML
    void doIscrivi(ActionEvent event) {
    	output.clear();
    	try {
       	Integer matricol=Integer.parseInt(matricola.getText());String nomeCorso=corsoTendina.getValue();
     	String[] temp=nomeCorso.split(" ");
     	  Corso cc=model.getCorso(temp[temp.length-1]);
     	  String s=model.getCorsiIscritto(matricol);  
          if(s.equals("")) {
         	 output.appendText("Inserire una matricola valida");
         	 return;
          }	 
          if(model.isIscritto(matricol, cc)) {
        	  output.setText("Lo studente è già iscritto a questo corso");
          }else {
        	  if(model.aggiungiStudenteAlCorso(matricol, cc)) {
        	  output.setText("Studente iscritto al corso!");
        	  }
        	  else {
        		  output.setText("Errore non è stato possibile iscrivere lo studente al corso!");
        	  }
          }
          
          
       	}catch(NumberFormatException nfe) {
       		output.setText("ERRORE, formato matricola non valido");
       		return;
       	}catch(NullPointerException npe) {
       		output.appendText("ERRORE scegliere un corso");
       		return;
       	}
    	

    }
    public void setModel(Model model) {
    	this.model=model;
    	corsoTendina.getItems().addAll(model.getCorsi());
    }
    @FXML
    void initialize() {
        assert corsoTendina != null : "fx:id=\"corsoTendina\" was not injected: check your FXML file 'Scene.fxml'.";
        assert matricola != null : "fx:id=\"matricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert nome != null : "fx:id=\"nome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cognome != null : "fx:id=\"cognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert output != null : "fx:id=\"output\" was not injected: check your FXML file 'Scene.fxml'.";
    }
}
