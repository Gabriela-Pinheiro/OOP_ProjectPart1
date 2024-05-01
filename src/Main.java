//Gabriela Pinheiro - R00225375 - Project_Part2

import controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;

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
