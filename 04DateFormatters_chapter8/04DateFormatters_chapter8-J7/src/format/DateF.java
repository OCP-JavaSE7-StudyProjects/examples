package format;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateF {

	public void currentDateAndTime() {
		Date now = new Date();
		System.out.println("Current Date:\t\t" + now.toString());
		System.out.println("Millis from 1970.01.01:\t" + now.getTime());
	}
	
	public void dateAndTimeCalculation() {
		Calendar now = Calendar.getInstance();
		System.out.println("Current Calendar:\t" + now.getTime());
		
		System.out.println("Day of week:\t\t" + now.get(Calendar.DAY_OF_WEEK));
		
		now.add(Calendar.MONTH, 12);
		System.out.println("add 12 month Calendar:\t" + now.getTime());
		
		now.roll(Calendar.MONTH, 12);
		System.out.println("roll 12 month Calendar:\t" + now.getTime());
	}
	
	public void formatCalendarToLocalSpecificDate() {		
		Calendar now = Calendar.getInstance();
		System.out.println("Current Calendar:\t" + now.getTime());
		
		Locale locale = new Locale("hu", "HU");
		DateFormat dfH = DateFormat.getDateInstance(DateFormat.FULL, locale);
		System.out.println("Hungary formatted:\t" + dfH.format(now.getTime()));
		
		DateFormat dfF = DateFormat.getDateInstance(DateFormat.FULL, Locale.FRANCE);
		System.out.println("Frace formatted:\t" + dfF.format(now.getTime()));
	}
	
	public static void main(String[] args) {
		DateF d = new DateF();
		
		System.out.println("-- Current date and Time --");
		d.currentDateAndTime();
		System.out.println();
		
		System.out.println("-- Date and Time calculation --");
		d.dateAndTimeCalculation();
		System.out.println();
		
		System.out.println("-- Format to Locale --");
		d.formatCalendarToLocalSpecificDate();
		System.out.println();
	}
	
}
