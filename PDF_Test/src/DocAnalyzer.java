import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;

public class DocAnalyzer {

	ArrayList<AnalyzedPage> analyzedPageList;
	PDDocument docInProcess;

	public DocAnalyzer(ArrayList<AnalyzedPage> analyzedPageList) {
		this.analyzedPageList = analyzedPageList;

	}

	/**
	 * This function filters documents based on user input.
	 * The user can select the sort order and the number of pages for output
	 * @param pageLimit number of maximum output pages
	 * @param sortByType 0 for normal page order, 1 for relevance order
	 * @return new ArrayList of analyzed pages
	 */
	public ArrayList<AnalyzedPage> filterDocument(int pageLimit, int sortByType) {

		 

		ArrayList<AnalyzedPage> filteredDoc = analyzedPageList; 
		
		if (sortByType == 1) {	
			Collections.sort(filteredDoc, Collections.reverseOrder()) ;
		}

		if (pageLimit < filteredDoc.size()) {
			filteredDoc.subList(pageLimit, filteredDoc.size()).clear();
		}

		return filteredDoc;

	}

	public ArrayList<AnalyzedPage> printDocument() {
		for (AnalyzedPage page : analyzedPageList) {
			System.out.println(page.getPageNumber());
			System.out.println(page.getScore());
		}

		return analyzedPageList;
	}

	public PDDocument makeDocument() {

		PDDocument outputDoc = new PDDocument();
		for(AnalyzedPage page : analyzedPageList){

			try {
				PDPage pdpage = page.getPdPage();
				outputDoc.addPage(pdpage);

			} catch (Exception e) {
				System.out.println("Error during document creation process");
			}

		}

		return outputDoc;

	}
	
	

	



}





