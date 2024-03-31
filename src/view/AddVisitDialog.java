//Gabriela Pinheiro - R00225375 - Project_Part1

package view;

import java.lang.ModuleLayer.Controller;
import java.util.function.Function;

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
public class AddVisitDialog extends Dialog<Visit>{
	
	private Patient patient;
	private Consultant consultant;
	private Visit visit;
	private TextField notesInput;
	private DatePicker dateInput;
	private ComboBox comboBox;

	public AddVisitDialog(Patient p, Visit v) {
		super();
		this.setTitle("Add Patient");
		this.patient = p;
		this.visit = v;
		buildUI();
		setPropertyBinding();
		setResultConverter();
	}
	
	
	private void buildUI() {
		
			GridPane gridPane = inputFieldsGrid();
			getDialogPane().setContent(gridPane);

			getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
			Button button = (Button) getDialogPane().lookupButton(ButtonType.OK);
			button.addEventFilter(ActionEvent.ACTION, new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					if(!validateDialog()) {
						event.consume();
					}
					
//				System.out.println("Visit Dialog line 51");
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

	private void setPropertyBinding() {
		notesInput.textProperty().bindBidirectional(visit.getNotesProperty());
		dateInput.valueProperty().bindBidirectional(visit.getDateOfVisitProperty());
		
	}

	private void setResultConverter() {			
			
		System.out.println("Visit Dialog line 78 " + this.patient.getPatientVisits());
		Callback<ButtonType, Visit> visitResultConverter = new Callback<ButtonType, Visit>(){

			@Override
			public Visit call(ButtonType param) {
				if(param == ButtonType.OK) {
//					System.out.println("Dialog line 93");
					return visit;					
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
			
			grid.add(dateBox, 0, 0, 1, 1);
			grid.add(notesBox, 0, 1, 1, 1);
	
			return grid;	
			
	}
	
	
	
}
