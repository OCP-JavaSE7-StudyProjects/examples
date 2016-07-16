package format;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;

public class DateF {

	public void currentTimestamp() {
		Instant instant = Instant.now();
		System.out.println("Timestamp via instant\t" + instant);
		System.out.println("Millis via instant\t" + instant.toEpochMilli());
		
		Clock clock = Clock.systemDefaultZone();
		System.out.println("Millis via clock\t" + clock.millis());
		
		Date now = Date.from(clock.instant());
		System.out.println("Current Date\t\t" + now);
	}
	
	public void timeZones() {
		System.out.println(ZoneId.getAvailableZoneIds());

		ZoneId zone1 = ZoneId.of("Europe/Budapest");
		ZoneId zone2 = ZoneId.of("Brazil/East");
		System.out.println("Budapest TimeZone:\t" + zone1.getRules());
		System.out.println("Brazil TimeZone:\t" + zone2.getRules());
	}
	
	public void localTime() {
		ZoneId zone1 = ZoneId.of("Europe/Budapest");
		ZoneId zone2 = ZoneId.of("Brazil/East");
		
		LocalTime now1 = LocalTime.now(zone1);
		LocalTime now2 = LocalTime.now(zone2);
		System.out.println("Budapest local time:\t" + now1);
		System.out.println("Brazil local time:\t" + now2);
		System.out.println("Brazil < Budapest:\t" + now2.isBefore(now1));

		System.out.println("Hours between:\t\t" + ChronoUnit.HOURS.between(now1, now2));
		System.out.println("Minutes between:\t" + ChronoUnit.MINUTES.between(now1, now2));
	}
	
	public void parseToLocalTime() {
		LocalTime late = LocalTime.of(23, 59, 59);
		System.out.println("Parsed LocalTime:\t" + late);

		DateTimeFormatter df = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).withLocale(Locale.JAPAN);
		LocalTime jpTime = LocalTime.parse("13:37", df);
		System.out.println("Japanese LocalTime:\t" + jpTime);
	}
	
	public void localDate() {
		LocalDate today = LocalDate.now();
		LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
		LocalDate yesterday = tomorrow.minusDays(2);

		System.out.println("Today:\t\t\t" + today);
		System.out.println("Tomorrow:\t\t" + tomorrow);
		System.out.println("Yesterday:\t\t" + yesterday);
		
		LocalDate day = LocalDate.of(2016, Month.MARCH, 15);
		System.out.println("DayOfWeek:\t\t" + day.getDayOfWeek());
	}
	
	public void localDateTime() {
		LocalDateTime sylvester = LocalDateTime.of(2016, Month.DECEMBER, 31, 23, 59, 59);

		System.out.println("DayOfWeek:\t\t" + sylvester.getDayOfWeek());
		System.out.println("Month:\t\t\t" + sylvester.getMonth());
		System.out.println("MinuteOfDay:\t\t" + sylvester.getLong(ChronoField.MINUTE_OF_DAY));
	}
	
	public void formatLocalDateTime() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy. MMM dd - HH:mm");

		LocalDateTime parsed = LocalDateTime.parse("2016. Mar 03 - 07:13", formatter);
		System.out.println("Formatted time:\t\t" + formatter.format(parsed));
	}
	
	public void period() {
		LocalDate sylvester = LocalDate.of(2016, Month.DECEMBER, 31);
		LocalDate anotherDate = LocalDate.of(2018, Month.JULY, 18);
		
		Period period = Period.between(sylvester, anotherDate);
		System.out.println(String.format("Period between:\t%d %d %d", period.getYears(), period.getMonths(), period.getDays()));
	}
	
	public void duration() {
		LocalDateTime comingMidnight = LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.MIDNIGHT);
		LocalDateTime now = LocalDateTime.now();
		
		Duration duration = Duration.between(now, comingMidnight);

		System.out.println(String.format("Duration between:\t%d %d", duration.toHours(), duration.toMinutes()));
	}
	
	public static void main(String[] args) {
		DateF d = new DateF();
		
		System.out.println("-- Current Timestamp --");
		d.currentTimestamp();
		System.out.println();
		
		System.out.println("-- TimeZones --");
		d.timeZones();
		System.out.println();
		
		System.out.println("-- LocalTime --");
		d.localTime();
		System.out.println();
		
		System.out.println("-- Parse to LocalTime --");
		d.parseToLocalTime();
		System.out.println();
		
		System.out.println("-- LocalDate --");
		d.localDate();
		System.out.println();
		
		System.out.println("-- LocalDateTime --");
		d.localDateTime();
		System.out.println();
		
		System.out.println("-- Format LocalDateTime --");
		d.formatLocalDateTime();
		System.out.println();
		
		System.out.println("-- Period --");
		d.period();
		System.out.println();
		
		System.out.println("-- Duration --");
		d.duration();
		System.out.println();
	}
	
}
