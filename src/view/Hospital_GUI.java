//Gabriela Pinheiro - R00225375 - Project_Part1

//if(object instanceof Patient) {
//			
//		}

package view;

import java.time.LocalDate;
import java.util.Optional;
import controller.Controller;
import model.AddDialog;
import model.AddVisitDialog;
import model.AlertBox;
import model.Consultant;
import model.Name;
import model.Patient;
import model.Visit;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Hospital_GUI extends VBox{
	
	private TabPane tabPane;
	private Tab patientTab, consultantTab;
	private Button addPButton, addCButton, removeButton, editButton, addVisitButton, loadButton, saveButton, exitButton;
	private TextField nameInput, studentIDInput, dobInput;
	private HBox patientTabBox, consultantTabBox, nameBox, studentIDBox, dobBox ;
	private Label nameLabel, studentIDLabel, dobLabel;
	private GridPane grid;
	private ListView patientListView, consultantListView;
	private int selectedIndex = -1;
	protected ListProperty<String> listProperty = new SimpleListProperty<>();
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
		MenuItem saveToFileItem = new MenuItem("Save to file");
		MenuItem aboutItem = new MenuItem("About");
		
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
		addCButton.setOnAction(e -> System.out.println("Add in consultant"));
		
		editButton = new Button("Edit Consultant");
		editButton.setOnAction(e -> System.out.println("Edit in consultant"));
		
		removeButton = new Button("Remove Consultant");
		removeButton.setOnAction(e -> removeConsultant());
		
		VBox consultantButtonsBox = new VBox(10);
		consultantButtonsBox.setPadding(new Insets(20));

		consultantButtonsBox.getChildren().addAll(addCButton, editButton, removeButton);
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
		
		removeButton = new Button("Remove Patient");
		removeButton.setOnAction(e -> removePatient());
		
		addVisitButton = new Button("Add Visit");
		addVisitButton.setOnAction(e -> addPatientVisit());
		
		VBox patientButtonsBox = new VBox(10);
		patientButtonsBox.setPadding(new Insets(20));

		patientButtonsBox.getChildren().addAll(addPButton, editButton, removeButton, addVisitButton);
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
		        		selectedIndex = patientListView.getSelectionModel().getSelectedIndex();
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
		        		selectedIndex = consultantListView.getSelectionModel().getSelectedIndex();
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
			this.patientListView.itemsProperty().bind(listProperty);		
			this.listProperty.set(FXCollections.observableArrayList(Controller.getInstance().getPatientData()));
			System.out.println("GUI line 177 " + FXCollections.observableArrayList(Controller.getInstance().getPatientData()));
		} else {
//			System.out.println("GIU line 179");
			this.patientListView.getItems().clear();
		}
	}

	private void addPatient() {
		System.out.println("GIU line 185");
//		addPButton.setUpWindow();  //possible solution for window appearing only on second click
		EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Dialog<Patient> personDialog = new AddDialog(Controller.getInstance().addPatient(new Patient(new Name("", ""), "")));
				Optional<Patient> result = personDialog.showAndWait();
//				System.out.println("GIU line 163 " + result.isPresent());
				
				if(result.isPresent()) {
					Patient patient = result.get();
					patientListView.getItems().add(patient.getName().getFirstName() + " " + patient.getName().getLastName() + " " + patient.getPatientVisits().toString());					
					setupListViewData();
				}
			}
		};
		addPButton.setOnAction(eventHandler);
	}
	
	
	
//	private void addConsultant() {
////		addPButton.setUpWindow();  //possible solution for window appearing only on second click
//		EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent event) {
//				Dialog<Consultant> personDialog = new AddDialog(Controller.getInstance().addConsultant(new Consultant(new Name("", ""), "")));
//				Optional<Consultant> result = personDialog.showAndWait();
////				System.out.println("GIU line 163 " + result.isPresent());
//				
//				if(result.isPresent()) {
//					Consultant consultant = result.get();
////					listView.getItems().add(patient.getName());
//					consultantListView.getItems().add(consultant.getName().getFirstName() + " " + consultant.getName().getLastName());					
//					setupListViewData();
//				}
//			}
//		};
//		addPButton.setOnAction(eventHandler);
//	}

	private void removePatient() {
		if(this.selectedIndex < 0) {
			alertBox.dialogInformation("Please select an item form the list", null);
		}
		else {
			Controller.getInstance().removePatient(this.selectedIndex);
			this.setupListViewData();
		}
		
	}
	
	private void removeConsultant() {
		if(this.selectedIndex < 0) {
			alertBox.dialogInformation("Please select an item form the list", null);
		}
		else {
			Controller.getInstance().removeConsultant(this.selectedIndex);
			this.setupListViewData();
		}
		
	}

	private void addPatientVisit() {
		System.out.println("GIU line 250");
//		addPButton.setUpWindow();  //possible solution for window appearing only on second click
		EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("GIU line 255");
				Patient selectedPatient = Controller.getInstance().searchPatient(selectedIndex);
				Dialog<Visit> visitDialog = new AddVisitDialog(selectedPatient, selectedPatient.addVisit(new Visit(LocalDate.of(2024, 03, 30), ""))); ///TODO date to be today's date
//				Dialog<Visit> visitDialog = new AddVisitDialog(Controller.getInstance().searchPatient(new Patient(new Name("", ""), "")));
				Optional<Visit> result = visitDialog.showAndWait();
//				System.out.println("GIU line 163 " + result.isPresent());
				
				if(result.isPresent()) {
					Visit visit = result.get();
					System.out.println("GUI line 266 " + selectedPatient.getPatientVisits());

//					patientListView.getItems().add(visit.getDateOfVisit() + " " + visit.getNotes());					
//					setupListViewData();
				}
			}
		};
		addVisitButton.setOnAction(eventHandler);
	}

}
