//Gabriela Pinheiro - R00225375 - Project_Part2

package controller;

import java.io.Serializable;
import java.util.ArrayList;
import collections.PatientList;
import data.DataHelper;
import data.MySQLStorage;
import data.SerialStorage;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Patient;
import model.Practice;
import model.Consultant;
import view.Hospital_GUI;
import view.AlertBox;


public class Controller implements Serializable{
	
	private static volatile Controller object;
	private Practice practice;
	private transient Stage stage;
	private transient Scene scene;
	private AlertBox alertBox = new AlertBox();
	private PatientList patients;
	private DataHelper dataHelper;
	
	private Controller() {
		practice = new Practice();
		object = this;
		this.dataHelper = new MySQLStorage();
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
			alertBox.dialogConfirmation();
		});
	}

	public Stage getStage() {
		return this.stage;
	}
	
	public ArrayList<String> getPatientData() {
		
		patients = new PatientList();
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
				 patient = p;
			}
		}
		return patient;
	}

	public Consultant addConsultant(Consultant c) {
		return object.practice.addConsultant(c);
	}
	
	public void removePatient(Patient toCompare) {
		for(Consultant c: this.practice.getConsultants()) {
			for(Patient p: c.getPatients()) {
				if(p == toCompare) {
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
			for(Patient p: c.getPatients()) {
				if(p.toString().equals(selectedPatient)) {
					object = p;
				}
			}
		}
		return object;
	}
	
	public Consultant searchPatientsConsultant(Patient selectedPatient) {
		Consultant object = null;
		for(Consultant c: this.practice.getConsultants()) {
			for(Patient p: c.getPatients()) {
				if(p == selectedPatient) {
					object = c;
				}
			}
		}
		return object;
	}
	
	public Consultant searchConsultant(int i) {
		return object.practice.searchConsltant(this.practice.getConsultants().get(i).getName().toString());
	}
	
	public Patient editPatient(Consultant c, Patient selectedPatient, String phone) {
		Patient patient = null;
		for(Patient p: c.getPatients()) {
				if(p == selectedPatient) {
					p.setPhone(phone);
					
				}
			}
		return patient;
	}
	
	public Consultant editConsultant(Consultant selectedConsultant,  String phone, String expertise) {
		Consultant consultant = null;
		for(Consultant c: practice.getConsultants()) {
				if(c == selectedConsultant) {
					c.setPhone(phone);
					c.setExpertise(expertise);
					consultant = c;
				}
			}
		return consultant;
	}
	
	public void setStorage() {
		new SerialStorage();
	}

	public void saveToFile() {
		this.dataHelper.createOrUpdate(object.practice);
	}
	
	public void loadFromFile() {
		Object obj = this.dataHelper.read();
		if(obj != null) {
			this.practice = (Practice)obj;
			int lastC = this.practice.getConsultantData().size()-1;
			Consultant.ID = lastC+2;
			int lastP = this.getPatientData().size()-1;
			Patient.ID = lastP+2;
		}
		
	}
	
}
