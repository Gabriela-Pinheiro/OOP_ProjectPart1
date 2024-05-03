//Gabriela Pinheiro - R00225375 - Project_Part1
package model;

import java.io.Serializable;
import java.util.Objects;

import validation.Validation;

public class Name implements Serializable{
	private String firstName;
	private String lastName;

	
	public Name(String firstName, String lastName) {
		super();
		this.setFirstName(firstName);
		this.setLastName(lastName);
	}
	
	public void setFirstName(String firstName) {
		if(Validation.hasAnyNumber(firstName)) {
			throw new Error("First Name cannot contain any number");
		}
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) {
		if(Validation.hasAnyNumber(lastName)) {
			throw new Error("Last Name cannot contain any number");
		}
		this.lastName = lastName;
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
		return firstName + " " + lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
}
