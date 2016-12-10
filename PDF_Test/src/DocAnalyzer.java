import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;

public class DocAnalyzer {

	ArrayList<AnalyzedPage> analyzedPageList;
	


	public DocAnalyzer(ArrayList<AnalyzedPage> analyzedPageList) {
		this.analyzedPageList = analyzedPageList;

	}

	public ArrayList<AnalyzedPage> filterDocument(int pageLimit, int sortByType) {

		//TO DO: implement sort by type 
		
		ArrayList<AnalyzedPage> filteredDoc = analyzedPageList; 
		Collections.sort(filteredDoc, Collections.reverseOrder()) ;

		filteredDoc.subList(pageLimit, filteredDoc.size()).clear();
		
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





