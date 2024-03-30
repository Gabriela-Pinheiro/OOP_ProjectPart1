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
	
	public ArrayList<String> getPatientsData(){
		ArrayList<String> patientsData = new ArrayList<String>();
		for(Patient p: this.patients) {
			patientsData.add(p.toString());
		}
		return patientsData;
	}

	public void setPatients(ArrayList<Patient> patients) {
		this.patients = patients;
	}

	public Patient addPatient(Patient patient) {
		try {
			this.patients.add(patient);			
		} catch (Exception e) {
			return null;
		} 
		return patient;
	}
	
	public Patient searchPatient(String name) {
		Patient obj = null;
		//TODO cannot be case sensitive - all to upper or lower case
		for(Patient p: this.patients) {
			if(p.getName().toString().equals(name)) {
				obj = p;
			}
		}
		return obj;
	}
	
	public boolean removePatient(String name) {
		Patient toRemove = this.searchPatient(name);
		
		if(toRemove != null) {
			this.patients.remove(toRemove);
			return true;
		}
		return false;
	}
	
	public boolean editPatient(String name) {
		Patient toEdit = this.searchPatient(name);
		
		if(toEdit != null) {
			//TODO edit patient
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "Patients: " + patients;
	}
	
	
}
