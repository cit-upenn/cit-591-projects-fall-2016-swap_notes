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


	public void saveDocument() throws IOException {

		documentToPrint.save("outputDoc.pdf");
	}

	public void saveDocumentAs(String savePath) throws IOException {

		documentToPrint.save(savePath);
	}


	public ArrayList<BufferedImage> getPreviewList(PDDocument documentToPrint) throws IOException{

		ArrayList<BufferedImage> previewList = new ArrayList<BufferedImage>();
		
		PDFRenderer pdfRenderer = new PDFRenderer(documentToPrint);
		for (int page = 0; page < documentToPrint.getNumberOfPages(); ++page) { 
			BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);

			previewList.add(bim);
			
			// suffix in filename will be used as the file format
			//			ImageIOUtil.writeImage(bim, pdfFilename + "-" + (page+1) + ".png", 300);
		}
		documentToPrint.close();
		
		return previewList;
	}


}
