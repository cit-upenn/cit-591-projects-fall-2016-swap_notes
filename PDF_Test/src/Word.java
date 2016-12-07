
public class Word {
	
	private String word;
	private int frequency;
	
	public Word(String word, int frequency) {
		this.word = word;
		this.frequency = frequency;
	}
	
	public int getFrequency() {
		return frequency;
	}

//	public int compareTo(Word word) {
//		if(this.frequency <  word.frequency) {
//			return 1;
//		} else if (this.frequency ==  word.frequency){
//			return 0;
//		} else {
//			return -1;
//		}
//	}
//	
//	public String toString() {
//		String result = "";
//		result = this.name + ":" + this.frequency;
//		return result;
//	
//	}
}
