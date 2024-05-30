package in.sachinshinde.z_basicsjava;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class TimeZoneApiCalls {
    
    private static final String DATE_FORMAT = "dd-M-yyyy hh:mm:ss a";
    
    private static final DateTimeFormatter GMT_OFFSET_PARSER
    = DateTimeFormatter.ofPattern("OOOO", Locale.ENGLISH);

    public static void main(String[] args) {
	long offsetLondon = TimeZone.getTimeZone("Europe/London").getOffset(Instant.now().toEpochMilli());
	System.out.println("London Offset : "+offsetLondon);
	
	long offsetNewyork = TimeZone.getTimeZone("America/New_York").getOffset(Instant.now().toEpochMilli()) / 1000 / 60;
	System.out.println("New_York Offset : "+offsetNewyork);
	
	long offsetMontreal = TimeZone.getTimeZone("America/Montreal").getOffset(new Date().getTime()) / 1000 / 60;
	System.out.println("Montreal Offset : "+offsetMontreal);
	
	long offsetCalcutta = TimeZone.getTimeZone("Asia/Calcutta").getOffset(new Date().getTime());
	
	Calendar cal = new GregorianCalendar();
	int currOffset = (cal.get(Calendar.ZONE_OFFSET) + cal.get(Calendar.DST_OFFSET))/(1000*60);
	offsetCalcutta = Calendar.ZONE_OFFSET;
	System.out.println("Current Offset : "+currOffset);
	
//	
//	String dateInString = new Date().toString();
//        LocalDateTime ldt = LocalDateTime.parse(dateInString);
//        ZoneId newYokZoneId = ZoneId.of("America/New_York");
//        ZonedDateTime nyDateTime = ldt.atZone(newYokZoneId);
//        System.out.println("Date (New York) : " + nyDateTime);
//        DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_FORMAT);
//        System.out.println("Date (New York) : " + format.format(nyDateTime));
        
//        String input = "GMT-05:00";
//        ZoneOffset offset2 = ZoneOffset.from(GMT_OFFSET_PARSER.parse(input));
//        int offsetSeconds = offset2.getTotalSeconds();
//        double hours = (double) offsetSeconds / (double) Duration.ofHours(1).toSeconds();
//        long minutes = Duration.ofSeconds(offsetSeconds).toMinutes();
        System.out.println("GMT Offset Minutes: " + 
        	Duration.ofSeconds(
        		ZoneOffset.from(GMT_OFFSET_PARSER.parse("GMT-05:00"))
        		.getTotalSeconds()
        	).toMinutes());
    }
}
