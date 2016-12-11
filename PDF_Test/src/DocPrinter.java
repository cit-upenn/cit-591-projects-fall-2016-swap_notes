import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

public class DocPrinter {

	PDDocument documentToPrint;

	public DocPrinter(PDDocument documentToPrint) {
		this.documentToPrint = documentToPrint;
	}


//	public void saveDocument() throws IOException {
//
//		documentToPrint.save("outputDoc.pdf");
//	}

	public void saveDocumentAs(String outputDirectory, String outputFileName) throws IOException {
		
		documentToPrint.save(outputDirectory + "/" + outputFileName + ".pdf");
	}





}
