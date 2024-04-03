//Gabriela Pinheiro - R00225375 - Project_Part1

package view;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.function.Function;
import controller.Controller;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import javafx.util.Pair;
import model.Consultant;
import model.Patient;
import model.Visit;


//TODO focus on date when open dialog
public class AddVisitDialog extends Dialog<Visit> {
	
	private Patient patient;
	private Consultant consultant;
	private Visit visit;
	private TextField notesInput;
	private DatePicker dateInput;
	private ComboBox comboBox;

	public AddVisitDialog(Patient p) {
		super();
		this.setTitle("Add Patient's Visit");
		this.patient = p;
		buildUI();
		setResultConverter();
	}
	
	
	private void buildUI() {
		
			GridPane gridPane = inputFieldsGrid();
			getDialogPane().setContent(gridPane);

			getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
			Button button = (Button) getDialogPane().lookupButton(ButtonType.OK);
			button.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {  //changed from addEventFilter

				@Override
				public void handle(ActionEvent event) {
					if(!validateDialog()) {
						event.consume();
					}
				}
				
				private boolean validateDialog() {
					if((notesInput.getText().isEmpty()) || (dateInput.getValue() == null)) {
//						System.out.println("Visit Dialog empty line 65");
						return false;					
					}
					return true;
				}
			});	
	}


	private void setResultConverter() {			
			
		System.out.println("Visit Dialog line 78 " + this.patient.getPatientVisits());
		Callback<ButtonType, Visit> visitResultConverter = new Callback<ButtonType, Visit>(){

			@Override
			public Visit call(ButtonType param) {
				if(param == ButtonType.OK) {
					return Controller.getInstance().searchPatient(patient.toString()).addVisit(new Visit(dateInput.getValue(), notesInput.getText()));					
				} else {
					return null;					
				}
			}
		};
		setResultConverter(visitResultConverter);
	}

	private GridPane inputFieldsGrid() {
			
			GridPane grid = new GridPane();
			grid.setVgap(8);
			grid.setHgap(10);
						
			// adding labels
			Label patientLabel = new Label(this.patient.toString());
			Label dateLabel = new Label("Enter Visit Date");
			Label notesLabel = new Label("Enter Notes");
			
			//adding text fields
			notesInput = new TextField();
			notesInput.setPromptText("Comments from visit");
			dateInput = new DatePicker();
			dateInput.setPromptText("YYYY-MM-DD");
			
			// creating hBox and addAll
			HBox dateBox = new HBox(5);
			HBox notesBox = new HBox(5);
			
			dateBox.getChildren().addAll(dateLabel, dateInput);
			notesBox.getChildren().addAll(notesLabel, notesInput);
			
			grid.add(patientLabel, 0, 0, 1, 1);
			grid.add(dateBox, 0, 1, 1, 1);
			grid.add(notesBox, 0, 2, 1, 1);
	
			return grid;	
			
	}
	
	
	
}
