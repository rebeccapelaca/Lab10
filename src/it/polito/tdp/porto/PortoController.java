package it.polito.tdp.porto;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.porto.model.Author;
import it.polito.tdp.porto.model.Model;
import it.polito.tdp.porto.model.Paper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class PortoController {
	
	Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Author> boxPrimo;

    @FXML
    private ComboBox<Author> boxSecondo;

    @FXML
    private TextArea txtResult;
    
    @FXML
    private Button txtReset;

    @FXML
    void doReset(ActionEvent event) {
    	
    	txtResult.clear();
    }

    @FXML
    void handleCoautori(ActionEvent event) {
    	
		for(Author coAutore : model.getCoAutori(boxPrimo.getValue()))
			txtResult.appendText(coAutore + "\n");
    }

    @FXML
    void handleSequenza(ActionEvent event) {
    	
    	String risultato = "";
    	
    	if(model.autoriValidi(boxPrimo.getValue(), boxSecondo.getValue())) {
    		risultato += "Autore di partenza: " + boxPrimo.getValue() + "\n\n";
    		for(Paper paper : model.getPercorsoArticoli(boxPrimo.getValue(), boxSecondo.getValue()))
    			risultato += paper + "\n";
    		risultato += "Autore di destinazione : " + boxSecondo.getValue() + "\n";	
    	}
    	else
    		risultato += "Selezionare due autori diversi che non abbiamo articoli in comune!";
    	
    	txtResult.appendText(risultato);
    }

    @FXML
    void initialize() {
        assert boxPrimo != null : "fx:id=\"boxPrimo\" was not injected: check your FXML file 'Porto.fxml'.";
        assert boxSecondo != null : "fx:id=\"boxSecondo\" was not injected: check your FXML file 'Porto.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Porto.fxml'.";

    }

	public void setModel(Model model) {
		
		this.model = model;
		
		List<Author> autori = model.getAutori();
		boxPrimo.getItems().addAll(autori);
		boxSecondo.getItems().addAll(autori);
		if (boxPrimo.getItems().size() > 0)
			boxPrimo.setValue(model.getAutori().get(0));
		if (boxSecondo.getItems().size() > 0)
			boxSecondo.setValue(model.getAutori().get(0));
	}
}
