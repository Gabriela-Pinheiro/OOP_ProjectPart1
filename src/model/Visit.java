//Gabriela Pinheiro - R00225375 - Project_Part2

package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.DatePicker;

public class Visit implements Serializable{

	private LocalDate dateOfVisit;
	private String ilness;
	private String notes;
	
	@SuppressWarnings("unchecked")
	public Visit(LocalDate dateOfVisit, String notes, String ilness, String patietnId) {
		this.dateOfVisit = 	dateOfVisit;
		this.notes = notes;
		this.ilness = ilness;
	}
	
	public LocalDate getDateOfVisit() {
		return dateOfVisit;
	}

	public String getNotes() {
		return notes;
	}
	
	public String getIlness() {
		return ilness;
	}

	public void setIlness(String ilness) {
		this.ilness = ilness;
	}

	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
		String formattedString = dateOfVisit.format(formatter);
		return "Visit on " + formattedString + "to treat " + getIlness() + " - Notes: " + this.getNotes() + "\n";
	}
}

