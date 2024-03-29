//Gabriela Pinheiro - R00225375 - Project_Part1
package model;

import java.io.Serializable;
import java.util.Objects;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Name implements Serializable{
	private StringProperty firstName;
	private StringProperty lastName;

	
	public Name(String firstName, String lastName) {
		super();
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
	}

	@Override 
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Name other = (Name) obj;
		return Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName);
	}

	@Override
	public String toString() {
		return firstName.get() + " " + lastName.get();
	}
	
	public StringProperty getFirstNameProperty() {
		return firstName;
	}
	
	public StringProperty getLastNameProperty() {
		return lastName;
	}
	
	public String getFirstName() {
		return firstName.get();
	}
	
	public String getLastName() {
		return lastName.get();
	}
}
