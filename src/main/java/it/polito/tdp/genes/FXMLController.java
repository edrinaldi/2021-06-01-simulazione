/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.genes;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.genes.model.Genes;
import it.polito.tdp.genes.model.Model;
import it.polito.tdp.genes.model.Studio;
import it.polito.tdp.genes.model.Vicino;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnCreaGrafo"
    private Button btnCreaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="cmbGeni"
    private ComboBox<Genes> cmbGeni; // Value injected by FXMLLoader

    @FXML // fx:id="btnGeniAdiacenti"
    private Button btnGeniAdiacenti; // Value injected by FXMLLoader

    @FXML // fx:id="txtIng"
    private TextField txtIng; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	// pulisco l'area di testo
    	this.txtResult.clear();
    	
    	// creo il grafo
    	this.model.creaGrafo();
    	
    	// stampo il risultato
    	this.txtResult.setText(String.format("Creato grafo con %d vertici e %d archi\n\n", this.model.nVertici(), 
    			this.model.nArchi()));
    	
    	// riempio il menu a tendina
    	this.cmbGeni.getItems().clear();
    	this.cmbGeni.getItems().addAll(this.model.getVertici());
    }

    @FXML
    void doGeniAdiacenti(ActionEvent event) {
    	// controllo il grafo
    	if(!this.model.isGrafoCreato()) {
    		this.txtResult.setText("Errore: devi prima creare il grafo.");
    		return;
    	}
    	
    	// controllo il gene in input
    	Genes gene = this.cmbGeni.getValue();
    	if(gene == null) {
    		this.txtResult.appendText("Errore: devi prima selezionare un gene.\n");
    		return;
    	}
    	
    	// trovo la lista di adiacenti
    	List<Vicino> adiacenti = this.model.getAdiacenti(gene);
    	
    	// stampo il risultato
    	this.txtResult.appendText("Geni adiacenti a: " + gene.toString() + "\n");
    	if(adiacenti.size() == 0) {
        	this.txtResult.appendText("NESSUNO");
        	return;

    	}
    	for(Vicino v : adiacenti) {
        	this.txtResult.appendText(v.toString() + "\n");
    	}
    	this.txtResult.appendText("\n");

    }

    @FXML
    void doSimula(ActionEvent event) {
    	// pulisco l'area di testo
    	this.txtResult.clear();
    	
    	// controllo il grafo
    	if(!this.model.isGrafoCreato()) {
    		this.txtResult.setText("Errore: devi prima creare il grafo.");
    		return;
    	}
    	
    	// controllo il gene in input
    	Genes gene = this.cmbGeni.getValue();
    	if(gene == null) {
    		this.txtResult.appendText("Errore: devi prima selezionare un gene.\n");
    		return;
    	}
    	
    	// controllo n
    	int n = 0;
    	try {
    		n = Integer.parseInt(this.txtIng.getText());
    	}
    	catch(NumberFormatException e) {
    		e.printStackTrace();
    		this.txtResult.setText("Errore: devi inserire un numero intero per n.");
    		return;
    	}
    	
    	// effettuo la simulazione
    	List<Studio> ricerca = this.model.simula(n, gene);
    	
    	// stampo il risultato
    	if(ricerca.size() == 0) {
        	this.txtResult.setText("Gene isolato: non è possibile effettuare la simulazione.\n");
        	return;
    	}
    	this.txtResult.setText("Risultato della simulazione:\n");
    	for(Studio s : ricerca) {
    		this.txtResult.appendText(s.toString() + "\n");
    	}
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbGeni != null : "fx:id=\"cmbGeni\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnGeniAdiacenti != null : "fx:id=\"btnGeniAdiacenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtIng != null : "fx:id=\"txtIng\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
    
}
