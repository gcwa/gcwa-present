package ca.gc.collectionscanada.misc;

import java.util.Calendar;
import java.util.Locale;

public class CalendarLocaleSet {
	public static void main(String k[]) {
		Calendar cal = Calendar.getInstance(new Locale("fr","CA"));
		
/*		String str = "Jan,Feb,Mar,Apr,May,Jun,July,Aug,Sep,Oct,Nov,Dec";
		
		String split [] = str.split(",");
		
		for (int i = 0; i < 12; i ++) {
			System.out.println("Month =" + split[i]);
		}*/
		
		String str = "locale=fr_CA??";
		
		if ((str.substring(str.length() -1).equals("?"))) {
			String proc = str.replace("?", "");
			System.out.println("String = " + proc);
		}
		
		
		
		
		
	}
}
