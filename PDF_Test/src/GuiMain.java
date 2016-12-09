import java.io.File;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sun.applet.Main;

public class GuiMain extends Application implements EventHandler<ActionEvent>{

	Stage window;
	Scene scene1;
	Scene scene2;
	
	Button button1;
	
	VBox layout1 = new VBox(20);
	VBox layout2 = new VBox(10);
	
	

	
	File file;
	
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		TextField fileLocation = new TextField();
		Button fileLocationButton = new Button("Open File");
		TextField outputLocation = new TextField();
		Button outputButton = new Button("Save As");
		Label keyLabel = new Label("Keywords"); 
		TextField keywords = new TextField();
		Label pageNumLabel = new Label("Number of Pages in Output");
		TextField pageNumberLimit = new TextField();
		Label sortByLabel = new Label("Sort By...");
		
		ChoiceBox<String> sortByType = new ChoiceBox<String>();
		
		sortByType.getItems().add("Page Order");
		sortByType.getItems().add("Relevance");
		sortByType.setValue("Page Order");
		
		Button startButton = new Button("Start");
		
	
		window = primaryStage;
		

		
		Label label1 = new Label("Hi, this is Swap Notes!"); 
		button1 = new Button("Start");

		//change to scene2
		button1.setOnAction(e -> {
			window.setScene(scene2);
			
		});

		
		final ImageView selectedImage = new ImageView(); 
		Image image1 = new Image("file:temp_logo.jpeg");
		selectedImage.setImage(image1);

		layout1.getChildren().addAll(selectedImage, label1, button1);


		fileLocationButton.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open File");
			file = fileChooser.showOpenDialog(window);
			
			System.out.println(file);
			
		});
		
		startButton.setOnAction(e -> {
			
			isInt(pageNumberLimit, pageNumberLimit.getText());
			
			
			System.out.println("File Location: " + file);
			System.out.println("Keywords: " + keywords.getText());
			System.out.println("Sort By: " + sortByType.getValue());
			
		});
		
		
		layout2.setPadding(new Insets(20, 20, 20, 20));
		layout2.getChildren().addAll(fileLocation, fileLocationButton, outputLocation, outputButton, keyLabel, keywords, pageNumLabel, pageNumberLimit, sortByLabel, sortByType, startButton);
		scene1 = new Scene(layout1, 600, 500);
		scene2 = new Scene(layout2, 600, 500);
		
		
		
		window.setScene(scene1);
		window.setTitle("Swap Notes");
		window.show();
	}

	@Override
	public void handle(ActionEvent event) {
		if (event.getSource() == button1) {
			System.out.println("You just pressed the button.");
		}
	}
	
	private boolean isInt(TextField input, String message) {
		
		try {
			
			int pageNum = Integer.parseInt(input.getText());
			System.out.println("Page Number Limit: " + pageNum);
			return true;
			
		} catch (NumberFormatException nfe) {
			
			System.out.println("Page Number Error: " + message + " is not a number");
			return false;
		}
		
	}

	

}

