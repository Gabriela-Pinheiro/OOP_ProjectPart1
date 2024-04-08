//Gabriela Pinheiro - R00225375 - Project_Part1

import java.time.LocalDate;

import controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;
import menu.Menu;
import model.Consultant;
import model.Name;
import model.Patient;
import model.Practice;
import model.SerialStorage;
import model.Visit;

public class Main extends Application {
	
	@Override
	public void start(Stage stage) {
		try {
			
			Controller.getInstance().init(stage);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
