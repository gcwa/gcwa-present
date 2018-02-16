package ca.gc.collectionscanada.gcwa.service;

import com.rometools.rome.feed.rss.Channel;
import org.springframework.web.client.RestTemplate;

import java.util.Properties;

/**
 * Service to access Archive It Opensearch API
 */
public class Search {
	public Channel SearchQuery(String q)  {
        String url = "https://archive-it.org/search-master/opensearch?q={q}&i=3935&i=4365&i=4988&i=5238";

        Properties props = System.getProperties();
        props.put("http.proxyHost", "localhost");
        props.put("http.proxyPort", "3128");
        props.put("https.proxyHost", "localhost");
        props.put("https.proxyPort", "3128");

        RestTemplate restTemplate = new RestTemplate();
        // Using rometools
        Channel searchResults = restTemplate.getForObject(url, Channel.class, q);

        return searchResults;
    }

}
