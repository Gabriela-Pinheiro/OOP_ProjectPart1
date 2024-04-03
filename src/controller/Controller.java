//Gabriela Pinheiro - R00225375 - Project_Part1

package controller;

import java.io.Serializable;
import java.util.ArrayList;

import collections.PatientList;
import collections.VisitList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Patient;
import model.Practice;
import model.SerialStorage;
import model.Test;
import model.Consultant;
import view.Hospital_GUI;


public class Controller implements Serializable{
	
	private static volatile Controller object;
	private Practice practice;
	private transient Stage stage;
	private transient Scene scene;
	
	private Controller() {
		practice = new Practice();
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
		//TODO dont allow close, ask to save instead
		stage.setOnCloseRequest(event -> {
			//alertBox.dialogConfirmation();
		});
	}

	public Stage getStage() {
		return this.stage;
	}
	
	public ArrayList<String> getPatientData() {
		
		PatientList patients = new PatientList();
		int i = 1;
		for(Consultant c: practice.getConsultants()) {
			for(Patient p: c.getPatients()) {					
				patients.addPatient(p);
				i++;
			}
		}
		return patients.getPatientsData();
	}
	
	public ArrayList<String> getConsultantData() {
		return object.practice.getConsultantData();
	}
	
	public Patient addPatientToConsultant(int consultantIndex, Patient p) {
		Patient patient = null;
		
		for(Consultant c: this.practice.getConsultants()) {
			if(c.getName().equals(this.practice.getConsultants().get(consultantIndex).getName())) {		
				 c.addPatient(p);
				 System.out.println("80 controller" + c.getPatients());
				 patient = p;
			}
		}
		return patient;
	}

	
	public Consultant addConsultant(Consultant c) {
		return object.practice.addConsultant(c);
	}
	
	public void removePatient(String toCompare) {
		//object.patients.removePatient(p.getName().toString());
		for(Consultant c: this.practice.getConsultants()) {
			System.out.println("controller 108");
			for(Patient p: c.getPatients()) {
				System.out.println("controller 110");
				if(p.toString().equals(toCompare)) {
					System.out.println("controller 112");
					c.removePatient(p);
					break;
				}
			}
		}
	}

	public void removeConsultant(int i) {
		object.practice.removeConsultant(this.practice.getConsultants().get(i).getName().toString());
	}
	
	public Patient searchPatient(String selectedPatient) {
		Patient object = null;
		for(Consultant c: this.practice.getConsultants()) {
			System.out.println("controller 130");
			for(Patient p: c.getPatients()) {
				System.out.println("controller 132");
				if(p.toString().equals(selectedPatient)) {
					System.out.println("controller 134");
					object = p;
				}
			}
		}
		return object;
		
	}
	
	public Consultant searchConsultant(int i) {
		
		return object.practice.searchConsltant(this.practice.getConsultants().get(i).getName().toString());
	}
	
	public void setStorage() {
		new SerialStorage();
	}

	public void saveToFile() {
		SerialStorage.writeFile(object.practice);
	}
	
	public void loadFromFile() {
		Object obj = SerialStorage.readFile();
		this.practice = (Practice)obj;
	}
	
}
