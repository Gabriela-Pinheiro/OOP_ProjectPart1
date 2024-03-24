//Gabriela Pinheiro - R00225375 - Project_Part1

package view;

import java.util.Optional;

import controller.Controller;
import model.AddDialog;
import model.Name;
import model.Patient;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Hospital_GUI extends VBox{
	
	private TabPane tabPane;
	private Tab patientTab, consultantTab;
	private Button addButton, removeButton, editButton, addVisitButton, loadButton, saveButton, exitButton;
	private TextField nameInput, studentIDInput, dobInput;
	private HBox patientTabBox, consultantTabBox, nameBox, studentIDBox, dobBox ;
	private Label nameLabel, studentIDLabel, dobLabel;
	private GridPane grid;
	private ListView listView;
	private int selectedIndex = -1;
	protected ListProperty<String> listProperty = new SimpleListProperty<>();
//	private AlertBox alertBox = new AlertBox();

	
	public Hospital_GUI() {
		super();
		setUpUI();		
	}

	private void setUpUI() {
		this.getChildren().addAll(this.setUpTabPane());
		
	}
	
	private TabPane setUpTabPane() {
		tabPane = new TabPane();
		patientTab = new Tab("Patient Management");
		consultantTab = new Tab("Consultant Management");
		
		tabPane.getTabs().add(patientTab);
		patientTab.setClosable(false);
		tabPane.getTabs().add(consultantTab);
		consultantTab.setClosable(false);
		
		patientTab.setContent(this.addListView());
		patientTab.setContent(this.patientTab());
		consultantTab.setContent(this.consultantTab());
		
		return tabPane;
	}
	
	private HBox consultantTab() {
		consultantTabBox = new HBox();
		consultantTabBox.setPadding(new Insets(20));
		
		//buttons
		addButton = new Button("Add Consultant");
		addButton.setOnAction(e -> System.out.println("Add in consultant"));
		
		editButton = new Button("Edit Consultant");
		addButton.setOnAction(e -> System.out.println("Edit in consultant"));
		
		removeButton = new Button("Remove Consultant");
		removeButton.setOnAction(e -> System.out.println("Remove in consultant"));
		
		VBox consultantButtonsBox = new VBox(10);
		consultantButtonsBox.setPadding(new Insets(20));

		consultantButtonsBox.getChildren().addAll(addButton, editButton, removeButton);
		consultantTabBox.getChildren().addAll(this.addListView(), consultantButtonsBox);
		
		return consultantTabBox;
	}
	
	private HBox patientTab() {
		patientTabBox = new HBox();
		patientTabBox.setPadding(new Insets(20));
		//buttons
		addButton = new Button("Add Patient");
		addButton.setOnAction(e -> add());
		
		editButton = new Button("Edit Patient");
		editButton.setOnAction(e -> System.out.println("Edit a patient"));
		
		removeButton = new Button("Remove Patient");
		removeButton.setOnAction(e -> System.out.println("Remove in patient"));
		
		addVisitButton = new Button("Add Visit");
		addVisitButton.setOnAction(e -> System.out.println("Add patient visit"));
		
		VBox patientButtonsBox = new VBox(10);
		patientButtonsBox.setPadding(new Insets(20));

		patientButtonsBox.getChildren().addAll(addButton, editButton, removeButton, addVisitButton);
		patientTabBox.getChildren().addAll(this.addListView(), patientButtonsBox);
		
		return patientTabBox;
	}

	private ListView addListView() {
		this.listView = new ListView();
		this.listView.setPrefWidth(300);
		this.listView.setPrefHeight(450);
		listView.setPlaceholder(new Label("No Content In List"));
		
//		this.listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
//
//	        @Override
//	        public void handle(MouseEvent event) {
//	        	if(Controller.getInstance().getStudentInfo().size() > 0)
//	        		selectedIndex = listView.getSelectionModel().getSelectedIndex();
//	        		//System.out.println("list MTU_GUI addlistview line 120");
//	        }
//	    });
		
//		this.setupListViewData();
		
		return this.listView;
	}
	
	private void setupListViewData() {
		if(Controller.getInstance().getPatientData().size() > 0) {
			this.listView.itemsProperty().bind(listProperty);		
			this.listProperty.set(FXCollections.observableArrayList(Controller.getInstance().getPatientData()));
//			this.delete.setDisable(false);
		}
		else {
			this.listView.getItems().clear();
//			this.delete.setDisable(true);
			System.out.println("set up list view line 122");
		}
	}

	private void add() {
		
		Dialog<Object> personDialog = new AddDialog(new Patient(new Name("", ""), ""));
		Optional<Object> result = personDialog.showAndWait();
		if(result.isPresent()) {
			Patient patient = (Patient) result.get();
//			listView.getItems().add(patient);
		}
		
		System.out.println("Add in patient");
		
		
	}
}
