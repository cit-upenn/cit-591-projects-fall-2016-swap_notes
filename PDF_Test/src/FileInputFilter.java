import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;

/**
 * This class takes a file as input and filters the pages that meets condition.
 * @author Leon Wee, Yoon Duk Kim, Na Luo
 *
 */
public class FileInputFilter {
	
	private ArrayList<AnalyzedPage> vectorTable;
	private ArrayList<String> keywords;
	private String outputMode;
	
	/**
	 * This constructor initializes a FileInputFilter by taking fileName, keywords, outputMode as parameters.
	 * @param fileName input pdf file path
	 * @param keywords list of keywords
	 * @param outputMode determines whether we should use AND or OR operator for multiple keywords
	 * @throws IOException
	 */
	public FileInputFilter(String fileName, ArrayList<String> keywords, String outputMode) throws IOException {
		this.keywords = keywords;
		this.outputMode = outputMode;
		vectorTable = new ArrayList<AnalyzedPage>();
		filter(fileName);	
	}
	
	/**
	 * This method takes a file and creates a AnalyzedPage object for each page that meets condition, and
	 * then adds every AnalyzedPage object created to the vectorTable.
	 * @param fileName input pdf file path
	 * @throws IOException
	 */
	private void filter(String fileName) throws IOException {
		File pdfFile = new File(fileName);
		PDDocument pdoc = PDDocument.load(pdfFile);
		
	
		for(int pageNumber = 1; pageNumber <= pdoc.getNumberOfPages(); pageNumber++){
		    PDFTextStripper pageStripper = new PDFTextStripper();
		    pageStripper.setStartPage(pageNumber);
		    pageStripper.setEndPage(pageNumber);
		    String contents = pageStripper.getText(pdoc).toLowerCase();
		    boolean filterCondition = (outputMode.equals("and")) ? outputAndMode(contents) : outputOrMode(contents);
  
		    if (filterCondition) {
				try {
					PDPage page = pdoc.getPage(pageNumber - 1);
					AnalyzedPage eachPage = new AnalyzedPage(page, pageNumber, keywords, contents, 0);
					vectorTable.add(eachPage);
				} catch (Exception e) {
					
				}	
		    }
		}
	}
	
	/**
	 * This method checks if the page contains all of keywords when the output mode is AND.
	 * @param pageContents string contents of a pdf page
	 * @return true if page contains all of keywords, false if the page doesn't contains all of keywords.
	 */
	private boolean outputAndMode(String pageContents) {
		for (String keyword : keywords) {
			if (!pageContents.contains(keyword)) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * This method checks if the page contains one of keywords when the output mode is OR.
	 * @param pageContents string contents of a pdf page
	 * @return true if page contains one of keywords, false if the page doesn't contains one of keywords.
	 */
	private boolean outputOrMode(String pageContents) {
		for (String keyword : keywords) {
			if (pageContents.contains(keyword)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * This method can access vectorTable.
	 * @return vectorTable list of AnalyzedPage objects
	 */
	public ArrayList<AnalyzedPage> getVectorTable() {
		return vectorTable;
	}

}
