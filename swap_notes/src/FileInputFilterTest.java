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


public class FileInputFilterTest {

//	@Before
//	public void setUp() throws Exception {
//	}
	
	private ArrayList<String> keywords;
	private FileInputFilter filterTest;
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
	public void setUp() throws Exception {
		keywords = new ArrayList<String>();
		keywords.add("paul");
		filterTest = new FileInputFilter("hw2.pdf", keywords, "and");
		pdfFile = new File("hw2.pdf");
		pDoc = PDDocument.load(pdfFile);
		page1 = pDoc.getPage(0);
		pageStripper = new PDFTextStripper();
	    pageStripper.setStartPage(1);
	    pageStripper.setEndPage(1);
	    pageOneContents = pageStripper.getText(pDoc).toLowerCase();
		firstAnalyzedPage = new AnalyzedPage(page1, 1, keywords, pageOneContents, 0);
	}

	@Test
	public void testFilterAnd() throws IOException {
		keywords.add("margins");
		ArrayList<AnalyzedPage> testVectorTable = new ArrayList<AnalyzedPage>();
		testVectorTable.add(firstAnalyzedPage);
		assertTrue(EqualsBuilder.reflectionEquals(testVectorTable, filterTest.getVectorTable()));

	}
	
	@Test
	public void testFilterOr() throws IOException {
		keywords.add("instructor");
		FileInputFilter filterTestOr = new FileInputFilter("hw2.pdf", keywords, "or");		
	    pageStripper.setStartPage(2);
	    pageStripper.setEndPage(2);
	    String pageTwoContents = pageStripper.getText(pDoc).toLowerCase();
		AnalyzedPage secondAnalyzedPage = new AnalyzedPage(page2, 2, keywords, pageTwoContents, 0);
		ArrayList<AnalyzedPage> testVectorTable = new ArrayList<AnalyzedPage>();
		testVectorTable.add(firstAnalyzedPage);
		testVectorTable.add(secondAnalyzedPage);
		assertTrue(EqualsBuilder.reflectionEquals(testVectorTable, filterTestOr.getVectorTable()));

	}

}
