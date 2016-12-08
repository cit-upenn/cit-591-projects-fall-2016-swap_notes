import java.util.ArrayList;
import java.util.HashMap;

import org.apache.pdfbox.pdmodel.PDPage;

public class PageAnalyzer {
	
	private PDPage page;
	private int pageNumber;
	private ArrayList<String> keywords;
	private String[] contentsArray;
	private HashMap<String, Integer> keywordFrequency; 
	

	public PageAnalyzer(PDPage page, int pageNumber, ArrayList<String> keywords, String pageContents) {
		this.page = page;
		this.pageNumber = pageNumber;
		this.keywords = keywords;
		this.contentsArray = pageContents.replaceAll("\\p{Punct}", "").split(" ");
		keywordFrequency = new HashMap<String, Integer>();
		
		//System.out.println("pageNumber: " + pageNumber);
		//count keyword frequency
		
	}
	
	public int countWords() {
		return contentsArray.length;
	}
	
	public void countFrequency() {
		for (int i = 0; i < keywords.size(); i++) {
			for (String word : contentsArray) {
				if (word.equals(keywords.get(i))) {
					if (keywordFrequency.containsKey(word)) {
						keywordFrequency.replace(word, keywordFrequency.get(word)+1);
					} else {
						keywordFrequency.put(word, 1);
					}
					
				}
			}
		}	
	}
	

	
	public HashMap<String, Integer> getKeywordFrequency() {
		return keywordFrequency;
	}
	
	public int getPageNumber() {
		return pageNumber;
	}
}
