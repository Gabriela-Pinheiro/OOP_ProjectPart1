//Gabriela Pinheiro - R00225375 - Final Project
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

	@Override // ??????
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
		return firstName + " " + lastName;
	}
	
	public StringProperty getFirstName() {
		return firstName;
	}
	
	public StringProperty getLastName() {
		return lastName;
	}
}
