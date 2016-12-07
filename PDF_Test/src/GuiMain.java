import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class GuiMain extends Application implements EventHandler<ActionEvent>{

	Stage window;
	Scene scene1;
	Scene scene2;
	
	Button button1;
	Button button2;
	


	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		window = primaryStage;
		

		
		Label label1 = new Label("Hi, this is Swap Notes!"); 
		button1 = new Button("Start");

		//change to scene2
		button1.setOnAction(e -> {
			window.setScene(scene2);
			
		});
		
		VBox layout1 = new VBox(20);
		layout1.getChildren().addAll(label1, button1);
		
		scene1 = new Scene(layout1, 400, 300);
		
		button2 = new Button("New Button");
			
		button2.setOnAction(e -> {
		
			System.out.println("New button!!");
			
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Resource File");
			fileChooser.showOpenDialog(window);
			
		});
		
		StackPane layout2 = new StackPane();
		layout2.getChildren().add(button2);
		scene2 = new Scene(layout2, 400, 300);
		
		
		
		
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

	

}

