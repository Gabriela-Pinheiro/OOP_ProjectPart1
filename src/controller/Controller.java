//Gabriela Pinheiro - R00225375 - Project_Part1

package controller;

import java.util.ArrayList;

import collections.PatientList;
import collections.VisitList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.Hospital_GUI;


public class Controller {
	
	private static volatile Controller object;
	private PatientList patients;
	private VisitList visits;
	private transient Stage stage;
	private transient Scene scene;


	
	private Controller() {
		patients = new PatientList();
		object = this;
	}

	public static Controller getInstance() {
		if(object == null) {
			synchronized(Controller.class){
				if(object == null) {
					object = new Controller();
				}
			}
		}
		return object;
	}
	
	private void setStage(Stage stage) {
		object.stage = stage;
		stage.setTitle("Hospital Consultancy System");
		stage.setOnCloseRequest(event -> {
			//alertBox.dialogConfirmation();
		});
	}

	public void init(Stage stage) {
		scene = new Scene(new Hospital_GUI(),550,550);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		this.setStage(stage);
		stage.setScene(scene);
		stage.show();
	}
	
	public Stage getStage() {
		return this.stage;
	}
	
	public ArrayList<String> getPatientData() {
		return object.patients.getPatientData();
	}

}
