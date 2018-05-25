package com.cit.MavenGames;

import java.io.FileNotFoundException;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;

public class Prizes extends Tab {

	private Label instruction = new Label("Pick Your Prize Below");
	private Label fourStarInfo = new Label();
	private Label fiveStarInfo = new Label();
	private Label sixStarInfo = new Label();
	private ComboBox<String> fourStarPrize = new ComboBox<String>();
	private ComboBox<String> fiveStarPrize = new ComboBox<String>();
	private ComboBox<String> sixStarPrize = new ComboBox<String>();
	private Button claim = new Button("Claim Prizes");
	private Button fourStarClaim = new Button("Claim");
	private Button fiveStarClaim = new Button("Claim");
	private Button sixStarClaim = new Button("Claim");
	private Label winnings = new Label();
	private PrizesController control = new PrizesController();
	private Winners winners;
	
	private VBox mainMenu = new VBox();

	public Prizes(TabPane tabPane, Winners winners) throws FileNotFoundException {

		setText("Prizes");
		VBox layout = new VBox();
		VBox options = new VBox();
		HBox fourStarOptions = new HBox();
		HBox fiveStarOptions = new HBox();
		HBox sixStarOptions = new HBox();

		loadTree();
		loadTokens();
		this.winners = winners;

		fourStarClaim.setOnAction(e -> {
			try {
				claim(4);
			} catch (FileNotFoundException e1) {
			}
		});
		fiveStarClaim.setOnAction(e -> {
			try {
				claim(5);
			} catch (FileNotFoundException e1) {
			}
		});
		sixStarClaim.setOnAction(e -> {
			try {
				claim(6);
			} catch (FileNotFoundException e1) {
			}
		});

		fourStarInfo.setPrefWidth(155);
		fiveStarInfo.setPrefWidth(155);
		sixStarInfo.setPrefWidth(155);
		fourStarPrize.setPromptText("Select 4* Prize");
		fiveStarPrize.setPromptText("Select 5* Prize");
		sixStarPrize.setPromptText("Select 6* Prize");

		instruction.setFont(Font.font("Verdona", FontWeight.BOLD, 20));
		fourStarInfo.setFont(new Font(15));
		fiveStarInfo.setFont(new Font(15));
		sixStarInfo.setFont(new Font(15));
		fourStarPrize.setStyle("-fx-font: 15px \"Serif\";");
		fiveStarPrize.setStyle("-fx-font: 15px \"Serif\";");
		sixStarPrize.setStyle("-fx-font: 15px \"Serif\";");
		fourStarClaim.setFont(new Font(15));
		fiveStarClaim.setFont(new Font(15));
		sixStarClaim.setFont(new Font(15));
		winnings.setFont(new Font(15));

		fourStarOptions.setSpacing(40);
		fourStarOptions.setAlignment(Pos.CENTER);
		fiveStarOptions.setSpacing(40);
		fiveStarOptions.setAlignment(Pos.CENTER);
		sixStarOptions.setSpacing(40);
		sixStarOptions.setAlignment(Pos.CENTER);
		options.setSpacing(20);
		options.setAlignment(Pos.CENTER);
		layout.setSpacing(40);
		layout.setAlignment(Pos.CENTER);
		fourStarOptions.getChildren().addAll(fourStarInfo, fourStarPrize, fourStarClaim);
		fiveStarOptions.getChildren().addAll(fiveStarInfo, fiveStarPrize, fiveStarClaim);
		sixStarOptions.getChildren().addAll(sixStarInfo, sixStarPrize, sixStarClaim);
		options.getChildren().addAll(fourStarOptions, fiveStarOptions, sixStarOptions, winnings);
		layout.getChildren().addAll(instruction, options);
		
		mainMenu(tabPane, layout);
	}

	public void addFourStar() throws FileNotFoundException {

		control.addFourStar();
		saveTokens();
		winners.addWinner(4);
		claim.setDisable(false);
		fourStarClaim.setDisable(false);
	}

	public void addFiveStar() throws FileNotFoundException {

		control.addFiveStar();
		saveTokens();
		winners.addWinner(5);
		claim.setDisable(false);
		fiveStarClaim.setDisable(false);
	}

	public void addSixStar() throws FileNotFoundException {

		control.addSixStar();
		saveTokens();
		winners.addWinner(6);
		claim.setDisable(false);
		sixStarClaim.setDisable(false);
	}

