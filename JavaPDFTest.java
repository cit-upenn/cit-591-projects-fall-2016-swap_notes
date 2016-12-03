
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;

public class JavaPDFTest {

	public void extractImages() throws Exception {
        try {
            //String destinationDir = "output.pdf";
            // Load the pdf
            String inputPdf = "Wiley.Big.Java.Early.Objects.5th.Edition.pdf";
            File input = new File(inputPdf);
            PDDocument document = PDDocument.load(input);
            
            
            List<PDPage> list = (List<PDPage>) ((PDDocument) document).getPages();
            // Declare output fileName
            String fileName = "output.pdf";
            // Create output file
            PDDocument newDocument = new PDDocument();
            // Create PDFTextStripper - used for searching the page string
            PDFTextStripper textStripper=new PDFTextStripper(); 
            // Declare "pages" and "found" variable
            String pages= null; 
            boolean found = false;     
            // Loop through each page and search for "SEARCH STRING". If this doesn't exist
            // ie is the image page, then copy into the new output.pdf. 
            for(int i = 0; i < list.size(); i++) {
                // Set textStripper to search one page at a time 
                textStripper.setStartPage(i); 
                textStripper.setEndPage(i);             
                PDPage returnPage = null;
                // Fetch page text and insert into "pages" string
                pages = textStripper.getText((PDDocument) document); 
                found = pages.contains("SEARCH STRING");
                    if (i != 0) {
                            // if nothing is found, then copy the page across to new                     output pdf file
                        if (found == false) {
                            returnPage = list.get(i - 1); 
                            System.out.println("page returned is: " + returnPage);
                            System.out.println("Copy page");
                            newDocument.importPage(returnPage);
                        }
                    }
            }    
            newDocument.save("/Users/luona/Documents" + "output.pdf");
            System.out.println(fileName + " saved");
         } 
         catch (Exception e) {
             e.printStackTrace();
             System.out.println("catch extract image");
         }
    }
}