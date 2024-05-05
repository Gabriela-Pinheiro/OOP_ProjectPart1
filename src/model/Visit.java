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
import validation.Validation;

public class Visit implements Serializable{

	private LocalDate dateOfVisit;
	private String ilness;
	private String notes;
	
	@SuppressWarnings("unchecked")
	public Visit(LocalDate dateOfVisit, String notes, String ilness, int patietnId) {
		this.dateOfVisit = 	dateOfVisit;
		this.notes = notes;
		this.setIlness(ilness);
	}
	
	public LocalDate getDateOfVisit() {
		return dateOfVisit;
	}
	
	public String getDateOfVisitAsSQLString() {

		return this.getDateOfVisit().toString();
	}

	public String getNotes() {
		return notes;
	}
	
	public String getIlness() {
		return ilness;
	}

	public void setIlness(String ilness) {
		if(Validation.hasAnyNumber(ilness)) {
			throw new Error("Ilness cannot contain any number");
		}
		this.ilness = ilness;
	}

	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
		String formattedString = dateOfVisit.format(formatter);
		return "Visit on " + formattedString + "to treat " + getIlness() + " - Notes: " + this.getNotes() + "\n";
	}
}

