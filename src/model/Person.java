//Gabriela Pinheiro - R00225375 - Project_Part1

package model;

import java.io.Serializable;

public abstract class Person implements Serializable{
	private Name name;
	protected String id;
	private String phone;
	
	public Person(Name name, String phone) {
		this.name = name;
		this.phone = phone;
	}

	public Name getName() {
		return name;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String n) {
		this.phone = n;
	}
	
	protected void setId(String prefix, int id) {
		if(this.getId() == null) {
			this.id = prefix + id;
		}
	}
	
	protected void setId(String id) {
		if(this.getId() == null) {
			this.id = id;
		}
	}
	
	public String getId() {
		return this.id;
	}

	@Override
	public String toString() {
		return "\nName: " + this.getName() + "\nId: " + this.getId() + "\nPhone: " + this.getPhone() + "\n";
	}


}
