//Gabriela Pinheiro - R00225375 - Project_Part1

package view;

import java.time.LocalDate;
import java.util.Optional;
import controller.Controller;
import model.Consultant;
import model.Name;
import model.Patient;
import model.Visit;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Hospital_GUI extends VBox{
	
	private TabPane tabPane;
	private Tab patientTab, consultantTab;
	private Button addPButton, addCButton, removePButton, removeCButton, editButton, addVisitButton, loadButton, saveButton, exitButton;
	private HBox patientTabBox, consultantTabBox;
	private GridPane grid;
	private ListView patientListView, consultantListView;
	private int patientSelectedIndex = -1;
	private int consultantSelectedIndex = -1;
	protected ListProperty<String> patientListProperty = new SimpleListProperty<>();
	protected ListProperty<String> consultantListProperty = new SimpleListProperty<>();
	private AlertBox alertBox = new AlertBox();
	private MenuBar menuBar;
	
	public Hospital_GUI() {
		super();
		setUpUI();
	}

	private void setUpUI() {
		this.getChildren().addAll(this.setUpTabPane(), this.menuBar());
	}
	
	private MenuBar menuBar() {
		menuBar = new MenuBar();
		Menu file = new Menu("File");
		Menu help = new Menu("Help");
		menuBar.setUseSystemMenuBar(true);
		
		menuBar.getMenus().addAll(file, help);
		
		MenuItem loadFromFileItem = new MenuItem("Load from file");
		loadFromFileItem.setOnAction(e -> loadFromFile());
		MenuItem saveToFileItem = new MenuItem("Save to file");
		saveToFileItem.setOnAction(e -> loadFromFile());
		MenuItem aboutItem = new MenuItem("About");
		aboutItem.setOnAction(e -> about());
		
		file.getItems().addAll(loadFromFileItem, saveToFileItem);		
		help.getItems().addAll(aboutItem);		
		
		return menuBar;
	}
	
	private TabPane setUpTabPane() {
		tabPane = new TabPane();
		patientTab = new Tab("Patient Management");
		consultantTab = new Tab("Consultant Management");
		
		tabPane.getTabs().add(patientTab);
		patientTab.setClosable(false);
		tabPane.getTabs().add(consultantTab);
		consultantTab.setClosable(false);
		
		patientTab.setContent(this.patientTab());
		consultantTab.setContent(this.consultantTab());
		
		return tabPane;
	}
	
	private HBox consultantTab() {
		consultantTabBox = new HBox();
		consultantTabBox.setPadding(new Insets(20));
		
		//buttons
		addCButton = new Button("Add Consultant");
		addCButton.setOnAction(e -> addConsultant());
		
		editButton = new Button("Edit Consultant");
		editButton.setOnAction(e -> System.out.println("Edit in consultant"));
		
		removeCButton = new Button("Remove Consultant");
		removeCButton .setOnAction(e -> removeConsultant());
		
		VBox consultantButtonsBox = new VBox(10);
		consultantButtonsBox.setPadding(new Insets(20));

		consultantButtonsBox.getChildren().addAll(addCButton, editButton, removeCButton);
		consultantTabBox.getChildren().addAll(this.addListView("consultant"), consultantButtonsBox);
		
		return consultantTabBox;
	}
	
	private HBox patientTab() {
		patientTabBox = new HBox();
		patientTabBox.setPadding(new Insets(20));
		
		//buttons
		addPButton = new Button("Add Patient");
		addPButton.setOnAction(e -> addPatient());
		
		editButton = new Button("Edit Patient");
		editButton.setOnAction(e -> System.out.println("Edit a patient"));
		
		removePButton = new Button("Remove Patient");
		removePButton.setOnAction(e -> removePatient());
		
		addVisitButton = new Button("Add Visit");
		addVisitButton.setOnAction(e -> addPatientVisit());
		
		VBox patientButtonsBox = new VBox(10);
		patientButtonsBox.setPadding(new Insets(20));

		patientButtonsBox.getChildren().addAll(addPButton, editButton, removePButton, addVisitButton);
		patientTabBox.getChildren().addAll(this.addListView("patient"), patientButtonsBox);
		
		return patientTabBox;
	}

	private ListView addListView(String s) {
		
		if(s == "patient") {
			
			this.patientListView = new ListView();
			this.patientListView.setPrefWidth(300);
			this.patientListView.setPrefHeight(450);
			patientListView.setPlaceholder(new Label("Nothing In List"));
			
			this.patientListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
	
		        @Override
		        public void handle(MouseEvent event) {
		        	if(Controller.getInstance().getPatientData().size() > 0)
		        		patientSelectedIndex = patientListView.getSelectionModel().getSelectedIndex();
		        }
		    });
		
			this.setupListViewData();
			return this.patientListView;
			
		} else if (s == "consultant") {
			
			this.consultantListView = new ListView();
			this.consultantListView.setPrefWidth(300);
			this.consultantListView.setPrefHeight(450);
			consultantListView.setPlaceholder(new Label("Nothing In List"));
			
			this.consultantListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
	
		        @Override
		        public void handle(MouseEvent event) {
		        	if(Controller.getInstance().getConsultantData().size() > 0)
		        		consultantSelectedIndex = consultantListView.getSelectionModel().getSelectedIndex();
		        }
		    });
		
			this.setupListViewData();
			return this.consultantListView;
		} else {
			return this.consultantListView;
		}
		
	}
	
	private void setupListViewData() {
//		System.out.println("GUI line 173 size " + Controller.getInstance().getPatientData().size());
		if(Controller.getInstance().getPatientData().size() > 0) {
			this.patientListView.itemsProperty().bind(patientListProperty);		
			this.patientListProperty.set(FXCollections.observableArrayList(Controller.getInstance().getPatientData()));
			System.out.println("GUI line 192 " + FXCollections.observableArrayList(Controller.getInstance().getPatientData()));
		} else {
//			System.out.println("GIU line 179");
			this.patientListView.getItems().clear();
		}
	}
	
	private void setupConsultantListViewData() {
//		System.out.println("GUI line 173 size " + Controller.getInstance().getPatientData().size());
		if(Controller.getInstance().getConsultantData().size() > 0) {
			this.consultantListView.itemsProperty().bind(consultantListProperty);
			
			this.consultantListProperty.set(FXCollections.observableArrayList(Controller.getInstance().getConsultantData()));
			System.out.println("GUI line 204 " + FXCollections.observableArrayList(Controller.getInstance().getConsultantData()));
		} else {
//			System.out.println("GIU line 179");
			this.consultantListView.getItems().clear();
		}
	}

	private void addPatient() {
		System.out.println("GIU line 212");

//		Dialog<Patient> personDialog = new AddPatientDialog(Controller.getInstance().addPatient(new Patient(new Name("", ""), "")));  //original code
		Dialog<Patient> personDialog = new AddPatientDialog(Controller.getInstance().addPatient(new Patient(new Name("", ""), "")));
		Optional<Patient> result = personDialog.showAndWait();
		
		if(result.isPresent()) {
			Patient patient = result.get();
			patientListView.getItems().add(patient.getName().getFirstName() + " " + patient.getName().getLastName() + " " + patient.getPatientVisits().toString());					
			setupListViewData();
		}
	}
	
	private void addConsultant() {
		Dialog<Consultant> personDialog = new AddConsultantDialog(Controller.getInstance().addConsultant(new Consultant(new Name("", ""), "", "")));
		Optional<Consultant> result = personDialog.showAndWait();
		System.out.println("GIU line 227 " + result.isPresent());
		
		if(result.isPresent()) {
			Consultant consultant = result.get();
			consultantListView.getItems().add(consultant + " " + consultant.getName().getFirstName() + " " + consultant.getName().getLastName());					
			setupConsultantListViewData();
		}
	}

	private void removePatient() {
		if(this.patientSelectedIndex < 0) {
			alertBox.dialogInformation("Please select an item form the list", null);
		}
		else {
			Controller.getInstance().removePatient(this.patientSelectedIndex);
			this.setupListViewData();
		}
		
	}
	
	private void removeConsultant() {
		if(this.consultantSelectedIndex < 0) {
			alertBox.dialogInformation("Please select an item form the list", null);
		}
		else {
			Controller.getInstance().removeConsultant(this.consultantSelectedIndex);
			this.setupConsultantListViewData();
		}
		
	}

	private void addPatientVisit() {

		//TODO if no patient is selected informationBox - select a patient
		System.out.println("GIU line 266");
		Patient selectedPatient = Controller.getInstance().searchPatient(patientSelectedIndex);
		Dialog<Visit> visitDialog = new AddVisitDialog(selectedPatient, selectedPatient.addVisit(new Visit(LocalDate.now(), "")));
		Optional<Visit> result = visitDialog.showAndWait();
//			System.out.println("GIU line 163 " + result.isPresent());
		
		if(result.isPresent()) {
			Visit visit = result.get();
//			patientListView.getItems().add(visit.getDateOfVisit() + " " + visit.getNotes());					
		}
		//TODO not to add empty patient
		
	}

	private void loadFromFile() {
		Controller.getInstance().loadFromFile();
	}
	
	private void saveToFile() {
		Controller.getInstance().saveToFile();
	}
	
	private void about() {
		alertBox.dialogInformation("Hospital Consultancy Application was developed by  Gabriela Pinheiro", "Student in Higher Certificate in Software Development\n©2024 All rights reserved");
	}
}
