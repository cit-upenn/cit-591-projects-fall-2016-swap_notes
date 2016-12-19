import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AnalyzedPageTest {

	private ArrayList<String> keywords;
	private File pdfFile;
	private PDDocument pDoc;
	private PDPage page1;
	private PDPage page2;
	private PDFTextStripper pageStripper;
	private String pageOneContents;
	private String pageTwoContents;
	private AnalyzedPage firstAnalyzedPage;
	private AnalyzedPage secondAnalyzedPage;
	private ArrayList<AnalyzedPage> passingInAnalyzedPage;
	
	@Before
	public void setup() throws Exception {
		keywords = new ArrayList<String>();
		keywords.add("paul");
		keywords.add("margins");
		pdfFile = new File("hw2.pdf");
		pDoc = PDDocument.load(pdfFile);
		page1 = pDoc.getPage(0);
		page2 = pDoc.getPage(1);
		pageStripper = new PDFTextStripper();
	    pageStripper.setStartPage(1);
	    pageStripper.setEndPage(1);
	    pageOneContents = pageStripper.getText(pDoc).toLowerCase();
	    pageStripper.setStartPage(2);
	    pageStripper.setEndPage(2);
	    pageTwoContents = pageStripper.getText(pDoc).toLowerCase();
		firstAnalyzedPage = new AnalyzedPage(page1, 1, keywords, pageOneContents, 0);
		secondAnalyzedPage = new AnalyzedPage(page2, 2, keywords, pageTwoContents, 0);
		passingInAnalyzedPage = new ArrayList<>();
		passingInAnalyzedPage.add(secondAnalyzedPage);
		passingInAnalyzedPage.add(firstAnalyzedPage);
	}
	
	
	@Test
	public void testGetRelativeFrequency() throws IOException {
		
		AnalyzedPage analyzedPage = new AnalyzedPage(page1, 1, keywords, pageOneContents, 0);
		HashMap<String, Double> testKeywordFrequency = new HashMap<>();
		testKeywordFrequency.put("paul", 2.0/107.0);
		testKeywordFrequency.put("margins", 1.0/107.0);
	
		assertEquals(testKeywordFrequency.size(), analyzedPage.getKeywordFrequency().size());
		for (String keyword : testKeywordFrequency.keySet()) {
			Double value = testKeywordFrequency.get(keyword);
			assertEquals(value, analyzedPage.getKeywordFrequency().get(keyword));
		}

	}
	
	@Test
	public void testDoVectorSpaceAnalysis() throws IOException {		
		AnalyzedPage analyzedPage = new AnalyzedPage(page1, 1, keywords, pageOneContents, 1);
		HashMap<String, Double> testKeywordFrequency = new HashMap<>();
		double paulCount = 2.0;
		double marginsCount = 1.0;
		double paulLog = Math.log10(paulCount) + 1;
		double marginsLog = Math.log10(marginsCount) + 1;
		double denominator = Math.pow(paulLog, 2) + Math.pow(marginsLog, 2);
		denominator = Math.sqrt(denominator);

		testKeywordFrequency.put("paul", paulLog/denominator);
		testKeywordFrequency.put("margins", marginsLog/denominator);
	
		assertEquals(testKeywordFrequency.size(), analyzedPage.getKeywordFrequency().size());
		for (String keyword : testKeywordFrequency.keySet()) {
			Double value = testKeywordFrequency.get(keyword);
			assertEquals(value, analyzedPage.getKeywordFrequency().get(keyword));
		}

	}
	
	@Test
	public void testGetPDPage() throws InvalidPasswordException, IOException {
		assertEquals(page1, firstAnalyzedPage.getPdPage());
	}

}
