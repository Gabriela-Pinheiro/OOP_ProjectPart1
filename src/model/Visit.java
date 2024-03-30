//Gabriela Pinheiro - R00225375 - Project_Part1

package model;

import java.io.Serializable;
import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.DatePicker;

public class Visit implements Serializable{

	private ObjectProperty<LocalDate> dateOfVisit;
	private StringProperty notes;
	
	@SuppressWarnings("unchecked")
	public Visit(LocalDate dateOfVisit, String notes) {
		this.dateOfVisit = 	new SimpleObjectProperty(dateOfVisit);
		this.notes = new SimpleStringProperty(notes);
	}

	public ObjectProperty<LocalDate> getDateOfVisitProperty() {
		//check to return the value itself
		return dateOfVisit;
	}
	
	public LocalDate getDateOfVisit() {
		//check to return the value itself
		return dateOfVisit.getValue();
	}

	public StringProperty getNotesProperty() {
		return notes;
	}
	
	public String getNotes() {
		return notes.get();
	}

//	public void setNotes(String notes) {
//		this.notes += "\n" + notes;
//	}

	@Override
	public String toString() {
		return "\nVisit on " + dateOfVisit.getValue() + ", notes: " + notes.get() + ".";
	}
}

