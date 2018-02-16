
package ca.gc.collectionscanada.gcwa.common.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;


/**
 * Page utility class to convert encoding, create previous and next link for navigation of search results
 * Escape html, convert text to html, trim start of url, wrap description, wrap text
 * This class is used mainly in the Search and Advance Search portion of the GC Web Archive
 * portal
 * @author khatrz
 *
 */
public class PageUtilities
{
    public static final int DEFAULT_WRAP_SIZE = 72;
    protected static final Log logger = LogFactory.getLog(PageUtilities.class);

    public PageUtilities()
    {
    }

    public static String convertCP1252ToUnicode(String s)
    {
        s = StringUtils.replace(s, "&#130;", "&#8218;");
        s = StringUtils.replace(s, "&#131;", "&#402;");
        s = StringUtils.replace(s, "&#132;", "&#8222;");
        s = StringUtils.replace(s, "&#133;", "&#8230;");
        s = StringUtils.replace(s, "&#134;", "&#8224;");
        s = StringUtils.replace(s, "&#135;", "&#8225;");
        s = StringUtils.replace(s, "&#136;", "&#710;");
        s = StringUtils.replace(s, "&#137;", "&#8240;");
        s = StringUtils.replace(s, "&#138;", "&#352;");
        s = StringUtils.replace(s, "&#139;", "&#8249;");
        s = StringUtils.replace(s, "&#140;", "&#338;");
        s = StringUtils.replace(s, "&#145;", "&#8216;");
        s = StringUtils.replace(s, "&#146;", "&#8217;");
        s = StringUtils.replace(s, "&#147;", "&#8220;");
        s = StringUtils.replace(s, "&#148;", "&#8221;");
        s = StringUtils.replace(s, "&#149;", "&#8226;");
        s = StringUtils.replace(s, "&#150;", "&#8211;");
        s = StringUtils.replace(s, "&#151;", "&#8212;");
        s = StringUtils.replace(s, "&#152;", "&#732;");
        s = StringUtils.replace(s, "&#153;", "&#8482;");
        s = StringUtils.replace(s, "&#154;", "&#353;");
        s = StringUtils.replace(s, "&#155;", "&#8250;");
        s = StringUtils.replace(s, "&#156;", "&#339;");
        s = StringUtils.replace(s, "&#159;", "&#376;");
        return s;
    }


    public static String appendExclusionsToQuery(String query, @Value("${exclusions.queryExclusionsFile}") String exclusionsFileString) {
    	DataInputStream in = null;
    	BufferedReader br = null;
        String newQuery = "";
        newQuery += query;
        try {
            if (exclusionsFileString != null) {
                File exclusionsFile = new File(exclusionsFileString);
                FileInputStream fstream = new FileInputStream(exclusionsFile);
                in = new DataInputStream(fstream);
                br = new BufferedReader(
                        new InputStreamReader(in));
                String strLine;
                // Read File Line By Line to match URLs that are excluded
                while ((strLine = br.readLine()) != null) {
                    newQuery += "+-" + strLine.trim();
                }
            }
        } catch (IOException e) {
            return newQuery;
        } finally {
            try {
            	if(br!=null){
            		br.close();
            	}
            	if(in != null) {
            		in.close();
            	}   
            } catch (Exception e) {
                return newQuery;
            }
        }
        return newQuery;
    }

    public static String buildNextLink(String contextPath, String query, int start, int totalResults, int resultsPerPage, boolean showAll)
    {
        String showAllStr;
        if(showAll)
            showAllStr = "t";
        else
            showAllStr = "f";
        int newStart;
        if(start + resultsPerPage > totalResults)
            newStart = start;
        else
            newStart = start + resultsPerPage;
        String link = (new StringBuilder()).append(contextPath).append("?q=").append(query).append("&amp;s=").append(newStart).append("&amp;sa=").append(showAllStr).toString();
        logger.info((new StringBuilder()).append("Next Link: ").append(link).toString());
        return link;
    }

