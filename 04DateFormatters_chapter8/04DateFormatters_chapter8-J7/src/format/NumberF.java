package format;

import java.text.NumberFormat;
import java.util.Locale;

public class NumberF {

	public void formatNumbers() {
		Locale locale = new Locale("hu", "HU");
		
		NumberFormat nfH = NumberFormat.getInstance(locale);
		System.out.println("Hungary number:\t\t" + nfH.format(1235.10f));
		
		NumberFormat nfF = NumberFormat.getInstance(Locale.FRANCE);
		System.out.println("France number:\t\t" + nfF.format(1235.10f));
	}
	
	public void formatCurrencies() {
		Locale locale = new Locale("hu", "HU");
		
		NumberFormat nfHC = NumberFormat.getCurrencyInstance(locale);
		System.out.println("Hungary currency:\t" + nfHC.format(1235.10f));
		
		NumberFormat nfFC = NumberFormat.getCurrencyInstance(Locale.FRANCE);
		System.out.println("France currency:\t" + nfFC.format(1235.10f));
	}
	
	public static void main(String[] args) {
		NumberF n = new NumberF();
	
		System.out.println("-- Format Numbers --");
		n.formatNumbers();
		System.out.println();
		
		System.out.println("-- Format Currencies --");
		n.formatCurrencies();
		System.out.println();
	}
	
}
