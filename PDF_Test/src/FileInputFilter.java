import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;

public class FileInputFilter {
	
	private ArrayList<AnalyzedPage> vectorTable;
	private ArrayList<String> keywords;
	private String outputMode;
	
	public FileInputFilter(String fileName, ArrayList<String> keywords, String outputMode) throws IOException {
		this.keywords = keywords;
		this.outputMode = outputMode;
		vectorTable = new ArrayList<AnalyzedPage>();
		filter(fileName);	
	}
	
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
	
	private boolean outputAndMode(String pageContents) {
		for (String keyword : keywords) {
			if (!pageContents.contains(keyword)) {
				return false;
			}
		}
		
		return true;
	}
	
	private boolean outputOrMode(String pageContents) {
		for (String keyword : keywords) {
			if (pageContents.contains(keyword)) {
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<AnalyzedPage> getVectorTable() {
		return vectorTable;
	}

}
