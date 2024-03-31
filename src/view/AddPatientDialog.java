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
import model.Consultant;
import model.Patient;

//TODO focus on name when open dialog
public class AddPatientDialog extends Dialog<Patient>{
	
	private Patient patient;
	private Consultant consultant;
	private AlertBox alertBox = new AlertBox();

	private TextField firstNameInput, lastNameInput, phoneInput;
	private ComboBox<String> comboBox;
	private int selectedIndex = -1;

	public AddPatientDialog(Patient p) {
		super();
		this.setTitle("Add Patient");
		this.patient = p;
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
				
			System.out.println("Dialog line 60");
			}
			
			private boolean validateDialog() {
				if((firstNameInput.getText().isEmpty()) || (phoneInput.getText().isEmpty())) {
					System.out.println("Dialog line 65");
					alertBox.dialogInformation("Invalid Input!", "Insert information required, please.");
					return false;					
				}
				System.out.println("Dialog line 68");
				return true;
			}
		});	
	}

	private void setPropertyBinding() {
		comboBox.setOnAction(e -> selectedIndex  = comboBox.getSelectionModel().getSelectedIndex());
		firstNameInput.textProperty().bindBidirectional(patient.getName().getFirstNameProperty());
		lastNameInput.textProperty().bindBidirectional(patient.getName().getLastNameProperty());
		phoneInput.textProperty().bindBidirectional(patient.getPhoneProperty());
	}
		
	private void setResultConverter() {			
			
		Callback<ButtonType, Patient> patientResultConverter = new Callback<ButtonType, Patient>(){

			@Override
			public Patient call(ButtonType param) {
				if(param == ButtonType.OK) {
//					System.out.println("Dialog line 88");
					return patient;					
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
