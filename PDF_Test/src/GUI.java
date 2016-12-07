import java.util.ArrayList;

/**
 * This class hangles the user inputs that are passed to other classes
 * @author ydkim
 *
 */
public class GUI {

	/** 
	 * The location of the input file 
	 */
	private String fileLocation;
	
	/**
	 * The path to save output file
	 */
	private String outputLocation;
	
	/**
	 * The list of keywords that the user wants to find
	 */
	private ArrayList<String> keywords;
	
	/**
	 * The number of pages user wants as output
	 */
	private int pageNumberLimit;
	
	/** 
	 * This number represents the sort mode for the document
	 * 0: Order by page number (default)
	 * 1: Order by importance, represented by cosign value of each page
	 */
	private int sortByType;

	/**
	 * @return the fileLocation
	 */
	public String getFileLocation() {
		return fileLocation;
	}

	/**
	 * @return the outputLocation
	 */
	public String getOutputLocation() {
		return outputLocation;
	}

	/**
	 * @return the keywords
	 */
	public ArrayList<String> getKeywords() {
		return keywords;
	}

	/**
	 * @return the pageNumberLimit
	 */
	public int getPageNumberLimit() {
		return pageNumberLimit;
	}

	/**
	 * @return the sortByType
	 */
	public int getSortByType() {
		return sortByType;
	}
	
	
	
	
}
