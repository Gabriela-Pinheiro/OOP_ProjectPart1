//Gabriela Pinheiro - R00225375 - Project_Part1

package model;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class AddDialog extends Dialog<Object>{
	
	private Patient patient;
	private Consultant consultant;

	private TextField firstNameInput, lastNameInput, phoneInput ;

	
	//Overloading
	public AddDialog(Patient p) {
		super();
		this.setTitle("Add Patient");
		this.patient = p;
		buildUI();
		setPropertyBinding();
		setResultConverter();
	}
	
	//Overloading
	public AddDialog(Consultant c) {
		super();
		this.setTitle("Add Consultant");
		this.consultant = c;
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
				
			}

			//TODO change palce maybe
			private boolean validateDialog() {
				if((firstNameInput.getText().isEmpty()) || (phoneInput.getText().isEmpty())) {
					return true;
					
				}
				return false;
			}
		});
	}

	

	private void setPropertyBinding() {
		firstNameInput.textProperty().bindBidirectional(patient.getName().getFirstName());
		lastNameInput.textProperty().bindBidirectional(patient.getName().getLastName());
		phoneInput.textProperty().bindBidirectional(patient.getPhone());
		
	}
	
	private void setResultConverter() {
		// TODO Auto-generated method stub
		
	}



	
	private GridPane inputFieldsGrid() {
		
		GridPane grid = new GridPane();
		grid.setVgap(8);
		grid.setHgap(10);
		
		// adding labels
		Label nameLabel = new Label("Enter Name");
		Label phoneLabel = new Label("Enter Phone");
//		Label dobLabel = new Label("Enter Date of Birth ");
		
		//adding text fields
		firstNameInput = new TextField();
		firstNameInput.setPromptText("Name");
		lastNameInput = new TextField();
		lastNameInput.setPromptText("Surname");
		phoneInput = new TextField();
		phoneInput.setPromptText("000-000-000");
//		TextField dobInput = new TextField();
//		dobInput.setPromptText("DD/MM/YYYY");
		
		// creating hBox and addAll
		HBox nameBox = new HBox(5);
		HBox phoneBox = new HBox(5);
//		HBox dobBox = new HBox(5);
		
		nameBox.getChildren().addAll(nameLabel, firstNameInput, lastNameInput);
		phoneBox.getChildren().addAll(phoneLabel, phoneInput);
//		dobBox.getChildren().addAll(dobLabel, dobInput);
		
		grid.add(nameBox, 0, 0, 1, 1);
		grid.add(phoneBox, 0, 1, 1, 1);
//		grid.add(dobBox, 0, 2, 1, 1);

		return grid;
		
	}
	
	
	
}
