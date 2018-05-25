package com.cit.MavenGames;

import java.io.FileNotFoundException;
import java.util.Optional;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Winners extends Tab {

	Label title = new Label("View & Edit Winners Below");
	TextArea winners = new TextArea();
	Button refresh = new Button("Refresh");
	Button generate = new Button("Generate Report");
	Button remove = new Button("Remove");
	Button backButton = new Button("Main Menu");
	WinnersController control = new WinnersController();

	public Winners(TabPane tabPane) throws FileNotFoundException {

		setText("Winners");
		VBox winnersMenu = new VBox();
		HBox options = new HBox();

		control.loadWinners();
		winners.setText(control.listWinners());
		winners.setStyle("-fx-font: 12px \"Monospace\";");

		title.setFont(Font.font("Verdona", FontWeight.BOLD, 20));
		winners.setEditable(false);

		refresh.setOnAction(e -> winners.setText(control.listWinners()));
		generate.setOnAction(e -> {
			try {
				generateReport();
			} catch (FileNotFoundException e1) {
			}
		});
		remove.setOnAction(e -> {
			try {
				removeWinner();
			} catch (FileNotFoundException e1) {
			}
		});
		backButton.setOnAction(e -> tabPane.getSelectionModel().select(2));

		refresh.setFont(new Font(15));
		generate.setFont(new Font(15));
		remove.setFont(new Font(15));
		backButton.setFont(new Font(15));

		options.setSpacing(30);
		options.setAlignment(Pos.CENTER);
		winnersMenu.setSpacing(30);
		winnersMenu.setAlignment(Pos.CENTER);
		options.getChildren().addAll(refresh, generate, remove, backButton);
		winnersMenu.getChildren().addAll(title, winners, options);
		setContent(winnersMenu);
	}

	public void addWinner(int category) throws FileNotFoundException {

		TextInputDialog dialog = new TextInputDialog("Player");
		dialog.setTitle("You Won!");
		dialog.setHeaderText("Add your name to the list of winners?");
		dialog.setContentText("Please enter your name:");

		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {

			control.addWinner(result.get(), category);
			winners.setText(control.listWinners());
		}
	}

	public void removeWinner() throws FileNotFoundException {

		TextInputDialog dialog = new TextInputDialog("0");
		dialog.setTitle("Remove Winner");
		dialog.setHeaderText("Remove a name from the list of winners?");
		dialog.setContentText("Please enter the position of the name:");

		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {

			try {
				int position = Integer.parseInt(result.get());
				control.removeWinner(position);
				winners.setText(control.listWinners());
			} catch (NumberFormatException e) {
				System.out.println("Incompatible entry, could not remove.");
			}
		}
	}

	public void generateReport() throws FileNotFoundException {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Generate Report");
		alert.setHeaderText("Generate Report for Winners");
		alert.setContentText("Order by:");

		ButtonType reportOne = new ButtonType("Name");
		ButtonType reportTwo = new ButtonType("Prize");
		ButtonType cancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(reportOne, reportTwo, cancel);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == reportOne)
			winners.setText(control.listWinnersByName());

		else if (result.get() == reportTwo)
			winners.setText(control.listWinnersByPrize());

	}
}