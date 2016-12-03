import java.io.File;
import java.io.IOException;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class JavaPDFTest {

	public static void main(String[] args) throws IOException {
		
		File pdfFile = new File("bigJava.pdf");
		PDDocument pdoc = PDDocument.load(pdfFile);
		System.out.println(pdoc.getNumberOfPages());
		
		for(int pageNumber = 1; pageNumber < pdoc.getNumberOfPages(); pageNumber++){
		    PDFTextStripper s = new PDFTextStripper();
		    s.setStartPage(pageNumber);
		    s.setEndPage(pageNumber);
		    String contents = s.getText(pdoc); 
		    
		    if (contents.contains("Array")) {
		    	int count = StringUtils.countMatches(contents, "Array");
		    	System.out.println("It works");
		    	System.out.println("These are the page numbers: " + pageNumber);
		    	System.out.println("Number of occurrences: " + count);
		    }
		}

	}    
}