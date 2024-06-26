//Gabriela Pinheiro - R00225375 - Project_Part1

package view;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import model.Consultant;
import model.Patient;

public class EditPatientDialog extends Dialog<Patient> {
	
	private Patient patient;
	private AlertBox alertBox = new AlertBox();
	private TextField phoneInput;
	private ComboBox<String> comboBox;
	private int selectedIndex = -1;
	private Consultant consultant;
	
	public EditPatientDialog(Patient p, Consultant c) {
		super();
		this.setTitle("Edit Patient");
		this.patient = p;
		this.consultant = c;
		buildUI();
		setResultConverter();
	}
	
	private void buildUI() {
		
		GridPane gridPane = inputFieldsGrid();
		getDialogPane().setContent(gridPane);
		gridPane.getStylesheets().add(getClass().getResource("dialogs.css").toExternalForm());

		getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
		Button button = (Button) getDialogPane().lookupButton(ButtonType.OK);
		// if addEventHandler do not prevent object to being added. addEventFilter consumes the action of ok button
		button.addEventFilter(ActionEvent.ACTION, new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(!validateDialog()) {
					event.consume();
				}	
			}
			
			private boolean validateDialog() {
				if(phoneInput.getText().isEmpty()) {
					alertBox.dialogInformation("Invalid Input!", "Insert required information, please.");
					return false;					
				}
				return true;
			}
		});	
	}
		
	public int getSelectedConsultant() {
		 selectedIndex  = comboBox.getSelectionModel().getSelectedIndex();
		 return selectedIndex;
	}

	private void setResultConverter() {			
			
		Callback<ButtonType, Patient> patientResultConverter = new Callback<ButtonType, Patient>(){

			@Override
			public Patient call(ButtonType param) { 
				if(param == ButtonType.OK) {
					
					return Controller.getInstance().editPatient(consultant, patient, phoneInput.getText());
										
				} else {	
					return null;					
				}
			}
		};
		setResultConverter(patientResultConverter);
	}

	private GridPane inputFieldsGrid() {
					
		GridPane grid = new GridPane();
		grid.setVgap(10);
		grid.setHgap(10);
		
		ObservableList<String> consultants = FXCollections.observableArrayList(Controller.getInstance().getConsultantData());

		Label consultantName = new Label("Consultant:" + this.consultant.toString());
		
		Label nameLabel = new Label("Patient's Name:");
		nameLabel.setId("name_label");
		Label phoneLabel = new Label("Patient's Phone");
		
		Label fname = new Label(this.patient.getName().getFirstName());
		fname.setId("f_name"); 
		Label lname = new Label(this.patient.getName().getLastName());
		lname.setId("l_name");
		phoneInput = new TextField();
		phoneInput.setPromptText("000-000-000");
		phoneInput.setText(this.patient.getPhone());
		
		HBox nameBox = new HBox(5);
		HBox phoneBox = new HBox(5);
		
		nameBox.getChildren().addAll(nameLabel, fname, lname);
		phoneBox.getChildren().addAll(phoneLabel, phoneInput);
		
		grid.add(consultantName, 0, 0, 1, 1);
		grid.add(nameBox, 0, 1, 1, 1);
		grid.add(phoneBox, 0, 2, 1, 1);

		return grid;		
	}
}