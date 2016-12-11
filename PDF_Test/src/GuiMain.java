import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sun.applet.Main;

public class GuiMain extends Application implements EventHandler<ActionEvent>{

	Stage window;
	private File pdfFileInput;
	private File outputDirectory;

	private Button startButton;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		window = primaryStage;
		
//<------ALL THE CODE HERE IS FOR THE ELEMENTS IN THE HOMEPAGE ------------>
		// (1) First Element
		// Creating the format of the first page upon starting the app
		GridPane gridOfHomePage = new GridPane();
		gridOfHomePage.setPadding(new Insets(10, 10, 10, 10));
		gridOfHomePage.setVgap(8);
		gridOfHomePage.setHgap(8);
		
		// (2) Second Element
		//This represents the label of the first scene
		Label homepageTitle = new Label("Hi, this is Swap Notes!"); 
		GridPane.setConstraints(homepageTitle, 0, 8);
		
		// (3) Third Element
		//This represents the button in the first page.
		startButton = new Button("Start");
		GridPane.setConstraints(startButton, 0, 6);
		
		// (4) Fourth Element
		//This represents the homepage image
		final ImageView homepageImage = new ImageView(); 
		Image swapPhoto = new Image("file:temp_logo.jpeg");
		homepageImage.setImage(swapPhoto);
		
		//This command adds all the elements into the gridHomepage
		gridOfHomePage.getChildren().addAll(homepageImage, homepageTitle, startButton);
		//Setting the homepageScene
		Scene homePageScene = new Scene(gridOfHomePage, 600, 500);
		homePageScene.getStylesheets().add("style.css");
		
//<--------------------------THE END--------------------------------------->
		
		
		
		

//<------ALL THE CODE HERE IS FOR THE ELEMENTS OF THE MAIN PAGE FOR USER INPUT ------->
		
		// (1) First Element
		// Creating the format of the first page upon starting the app
		GridPane gridOfMainPage = new GridPane();
		gridOfMainPage.setPadding(new Insets(10, 10, 10, 10));
		gridOfMainPage.setVgap(8);
		gridOfMainPage.setHgap(8);
		
		// (2) Second Element
		//This represents the textfield where the user will put the file
		TextField fileLocation = new TextField();
		fileLocation.setDisable(true);
		GridPane.setConstraints(fileLocation, 1, 0);
		
		// (3) Third Element
		//This represents the button name
		Button fileLocationButton = new Button("Open File");
		GridPane.setConstraints(fileLocationButton, 0, 0);
		
		// (4) Fourth Element
		//This represents keywords textfield
		TextField keywords = new TextField();
		GridPane.setConstraints(keywords, 1, 1);
		
		// (5) Fifth Element
		// This represents label for the keywords label
		Label keywordsLabel = new Label("Keywords:");
		GridPane.setConstraints(keywordsLabel, 0, 1);
		
		// (6) Sixth Element
		// This represents the page limit label
		Label pageLimitLabel = new Label("Number of Pages in Output:");
		GridPane.setConstraints(pageLimitLabel, 0, 2);
		
		// (7) Seventh Element
		// This represents the page limit textfield
		TextField pageNumberLimit = new TextField();
		GridPane.setConstraints(pageNumberLimit, 1, 2);
		
		// (8) Eighth Element
		// This represents the page label for user option to sort the pages
		Label includePages = new Label("Page filtering condition...");
		GridPane.setConstraints(includePages, 0, 3);
		
		// (9) Ninth Element
		// This represents the choice box for users to select options
		ChoiceBox<String> includePagesType = new ChoiceBox<String>();
		GridPane.setConstraints(includePagesType, 1, 3);
		includePagesType.getItems().add("And");
		includePagesType.getItems().add("Or");
		includePagesType.setValue("And");
		
		// (10) Tenth Element
		// This represents the page label for user option to sort the pages
		Label sortByLabel = new Label("Sort By...");
		GridPane.setConstraints(sortByLabel, 0, 4);
		
