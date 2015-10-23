package ca.gc.collectionscanada;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

import junit.framework.TestCase;

/**
 * StringUtilityTest junit test,
 * Append more tests as needed
 * 
 * @author khatrz
 *
 */
public class StringUtilityTests extends TestCase{
	
/*	protected void setUp() {
		
	}
	
	public void test_String_02() {
		String testString = "http://www.1800oCanada.gc.ca";
		//String output = testString.substring(11);
		String sevenString = testString.substring(7);
		System.out.println("sevenString http://www = " + sevenString);
			
		//assertTrue(Character.isDigit(output.charAt(0)));
		assertTrue(testString.startsWith("http"));
		assertTrue(sevenString.startsWith("www"));
		assertTrue(Character.isDigit("www.1800ocanada.gc.ca".substring(4).charAt(0)));
	}
	
	public void test_String_URL() {
		String url = "http://www.example.cra.gc.ca/";
		
		try {
			URL aURL = new URL(url);
			
		    System.out.println("protocol = " + aURL.getProtocol()); //http
		    System.out.println("authority = " + aURL.getAuthority()); //example.com:80
		    System.out.println("host = " + aURL.getHost()); //example.com
		    System.out.println("port = " + aURL.getPort()); //80
		    System.out.println("path = " + aURL.getPath()); //  /docs/books/tutorial/index.html
		    System.out.println("query = " + aURL.getQuery()); //name=networking
		    System.out.println("filename = " + aURL.getFile()); ///docs/books/tutorial/index.html?name=networking
		    System.out.println("ref = " + aURL.getRef()); //DOWNLOADING
		    
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void test_URL() throws MalformedURLException {
		String url = "http://v41webarci01-q:8080/wayback/";
		
		URL aURL = new URL(url);

	    System.out.println("protocol = " + aURL.getProtocol()); //http
		 System.out.println("host = " + aURL.getHost()); //example.com
		    System.out.println("port = " + aURL.getPort()); //80
		    System.out.println("path = " + aURL.getPath()); //  /docs/books/tutorial/index.html

		    String gcWebURL = aURL.getProtocol() + "://" + aURL.getHost() + ":" + aURL.getPort() + "/GCWebArchive";
		    
		    System.out.println("GC Web Archive = " + gcWebURL);
		    
		    
	}*/
	
	public void test_URL_2()  {
		String url = "http://nrtee-trnee.ca/?lang=en?locale=en_CA";
		URL aURL = null;
		try {
			aURL = new URL(url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    System.out.println("protocol = " + aURL.getProtocol()); //http
		 System.out.println("host = " + aURL.getHost()); //example.com
		    System.out.println("port = " + aURL.getPort()); //80
		    System.out.println("path = " + aURL.getPath()); //  /docs/books/tutorial/index.html

		    String gcWebURL = aURL.getProtocol() + "://" + aURL.getHost() + ":" + aURL.getPort() + "/GCWebArchive";
		    
		    System.out.println("GC Web Archive = " + gcWebURL);
		    
		    System.out.println("Get Query = " + aURL.getQuery());
		    
		    if(aURL.getQuery().contains("locale=fr_CA") || aURL.getQuery().contains("locale=en_CA")){
		    	System.out.println("Test query is True...");
		    }
		    
		    String query = aURL.getQuery();
		    
		    String attrib = query.substring(query.lastIndexOf("locale"));
		    System.out.println("Attribute = " + attrib);
		    
		    String local = attrib.substring(attrib.indexOf("=") + 1);
		    
		    System.out.println("Locale = " + local);
		    
		    String [] array = local.split("_");
		    
		    //Locale locale = new Locale(array[0], array[1]);
		    //System.out.println("First element = " + array[0]);
		   // System.out.println("Second element = " + array[1]);
		    
		    /*String splitLang = local.substring(0, 2);
		    
		    System.out.println("Split language = " + splitLang);
		    
		    String splitCountry = local.substring(3, local.lastIndexOf("_CA"));
		    
		    System.out.println("Split Country = " + splitCountry);*/

		    
		 /*   Locale locale = new Locale(local); */
		    
		    //System.out.println("Language from locale =" + locale.getLanguage());
		    //System.out.println("Country from locale =" + locale.getCountry());
		    
		    String [] arrays = url.split("\\?");
		    
		    System.out.println("Last element = " + arrays[arrays.length - 1]);
		    
		    String lastElem = arrays[arrays.length - 1];
		    
		    String [] array2 = lastElem.split("=");
		    System.out.println("Last element = " + array2[array2.length - 1]);
		    
		    String [] localeArray = array2[array2.length - 1].split("_");
		    
		    Locale locale3 = new Locale(localeArray[0], localeArray[1]);
		    
		    System.out.println("Language from locale3 =" + locale3.getLanguage());
		    System.out.println("Country from locale3 =" + locale3.getCountry());
		    
	}
	
}
