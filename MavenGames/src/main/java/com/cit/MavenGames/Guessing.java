package com.cit.MavenGames;

import java.io.FileNotFoundException;
import java.util.Optional;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;

public class Guessing extends Tab {

	private Label instruction = new Label("Guess the Number");
	private Label label = new Label("Enter Number: ");
	private TextField input = new TextField();
	private Label displayAttempt = new Label();
	private Label hint = new Label();
	private Button reset = new Button("Reset");
	private Button placeGuess = new Button("Guess");
	private Label result = new Label();
	private Button quit = new Button("Quit");
	private HBox options = new HBox();

	private static GuessingController control = new GuessingController();

	public Guessing(Prizes prizes) {

		setText("Guessing");
		VBox layout = new VBox();
		HBox display = new HBox();

		resetGame();

		reset.setOnAction(e -> resetGame());
		placeGuess.setOnAction(e -> {try {placeGuess(prizes);} catch (FileNotFoundException e1) {}});
		quit.setOnAction(e -> exit());

		instruction.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		label.setFont(new Font(15));
		input.setPromptText("1 to 100");
		input.setFont(new Font(15));
		displayAttempt.setFont(new Font(15));
		hint.setFont(new Font(15));
		hint.setAlignment(Pos.CENTER);
		reset.setFont(new Font(15));
		reset.setPrefWidth(80);
		placeGuess.setFont(new Font(15));
		placeGuess.setPrefWidth(80);
		result.setFont(new Font(15));
		result.setPrefWidth(80);
		result.setAlignment(Pos.CENTER);
		quit.setFont(new Font(15));
		quit.setPrefWidth(80);

		display.setSpacing(10);
		display.setAlignment(Pos.CENTER);
		options.setSpacing(50);
		options.setAlignment(Pos.CENTER);
		layout.setSpacing(40);
		layout.setAlignment(Pos.CENTER);
		display.getChildren().addAll(label, input, displayAttempt);
		options.getChildren().addAll(reset, placeGuess, quit);
		layout.getChildren().addAll(instruction, display, hint, options);
		setContent(layout);
	}

	public void placeGuess(Prizes prizes) throws FileNotFoundException {

		boolean success = control.placeGuess(input.getText());

		hint.setText(control.getHint());

		if (success == true) {

			displayAttempt.setText("Attempt: " + control.getAttempt());

			if (control.getResult().equals("Correct!") || control.getResult().equals("Game Over!")) {

				result.setText(control.getResult());
				options.getChildren().removeAll(placeGuess, quit);
				options.getChildren().addAll(result, quit);
				
				if(control.getResult().equals("Correct!"))
					prizes.addFourStar();
			}
		}
	}

	public void resetGame() {

		control.resetGame();

		input.clear();
		displayAttempt.setText("Attempt: " + control.getAttempt());
		hint.setText(control.getHint());

		if (options.getChildren().contains(result)) {

			options.getChildren().removeAll(result, quit);
			options.getChildren().addAll(placeGuess, quit);
		}
	}

	public void exit() {

		Alert alert = new Alert(AlertType.CONFIRMATION);

		alert.initModality(Modality.APPLICATION_MODAL);
		alert.setTitle("Warning");
		alert.setHeaderText("Are you sure you want to exit?");

		Optional<ButtonType> response = alert.showAndWait();

		if (response.get() == ButtonType.OK)
			Platform.exit();

		else
			alert.close();
	}
}