    public static String buildPreviousLink(String contextPath, String query, int start, int totalResults, int resultsPerPage, boolean showAll)
    {
        String showAllStr;
        if(showAll)
            showAllStr = "t";
        else
            showAllStr = "f";
        int newStart;
        if(start > 0)
            newStart = start - resultsPerPage;
        else
            newStart = 0;
        String link = (new StringBuilder()).append(contextPath).append("?q=").append(query).append("&amp;s=").append(newStart).append("&amp;sa=").append(showAllStr).toString();
        return link;
    }
    
    /**
     * Convert special characters to html entities.
     * 
     * @param s
     *            String to convert
     * @param preformat
     *            If true also convert newlines to &lt;br&gt;
     * @return Converted string
     */
    public static String text2html(String s, boolean preformat) {
        StringBuffer buf = new StringBuffer();

        for (int i = 0, limit = s.length(); i < limit; i++) {
            char c = s.charAt(i);
            switch (c) {
            case ' ':
                if (preformat)
                    buf.append("&nbsp;");
                else
                    buf.append(' ');
                break;
            case '<':
                buf.append("&lt;");
                break;
            case '>':
                buf.append("&gt;");
                break;
            case '&':
                buf.append("&amp;");
                break;
            case '\'':
                buf.append("&#39;");
                break;
            case '"':
                buf.append("&quot;");
                break;
            case '\n':
                if (preformat)
                    buf.append("<br>");
                break;
            default:
                buf.append(c);
            }
        }
        return buf.toString();
    }


    public static String wrapText(String aString) {
        return wrapText(aString, DEFAULT_WRAP_SIZE);
    }
    
    public static String wrapText(String aString, int length) {
        String wrappedString = WordUtils.wrap(aString, length, "<br />", true);
        wrappedString = escapeHtml(wrappedString);
        wrappedString = StringUtils.replace(wrappedString, "&lt;br /&gt;",
                "<br />");
        return wrappedString;
    }

    public static String escapeDescription(String aDesc) {
        String escapedDescription = null;
        if (aDesc != null) {
            escapedDescription = escapeHtml(aDesc);
            escapedDescription = StringUtils.replace(escapedDescription,
                    "&lt;br /&gt;", "<br />");
            escapedDescription = StringUtils.replace(escapedDescription,
                    "&lt;span class=&quot;highlight&quot;&gt;",
                    "<span class=\"highlight\">");
            escapedDescription = StringUtils.replace(escapedDescription,
                    "&lt;span class=&quot;ellipsis&quot;&gt;",
                    "<span class=\"ellipsis\">");
            escapedDescription = StringUtils.replace(escapedDescription,
                    "&lt;/span&gt;", "</span>");
        }
        return escapedDescription;
    }
    
    public static String wrapDescription(String aDesc, int length) {
        String wrappedDescription = null;
        if (aDesc != null) {
            wrappedDescription = "";
            String[] descPieces = aDesc.split(" ");

            for (String piece : descPieces) {
                if (piece.length() > length && piece.indexOf("<span") == -1 && piece.indexOf("</span>") == -1) {
                    piece = "<br />"
                            + WordUtils.wrap(piece, length, "<br />", true);
                }
                wrappedDescription += ' ' + piece;
            }
        }
        return wrappedDescription;
    }
    
    public static String escapeHtml(String s) {
        String escapedString = StringEscapeUtils.escapeHtml4(s);
        escapedString = convertCP1252ToUnicode(escapedString);
        return escapedString;
    }
    
    
    /**
     * Utility class to strip leading http:// and www from the 
     * start of URL string.
     * 
     * @param url The URL string
     * @return URL string with http:// and www removed from the start 
     */
    public static String trimStartOfUrl(String url) {
        Pattern urlRegex = Pattern.compile("(https?://)?(www\\d*?\\.)?([^?]+)(.*)");
        Matcher matcher = urlRegex.matcher(url);
        if (matcher != null && matcher.matches()) {
            String trimmedUrl = matcher.group(3);
            return trimmedUrl;
        }
        return url;
    }

}


