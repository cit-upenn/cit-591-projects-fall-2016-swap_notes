import java.util.ArrayList;
import java.util.Collections;

public class DocAnalyzer {

	ArrayList<PageAnalyzer> pdfDocument;
	
	
	public DocAnalyzer(ArrayList<PageAnalyzer> pdfDocument) {
		this.pdfDocument = pdfDocument;
		
	}
	
	public void filterDocument(int pageLimit, int sortByType) {
		
		Collections.sort(pdfDocument, Collections.reverseOrder()) ;
		
	}
	
	public ArrayList<PageAnalyzer> getDocument() {
		for (PageAnalyzer page : pdfDocument) {
			System.out.println(page.getPageNumber());
			System.out.println(page.getScore());
		}
		
		return pdfDocument;
	}
	
	public PDDocument makeDocument() {
		
		
		
	}
	
	
}
