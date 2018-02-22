package ca.gc.collectionscanada.gcwa.service;

import ca.gc.collectionscanada.gcwa.domain.SearchItem;
import ca.gc.collectionscanada.gcwa.domain.SearchMetadata;
import com.rometools.rome.feed.rss.Channel;
import com.rometools.rome.feed.rss.Item;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * Service to access Archive It Opensearch API
 */
public class Search {

    public static final int RESULTS_PER_PAGE = 10;

	public Channel SearchQuery(String query, String contentType, Integer startPosition)  {
        String url = "https://archive-it.org/search-master/opensearch?q={q}&n={n}&p={p}&i=3935&i=4365&i=4988&i=5238";
        Map<String, String> uriParameters = new HashMap<>();
        uriParameters.put("q", query);
        uriParameters.put("p", String.valueOf(startPosition));
        uriParameters.put("n", String.valueOf(RESULTS_PER_PAGE));

        uriParameters.put("t", contentType);
        if (contentType != null && !contentType.isEmpty() && !contentType.equals("0")) {
            url = url.concat("&t={t}");
        }

        //FIXME only for temp dev
        Properties props = System.getProperties();
        props.put("http.proxyHost", "localhost");
        props.put("http.proxyPort", "3128");
        props.put("https.proxyHost", "localhost");
        props.put("https.proxyPort", "3128");

        RestTemplate restTemplate = new RestTemplate();
        // Automatically using rometools RSS 2.0 converter
        Channel searchResults = restTemplate.getForObject(url, Channel.class, uriParameters);

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
