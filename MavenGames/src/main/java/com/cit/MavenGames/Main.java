package com.cit.MavenGames;

import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws FileNotFoundException {
		
		Group root = new Group();
		Scene scene = new Scene(root, 600, 400);
		TabPane tabPane = new TabPane();
		BorderPane borderPane = new BorderPane();
		
		Winners winners = new Winners(tabPane);
		Prizes prizes = new Prizes(tabPane, winners);
		
		tabPane.getTabs().add(new Guessing(prizes));
		tabPane.getTabs().add(new Lotto(prizes));
		tabPane.getTabs().add(prizes);
		tabPane.getTabs().add(winners);
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		tabPane.getSelectionModel().select(2);
		
		borderPane.prefHeightProperty().bind(scene.heightProperty());
		borderPane.prefWidthProperty().bind(scene.widthProperty());
		
		borderPane.setCenter(tabPane);
		root.getChildren().add(borderPane);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Games");
		primaryStage.show();
	}

}