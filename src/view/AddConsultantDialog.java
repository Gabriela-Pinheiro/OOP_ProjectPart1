//Gabriela Pinheiro - R00225375 - Project_Part1

package view;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import model.Consultant;
import model.Name;
import model.SerialStorage;
import model.Test;

//TODO focus on name when open dialog
public class AddConsultantDialog extends Dialog<Consultant> {
	
	private TextField firstNameInput, lastNameInput, phoneInput, expertiseInput;
	
	public AddConsultantDialog() {
		super();
		this.setTitle("Add Consultant");
		buildUI();
//		setPropertyBinding();
		setResultConverter();
	}
	
	private void buildUI() {
		
		GridPane gridPane = inputFieldsGrid();
		getDialogPane().setContent(gridPane);

		getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
		Button button = (Button) getDialogPane().lookupButton(ButtonType.OK);
		button.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() { //changed from addEventFilter

			@Override
			public void handle(ActionEvent event) {
				if(!validateDialog()) {
					event.consume();
				}
//				
//				System.out.println("C Dialog line 50");
			}
			
			private boolean validateDialog() {
				if((firstNameInput.getText().isEmpty()) || (lastNameInput.getText().isEmpty() || (phoneInput.getText().isEmpty()) || (expertiseInput.getText().isEmpty()))) {
					System.out.println("C Dialog line 55");
					return false;					
				}
				System.out.println("C Dialog line 58");
				return true;
			}
		});
	}
	
	private void setResultConverter() {

		Callback<ButtonType, Consultant> resultConverter = new Callback<ButtonType, Consultant>(){

			@Override
			public Consultant call(ButtonType param) {
				if(param == ButtonType.OK) {
//					System.out.println("C Dialog line 83");
					return new Consultant(new Name(firstNameInput.getText(), lastNameInput.getText()), phoneInput.getText(), expertiseInput.getText());					
//					return consultant;					
				} else {
					return null;					
				}
			}
		};
		setResultConverter(resultConverter);
	}

	private GridPane inputFieldsGrid() {
		
		GridPane grid = new GridPane();
		grid.setVgap(8);
		grid.setHgap(10);
		
		// adding labels
		Label nameLabel = new Label("Enter Name");
		Label phoneLabel = new Label("Enter Phone");
		Label expertiseLabel = new Label("Enter Expertise");

		
		//adding text fields
		firstNameInput = new TextField();
		firstNameInput.setPromptText("Name");
		lastNameInput = new TextField();
		lastNameInput.setPromptText("Surname");
		phoneInput = new TextField();
		phoneInput.setPromptText("000-000-000");
		expertiseInput = new TextField();
		expertiseInput.setPromptText("General");

		
		// creating hBox and addAll
		HBox nameBox = new HBox(5);
		HBox phoneBox = new HBox(5);
		HBox expertiseBox = new HBox(5);

		
		nameBox.getChildren().addAll(nameLabel, firstNameInput, lastNameInput);
		phoneBox.getChildren().addAll(phoneLabel, phoneInput);
		expertiseBox.getChildren().addAll(expertiseLabel, expertiseInput);

		
		grid.add(nameBox, 0, 0, 1, 1);
		grid.add(phoneBox, 0, 1, 1, 1);
		grid.add(expertiseBox, 0, 2, 1, 1);
	
		return grid;	
	}
	
	
}
