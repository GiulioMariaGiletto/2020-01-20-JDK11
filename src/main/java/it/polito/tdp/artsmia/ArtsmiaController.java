package it.polito.tdp.artsmia;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.artsmia.model.Adiacenza;
import it.polito.tdp.artsmia.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ArtsmiaController {
	
	private Model model ;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCreaGrafo;

    @FXML
    private Button btnArtistiConnessi;

    @FXML
    private Button btnCalcolaPercorso;

    @FXML
    private ComboBox<String> boxRuolo;

    @FXML
    private TextField txtArtista;

    @FXML
    private TextArea txtResult;

    @FXML
    void doArtistiConnessi(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText("Calcola artisti connessi");
    	for(Adiacenza a:model.vicini()) {
    		txtResult.appendText(a+"\n");
    	}
    }

    @FXML
    void doCalcolaPercorso(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText("Calcola percorso");
    	int n;
    	try {
    		n=Integer.parseInt(txtArtista.getText());
    		List<Integer> percorso=model.ricorsione(n);
    		if(percorso==null) {
    			txtResult.appendText("ARTISTA NON TROVATO");
    		}
    		else {
    			for(int ll:percorso) {
    				txtResult.appendText(ll+"\n");
    				txtResult.appendText(""+percorso.size());
    			}
    		}
    	}catch(NumberFormatException e) {
    		txtResult.appendText("INSERISCI UN NUMERO VALIDO +\n");
    	}
    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText("Crea grafo \n");
    	String ruolo=boxRuolo.getValue();
    	if(ruolo==null) {
    		txtResult.appendText("INSERISCI PRIMA RUOLO"+"\n");
    		return;
    	}
    	else {
    		model.creaGrafo(ruolo);
    		txtResult.appendText("Il grafo satmpato ha "+model.nVert()+" vertici e numero di archi pari a "  +model.nArc());
    	}
    }

    public void setModel(Model model) {
    	this.model = model;
    	boxRuolo.getItems().addAll(model.getRole());
    }

    
    @FXML
    void initialize() {
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert btnArtistiConnessi != null : "fx:id=\"btnArtistiConnessi\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert btnCalcolaPercorso != null : "fx:id=\"btnCalcolaPercorso\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert boxRuolo != null : "fx:id=\"boxRuolo\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert txtArtista != null : "fx:id=\"txtArtista\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Artsmia.fxml'.";

    }
}
