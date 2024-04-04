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
import javafx.scene.layout.Priority;
import javafx.util.Callback;
import model.Name;
import model.Patient;

//TODO focus on name when open dialog
public class AddPatientDialog extends Dialog<Patient> {
	
	private AlertBox alertBox = new AlertBox();

	private TextField firstNameInput, lastNameInput, phoneInput;
	private ComboBox<String> comboBox;
	private int selectedIndex = -1;
	
	public AddPatientDialog() {
		super();
		this.setTitle("Add Patient");
		buildUI();
		setResultConverter();
	}
	
	private void buildUI() {
		
		GridPane gridPane = inputFieldsGrid();
		getDialogPane().setContent(gridPane);

		getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
		Button button = (Button) getDialogPane().lookupButton(ButtonType.OK);
		button.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(!validateDialog()) {
					event.consume();
				}	
			}
			
			private boolean validateDialog() {
				if((firstNameInput.getText().isEmpty()) || (lastNameInput.getText().isEmpty()) || (phoneInput.getText().isEmpty())) {
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
			public Patient call(ButtonType param) { //TODO oblige to select a Consultant
				if(param == ButtonType.OK) {
//					if(selectedIndex < 0) {
//						alertBox.dialogInformation("Please, select a Consultant", null);
//						return null;
//					} else {						
						return Controller.getInstance().addPatientToConsultant(selectedIndex, new Patient(new Name(firstNameInput.getText(), lastNameInput.getText()), phoneInput.getText()));
//					}
										
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

		comboBox = new ComboBox(); 
		comboBox.setPromptText("Select a Consultant:");;
		comboBox.setItems(consultants);
		comboBox.setOnAction(e -> getSelectedConsultant());
		
		// adding labels
		Label nameLabel = new Label("Enter Name");
		Label phoneLabel = new Label("Enter Phone");
		
		//adding text fields
		firstNameInput = new TextField();
		firstNameInput.setPromptText("Name");
		firstNameInput.requestFocus();
		lastNameInput = new TextField();
		lastNameInput.setPromptText("Surname");
		phoneInput = new TextField();
		phoneInput.setPromptText("000-000-000");
		
		// creating hBox and addAll
		HBox nameBox = new HBox(5);
		HBox phoneBox = new HBox(5);
		
		nameBox.getChildren().addAll(nameLabel, firstNameInput, lastNameInput);
		phoneBox.getChildren().addAll(phoneLabel, phoneInput);
		
		grid.add(comboBox, 0, 0, 1, 1);
		grid.add(nameBox, 0, 1, 1, 1);
		GridPane.setHgrow(firstNameInput, Priority.ALWAYS);
		GridPane.setHgrow(lastNameInput, Priority.ALWAYS);
		grid.add(phoneBox, 0, 2, 1, 1);
		GridPane.setHgrow(phoneInput, Priority.ALWAYS);

		return grid;		
	}
}
