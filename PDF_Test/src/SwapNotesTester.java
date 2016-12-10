import java.io.IOException;
import java.util.ArrayList;

public class SwapNotesTester {

	public static void main(String[] args) throws IOException {
		ArrayList<String> keywords = new ArrayList<String>();
		keywords.add("fibonacci");
		keywords.add("assemble");
		String condition = "or";
		FileInputFilter test = new FileInputFilter("CIT593_HW07-Assembly.pdf", keywords, condition);
		
		
		
//		test.logWeightWordFrequency();
	}

}
