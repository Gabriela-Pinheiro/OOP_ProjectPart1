package model;

import java.io.Serializable;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Test implements Serializable{
	
	
	private String testName;
	private transient StringProperty testNameProperty;
	
	public void fillAfterLoad() {
		if(testName != null) {
			this.setTestNameProperty(testName);
		}
	}
	
	public void prepareToSave() {
		this.testName = getTestNameProperty().get();
	}
	
	public StringProperty getTestNameProperty() {
		return testNameProperty;
	}
	public void setTestNameProperty(String testNameProperty) {
		this.testNameProperty = new SimpleStringProperty(testNameProperty);
	}
	
//	public void setTestName(String testName) {
//		this.testName = testName;
//		this.setTestNameProperty(testName);
//	}

}
