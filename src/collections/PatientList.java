//Gabriela Pinheiro - R00225375 - Project_Part1

package collections;

import java.io.Serializable;
import java.util.ArrayList;
import model.Patient;
import model.Visit;

public class PatientList implements Serializable{
	
	private ArrayList<Patient> patients;
	
	public PatientList() {
		this.patients = new ArrayList<Patient>();
	}
		
	public ArrayList<Patient> getPatients() {
		return this.patients;
	}
	
	public ArrayList<String> getPatientData(){
		ArrayList<String> patientData = new ArrayList<String>();
		for(Patient p: this.patients) {
			patientData.add(p.toString());
		}
		return patientData;
	}

	public void setPatients(ArrayList<Patient> patients) {
		this.patients = patients;
	}

	public boolean addPatients(Patient patient) {
		try {
			this.patients.add(patient);			
		} catch (Exception e) {
			return false;
		} 
		return true;
	}

	@Override
	public String toString() {
		return "Patients: " + patients;
	}
	
	
}
