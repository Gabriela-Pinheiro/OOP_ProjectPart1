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
public class EditConsultantDialog extends Dialog<Consultant> {
	
	private AlertBox alertBox = new AlertBox();
	private TextField firstNameInput, lastNameInput, phoneInput;
	private ComboBox<String> comboBox;
	private int selectedIndex = -1;
	private Consultant consultant;
	private TextField expertiseInput;
	
	public EditConsultantDialog(Consultant c) {
		super();
		this.setTitle("Edit Consultant");
		this.consultant = c;
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
			
		Callback<ButtonType, Consultant> resultConverter = new Callback<ButtonType, Consultant>(){

			@Override
			public Consultant call(ButtonType param) { 
				if(param == ButtonType.OK) {
					
					return Controller.getInstance().editConsultant(consultant, phoneInput.getText(), expertiseInput.getText());
										
				} else {	
					return null;					
				}
			}
		};
		setResultConverter(resultConverter);
	}

	private GridPane inputFieldsGrid() {
					
		GridPane grid = new GridPane();
		grid.setVgap(10);
		grid.setHgap(10);
		
		ObservableList<String> consultants = FXCollections.observableArrayList(Controller.getInstance().getConsultantData());
		
		// adding labels
		Label nameLabel = new Label("Consultant's Name:");
		Label phoneLabel = new Label("Consultant's Phone");
		Label expertiseLabel = new Label("Consultant's Expertise");
		
		Label fname = new Label(this.consultant.getName().getFirstName());
		Label lname = new Label(this.consultant.getName().getLastName());
		phoneInput = new TextField();
		phoneInput.setPromptText("000-000-000");
		phoneInput.setText(this.consultant.getPhone());
		
		expertiseInput = new TextField();
		expertiseInput.setPromptText("General");
		expertiseInput.setText(this.consultant.getExpertise());
		
		// creating hBox and addAll
		HBox nameBox = new HBox(5);
		HBox phoneBox = new HBox(5);
		HBox expertiseBox = new HBox(5);

		
		nameBox.getChildren().addAll(nameLabel, fname, lname);
		phoneBox.getChildren().addAll(phoneLabel, phoneInput);
		expertiseBox.getChildren().addAll(expertiseLabel, expertiseInput);

		grid.add(nameBox, 0, 0, 1, 1);
		grid.add(phoneBox, 0, 1, 1, 1);
		grid.add(expertiseBox, 0, 2, 1, 1);

		return grid;		
	}
}