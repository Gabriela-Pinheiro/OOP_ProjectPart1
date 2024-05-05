//Gabriela Pinheiro - R00225375 - Project_Part1

package model;

import java.io.Serializable;

import validation.Validation;

public abstract class Person implements Serializable{
	private Name name;
	private int ID;
	private String phone;
	
	public Person(Name name, String phone) {
		this.name = name;
		this.setPhone(phone);
	}

	public Name getName() {
		return name;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String n) {
		if(!Validation.isPhone(n)) {
			throw new Error("Phone cannot contain any letter");
		}
		this.phone = n;
	}
	
	public void setId(int id) {
		if(this.getId() == 0) {
			this.ID = id;
		}
	}
	
	public int getId() {
		return this.ID;
	}

	@Override
	public String toString() {
		return "\nName: " + this.getName() + "\nId: " + this.getId() + "\nPhone: " + this.getPhone() + "\n";
	}


}
