import java.util.ArrayList;

import org.apache.pdfbox.pdmodel.PDPage;

public class PageAnalyzer {
	private ArrayList<Word> keywordFrequency = new ArrayList<Word>();

	public PageAnalyzer(PDPage page, int pageNumber, ArrayList<String> keywords, String pageContents) {
		System.out.println("pageNumber: " + pageNumber);
		//count keyword frequency
		
	}
	
	private int countWords(String contents) {
		String[] contentsArray = contents.replaceAll("\\p{Punct}", "").split(" ");
		return contentsArray.length;
	}
	
	//for (each keyword)
	// for (each word in PageContents)
	// count ++
	
}
