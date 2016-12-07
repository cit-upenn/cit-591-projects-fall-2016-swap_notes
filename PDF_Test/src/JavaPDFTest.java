import java.io.File;
import java.io.IOException;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;

public class JavaPDFTest {

	public static void main(String[] args) throws IOException {
		
		File pdfFile = new File("/Users/ydkim/Documents/ebook/Textbooks/591.pdf");
		PDDocument pdoc = PDDocument.load(pdfFile);
		System.out.println(pdoc.getNumberOfPages());
		
		PDDocument outputDoc = new PDDocument();
		
		for(int pageNumber = 1; pageNumber < pdoc.getNumberOfPages(); pageNumber++){
		    PDFTextStripper s = new PDFTextStripper();
		    s.setStartPage(pageNumber);
		    s.setEndPage(pageNumber);
		    String contents = s.getText(pdoc).toLowerCase(); 
		    
		    if (contents.contains("hashmap")) {
//		    	int count = StringUtils.countMatches(contents, "array");
//		    	System.out.println("It works");
		    	System.out.println("These are the page numbers: " + pageNumber);
//		    	System.out.println("Number of occurrences: " + count);
	
				
				try {
					PDPage page = pdoc.getPage(pageNumber - 1);
					outputDoc.addPage(page);
//					outputDoc.save("output.pdf");
				} catch (Exception e) {
					
				}
		    	
		    }
		}
		
		System.out.println(outputDoc);
		outputDoc.save("output.pdf");
		outputDoc.close();


	}    
}