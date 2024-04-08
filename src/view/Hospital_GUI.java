//Gabriela Pinheiro - R00225375 - Project_Part1

package view;

import java.util.Optional;
import controller.Controller;
import model.Consultant;
import model.Patient;
import model.Visit;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Hospital_GUI extends VBox {
	
	private TabPane tabPane;
	private Tab patientTab, consultantTab;
	private Button addPButton, addCButton, removePButton, removeCButton, editPButton, editCButton, addVisitButton;
	private HBox patientTabBox, consultantTabBox;
	private ListView patientListView, consultantListView;
	private int patientSelectedIndex = -1;
	private Patient selectedPatient;
	private Consultant selectedPatientsConsultant, selectedConsultant;
	private String selectedConsultantsName;
	private int selectedConsultantsIndex = -1;
	protected transient ListProperty<String> patientListProperty = new SimpleListProperty<>();
	protected transient ListProperty<String> consultantListProperty = new SimpleListProperty<>();
	private AlertBox alertBox = new AlertBox();
	private MenuBar menuBar;
	
	public Hospital_GUI() {
		super();
		setUpUI();
		loadFromFile();
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
		saveToFileItem.setOnAction(e -> saveToFile());
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
		
		addCButton = new Button("Add Consultant");
		addCButton.setOnAction(e -> addConsultant());
		
		editCButton = new Button("Edit Consultant");
		editCButton.setOnAction(e -> editConsultant());
		
		removeCButton = new Button("Remove Consultant");
		removeCButton .setOnAction(e -> removeConsultant());
		
		VBox consultantButtonsBox = new VBox(10);
		consultantButtonsBox.setPadding(new Insets(20));

		consultantButtonsBox.getChildren().addAll(addCButton, editCButton, removeCButton);
		consultantTabBox.getChildren().addAll(this.addConsultantsListView(), consultantButtonsBox);
		
		return consultantTabBox;
	}
	
	private HBox patientTab() {
		patientTabBox = new HBox();
		patientTabBox.setPadding(new Insets(20));
		
		addPButton = new Button("Add Patient");
		addPButton.setOnAction(e -> addPatient());
		
		editPButton = new Button("Edit Patient");
		editPButton.setOnAction(e -> editPatient());
		
		removePButton = new Button("Remove Patient");
		removePButton.setOnAction(e -> removePatient());
		
		addVisitButton = new Button("Add Visit");
		addVisitButton.setOnAction(e -> addPatientVisit());
		
		VBox patientButtonsBox = new VBox(10);
		patientButtonsBox.setPadding(new Insets(20));

		patientButtonsBox.getChildren().addAll(addPButton, editPButton, removePButton, addVisitButton);
		patientTabBox.getChildren().addAll(this.addPatientsListView(), patientButtonsBox);
		
		return patientTabBox;
	}

	private ListView addPatientsListView() { 
		this.patientListView = new ListView();
		this.patientListView.setPrefWidth(300);
		this.patientListView.setPrefHeight(450);
		patientListView.setPlaceholder(new Label("No Patients In List"));
		
		this.patientListView.setOnMouseClicked(new EventHandler<MouseEvent>() {

	        @Override
	        public void handle(MouseEvent event) {
	        	if(Controller.getInstance().getPatientData().size() > 0)
	        		patientSelectedIndex = patientListView.getSelectionModel().getSelectedIndex();
	        		selectedPatient = Controller.getInstance().searchPatient(patientListView.getSelectionModel().getSelectedItem().toString());
	        		selectedPatientsConsultant = Controller.getInstance().searchPatientsConsultant(selectedPatient);
		        	
	        }
	    });
	
		this.setupPatientListViewData();
		return this.patientListView;		
	}
	
	private ListView addConsultantsListView() { 		
		this.consultantListView = new ListView();
		this.consultantListView.setPrefWidth(300);
		this.consultantListView.setPrefHeight(450);
		consultantListView.setPlaceholder(new Label("No Consultants In List"));
		
		this.consultantListView.setOnMouseClicked(new EventHandler<MouseEvent>() {

	        @Override
	        public void handle(MouseEvent event) {
	        	if(Controller.getInstance().getConsultantData().size() > 0)
	        		selectedConsultantsIndex = consultantListView.getSelectionModel().getSelectedIndex();
	        		selectedConsultantsName = (String) consultantListView.getSelectionModel().getSelectedItem();
	        		selectedConsultant = Controller.getInstance().searchConsultant(selectedConsultantsIndex);
	        }
	    });
	
		this.setupConsultantListViewData();
		return this.consultantListView;
	}
	
	private void setupPatientListViewData() {
		if(Controller.getInstance().getPatientData().size() > 0) {
			Controller.getInstance().getPatientData();
			this.patientListView.itemsProperty().bind(patientListProperty);		
			this.patientListProperty.set(FXCollections.observableArrayList(Controller.getInstance().getPatientData()));
			this.removePButton.setDisable(false);
			this.editPButton.setDisable(false);
			this.addVisitButton.setDisable(false);
		} else {
			this.patientListView.getItems().clear();
			this.removePButton.setDisable(true);
			this.editPButton.setDisable(true);
			this.addVisitButton.setDisable(true);
		}
	}
	
	private void setupConsultantListViewData() {
		if(Controller.getInstance().getConsultantData().size() > 0) {
			this.consultantListView.itemsProperty().bind(consultantListProperty);
			
			this.consultantListProperty.set(FXCollections.observableArrayList(Controller.getInstance().getConsultantData()));
			this.addPButton.setDisable(false);
			this.removeCButton.setDisable(false);
			this.editCButton.setDisable(false);
		} else {
			this.addPButton.setDisable(true);
			this.removeCButton.setDisable(true);
			this.editCButton.setDisable(true);
			this.consultantListView.getItems().clear();
		}
	}

	private void addPatient() {
		Dialog<Patient> personDialog = new AddPatientDialog();
		Optional<Patient> result = personDialog.showAndWait();
		
		if(result.isPresent()) {
			setupPatientListViewData();
		}
	}
	
	private void addConsultant() {
		Dialog<Consultant> personDialog = new AddConsultantDialog();
		Optional<Consultant> result = personDialog.showAndWait();
		
		if(result.isPresent()) {
			Controller.getInstance().addConsultant(result.get());
			setupConsultantListViewData();
		}
	}

	private void removePatient() {
		if(this.patientSelectedIndex < 0) {
			alertBox.dialogInformation("Please, select a Patient to be removed", null);
		}
		else {
			Controller.getInstance().removePatient(selectedPatient);
			this.setupPatientListViewData();
		}
	}
	
	private void removeConsultant() {
		if(this.selectedConsultantsIndex < 0) {
			alertBox.dialogInformation("Please select an item form the list", null);
		}
		else {
			alertBox.dialogInformation("Consultant " + selectedConsultantsName + " is deleted", "As well as all its patients");
			Controller.getInstance().removeConsultant(this.selectedConsultantsIndex);
			this.setupConsultantListViewData();
			this.setupPatientListViewData();
		}
		
	}

	private void addPatientVisit() {

		if(selectedPatient == null) {
			alertBox.dialogInformation("Please, select a Patient to add a visit to", "You can only add a visit to an exisintg patient");

		} else {
			Dialog<Visit> visitDialog = new AddVisitDialog(selectedPatient);
			Optional<Visit> result = visitDialog.showAndWait();
			
			if(result.isPresent()) {
				Visit visit = result.get();
				alertBox.dialogInformation("Visit sucessfully added to\n" + selectedPatient.getName(), "" + selectedPatient.getPatientVisits());

			}
		}	
	}
	
	private void editPatient() {
		if(selectedPatient == null) {
			alertBox.dialogInformation("Please, select a Patient to be edit", null);

		} else {
			Dialog<Patient> editDialog = new EditPatientDialog(selectedPatient, selectedPatientsConsultant);
			Optional<Patient> result = editDialog.showAndWait();
			
			if(result.isPresent()) {
				Patient patient = result.get();
				alertBox.dialogInformation("Patient sucessfully changed", null);
			}
		}
		this.setupPatientListViewData();

	}
	
	private void editConsultant() { ///TODO finish method
		if(selectedConsultant == null) {
			alertBox.dialogInformation("Please, select a Consultant to be edit", null);
		} else {
			Dialog<Consultant> editDialog = new EditConsultantDialog(selectedConsultant);
			Optional<Consultant> result = editDialog.showAndWait();
			
			if(result.isPresent()) {
				Consultant consultant = result.get();
				alertBox.dialogInformation("Consultant sucessfully changed", null);
			}
		}
		this.setupConsultantListViewData();

	}

	private void loadFromFile() { //TODO validate if there is no data in practice, currently validation always returns true
		if(Controller.getInstance() != null) {
			Controller.getInstance().loadFromFile();
			this.setupConsultantListViewData();
			this.setupPatientListViewData();			
		}
	}
	
	private void saveToFile() {
		Controller.getInstance().saveToFile();
	}
	
	private void about() {
		alertBox.dialogInformation("Hospital Consultancy Application was developed by  Gabriela Pinheiro", "Student in Higher Certificate in Software Development\n©2024 1.0 All rights reserved");
	}
}
