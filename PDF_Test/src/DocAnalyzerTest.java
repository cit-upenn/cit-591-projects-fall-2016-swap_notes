import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DocAnalyzerTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testFilterDocumentSort() throws IOException {
		ArrayList<String> keywords = new ArrayList<String>();
		keywords.add("programming");
		FileInputFilter test = new FileInputFilter("hw2.pdf", keywords, "or");
		
		File pdfFile = new File("hw2.pdf");
		PDDocument pdoc = PDDocument.load(pdfFile);
		PDPage page1 = pdoc.getPage(0);
		PDPage page2 = pdoc.getPage(1);
		
		PDFTextStripper pageStripper = new PDFTextStripper();
	    pageStripper.setStartPage(1);
	    pageStripper.setEndPage(1);
	    String contents1 = pageStripper.getText(pdoc).toLowerCase();
	    pageStripper.setStartPage(2);
	    pageStripper.setEndPage(2);
	    String contents2 = pageStripper.getText(pdoc).toLowerCase();
		
		AnalyzedPage analyzedPage1 = new AnalyzedPage(page1, 1, keywords, contents1, 0);
		AnalyzedPage analyzedPage2 = new AnalyzedPage(page2, 2, keywords, contents2, 0);
		ArrayList<AnalyzedPage> actualAnalyzedPage = new ArrayList<>();
		actualAnalyzedPage.add(analyzedPage2);
		actualAnalyzedPage.add(analyzedPage1);
		
		ArrayList<AnalyzedPage> expectedAnalyzedPage = new ArrayList<>();
		expectedAnalyzedPage.add(analyzedPage1);
		expectedAnalyzedPage.add(analyzedPage2);
		
		DocAnalyzer doc = new DocAnalyzer(actualAnalyzedPage);
		int i = 0;
		doc.filterDocument(2, 1);
		for (AnalyzedPage page : doc.getAnalyzedPageList()) {
			assertEquals(expectedAnalyzedPage.get(i).getPageNumber(), page.getPageNumber());
			i++;
		}
		
	}
	
	public void testMakeDocument() throws IOException {
		ArrayList<String> keywords = new ArrayList<String>();
		keywords.add("programming");
		FileInputFilter test = new FileInputFilter("hw2.pdf", keywords, "or");
		
		File pdfFile = new File("hw2.pdf");
		PDDocument pdoc = PDDocument.load(pdfFile);
		PDPage page1 = pdoc.getPage(0);
		PDPage page2 = pdoc.getPage(1);
		
		PDFTextStripper pageStripper = new PDFTextStripper();
	    pageStripper.setStartPage(1);
	    pageStripper.setEndPage(1);
	    String contents1 = pageStripper.getText(pdoc).toLowerCase();
	    pageStripper.setStartPage(2);
	    pageStripper.setEndPage(2);
	    String contents2 = pageStripper.getText(pdoc).toLowerCase();
		
		AnalyzedPage analyzedPage1 = new AnalyzedPage(page1, 1, keywords, contents1, 0);
		AnalyzedPage analyzedPage2 = new AnalyzedPage(page2, 2, keywords, contents2, 0);
		ArrayList<AnalyzedPage> actualAnalyzedPage = new ArrayList<>();
		actualAnalyzedPage.add(analyzedPage2);
		actualAnalyzedPage.add(analyzedPage1);
		
		ArrayList<AnalyzedPage> expectedAnalyzedPage = new ArrayList<>();
		expectedAnalyzedPage.add(analyzedPage1);
		expectedAnalyzedPage.add(analyzedPage2);
		
		DocAnalyzer doc = new DocAnalyzer(actualAnalyzedPage);
		doc.filterDocument(2, 1);
//		doc.makeDocument(); // This is actual output PDF document
		
		PDDocument expectedOutputDoc = new PDDocument();
		expectedOutputDoc.addPage(page1);
		expectedOutputDoc.addPage(page2);
//		assertEquals(expectedOutputDoc, doc.makeDocument());
//		assertTrue(expectedOutputDoc.equals(doc.makeDocument()));
		Assert.assertTrue(EqualsBuilder.reflectionEquals(expectedOutputDoc, doc.makeDocument()));

		
	}

}
