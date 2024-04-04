//Gabriela Pinheiro - R00225375 - Project_Part1

package view;

import java.util.Optional;
import controller.Controller;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class AlertBox {
	
	public void dialogInformation(String textHeader, String textContent) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Hospital Consultancy System Informs");
		alert.setHeaderText(textHeader);
		alert.setContentText(textContent);
		alert.show();
	}
	
	public void dialogConfirmation() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Hospital Consultancy System");
		alert.setHeaderText("Save before close?");
		alert.setContentText("Cancel to close without saving!");
		
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			Controller.getInstance().saveToFile();
		}
		Controller.getInstance().getStage().close();
	}

}