		// (11) 11th Element
		// This represents the choice box for users to select options
		ChoiceBox<String> sortByType = new ChoiceBox<String>();
		GridPane.setConstraints(sortByType, 1, 4);
		sortByType.getItems().add("Page Order");
		sortByType.getItems().add("Relevance");
		sortByType.setValue("Page Order");
		
		// (12) 12th Element
		// This represents the page label for user to choose their output filename 
		Label outputFileLabel = new Label("Select a name for your file...");
		GridPane.setConstraints(outputFileLabel, 0, 5);
		
		// (13) 13th Element
		// This represents the page output file name textfield
		TextField outputFileNameField = new TextField();
		GridPane.setConstraints(outputFileNameField, 1, 5);
		
		// (14) 14th Element
		TextField outputFileLocation = new TextField();
		outputFileLocation.setDisable(true);
		GridPane.setConstraints(outputFileLocation, 1, 6);
	
//		// (15) 15th Element
		Button outputFileLocationButton = new Button("Save file location");
		GridPane.setConstraints(outputFileLocationButton, 0, 6);

		// (14) 14th Element
		// This represents the button to create the output file
		Button summarizeFileButton = new Button("Summarize book");
		GridPane.setConstraints(summarizeFileButton, 1, 7);
		
		
		gridOfMainPage.getChildren().addAll(fileLocation, fileLocationButton, keywords, keywordsLabel, pageLimitLabel, pageNumberLimit, includePages, includePagesType, sortByType, sortByLabel, outputFileLabel, outputFileNameField, outputFileLocation, outputFileLocationButton, summarizeFileButton);
		Scene mainPageScene = new Scene(gridOfMainPage, 600, 500);
//		
//<--------------------------THE END--------------------------------------->
		
		
		
		
		
//<------ALL THE CODE HERE IS TO HANDLE THE LOGIC BETWEEN USER INPUT AND APP ------->
		
		//This handles moving from the home page to the next page
		startButton.setOnAction(e -> {
			window.setScene(mainPageScene);
		});


		fileLocationButton.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open File");
			pdfFileInput = fileChooser.showOpenDialog(window);
			fileLocation.setText(pdfFileInput.toString());
		});
		
		outputFileLocationButton.setOnAction(e -> {
			DirectoryChooser directoryChooser = new DirectoryChooser();
			directoryChooser.setTitle("Choose a directory");
			outputDirectory = directoryChooser.showDialog(window);
			outputFileLocation.setText(outputDirectory.toString());
		});
		

		summarizeFileButton.setOnAction(e -> {
			isInt(pageNumberLimit, pageNumberLimit.getText());
			String outputFileName = outputFileNameField.getText();
			int sortPageCondition = (sortByType.getValue().equals("Relevance")) ? 1 : 0;
			String[] keywordsArray = keywords.getText().toLowerCase().split(" ");
			ArrayList<String> keywordsList = new ArrayList<>(Arrays.asList(keywordsArray));
			try {
				createSummaryDocument(pdfFileInput.toString(), keywordsList, includePagesType.getValue().toLowerCase(), Integer.parseInt(pageNumberLimit.getText()), sortPageCondition, outputFileName, outputDirectory.toString());
			} catch (NumberFormatException e1) {
				
			} catch (IOException e1) {
				
			}
		});
		
		window.setScene(homePageScene);
		window.setTitle("Swap Notes");
		window.show();
	}

	@Override
	public void handle(ActionEvent event) {
		if (event.getSource() == startButton) {
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

	private void createSummaryDocument(String fileName, ArrayList<String> keywords, String outputMode, int pageNumberLimit, int sortPageCondition, String outputFileName, String outputDirectory) throws IOException {
		FileInputFilter filteringPages = new FileInputFilter(fileName, keywords, outputMode);
		DocAnalyzer docAnalyzer = new DocAnalyzer(filteringPages.getVectorTable());
		docAnalyzer.printDocument();
		docAnalyzer.filterDocument(pageNumberLimit, sortPageCondition);
		docAnalyzer.printDocument();
		DocPrinter printer = new DocPrinter(docAnalyzer.makeDocument());
		printer.saveDocumentAs(outputDirectory, outputFileName);
	}
	

}

