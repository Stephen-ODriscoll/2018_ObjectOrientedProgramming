package View;

import java.util.Optional;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;

public class Exit {

	public static void exit() {

		Alert alert = new Alert(AlertType.CONFIRMATION);

		alert.initModality(Modality.APPLICATION_MODAL);
		alert.setTitle("Exit");
		alert.setHeaderText("Are you sure you want to exit?");

		Optional<ButtonType> response = alert.showAndWait();

		if (response.get() == ButtonType.OK) {
			
			Platform.exit();
		}

		else
			alert.close();
	}
}