	public void loadTokens() throws FileNotFoundException {

		boolean exists = control.loadTokens();

		if (exists == false)
			saveTokens();

		fourStarInfo.setText("4* Tokens Available: " + control.getFourStar());
		fiveStarInfo.setText("5* Tokens Available: " + control.getFiveStar());
		sixStarInfo.setText("6* Tokens Available: " + control.getSixStar());

		if (control.getFourStar() == 0)
			fourStarClaim.setDisable(true);

		if (control.getFiveStar() == 0)
			fiveStarClaim.setDisable(true);

		if (control.getSixStar() == 0)
			sixStarClaim.setDisable(true);
	}

	public void saveTokens() throws FileNotFoundException {

		control.saveTokens();

		fourStarInfo.setText("4* Tokens Available: " + control.getFourStar());
		fiveStarInfo.setText("5* Tokens Available: " + control.getFiveStar());
		sixStarInfo.setText("6* Tokens Available: " + control.getSixStar());

		if (control.getFourStar() == 0)
			fourStarClaim.setDisable(true);

		if (control.getFiveStar() == 0)
			fiveStarClaim.setDisable(true);

		if (control.getSixStar() == 0)
			sixStarClaim.setDisable(true);
	}

	public void claim(int category) throws FileNotFoundException {

		String prizeKey = "";

		switch (category) {
		case 4:

			prizeKey = fourStarPrize.getSelectionModel().getSelectedItem();

			if (prizeKey == null)
				return;

			control.subtractFourStar();
			saveTokens();

			if (control.getFourStar() == 0)
				fourStarClaim.setDisable(true);
			break;
		case 5:
			prizeKey = fiveStarPrize.getSelectionModel().getSelectedItem();

			if (prizeKey == null)
				return;

			control.subtractFiveStar();
			saveTokens();

			if (control.getFiveStar() == 0)
				fiveStarClaim.setDisable(true);
			break;
		case 6:
			prizeKey = sixStarPrize.getSelectionModel().getSelectedItem();

			if (prizeKey == null)
				return;

			control.subtractSixStar();
			saveTokens();

			if (control.getSixStar() == 0)
				sixStarClaim.setDisable(true);
			break;

		}

		if (control.getFourStar() == 0 && control.getFiveStar() == 0 && control.getSixStar() == 0)
			claim.setDisable(true);

		String prize = control.getTree().search(prizeKey);

		Alert alert = new Alert(AlertType.INFORMATION);

		// Block events to other windows
		alert.initModality(Modality.APPLICATION_MODAL);
		alert.setTitle("Congratulations");
		alert.setHeaderText("Congrats, You Won " + prize);
		alert.showAndWait();

	}

	public void mainMenu(TabPane tabPane, VBox layout) {

		Button guessing = new Button("Play Guessing");
		Button lotto = new Button("Play Lotto Cure");
		Button winners = new Button("Winners");

		if (control.getFourStar() == 0 && control.getFiveStar() == 0 && control.getSixStar() == 0)
			claim.setDisable(true);

		guessing.setOnAction(e -> tabPane.getSelectionModel().select(0));
		lotto.setOnAction(e -> tabPane.getSelectionModel().select(1));
		claim.setOnAction(e -> setContent(layout));
		winners.setOnAction(e -> tabPane.getSelectionModel().select(3));
		setOnSelectionChanged(e -> setContent(mainMenu));

		guessing.setFont(Font.font("Monospace", FontWeight.BOLD, 20));
		lotto.setFont(Font.font("Monospace", FontWeight.BOLD, 20));
		claim.setFont(Font.font("Monospace", FontWeight.BOLD, 20));
		winners.setFont(Font.font("Monospace", FontWeight.BOLD, 20));
		guessing.setPrefWidth(220);
		lotto.setPrefWidth(220);
		claim.setPrefWidth(220);
		winners.setPrefWidth(220);

		mainMenu.setSpacing(40);
		mainMenu.setAlignment(Pos.CENTER);
		mainMenu.getChildren().addAll(guessing, lotto, claim, winners);
		setContent(mainMenu);
	}

	public void loadTree() throws FileNotFoundException {
		
		control.loadTree();

		for (int i = 0; i < control.getTree().getLength(); i++) {

			switch (control.getCategory(i)) {
			case 4:
				fourStarPrize.getItems().add(control.getKey(i));
				break;
			case 5:
				fiveStarPrize.getItems().add(control.getKey(i));
				break;
			case 6:
				sixStarPrize.getItems().add(control.getKey(i));
				break;
			}
		}
	}
}
