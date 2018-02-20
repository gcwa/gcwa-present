package ca.gc.collectionscanada.gcwa.service;

import ca.gc.collectionscanada.gcwa.domain.SearchItem;
import ca.gc.collectionscanada.gcwa.domain.SearchMetadata;
import com.rometools.rome.feed.rss.Channel;
import com.rometools.rome.feed.rss.Item;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
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

    public SearchMetadata hydrateMetadata(Channel results) {
	    return new SearchMetadata(results);
    }

    public List<SearchItem> hydrateResults(Channel results) {
        List<SearchItem> items = new ArrayList<>();
        for (Item rssItem : results.getItems()) {
            items.add(new SearchItem(rssItem));
        }

        return items;
    }

}
