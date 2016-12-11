import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;

public class DocAnalyzer {

	ArrayList<AnalyzedPage> analyzedPageList;
	PDDocument docInProgress;

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

		outputDoc = docInProgress;
		return outputDoc;

	}
	
	
	public ArrayList<BufferedImage> getPreviewList() throws IOException{

		ArrayList<BufferedImage> previewList = new ArrayList<BufferedImage>();
		
		PDFRenderer pdfRenderer = new PDFRenderer(docInProgress);
		for (int page = 0; page < docInProgress.getNumberOfPages(); ++page) { 
			BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);

			previewList.add(bim);
			
			// suffix in filename will be used as the file format
			//			ImageIOUtil.writeImage(bim, pdfFilename + "-" + (page+1) + ".png", 300);
		}
		docInProgress.close();
		
		return previewList;
	}
	



}





