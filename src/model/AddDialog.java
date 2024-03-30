//Gabriela Pinheiro - R00225375 - Project_Part1

package model;

import java.lang.ModuleLayer.Controller;
import java.util.function.Function;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.util.Callback;
import javafx.util.Pair;

//TODO focus on name when open dialog
public class AddDialog extends Dialog<Patient>{
	
	private Patient patient;
	private Consultant consultant;

	private TextField firstNameInput, lastNameInput, phoneInput, expertiseInput;

	public AddDialog(Patient p) {
		super();
		this.setTitle("Add Patient");
		this.patient = p;
		buildUI();
		setPropertyBindingPatient();
		setResultConverter();
	}
	
	//Overloading
	public AddDialog(Consultant c) {
		super();
		this.setTitle("Add Consultant");
		this.consultant = c;
		buildUI();
		setPropertyBindingConsultant();
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
						return false;					
					}
					System.out.println("Dialog line 68");
					return true;
				}
			});
		
		
	}

	private void setPropertyBindingPatient() {
		//fName-lNmae from person. phone from patient. expertise from consultant

//		firstNameInput.textProperty().bindBidirectional(Controller.getInstance().getName().getFirstName());
		firstNameInput.textProperty().bindBidirectional(patient.getName().getFirstNameProperty());
		lastNameInput.textProperty().bindBidirectional(patient.getName().getLastNameProperty());
		phoneInput.textProperty().bindBidirectional(patient.getPhoneProperty());

	}
	
	private void setPropertyBindingConsultant() {
		//fName-lNmae from person. phone from patient. expertise from consultant

//		firstNameInput.textProperty().bindBidirectional(Controller.getInstance().getName().getFirstName());
		firstNameInput.textProperty().bindBidirectional(consultant.getName().getFirstNameProperty());
		lastNameInput.textProperty().bindBidirectional(consultant.getName().getLastNameProperty());
		expertiseInput.textProperty().bindBidirectional(consultant.getPhoneProperty());

	}
	
	private void setResultConverter() {
//		if(s.equals("patient")){			
			
			Callback<ButtonType, Patient> patientResultConverter = new Callback<ButtonType, Patient>(){

				@Override
				public Patient call(ButtonType param) {
					if(param == ButtonType.OK) {
//					System.out.println("Dialog line 93");
						return patient;					
					} else {
						return null;					
					}
				}
			};
			setResultConverter(patientResultConverter);
//		} else {
//			
//			Callback<ButtonType, Consultant> consultantResultConverter = new Callback<ButtonType, Consultant>(){
//	
//				@Override
//				public Consultant call(ButtonType param) {
//					if(param == ButtonType.OK) {
//	//					System.out.println("Dialog line 93");
//						return consultant;					
//					} else {
//						return null;					
//					}
//				}
//			};
//			setResultConverter(consultantResultConverter);
//		}
	}

	private GridPane inputFieldsGrid() {
		
//		if(o instanceof Patient) {
			
			GridPane grid = new GridPane();
			grid.setVgap(8);
			grid.setHgap(10);
			
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
			
			grid.add(nameBox, 0, 0, 1, 1);
			GridPane.setHgrow(firstNameInput, Priority.ALWAYS);
			GridPane.setHgrow(lastNameInput, Priority.ALWAYS);
			grid.add(phoneBox, 0, 1, 1, 1);
			GridPane.setHgrow(phoneInput, Priority.ALWAYS);
	
			return grid;

//		} else if (o instanceof Consultant) {
//			
//			GridPane grid = new GridPane();
//			grid.setVgap(8);
//			grid.setHgap(10);
//			
//			// adding labels
//			Label nameLabel = new Label("Enter Name");
//			Label expertiseLabel = new Label("Enter Expertise");
//
//			
//			//adding text fields
//			firstNameInput = new TextField();
//			firstNameInput.setPromptText("Name");
//			lastNameInput = new TextField();
//			lastNameInput.setPromptText("Surname");
//			expertiseInput = new TextField();
//			expertiseInput.setPromptText("General");
//
//			
//			// creating hBox and addAll
//			HBox nameBox = new HBox(5);
//			HBox expertiseBox = new HBox(5);
//
//			
//			nameBox.getChildren().addAll(nameLabel, firstNameInput, lastNameInput);
//			expertiseBox.getChildren().addAll(expertiseLabel, expertiseInput);
//
//			
//			grid.add(nameBox, 0, 0, 1, 1);
//			grid.add(expertiseBox, 0, 1, 1, 1);
//		
//			return grid;
//			
//		} else {
//			return null;
//		}
//		
		
		
			
	}
	
	
	
}
