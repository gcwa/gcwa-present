package ca.gc.collectionscanada.gcwa.domain;

import java.util.List;
import java.util.Map;
import java.net.ConnectException;

/**
 * A Public interface which is used by NutchWaxParser
 * @author khatrz
 *
 */
public interface OpenSearchParser {

	public List<SearchItem> getItems();
	public Map<String, Integer> getSearchStats();
	public boolean hasMorePages();
	public void parseXmlFile(String fileOrUri) throws ConnectException;
	
}