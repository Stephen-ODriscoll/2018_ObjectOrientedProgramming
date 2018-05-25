package View;

import java.util.Optional;

import Model.Customer;
import Utility.LogInOut;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Pair;

public class Top {
	
	private static VBox top = new VBox();
	private static BorderPane info = new BorderPane();
	private static Label status = new Label();
	private static Button logInOut = new Button("Log In");
	private static Button register = new Button("Register");
	private static Label title = new Label("Product Database - Products");
	private static BorderPane borderPane;
	private static ProductMenu productMenu;
	private static HBox logOptions;
	
	public Top(BorderPane borderPane, ProductMenu productMenu) {
		
		Top.productMenu = productMenu;
		Top.borderPane = borderPane;
		logOptions = new HBox();
		
		logInOut.setOnAction(e -> logInOut(true));
		register.setOnAction(e -> new AddCustomer());
		
		title.setFont(Font.font("Verdona", FontWeight.BOLD, 30));
		top.setAlignment(Pos.CENTER);
		logOptions.setSpacing(20);
		logOptions.setAlignment(Pos.TOP_RIGHT);
		
		info.setLeft(status);
		logOptions.getChildren().addAll(register, logInOut);
		info.setRight(logOptions);
		top.getChildren().addAll(info, title);
	}

	public static void logInOut(boolean firstAttempt) {

		if (LogInOut.getLogIn()) {

			borderPane.setCenter(productMenu);
			logInOut.setText("Log In");
			status.setText("");
			LogInOut.logOut();
			logOptions.getChildren().remove(logInOut);
			logOptions.getChildren().addAll(register, logInOut);
			return;
		}

		// Create the custom dialog.
		Dialog<Pair<String, String>> dialog = new Dialog<>();
		dialog.setTitle("Customer Login");
		
		if(firstAttempt == true)
			dialog.setHeaderText("Please enter your username & password");
		
		else 
			dialog.setHeaderText("Username or password incorrect, try again");

		// Set the button types.
		ButtonType loginButtonType = new ButtonType("Login", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

		// Create the username and password labels and fields.
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		TextField username = new TextField();
		username.setPromptText("Username");
		PasswordField password = new PasswordField();
		password.setPromptText("Password");

		grid.add(new Label("Username:"), 0, 0);
		grid.add(username, 1, 0);
		grid.add(new Label("Password:"), 0, 1);
		grid.add(password, 1, 1);

		// Enable/Disable login button depending on whether a username was entered.
		Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
		loginButton.setDisable(true);

		// Do some validation (using the Java 8 lambda syntax).
		username.textProperty().addListener((observable, oldValue, newValue) -> {
			loginButton.setDisable(newValue.trim().isEmpty());
		});

		dialog.getDialogPane().setContent(grid);

		// Request focus on the username field by default.
		Platform.runLater(() -> username.requestFocus());

		// Convert the result to a username-password-pair
		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == loginButtonType) {
				return new Pair<>(username.getText(), password.getText());
			}
			return null;
		});

		Optional<Pair<String, String>> result = dialog.showAndWait();

		result.ifPresent(usernamePassword -> {

			LogInOut.logIn(usernamePassword.getKey(), usernamePassword.getValue());

			if (Customer.getName() != null) {
				
				logOptions.getChildren().remove(register);
				logInOut.setText("Log Out");
				status.setText("Welcome, " + usernamePassword.getKey());
				OrderMenu.refreshStatus();
			}

			else
				logInOut(false);

		});
		return;
	}
	
	public static VBox getTop() {
		
		return top;
	}
	
	public static Button getButton() {
		
		return logInOut;
	}
	
	public static String getStatus() {
		
		return status.getText();
	}
}
