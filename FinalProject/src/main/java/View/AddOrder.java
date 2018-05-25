package View;

import Controller.AddOrderControl;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class AddOrder extends Stage {

	private TextField searchInput = new TextField();
	private Button submitSearch = new Button("Search");
	private TextArea productInfo = new TextArea();
	private Label quantityInfo = new Label("Quantity* :\t");
	private TextField quantityInput = new TextField();
	private Button add = new Button("Add");
	private Button finish = new Button("Finish");
	private VBox layout = new VBox();
	private AddOrderControl control = new AddOrderControl();

	public AddOrder() {

		HBox searchLayout = new HBox();
		HBox quantityLayout = new HBox();
		BorderPane options = new BorderPane();

		submitSearch.setOnAction(e -> productInfo.setText(control.search(searchInput.getText())));
		add.setOnAction(e -> add());
		finish.setOnAction(e -> hide());

		searchInput.setPromptText("Search by name or id");

		searchLayout.setSpacing(40);
		searchLayout.setAlignment(Pos.CENTER);
		productInfo.setStyle("-fx-background-color: blue; -fx-border-color: grey ; -fx-border-width: 1px ;");
		productInfo.setFont(Font.font("Monospace", 15));
		productInfo.setEditable(false);
		productInfo.setMaxSize(280, 40);
		quantityInput.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
		quantityLayout.setSpacing(10);
		quantityLayout.setAlignment(Pos.CENTER);
		layout.setSpacing(15);
		layout.setAlignment(Pos.CENTER);

		options.setLeft(add);
		options.setRight(finish);
		options.setMaxWidth(200);
		searchLayout.getChildren().addAll(searchInput, submitSearch);
		quantityLayout.getChildren().addAll(quantityInfo, quantityInput);
		layout.getChildren().addAll(searchLayout, productInfo, quantityLayout, options);
		newWindow();
	}

	public void newWindow() {

		Group root = new Group();
		Scene scene = new Scene(root, 300, 200);
		BorderPane borderPane = new BorderPane();

		borderPane.prefHeightProperty().bind(scene.heightProperty());
		borderPane.prefWidthProperty().bind(scene.widthProperty());

		borderPane.setCenter(layout);
		root.getChildren().add(borderPane);
		setScene(scene);
		setTitle("Add Product to Order");
		show();
	}

	public void add() {

		int quantity;

		try {
			quantity = Integer.parseInt(quantityInput.getText());

			if (productInfo.getText().equals("") || quantity <= 0) {

				quantityInput.setText("");
				System.out.println("Input all fields & positive quantity");
			}

			else
				control.add(quantity);

		} catch (Exception e) {

			quantityInput.setText("");
			quantityInput.setPromptText("Enter a number");
			return;
		}

	}
}
