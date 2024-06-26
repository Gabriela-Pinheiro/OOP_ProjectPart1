//Gabriela Pinheiro - R00225375 - Project_Part1

package view;

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

public class AddConsultantDialog extends Dialog<Consultant> {
	
	private TextField firstNameInput, lastNameInput, phoneInput, expertiseInput;
	private AlertBox alertBox = new AlertBox();

	
	public AddConsultantDialog() {
		super();
		this.setTitle("Add Consultant");
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
				if((firstNameInput.getText().isEmpty()) || (lastNameInput.getText().isEmpty() || (phoneInput.getText().isEmpty()) || (expertiseInput.getText().isEmpty()))) {
					alertBox.dialogInformation("Invalid Input!", "Insert required information, please.");

					return false;					
				}
				return true;
			}
		});
	}
	
	private void setResultConverter() {

		Callback<ButtonType, Consultant> resultConverter = new Callback<ButtonType, Consultant>(){

			@Override
			public Consultant call(ButtonType param) {
				if(param == ButtonType.OK) {
					return new Consultant(new Name(firstNameInput.getText(), lastNameInput.getText()), phoneInput.getText(), expertiseInput.getText());					
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
