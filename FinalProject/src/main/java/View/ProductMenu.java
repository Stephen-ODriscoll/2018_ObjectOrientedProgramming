package View;

import java.util.ArrayList;

import Controller.ProductMenuControl;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class ProductMenu extends VBox {

	private static ScrollPane scrollPane = new ScrollPane();
	private static TextArea productInfo;
	private static Button newProduct = new Button("New Product");
	private static Button removeProduct = new Button("Remove Product");
	private static Button exit = new Button("Exit");
	private static VBox products = new VBox();
	private static ProductMenuControl control = new ProductMenuControl();

	public ProductMenu(Button manageOrders) {
		
		HBox bottom = new HBox();
		products = new VBox();
		
		refreshProducts();
		
		newProduct.setOnAction(e -> new AddProduct());
		removeProduct.setOnAction(e -> control.removeProducts());
		exit.setOnAction(e -> Exit.exit());

		scrollPane.setPrefSize(555, 300);
		newProduct.setPrefWidth(110);
		removeProduct.setPrefWidth(110);
		manageOrders.setPrefWidth(110);
		exit.setPrefWidth(110);

		bottom.setSpacing(40);
		bottom.setAlignment(Pos.CENTER);
		setSpacing(25);
		setAlignment(Pos.TOP_CENTER);
		scrollPane.setContent(products);
		bottom.getChildren().addAll(newProduct, removeProduct, manageOrders, exit);
		getChildren().addAll(Top.getTop(), scrollPane, bottom);
		setPadding(new Insets(20, 20, 20, 20));
	}

	public static void select(MouseEvent e) {

		TextArea productInfo = (TextArea) e.getSource();

		if (control.deselect(productInfo))
			productInfo.setStyle("-fx-background-color: white;" + "-fx-border-color: grey ; -fx-border-width: 1px ;");

		else {

			control.select(productInfo);
			productInfo.setStyle("-fx-background-color: blue;" + "-fx-border-color: grey ; -fx-border-width: 1px ;");
		}
	}
	
	public static void refreshProducts() {
		
		ArrayList<String> productDB = control.getProducts();
		control.deselectAll();
		products.getChildren().clear();
		
		for (int i = 0; i < productDB.size(); i++) {

			productInfo = new TextArea();

			productInfo.setOnMouseClicked(e -> select(e));

			productInfo.setStyle("-fx-background-color: white;" + "-fx-border-color: grey ; -fx-border-width: 1px ;");
			productInfo.setFont(Font.font("Monospace", 15));
			productInfo.setEditable(false);
			productInfo.setMinSize(558, 32);
			productInfo.setPrefSize(productDB.get(i).length() * 10, 32);

			productInfo.setText(productDB.get(i));
			products.getChildren().add(productInfo);
		}
	}
}
