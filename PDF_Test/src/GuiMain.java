import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

/**
 * This class represents the GUI (Graphical User Interface) for SwapNotes.
 * @author Leon Wee, Yoon Duk Kim, Na Luo
 *
 */
public class GuiMain extends Application implements EventHandler<ActionEvent>{

	Stage window;
	private File pdfFileInput;
	private File outputDirectory;

	private Button startButton;
	
	/**
	 * This is the constructor for the class. It automatically launches the app.
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * This method is automatically called when we launch the app.
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		window = primaryStage;
		
//<------ALL THE CODE HERE IS FOR THE ELEMENTS IN THE HOMEPAGE ------------>
		// (1) First Element
		// Creating the format of the first page upon starting the app
		GridPane gridOfHomePage = new GridPane();
		gridOfHomePage.setPadding(new Insets(20, 20, 20, 20));
		gridOfHomePage.setVgap(8);
		gridOfHomePage.setHgap(8);
		gridOfHomePage.setAlignment(Pos.CENTER);
		
		// (2) Second Element
		//This represents the label of the first scene
		Label homepageTitle = new Label("Hi, this is Swap Notes!"); 
		GridPane.setConstraints(homepageTitle, 0, 4);
		
		
		// (3) Third Element
		//This represents the button in the first page.
		startButton = new Button("Start");
		GridPane.setConstraints(startButton, 0, 3);
		
		
		// (4) Fourth Element
		//This represents the homepage image
		final ImageView homepageImage = new ImageView(); 
		Image swapPhoto = new Image("file:swap_logo_new.jpg");
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
		gridOfMainPage.setAlignment(Pos.CENTER);
		
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
		
		//This command adds all the elements into the gridMainpage
		gridOfMainPage.getChildren().addAll(fileLocation, fileLocationButton, keywords, keywordsLabel, pageLimitLabel, pageNumberLimit, includePages, includePagesType, sortByType, sortByLabel, outputFileLabel, outputFileNameField, outputFileLocation, outputFileLocationButton, summarizeFileButton);
		Scene mainPageScene = new Scene(gridOfMainPage, 600, 500);
//		
//<--------------------------THE END--------------------------------------->
		
		
		
		
		
//<------ALL THE CODE HERE IS TO HANDLE THE LOGIC BETWEEN USER INPUT AND APP ------->
		
		//This handles moving from the home page to the next page
		startButton.setOnAction(e -> {
			window.setScene(mainPageScene);
		});

		//This allows the user to select which PDF files they want to summarize
		fileLocationButton.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open File");
			pdfFileInput = fileChooser.showOpenDialog(window);
			try{
				fileLocation.setText(pdfFileInput.toString());
			}
			catch(NullPointerException npe){
				System.out.println("Input for file location is required!");
			}
		});
		
		//This allows the user to select where they want to save the PDF file.
		outputFileLocationButton.setOnAction(e -> {
			DirectoryChooser directoryChooser = new DirectoryChooser();
			directoryChooser.setTitle("Choose a directory");
			outputDirectory = directoryChooser.showDialog(window);
			outputFileLocation.setText(outputDirectory.toString());
		});
		

		//This lets us pass in all the arguments from the GUI to the main Java app.
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

	/**
	 * This method handles and prints out a message to the user that they have pressed the button
	 */
	@Override
	public void handle(ActionEvent event) {
		if (event.getSource() == startButton) {
			System.out.println("You just pressed the button.");
		}
	}
	
	/**
	 * This method checks if the user has entered integers in the field.
	 * @param input
	 * @param message
	 * @return
	 */
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

	/**
	 * This method runs the SwapNotes app. 
	 * It first filters the input pdf file with the FileInputFilter class find the relevant pages.
	 * It then uses the DocAnalyzer class to sort the pages by importance/order and limits the number of pages in the document.
	 * The document is saved as a PDDocument object and is saved using the DocPrinter class.
	 * @param fileName input pdf file path
	 * @param keywords list of keywords
	 * @param outputMode determines whether we should use AND or OR operator for multiple keywords
	 * @param pageNumberLimit maximum number of pages that the user wants
	 * @param sortPageCondition how to sort output document. 0 for normal page order, 1 for relevance order
	 * @param outputFileName name of the output file
	 * @param outputDirectory directory path of the output file
	 * @throws IOException
	 */
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

