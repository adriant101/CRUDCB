

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Graphics extends Application {
	
	
	
private File myFile = new File("customers.txt");
private Scene myScene;
private Group root;
private ComicStore operation = new ComicStore();
private TextField firstName = new TextField(); 
private TextField lastName = new TextField();
private TextField date = new TextField(); 
private TextField comic = new TextField();
private TextField publisher = new TextField(); 
private TextField cost = new TextField(); 
private DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS"); //This format(dd-MM-yyyy HH:mm:ss.SSS) seems a little harsh for a user to enter, so it's been changed.
public static TextField editDate = new TextField();
public static TextField editComic = new TextField();
public static  TextField editPublisher = new TextField();
public static  TextField editCost = new TextField();
private TilePane editFields = new TilePane();
private Button changeOrder = new Button("Make Changes");
private Button selectCust = new Button ("Select");
private ChoiceBox<String> dropDown= new ChoiceBox<>();
private Button submitCust;
private TilePane textTiles;
private Button selectRemovedCust;
static ArrayList<ArrayList<String>> aList = new ArrayList<ArrayList<String> >(5); 
private Date dateSigned;
private Label firstN;
private Label lastN;
private Label dateL =  new Label("Date Format dd-MM-yyyy");
private Label comicTitle = new Label ("Comic Title");
private Label publisherL = new Label("Publisher");
private Label costL =  new Label("Cost");
private Text error = new Text("");

public static void main(String[] args) throws FileNotFoundException {
	
	launch(args);
	

}



	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		 Scanner myScan = new Scanner(myFile);
		
		 // Customers stored in arraylist of arraylists
		 BufferedReader reader = new BufferedReader(new FileReader("customers.txt"));
		 int lines = 0;
		 while (reader.readLine() != null) lines++;
		 reader.close();
		 
		 while(myScan.hasNext()) {
			 
			 for (int i = 0; i <lines ; i++) {
				 ArrayList<String> a1 = new ArrayList<String>();
				 String [] line = myScan.nextLine().split(",");
				 for(int j = 0; j<line.length; j++) {
					 a1.add(line[j]);
		  }aList.add(a1);
			 }
			 
			 
		 }
	       
		 
		  
			dropDown.setLayoutX(400);dropDown.setLayoutY(200);
		
			
			for (int i = 0; i < aList.size(); i++) {
				for(int j = 0; j<1; j++) {	
					dropDown.getItems().add(aList.get(i).get(j)+" "+ aList.get(i).get(j+1));
					}
				
			}
		 //***********Add Customer Code*******************//
		 
		 
		 Button addCustomer = new Button("Add Customer");
		 addCustomer.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					if(root.getChildren().contains(submitCust)) {
						root.getChildren().remove(submitCust);
					}
					while (root.getChildren().contains(editFields)) {
						root.getChildren().remove(editFields);
					}while (root.getChildren().contains(changeOrder)) {
						root.getChildren().remove(changeOrder);
					}while (root.getChildren().contains(selectCust)) {
						root.getChildren().remove(selectCust);
					}if (root.getChildren().contains(dropDown)) {
						root.getChildren().remove(dropDown);
					}while(root.getChildren().contains(selectRemovedCust)){
						root.getChildren().remove(selectRemovedCust);
						
					}if(root.getChildren().contains(textTiles)) {
						root.getChildren().remove(textTiles);
					}
				while(root.getChildren().contains(changeOrder)) {
					root.getChildren().remove(changeOrder);
				}
					 textTiles = new TilePane();
					textTiles.setOrientation(Orientation.HORIZONTAL);
					textTiles.setHgap(10);
					textTiles.setPrefColumns(6);
					textTiles.setLayoutX(200);textTiles.setLayoutY(200);
					firstN = new Label("First Name"); firstN.setLabelFor(firstName);
					lastN = new Label ("Last Name"); lastN.setLabelFor(lastName);
					dateL.setLabelFor(date);
					comicTitle.setLabelFor(comic);
					publisherL.setLabelFor(publisher);
					costL.setLabelFor(cost);
					error.setX(650);error.setY(150);error.setFill(Color.RED);
					textTiles.getChildren().addAll(firstName,lastName, date, comic, publisher, cost, firstN, lastN, dateL, comicTitle, publisherL, costL);
					firstName.setPromptText("First Name");lastName.setPromptText("Last Name");date.setPromptText("dd-MM-yyyy");comic.setPromptText("Comic Title");
					publisher.setPromptText("Publisher");cost.setPromptText("Cost");
					
					
					
					
					 submitCust = new Button("Submit New Customer");
					submitCust.setLayoutX(700);submitCust.setLayoutY(300);
					root.getChildren().addAll(textTiles, submitCust);
					
					submitCust.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							if (root.getChildren().contains(dropDown)) {
								root.getChildren().remove(dropDown);
							}
							
							if (firstName.getText().isEmpty() || lastName.getText().isEmpty()|| date.getText().isEmpty()|| comic.getText().isEmpty()||
									publisher.getText().isEmpty()||cost.getText().isEmpty()) {
								error.setText("Error: You must complete all fields.");
							}else {
								error.setText("");
								try {
									 dateSigned = formatter.parse(date.getText() + " 00:00:00.000");
								} catch (ParseException e) {
									
									
									e.printStackTrace();
								}
								String datesigned = formatter.format(dateSigned);	
							operation.addCustomer(aList, firstName.getText(), lastName.getText(), datesigned, comic.getText(), publisher.getText(), cost.getText());
							
							}
							ObservableList<String> listAdd = FXCollections.observableArrayList();
							
							for (int i = 0; i < aList.size(); i++) {
								for(int j = 0; j<1; j++) {	
									listAdd.add(aList.get(i).get(j)+" "+ aList.get(i).get(j+1));
									dropDown.setItems(listAdd);
									
									
									
									
									}
								
							}
							
						operation.writeToFile(aList);
						firstName.clear();lastName.clear();date.clear();comic.clear();publisher.clear();cost.clear();
							
						}});
		 
				}
			});
		 dateL.setLabelFor(editDate);comicTitle.setLabelFor(editComic); publisherL.setLabelFor(editPublisher);costL.setLabelFor(editCost);
		 editFields.getChildren().addAll(editDate, editComic, editPublisher, editCost, dateL, comicTitle, publisherL, costL);
		
		 //***********Update Order Code*******************//
		 
		 Button updateOrder = new Button("Update Order");
		 updateOrder.setLayoutY(50);
		 updateOrder.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					error.setText("");
					
					if (root.getChildren().contains(textTiles)) {
						root.getChildren().remove(textTiles);
					} while (root.getChildren().contains(submitCust)) {
						root.getChildren().remove(submitCust);
					}while(root.getChildren().contains(selectRemovedCust)){
						root.getChildren().remove(selectRemovedCust);
					}if(root.getChildren().contains(dropDown)){
					 }else {
						 root.getChildren().add(dropDown);
					 }
					
					
					selectCust.setLayoutX(650);selectCust.setLayoutY(200);
					 if(root.getChildren().contains(selectCust)){
						 
					 }else {
						 root.getChildren().add(selectCust);
					 }
					 
					
					
					selectCust.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							
							
							if(!root.getChildren().contains(editFields)) {
							
							root.getChildren().addAll(editFields);
							}
							
							editFields.setLayoutX(400);editFields.setLayoutY(300);editFields.setPrefColumns(4); editFields.setHgap(20);
							operation.fetchOrder(aList, dropDown.getValue());
							
							//changeOrder = new Button("Make Changes");
							changeOrder.setLayoutX(750);changeOrder.setLayoutY(450);
							
							if(!root.getChildren().contains(changeOrder)){
									root.getChildren().add(changeOrder);}
							
							changeOrder.setOnAction(new EventHandler<ActionEvent>() {
								@Override
								public void handle(ActionEvent event) {
									operation.updateOrder(aList, dropDown.getValue(), editDate.getText(), editComic.getText(), editPublisher.getText(), editCost.getText());
									
									operation.writeToFile(aList);
								
									
									
									
									
									
								}});
							}});
				}});
		 
		 
		 
		 //***********Remove Customer Code*******************//
		 
		 Button removeCustomer = new Button("Remove Customer");
		 removeCustomer.setLayoutY(100);
		removeCustomer.setOnAction( new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				error.setText("");
				if (root.getChildren().contains(editFields)) {
					root.getChildren().remove(editFields);
				}if (root.getChildren().contains(changeOrder)) {
					
					root.getChildren().remove(changeOrder);
				}if (root.getChildren().contains(textTiles)) {
					root.getChildren().remove(textTiles);
				} while (root.getChildren().contains(submitCust)) {
					root.getChildren().remove(submitCust);
				}
				if(root.getChildren().contains(dropDown)){
			 
				}else {
					root.getChildren().add(dropDown);
				}
		 
		 selectRemovedCust = new Button("Select for Deletion"); selectRemovedCust.setLayoutX(650);selectRemovedCust.setLayoutY(200);
		 root.getChildren().add(selectRemovedCust);
		 
		 selectRemovedCust.setOnAction( new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					operation.removeCustomer(aList, dropDown.getValue());
					ObservableList<String> list = FXCollections.observableArrayList();
					
					for (int i = 0; i < aList.size(); i++) {
						for(int j = 0; j<1; j++) {	
							list.add(aList.get(i).get(j)+" "+ aList.get(i).get(j+1));
							dropDown.setItems(list);
		
							}
						
					}
					
					
					operation.writeToFile(aList);
					
					
				}});
		 
			}});
		 
		 Text logo = new Text("Comic Bookstore CMS");
		logo.setX(550);logo.setY(70);logo.setFont(Font.font ("Verdana", 40));
		
		 root = new Group(addCustomer, updateOrder, removeCustomer, error, logo);
		 myScene = new Scene(root, 1500, 760, Color.CYAN);
		 primaryStage.setScene(myScene);
		 primaryStage.setTitle("Comic Bookstore CRUD");
		 primaryStage.show();
		 
		 
		 
		 
		 
		 
	
		
		 
		 
		 
		 
		 
	}

}
	
