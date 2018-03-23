package ca.gc.collectionscanada.gcwa.web;

import ca.gc.collectionscanada.gcwa.domain.SearchItem;
import ca.gc.collectionscanada.gcwa.domain.SearchMetadata;
import ca.gc.collectionscanada.gcwa.service.SearchService;
import com.rometools.rome.feed.rss.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Locale;

/**
 * Full text search into the archives
 */
@Controller
@RequestMapping("/search")
public class SearchController {

    private final Logger log = LoggerFactory.getLogger(SearchController.class);

    @Autowired
    SearchService searchService;

    @RequestMapping("")
    public String index(@RequestParam(value = "q", required = false) String query,
    		@RequestParam(value = "t", required = false) String contentType,
            @RequestParam(value = "p", required = false) Integer startPosition,
            @RequestParam(value = "s", required = false) String host,
    		Model model, Locale locale) {

        if (query != null) {
            log.info("/search searching for: " + query);

            if (startPosition == null) { startPosition = 0; }
            if (host == null) { host = ""; }
            Channel searchResults = searchService.SearchQuery(query, contentType, startPosition, host);

            SearchMetadata metadata = searchService.hydrateMetadata(searchResults);
            List<SearchItem> items = searchService.hydrateResults(searchResults);
            int currentPage = (int)Math.ceil((metadata.getStartIndex()) / (float)metadata.getItemsPerPage()) + 1;

            // because the api cannot display all the results
            Long maxResults;
            if (metadata.getTotalResults() > SearchService.MAX_TOTAL_RESULTS) {
                maxResults = SearchService.MAX_TOTAL_RESULTS;
            } else {
                maxResults = metadata.getTotalResults();
            }

            model.addAttribute("metadata", metadata);
            model.addAttribute("items", items);
            model.addAttribute("contentType", contentType);
            model.addAttribute("totalPages", maxResults / metadata.getItemsPerPage());
            model.addAttribute("currentPage", currentPage);
        }

        return "search/search";
    }
    
}
