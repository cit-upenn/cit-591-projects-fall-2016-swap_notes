import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class DocAnalyzerTest {
	
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
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	@Before
	public void setUp() throws Exception {
		keywords = new ArrayList<String>();
		keywords.add("programming");		
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
		System.setOut(new PrintStream(outContent));
	}

	@Test
	public void testFilterDocumentSort() throws IOException {		
		ArrayList<AnalyzedPage> expectedAnalyzedPage = new ArrayList<>();
		expectedAnalyzedPage.add(firstAnalyzedPage);
		expectedAnalyzedPage.add(secondAnalyzedPage);
		
		DocAnalyzer doc = new DocAnalyzer(passingInAnalyzedPage);
		int index = 0;
		doc.filterDocument(2, 1);		
		for (AnalyzedPage page : doc.getAnalyzedPageList()) {
			assertEquals(expectedAnalyzedPage.get(index).getPageNumber(), page.getPageNumber());
			index++;
		}
		
	}
	
	@Test
	public void testFilterDocumentPageLimit() throws IOException {
		ArrayList<AnalyzedPage> expectedAnalyzedPage = new ArrayList<>();
		expectedAnalyzedPage.add(firstAnalyzedPage);		
		DocAnalyzer doc = new DocAnalyzer(passingInAnalyzedPage);
		int index = 0;
		doc.filterDocument(1, 1);
		for (AnalyzedPage page : doc.getAnalyzedPageList()) {
			assertEquals(expectedAnalyzedPage.get(index).getPageNumber(), page.getPageNumber());
			index++;
		}
		
	}
	
	@Test
	public void testMakeDocument() throws IOException {
		DocAnalyzer doc = new DocAnalyzer(passingInAnalyzedPage);
		doc.filterDocument(2, 1);				
		Assert.assertNotNull(doc.makeDocument());  //Checks for null
		assertEquals(doc.makeDocument().getClass().getName(), "org.apache.pdfbox.pdmodel.PDDocument");
	}




}
