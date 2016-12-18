import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

/**
 * This class saves the PDDocument as a pdf file
 * @author Leon Wee, Yoon Duk Kim, Na Luo
 *
 */
public class DocPrinter {

	PDDocument documentToPrint;

	/**
	 * Constructor for DocPrinter class
	 * @param documentToPrint PDDocument format document
	 */
	public DocPrinter(PDDocument documentToPrint) {
		this.documentToPrint = documentToPrint;
	}


//	public void saveDocument() throws IOException {
//
//		documentToPrint.save("outputDoc.pdf");
//	}

	/**
	 * Saves document as a pdf file to the given directory
	 * @param outputDirectory location of the output directory
	 * @param outputFileName name of the output file
	 * @throws IOException
	 */
	public void saveDocumentAs(String outputDirectory, String outputFileName) throws IOException {
		
		documentToPrint.save(outputDirectory + "/" + outputFileName + ".pdf");
	}





}
