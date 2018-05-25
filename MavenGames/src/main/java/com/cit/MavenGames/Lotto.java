package com.cit.MavenGames;

import java.io.FileNotFoundException;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Lotto extends Tab {

	private Label instruction = new Label("Pick Your Numbers");
	private Button[] box;
	private Button button = new Button();
	private Button[] disabledButtons;
	private Button newNumbers = new Button("New Numbers");
	private Button submit = new Button("Submit");
	private Button clear = new Button("Clear Numbers");
	private Label result = new Label();
	private Label winner = new Label("Winner!");
	private HBox options = new HBox();

	private LottoController control = new LottoController();

	public Lotto(Prizes prizes) throws FileNotFoundException {

		control.loadNumbers();

		box = new Button[control.getNumberOfNumbers()];
		disabledButtons = new Button[control.getNumberOfNumbers()];

		setText("Lotto Cure");
		VBox layout = new VBox();
		VBox buttonBox = new VBox();
		HBox boxes = new HBox();
		HBox[] buttons = new HBox[control.getRows()];
		buttons[0] = new HBox();

		for (int i = 0; i < control.getNumberOfNumbers(); i++) {

			box[i] = new Button();
			box[i].setOnAction(e -> clearBox(e));
			box[i].setFont(new Font(30));
			box[i].setMinSize(80, 100);
			boxes.getChildren().add(box[i]);
		}

		for (int i = 1, x = 0; i <= control.getNumbers(); i++) {

			button = new Button("" + i);
			button.setOnAction(e -> input(e));
			button.setFont(new Font(12));
			button.setPrefWidth(30);
			buttons[x].getChildren().add(button);

			if (i % control.getNumbersPerRow() == 0 && x != control.getRows() - 1) {

				buttons[x + 1] = new HBox();
				buttons[x].setSpacing(15);
				buttons[x].setAlignment(Pos.CENTER);
				x++;
			}
		}

		for (int i = 0; i < control.getRows(); i++)
			buttonBox.getChildren().add(buttons[i]);

		newNumbers.setOnAction(e -> {
			try {
				newNumbers();
			} catch (FileNotFoundException e1) {
			}
		});
		submit.setOnAction(e -> {
			try {
				submit(prizes);
			} catch (FileNotFoundException e1) {
			}
		});
		clear.setOnAction(e -> clear());

		instruction.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		newNumbers.setFont(new Font(15));
		submit.setFont(new Font(15));
		submit.setPrefWidth(75);
		clear.setFont(new Font(15));
		result.setFont(new Font(15));
		winner.setFont(new Font(15));
		winner.setPrefWidth(75);
		winner.setAlignment(Pos.CENTER);

		boxes.setSpacing(15);
		boxes.setAlignment(Pos.CENTER);
		buttons[control.getRows() - 1].setSpacing(15);
		buttons[control.getRows() - 1].setAlignment(Pos.CENTER);
		buttonBox.setSpacing(10);
		options.setSpacing(100);
		options.setAlignment(Pos.CENTER);
		layout.setSpacing(15);
		layout.setAlignment(Pos.CENTER);
		options.getChildren().addAll(newNumbers, submit, clear);
		layout.getChildren().addAll(instruction, boxes, buttonBox, options, result);
		setContent(layout);
	}

	private void clearBox(ActionEvent e) {

		Button box = (Button) e.getSource();
		int num;

		try {
			num = Integer.parseInt(box.getText());
		} catch (NumberFormatException e1) {
			return;
		}

		control.clearBox(num);

		for (int i = 0; i < control.getNumberOfNumbers(); i++)
			if (disabledButtons[i] != null && num == Integer.parseInt(disabledButtons[i].getText())) {

				disabledButtons[i].setDisable(false);
				disabledButtons[i] = null;
				break;
			}

		box.setText("");
	}

	private void input(ActionEvent e) {

		Button button = (Button) e.getSource();

		control.input(Integer.parseInt(button.getText()));
		int[] input = control.getInput();

		for (int i = 0; i < control.getNumberOfNumbers(); i++)
			if (input[i] != 0)
				box[i].setText("" + input[i]);

		for (int i = 0; i < control.getNumberOfNumbers(); i++)
			if (disabledButtons[i] == null) {

				disabledButtons[i] = button;
				button.setDisable(true);
				break;
			}
	}

	private void newNumbers() throws FileNotFoundException {

		control.newNumbers();

		result.setText("");

		if (options.getChildren().contains(winner)) {

			options.getChildren().removeAll(winner, clear);
			options.getChildren().addAll(submit, clear);
		}
	}

	private void saveNumbers() throws FileNotFoundException {

		boolean finished = false;

		if (options.getChildren().contains(newNumbers) && !options.getChildren().contains(submit))
			finished = true;

		control.saveNumbers(finished);

	}

	private void submit(Prizes prizes) throws FileNotFoundException {

		boolean win = control.submit(prizes);

		result.setText(control.getResult());

		if (win == true) {

			options.getChildren().removeAll(submit, clear);
			options.getChildren().addAll(winner, clear);
			saveNumbers();
		}

	}

	private void clear() {

		control.clear();

		for (int i = 0; i < control.getNumberOfNumbers(); i++) {

			box[i].setText("");

			if (disabledButtons[i] != null) {

				disabledButtons[i].setDisable(false);
				disabledButtons[i] = null;
			}
		}
		result.setText("");
	}
}