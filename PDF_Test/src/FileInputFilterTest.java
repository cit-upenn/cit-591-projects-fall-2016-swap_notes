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

	@Test
	public void testFilterAnd() throws IOException {
		ArrayList<String> keywords = new ArrayList<String>();
		keywords.add("paul");
		keywords.add("margins");
		FileInputFilter test = new FileInputFilter("hw2.pdf", keywords, "and");
		
		File pdfFile = new File("hw2.pdf");
		PDDocument pdoc = PDDocument.load(pdfFile);
		PDPage page = pdoc.getPage(0);
		
		PDFTextStripper pageStripper = new PDFTextStripper();
	    pageStripper.setStartPage(1);
	    pageStripper.setEndPage(1);
	    String contents = pageStripper.getText(pdoc).toLowerCase();
		
		AnalyzedPage analyzedPage = new AnalyzedPage(page, 1, keywords, contents, 0);
		ArrayList<AnalyzedPage> testVectorTable = new ArrayList<AnalyzedPage>();
		testVectorTable.add(analyzedPage);
		Assert.assertTrue(EqualsBuilder.reflectionEquals(testVectorTable, test.getVectorTable()));

	}
	
	@Test
	public void testFilterOr() throws IOException {
		ArrayList<String> keywords = new ArrayList<String>();
		keywords.add("paul");
		keywords.add("instructor");
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
		ArrayList<AnalyzedPage> testVectorTable = new ArrayList<AnalyzedPage>();
		testVectorTable.add(analyzedPage1);
		testVectorTable.add(analyzedPage2);
		Assert.assertTrue(EqualsBuilder.reflectionEquals(testVectorTable, test.getVectorTable()));

	}

}
