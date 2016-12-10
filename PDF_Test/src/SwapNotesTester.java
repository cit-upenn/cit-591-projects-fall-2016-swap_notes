import java.io.IOException;
import java.util.ArrayList;

public class SwapNotesTester {

	public static void main(String[] args) throws IOException {
		ArrayList<String> keywords = new ArrayList<String>();
		keywords.add("memory");
		keywords.add("list");
		String condition = "and";
		FileInputFilter test = new FileInputFilter("CIT593_HW11.pdf", keywords, condition);
		DocAnalyzer testdoc = new DocAnalyzer(test.getVectorTable());
		testdoc.printDocument();
		
		
		testdoc.filterDocument(20, 0);
		testdoc.printDocument();
		
		
		DocPrinter printer = new DocPrinter(testdoc.makeDocument());
		printer.saveDocument();
		
	}

}
