package format;


public class StringF {

	public void formatStringPrintf() {
		System.out.printf("%2$d + %1$d\n", 123, 456);
		
		System.out.printf(">%1$(7d<\n", -123);
		System.out.printf(">%0,7d<\n", 12345);
	}
	
	public void formatString() {
		System.out.println(String.format("Hello %03d %s", 7, "Bond"));
	}
	
	public static void main(String[] args) {
		StringF s = new StringF();
		
		System.out.println("-- Printf --");
		s.formatStringPrintf();
		System.out.println();
		
		System.out.println("-- String.format --");
		s.formatString();
		System.out.println();
	}
	
}
