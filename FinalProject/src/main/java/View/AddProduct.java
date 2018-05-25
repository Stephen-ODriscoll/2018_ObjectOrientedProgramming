package View;

import Model.Phone;
import Model.ProductDB;
import Model.TV;
import Utility.Connect;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AddProduct extends Stage{
	
	private Label title = new Label("Add Phone");
	private Label makeInfo = new Label("Make* :\t\t");
	private TextField makeInput = new TextField();
	private Label modelInfo = new Label("Model* :\t\t");
	private TextField modelInput = new TextField();
	private Label storageInfo = new Label("Storage* :\t\t");
	private ComboBox<String> storageInput = new ComboBox<>();
	private Label priceInfo = new Label("Price ( € )* :\t");
	private TextField priceInput = new TextField();
	private HBox makeLayout = new HBox();
	private HBox modelLayout = new HBox();
	private HBox priceLayout = new HBox();
	private HBox storageLayout = new HBox();
	
	private Label screenInfo = new Label("Screen Size* :\t");
	private TextField screenInput = new TextField();
	private Label typeInfo = new Label("Type* :\t");
	private ComboBox<String> typeInput = new ComboBox<>();
	private Label threeDInfo = new Label("3D* :\t");
	private ComboBox<String> threeDInput = new ComboBox<>();
	private HBox screenLayout = new HBox();
	private HBox combosLayout = new HBox();
	
	private Button switchProduct = new Button("TV");
	private Button cancel = new Button("Cancel");
	private Button submit = new Button("Submit");
	
	private HBox options = new HBox();
	private VBox layout = new VBox();
	
	public AddProduct() {
		
		switchProduct.setOnAction(e -> switchProduct());
		cancel.setOnAction(e -> hide());
		submit.setOnAction(e -> submit());
		
		storageInput.getItems().addAll("8", "16", "32", "64", "128");
		typeInput.getItems().addAll("LCD", "LED", "Plasma");
		threeDInput.getItems().addAll("Yes", "No");
		
		title.setFont(new Font(20));
		storageInput.setPrefWidth(150);
		
		makeLayout.setSpacing(10);
		makeLayout.setAlignment(Pos.CENTER);
		modelLayout.setSpacing(10);
		modelLayout.setAlignment(Pos.CENTER);
		storageLayout.setSpacing(10);
		storageLayout.setAlignment(Pos.CENTER);
		priceLayout.setSpacing(10);
		priceLayout.setAlignment(Pos.CENTER);
		
		screenLayout.setSpacing(10);
		screenLayout.setAlignment(Pos.CENTER);
		combosLayout.setSpacing(10);
		combosLayout.setAlignment(Pos.CENTER);
		
		options.setMaxWidth(200);
		options.setAlignment(Pos.CENTER);
		options.setSpacing(20);
		layout.setSpacing(20);
		layout.setAlignment(Pos.CENTER);
		
		options.getChildren().addAll(switchProduct, cancel, submit);
		makeLayout.getChildren().addAll(makeInfo, makeInput);
		modelLayout.getChildren().addAll(modelInfo, modelInput);
		storageLayout.getChildren().addAll(storageInfo, storageInput);
		priceLayout.getChildren().addAll(priceInfo, priceInput);
		screenLayout.getChildren().addAll(screenInfo, screenInput);
		combosLayout.getChildren().addAll(typeInfo, typeInput, threeDInfo, threeDInput);
		layout.getChildren().addAll(title, makeLayout, modelLayout, storageLayout, priceLayout, options);
		newWindow();
	}
	
	public void submit() {
		
		if(switchProduct.getText().equals("TV"))
			addPhone();
		
		else
			addTV();
	}
	
	public void addPhone() {
		
		if(makeInput.getText().equals("") || modelInput.getText().equals("")
				|| storageInput.getSelectionModel().getSelectedItem() == null || priceInput.getText().equals(""))
			System.out.println("Input all fields");
		
		else if(ProductDB.searchByName(makeInput.getText() + " " + modelInput.getText()) == null) {
			
			int id = ProductDB.nextID();
			int storage = Integer.parseInt(storageInput.getSelectionModel().getSelectedItem());
			double price = Double.parseDouble(priceInput.getText());

			Connect.insertDelete("Insert Into phones (productID, make, model, storage, price) Values (" + 
					id + ", '" + makeInput.getText() + "', '" + modelInput.getText() + "', " + storage + 
					", " + price + ");");
			Connect.insertDelete("Insert Into products (productID) Values (" + id + ");");
			Phone phone  = new Phone(id, makeInput.getText(), modelInput.getText(), storage, price);
			ProductDB.add(phone.getProduct());
			ProductMenu.refreshProducts();
			hide();
		}
		
		else
			System.out.println("This make and model already exists");
	}
	
	public void addTV() {
		
		if(makeInput.getText().equals("") || screenInput.getText().equals("")
				|| typeInput.getSelectionModel().getSelectedItem() == null
				|| threeDInput.getSelectionModel().getSelectedItem() == null || priceInput.getText().equals(""))
			System.out.println("Input all fields");
		
		else if(ProductDB.searchByName(makeInput.getText()) == null) {
			
			int id = ProductDB.nextID();
			int screen = Integer.parseInt(screenInput.getText());
			double price = Double.parseDouble(priceInput.getText());
			String type = typeInput.getSelectionModel().getSelectedItem();
			String threeD = threeDInput.getSelectionModel().getSelectedItem();

			Connect.insertDelete("Insert Into tvs (productID, make, screen, type, threeD, price) Values (" + 
					id + ", '" + makeInput.getText() + "', " + screen + ", '" + type + "', '" + threeD
					 + "', " +  price + ");");
			Connect.insertDelete("Insert Into products (productID) Values (" + id + ");");
			TV tv = new TV(id, makeInput.getText(), screen, type, threeD, price);
			ProductDB.add(tv.getProduct());
			ProductMenu.refreshProducts();
			hide();
		}
		
		else
			System.out.println("This item already exists");
	}
	
	public void switchProduct() {
		
		if(switchProduct.getText().equals("TV")) {
			
			layout.getChildren().removeAll(modelLayout, storageLayout, options);
			layout.getChildren().addAll(screenLayout, combosLayout, options);
			switchProduct.setText("Phone");
			title.setText("Add TV");
		}
		
		else {
			
			layout.getChildren().removeAll(priceLayout, screenLayout, combosLayout, options);
			layout.getChildren().addAll(modelLayout, storageLayout, priceLayout, options);
			switchProduct.setText("TV");
			title.setText("Add Phone");
		}
	}
	
	public void newWindow() {
		
		Group root = new Group();
		Scene scene = new Scene(root, 300, 300);
		BorderPane borderPane = new BorderPane();
		
		borderPane.prefHeightProperty().bind(scene.heightProperty());
		borderPane.prefWidthProperty().bind(scene.widthProperty());
		
		borderPane.setCenter(layout);
		root.getChildren().add(borderPane);
		setScene(scene);
		setTitle("Add Product");
		show();
	}
}
