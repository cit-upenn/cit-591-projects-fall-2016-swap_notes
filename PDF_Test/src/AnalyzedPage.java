import java.util.ArrayList;
import java.util.HashMap;

import org.apache.pdfbox.pdmodel.PDPage;

public class AnalyzedPage implements Comparable<AnalyzedPage> {

	private PDPage page;
	private int pageNumber;
	private ArrayList<String> keywords;
	private String[] contentsArray;
	private double wordCount;
	private HashMap<String, Double> keywordImportance;
	//	private HashMap<String, Double> keywordRelFreq;
	private double rankScore = 0;


	//This is the vector space model to judge similarity between input page and the document pages
	public AnalyzedPage(PDPage page, int pageNumber, ArrayList<String> keywords, String pageContents, int pageCompare) {
		this.page = page;
		this.pageNumber = pageNumber;
		this.keywords = keywords;
		this.contentsArray = pageContents.replaceAll("\\p{Punct}", " ").split(" ");
		wordCount = contentsArray.length;
		System.out.println("This is word count of the Page: " + wordCount);

		if (pageCompare == 1) {
			//do page-by-page vector space analysis
			doVectorSpaceAnalysis();
		} else {

			//adjusted vector space model using relative frequency 
			keywordImportance = new HashMap<String, Double>();
			getRelativeFreq();
			calculateRankScore();
		}
	}




	//This is the ranking algorithm to find out the relative frequency of each page
	private void getRelativeFreq() {

		for (int i = 0; i < keywords.size(); i++) {
			for (String word : contentsArray) {
				if (word.contains(keywords.get(i))) {
					if (keywordImportance.containsKey(keywords.get(i))) {
						keywordImportance.replace(keywords.get(i), keywordImportance.get(keywords.get(i)) + 1.0/wordCount);
					} else {
						keywordImportance.put(keywords.get(i), 1.0/wordCount);
					}

				}
			}
		}	
		System.out.println("This is the relative keyword frequency terms without log weighting: " + keywordImportance);
		System.out.println("This is the page number: " + pageNumber);


	}

	private void doVectorSpaceAnalysis() {
		keywordImportance = new HashMap<String, Double>();
		countFrequency();
		logWeightWordFrequency();
		lengthNormalize();
		calculateRankScore();
	}

	private void countFrequency() {
		for (int i = 0; i < keywords.size(); i++) {
			for (String word : contentsArray) {
				if (word.contains(keywords.get(i))) {
					if (keywordImportance.containsKey(keywords.get(i))) {
						keywordImportance.replace(keywords.get(i), keywordImportance.get(keywords.get(i)) + 1);
					} else {
						keywordImportance.put(keywords.get(i), 1.0);
					}

				}
			}
		}	
		System.out.println("This is the keyword frequency terms without log weighting: " + keywordImportance);
		System.out.println("This is the page number: " + pageNumber);

	}

	private void logWeightWordFrequency() {
		for (String word : keywordImportance.keySet()) {
			keywordImportance.put(word, Math.log10(keywordImportance.get(word)) + 1);
		}	
	}

	private void lengthNormalize() {

		double denominator = 0;
		for (String word : keywordImportance.keySet()) {
			denominator += Math.pow(keywordImportance.get(word), 2);
		}

		denominator = Math.sqrt(denominator);
		for (String word : keywordImportance.keySet()) {
			keywordImportance.put(word, keywordImportance.get(word) / denominator);
		}

		//		System.out.println("=========================================================");
		System.out.println("This is the length normalized value: " + keywordImportance);
	}

	private void calculateRankScore() {
		for (String word : keywordImportance.keySet()) {
			rankScore+= keywordImportance.get(word);

		}

		System.out.println("The rank score of this page is : " + rankScore);
	}


	public HashMap<String, Double> getKeywordFrequency() {
		return keywordImportance;
	}

	public double getScore() {
		return rankScore;
	}


	public int getPageNumber() {
		return pageNumber;

	}

	public PDPage getPdPage() {
		return page;
	}
	
	

	@Override
	public int compareTo(AnalyzedPage page) {
		double score = this.getScore();
		double otherScore = page.getScore();
		if (score > otherScore) {
			return 1;
		} else if (score < otherScore) {
			return -1;
		} else {
			return 0;
		}
	}
}
