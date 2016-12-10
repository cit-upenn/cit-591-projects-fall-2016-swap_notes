import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;

public class DocPrinter {

	PDDocument documentToPrint;
	
	public DocPrinter(PDDocument documentToPrint) {
		this.documentToPrint = documentToPrint;
	}
	
	
	public void saveDocument() throws IOException {

		documentToPrint.save("outputDoc.pdf");
	}
	
	
}
