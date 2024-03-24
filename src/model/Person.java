//Gabriela Pinheiro - R00225375 - Final Project

package model;

import java.io.Serializable;
import java.util.Objects;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public abstract class Person implements Serializable{
	private Name name;
	protected String id;
	private StringProperty phone;
	
	public Person(Name name, String phone) {
		this.name = name;
		this.phone = new SimpleStringProperty(phone);
	}

	public Name getName() {
		return name;
	}
	
	public StringProperty getPhone() {
		return phone;
	}
	
	protected void setId(String prefix, int id) {
		this.id = prefix + id;
	}
	
	public String getId() {
		return this.id;
	}

	@Override
	public String toString() {
		return "\nName: " + this.getName() + "\nId: " + this.getId() + "\nPhone: " + this.getPhone() + "\n";
	}


}
