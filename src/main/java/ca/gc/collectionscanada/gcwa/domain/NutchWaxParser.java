package ca.gc.collectionscanada.gcwa.domain;

import java.io.IOException;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 * XML parsing class used to parse an XML file of Nutchwax open search results.
 * It convert the XML results to a collection of SearchItems that can easily be
 * passed to other parts of the application and displayed in the UI layer.
 * @author khatrz
 *
 */
public class NutchWaxParser implements OpenSearchParser {

    private List<SearchItem> items;
    private Map<String, Integer> searchStats;
    private Document dom;
    private boolean morePages = false;

    public NutchWaxParser() {
        items = new ArrayList<SearchItem>();
        searchStats = new HashMap<String, Integer>();
        searchStats.put("totalResults", new Integer(-1));
        searchStats.put("startIndex", new Integer(0));
    }

    /**
     * Get a list of SearchItems from this opensearch result
     * 
     * @return List of SearchItem objects representing search results
     */
    public List<SearchItem> getItems() {
        return this.items;
    }

    /**
     * Get a Map of general search stats for these results
     * 
     * @return Map of search stats (e.g. total results, results per page, etc.)
     */
    public Map<String, Integer> getSearchStats() {
        searchStats.put("numberOfItems", items.size());
        return this.searchStats;
    }

    public boolean hasMorePages() {
        return this.morePages;
    }

    /**
     * Parse opensearch XML file or URL pointing to opensearch XML file
     * 
     * @param fileOrUri
     *            File or URI to parse
     */
    public void parseXmlFile(String fileOrUri) throws ConnectException {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder db = dbf.newDocumentBuilder();

            // parse using builder to get DOM representation of the XML file
            dom = db.parse(fileOrUri);
        } catch (ConnectException e) {
            throw new ConnectException();
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (SAXException se) {
            se.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        parseDocument();
    }

    public void parseDocument() {
        // get the root elememt
        Element docEle = dom.getDocumentElement();

        // Get a node list of search items
        NodeList itemNL = docEle.getElementsByTagName("item");

        // Parse the search items out of the XML
        if (itemNL != null && itemNL.getLength() > 0) {
            for (int i = 0; i < itemNL.getLength(); i++) {

                // get the search item element
                Element el = (Element) itemNL.item(i);

                // get the search item object
                SearchItem item = getItem(el);

                // add it to the list of search items
                items.add(item);
            }
        }

        // Parse the general search stats and place in searchStats Map
        searchStats.put("totalResults", new Integer(getIntValue(docEle,
                "opensearch:totalResults")));
        searchStats.put("startIndex", new Integer(getIntValue(docEle,
                "opensearch:startIndex")));
        searchStats.put("itemsPerPage", new Integer(getIntValue(docEle,
                "opensearch:itemsPerPage")));

        if (getTextValue(docEle, "nutch:nextPage") != null)
            this.morePages = true;
        else
            this.morePages = false;
    }

    /**
     * Convert an item element in opensearch XML format to SearchItem object and
     * return it
     * 
     * @param anItem
     *            Item in XML file to convert
     * @return SearchItem
     */
    public SearchItem getItem(Element anItem) {

        String title = getTextValue(anItem, "title");
        String description = getTextValue(anItem, "description");
        String link = getTextValue(anItem, "link");
        String site = getTextValue(anItem, "nutch:site");
        String arcDate = getTextValue(anItem, "nutch:arcdate");
        if (arcDate == null)
            arcDate = getTextValue(anItem, "nutch:tstamp").substring(0, 14);
        if (arcDate.length() > 14)
            arcDate = arcDate.substring(0, 14);

        String cleanDate = arcDate.substring(0, 4) + "-"
                + arcDate.substring(4, 6) + "-" + arcDate.substring(6, 8);
        String cleanTime = arcDate.substring(8, 10) + ":"
                + arcDate.substring(10, 12) + ":" + arcDate.substring(6, 8);

        // Create a new item
        SearchItem item = new SearchItem();
        item.setTitle(title);
        if (description.length() <= 0)
            item.setDescription(null);
        else
            item.setDescription(description);
        item.setLink(link);
        item.setSite(site);
        item.setArcDate(arcDate);
        item.setCleanDate(cleanDate);
        item.setCleanTime(cleanTime);

        return item;
    }

    /**
     * Take an xml element and the tag name, look for the tag and get the text
     * content
     * 
     * @param ele
     * @param tagName
     * @return text content of tag
     */
    private String getTextValue(Element ele, String tagName) {
        String textVal = null;
        NodeList nl = ele.getElementsByTagName(tagName);
        if (nl != null && nl.getLength() > 0) {
            Element el = (Element) nl.item(0);
            if (el != null && el.getFirstChild() != null)
                textVal = el.getFirstChild().getNodeValue();
            else
                textVal = "";
        } else
            return null;

        /*
         * if (tagName.equals("description")) { System.out.println(textVal);
         * System.out.println(""); }
         */
        return textVal;
    }

    /**
     * Calls getTextValue and returns a int value
     * 
     * @param ele
     * @param tagName
     * @return integer value of String returned by getTextValue
     */
    private int getIntValue(Element ele, String tagName) {
        // in production application you would catch the exception
        return Integer.parseInt(getTextValue(ele, tagName));
    }

    public static void main(String args[]) throws ConnectException {
        String url = "http://dclnx06.lac-bac.gc.ca:8080/opensearch?query=canada+site%3Awww.faa-lfi.gc.ca&hitsPerDup=0";
        NutchWaxParser thisParser = new NutchWaxParser();

        thisParser.parseXmlFile(url);
    }

}