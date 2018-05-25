package Controller;

import Utility.Connect;
import Utility.LogInOut;
import View.Top;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
	
	private Button manageOrders = new Button("Manage Orders");
	private Button viewProducts = new Button("View Products");

		public static void main(String[] args) {
			
			launch(args);
		}
		
		@Override
		public void start(Stage primaryStage) {
			
			Group root = new Group();
			Scene scene = new Scene(root, 600, 400);
			BorderPane borderPane = new BorderPane();
			
			Connect.getInstance();
			View.ProductMenu productMenu = new View.ProductMenu(manageOrders);
			View.OrderMenu orderMenu = new View.OrderMenu(viewProducts);
			new Top(borderPane, productMenu);
			
			viewProducts.setOnAction(e -> borderPane.setCenter(productMenu));
			manageOrders.setOnAction(e -> {
				
				if(LogInOut.getLogIn())
					borderPane.setCenter(orderMenu);
				
				else {
					
					Top.logInOut(true);
					
					if(LogInOut.getLogIn())
						borderPane.setCenter(orderMenu);
				}
			});
			
			
			borderPane.prefHeightProperty().bind(scene.heightProperty());
			borderPane.prefWidthProperty().bind(scene.widthProperty());
			
			borderPane.setCenter(productMenu);
			root.getChildren().add(borderPane);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Product Database Program");
			primaryStage.show();
		}
}