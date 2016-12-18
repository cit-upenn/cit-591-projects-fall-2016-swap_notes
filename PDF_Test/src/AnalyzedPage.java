import java.util.ArrayList;
import java.util.HashMap;

import org.apache.pdfbox.pdmodel.PDPage;

/**
 * This class does a vector space analysis or keyword frequency analysis for a PDPage.
 * @author Leon Wee, Yoon Duk Kim, Na Luo
 *
 */
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
	/**
	 * This constructor initializes a AnalyzedPage object.
	 * @param page PDPage object
	 * @param pageNumber page number identifier
	 * @param keywords list of keywords
	 * @param pageContents string contents of a page
	 * @param pageCompare Comparison algorithm, set to 0 by default. 1 = page-by-page vector space analysis. 2 = adjusted vector space model using relative frequency. 
	 */
	public AnalyzedPage(PDPage page, int pageNumber, ArrayList<String> keywords, String pageContents, int pageCompare) {
		this.page = page;
		this.pageNumber = pageNumber;
		this.keywords = keywords;
		this.contentsArray = pageContents.replaceAll("\\p{Punct}", " ").split(" ");
		wordCount = contentsArray.length;
		keywordImportance = new HashMap<String, Double>();
		System.out.println("This is word count of the Page: " + wordCount);
		if (pageCompare == 1) {
			//do page-by-page vector space analysis
			doVectorSpaceAnalysis();
		} else {
			//adjust vector space model using relative frequency 
			getRelativeFreq();
			calculateRankScore();
		}
	}


	/**
	 * This method counts the relative frequency of keywords in this page and stores the frequency in hashmap. 
	 */
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
	}
    
	/**
	 * This method does vector space analysis, by normalizing the log weight of the word's frequency.
	 */
	private void doVectorSpaceAnalysis() {
		keywordImportance = new HashMap<String, Double>();
		countFrequency();
		logWeightWordFrequency();
		lengthNormalize();
		calculateRankScore();
	}
    
	/**
	 * This method counts the frequency of keywords in this page.
	 */
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
	}

	/**
	 * This method counts the logWeight of keywords in this page.
	 */
	private void logWeightWordFrequency() {
		for (String word : keywordImportance.keySet()) {
			keywordImportance.put(word, Math.log10(keywordImportance.get(word)) + 1);
		}	
	}
    
	/**
	 * This method does length normalization.
	 */
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

	
	/**
	 * This method calculates the rank score of this page.
	 */
	private void calculateRankScore() {
		for (String word : keywordImportance.keySet()) {
			rankScore+= keywordImportance.get(word);
		}
	}

    
	/**
	 * This method can access the keywordImportance.
	 * @return keywordImportance the hashmap of keyword and their relative importance
	 */
	public HashMap<String, Double> getKeywordFrequency() {
		return keywordImportance;
	}

	/**
	 * This method can access the rankScore.
	 * @return rankScore the final output used for comparing page importance
	 */
	public double getScore() {
		return rankScore;
	}

    /**
     * This method can access the pageNumber.
     * @return pageNumber
     */
	public int getPageNumber() {
		return pageNumber;

	}

	/**
	 * This method can access PDPage.
	 * @return page
	 */
	public PDPage getPdPage() {
		return page;
	}
	
	/**
	 * This method can make a comparison between two AnalyzedPage objects according to their rank score.
	 */
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
