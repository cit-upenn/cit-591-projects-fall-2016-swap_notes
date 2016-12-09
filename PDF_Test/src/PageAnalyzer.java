import java.util.ArrayList;
import java.util.HashMap;

import org.apache.pdfbox.pdmodel.PDPage;

public class PageAnalyzer {
	
	private PDPage page;
	private int pageNumber;
	private ArrayList<String> keywords;
	private String[] contentsArray;
	private HashMap<String, Double> keywordFrequency; 
	

	public PageAnalyzer(PDPage page, int pageNumber, ArrayList<String> keywords, String pageContents) {
		this.page = page;
		this.pageNumber = pageNumber;
		this.keywords = keywords;
		this.contentsArray = pageContents.replaceAll("\\p{Punct}", " ").split(" ");
		keywordFrequency = new HashMap<String, Double>();
		countFrequency();
		logWeightWordFrequency();
	}
	
	public int countWords() {
		return contentsArray.length;
	}
	
	private void countFrequency() {
		for (int i = 0; i < keywords.size(); i++) {
			for (String word : contentsArray) {
				if (word.contains(keywords.get(i))) {
					if (keywordFrequency.containsKey(keywords.get(i))) {
						keywordFrequency.replace(keywords.get(i), keywordFrequency.get(keywords.get(i)) + 1);
					} else {
						keywordFrequency.put(keywords.get(i), 1.0);
					}
					
				}
			}
		}	
		System.out.println("This is the keyword frequency terms without log weighting: " + keywordFrequency);
		System.out.println("This is the page number: " + pageNumber);

	}
	
	private void logWeightWordFrequency() {
		for (String word : keywordFrequency.keySet()) {
			keywordFrequency.put(word, Math.log10(keywordFrequency.get(word)) + 1);
		}

	}
	
	public HashMap<String, Double> getKeywordFrequency() {
		return keywordFrequency;
	}
	
	public int getPageNumber() {
		return pageNumber;
	}
}
