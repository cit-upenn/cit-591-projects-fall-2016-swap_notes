import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AnalyzedPageTest {

/**	@throws IOException 
 * @Before
	public void setUp() throws Exception {
	}
**/
	@Test
	public void test() throws IOException {
		
		ArrayList<String> keywords = new ArrayList<String>();
		keywords.add("paul");
		keywords.add("margins");
		
		File pdfFile = new File("hw2.pdf");
		PDDocument pdoc = PDDocument.load(pdfFile);
		PDPage page = pdoc.getPage(0);
		
		PDFTextStripper pageStripper = new PDFTextStripper();
	    pageStripper.setStartPage(1);
	    pageStripper.setEndPage(1);
	    String contents = pageStripper.getText(pdoc).toLowerCase();
		
		AnalyzedPage analyzedPage = new AnalyzedPage(page, 1, keywords, contents, 0);
		HashMap<String, Double> testKeywordFrequency = new HashMap<>();
		testKeywordFrequency.put("paul", 2.0/107.0);
		testKeywordFrequency.put("margins", 1.0/107.0);
	
		assertEquals(testKeywordFrequency.size(), analyzedPage.getKeywordFrequency().size());
		for (String keyword : testKeywordFrequency.keySet()) {
			Double value = testKeywordFrequency.get(keyword);
			assertEquals(value, analyzedPage.getKeywordFrequency().get(keyword));
		}

	}

}
