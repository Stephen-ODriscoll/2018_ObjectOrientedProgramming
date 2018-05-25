package View;

import Model.Customer;
import Utility.Connect;
import Utility.Encrypt;
import Utility.Query;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddCustomer extends Stage{

	private Label nameInfo = new Label("Username* :\t");
	private TextField nameInput = new TextField();
	private Label addressInfo = new Label("Address* :\t");
	private TextField addressInput = new TextField();
	private Label passwordInfo = new Label("Password* :\t");
	private PasswordField passwordInput = new PasswordField();
	private Button cancel = new Button("Cancel");
	private Button register = new Button("Register");
	
	private VBox layout = new VBox();
	
	public AddCustomer() {
		
		HBox nameLayout = new HBox();
		HBox addressLayout = new HBox();
		HBox passwordLayout = new HBox();
		BorderPane options = new BorderPane();
		
		cancel.setOnAction(e -> hide());
		register.setOnAction(e -> submit());
		
		nameLayout.setSpacing(10);
		nameLayout.setAlignment(Pos.CENTER);
		addressLayout.setSpacing(10);
		addressLayout.setAlignment(Pos.CENTER);
		passwordLayout.setSpacing(10);
		passwordLayout.setAlignment(Pos.CENTER);
		layout.setSpacing(20);
		layout.setAlignment(Pos.CENTER);

		options.setLeft(cancel);
		options.setRight(register);
		options.setMaxWidth(200);
		nameLayout.getChildren().addAll(nameInfo, nameInput);
		addressLayout.getChildren().addAll(addressInfo, addressInput);
		passwordLayout.getChildren().addAll(passwordInfo, passwordInput);
		layout.getChildren().addAll(nameLayout, addressLayout, passwordLayout, options);
		newWindow();
	}
	
	public void submit() {
		
		if(nameInput.getText().equals("") || addressInput.getText().equals("")
				|| passwordInput.getText().equals(""))
			System.out.println("Input all fields");
		
		else if(Query.getCustomer((nameInput.getText())).equals("")) {
			
			String encrypted = Encrypt.md5Hash(passwordInput.getText());
			
			new Customer(0, nameInput.getText(), addressInput.getText());
			Connect.insertDelete("Insert Into Customers (username, address, password) Values ('" + 
			nameInput.getText() + "', '" + addressInput.getText() + "', '" + encrypted + "');");
			hide();
		}
		
		else {
			
			System.out.println("Customer already exists");
		}
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
		setTitle("Add Customer");
		show();
	}
}
