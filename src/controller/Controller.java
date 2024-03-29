//Gabriela Pinheiro - R00225375 - Project_Part1

package controller;

import java.util.ArrayList;

import collections.PatientList;
import collections.VisitList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Patient;
import model.Practice;
import model.SerialStorage;
import model.Consultant;
import view.Hospital_GUI;


public class Controller {
	
	private static volatile Controller object;
	private Practice practice; //TODO my Controller needs to be a practice right?
	private PatientList patients;
	private VisitList visits;
	private transient Stage stage;
	private transient Scene scene;
	private SerialStorage store;


	//this will be able to reference both objects???
	private Controller() {
		practice = new Practice(); //TODO i think thats what makes more sense. both
		patients = new PatientList();
		object = this;
	}

	public static Controller getInstance() {
		if(object == null) {
			synchronized(Controller.class){
				if(object == null) {
					object = new Controller();
				}
			}
		}
		return object;
	}
	
	public void init(Stage stage) {
		scene = new Scene(new Hospital_GUI(),550,550);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		this.setStage(stage);
		stage.setScene(scene);
		stage.show();
	}
	
	private void setStage(Stage stage) {
		object.stage = stage;
		stage.setTitle("Hospital Consultancy System");
		stage.setOnCloseRequest(event -> {
			//alertBox.dialogConfirmation();
		});
	}

	public Stage getStage() {
		return this.stage;
	}
	
	public ArrayList<String> getPatientData() {
		return object.patients.getPatientsData();
	}
	
	public ArrayList<String> getConsultantData() {
		return object.practice.getConsultantData();
	}
	
	public Consultant addConsultant(Consultant c) {
		return object.practice.addConsultant(c);
	}
	
	public Patient addPatient(Patient p) {
		return object.patients.addPatient(p);
	}
	
	public void removeConsultant(int i) {
		//how to compare if the Object o is a Patient or a Consultant?
		object.practice.removeConsultant(this.practice.getConsultants().get(i).getName().toString());
	}
	
	public void removePatient(int i) {
		//how to compare if the Object o is a Patient or a Consultant?
		object.patients.removePatient(this.patients.getPatients().get(i).getName().toString());
	}

	public void setStorage() {
		store = new SerialStorage();
	}

	public boolean saveToFile() { //TODO now saving patients only
		return store.writeFile(object.practice);
	}
	
	
}
