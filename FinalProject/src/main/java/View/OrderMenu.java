package View;

import java.util.ArrayList;

import Controller.OrderMenuControl;
import Model.Customer;
import Model.ProductDB;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class OrderMenu extends VBox{

	private static Label status = new Label();
	private static Button logOut = new Button("Log Out");
	private static Label title = new Label("Product Database - Orders");
	private static ScrollPane scrollPane = new ScrollPane();
	private static TextArea orderInfo = new TextArea();
	private static Button placeOrder = new Button("Place Order");
	private static Button removeOrder = new Button("Remove Order");
	private static Button exit = new Button("Exit");
	private static VBox orders = new VBox();
	
	private static OrderMenuControl control = new OrderMenuControl();
	
	public OrderMenu(Button viewProducts) {
		
		BorderPane info = new BorderPane();
		VBox top = new VBox();
		HBox bottom = new HBox();
		
		logOut.setOnAction(e -> Top.logInOut(true));
		
		placeOrder.setOnAction(e -> new AddOrder());
		removeOrder.setOnAction(e -> control.removeOrders());
		exit.setOnAction(e -> Exit.exit());
		
		title.setFont(Font.font("Verdona", FontWeight.BOLD, 30));
		orderInfo.setStyle("-fx-background-color: white; -fx-text-inner-color: black;"
				+ "-fx-border-color: grey ; -fx-border-width: 1px ;");
		scrollPane.setPrefSize(555, 300);
		scrollPane.setMaxWidth(557);
		orderInfo.setEditable(false);
		orderInfo.setPrefSize(555, 10);
		placeOrder.setPrefWidth(110);
		removeOrder.setPrefWidth(110);
		viewProducts.setPrefWidth(110);
		exit.setPrefWidth(110);
		
		bottom.setSpacing(40);
		bottom.setAlignment(Pos.CENTER);
		setSpacing(25);
		setAlignment(Pos.TOP_CENTER);
		info.setLeft(status);
		info.setRight(logOut);
		top.setAlignment(Pos.CENTER);
		scrollPane.setContent(orders);
		top.getChildren().addAll(info, title);
		bottom.getChildren().addAll(placeOrder, removeOrder, viewProducts, exit);
		getChildren().addAll(top, scrollPane, bottom);
		setPadding(new Insets(20, 20, 20, 20));
	}
	
	public static void refreshStatus() {
		
		control.updateOrdersList();
		status.setText(Top.getStatus());
		refreshOrders();
	}
	
	public static void select(MouseEvent e) {
		
		TextArea orderInfo = (TextArea) e.getSource();
		
		if(control.deselect(orderInfo))
			orderInfo.setStyle("-fx-background-color: white;" + "-fx-border-color: grey ; -fx-border-width: 1px ;");
		
		else {
			
			control.select(orderInfo);
			orderInfo.setStyle("-fx-background-color: blue;" + "-fx-border-color: grey ; -fx-border-width: 1px ;");
		}
	}
	
	public static void refreshOrders() {
		
		ArrayList<String> orderDB = control.getOrders();
		control.deselectAll();
		orders.getChildren().clear();
		
		for (int i = 0; i < orderDB.size(); i++) {

			orderInfo = new TextArea();

			orderInfo.setOnMouseClicked(e -> select(e));

			orderInfo.setStyle("-fx-background-color: white;" + "-fx-border-color: grey ; -fx-border-width: 1px ;");
			orderInfo.setFont(Font.font("Monospace", 15));
			orderInfo.setEditable(false);
			orderInfo.setMinSize(558, 32);
			orderInfo.setPrefSize((ProductDB.getLongestProduct()*10) + 200, Customer.getHeight(i) + 30);
			orderInfo.setText(orderDB.get(i));
			orders.getChildren().add(orderInfo);
		}
	}
	
}
