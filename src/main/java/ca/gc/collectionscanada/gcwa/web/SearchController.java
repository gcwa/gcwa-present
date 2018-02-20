package ca.gc.collectionscanada.gcwa.web;

import ca.gc.collectionscanada.gcwa.domain.SearchItem;
import ca.gc.collectionscanada.gcwa.domain.SearchMetadata;
import ca.gc.collectionscanada.gcwa.service.Search;
import com.rometools.rome.feed.rss.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * Full text search into the archives
 */
@Controller
@RequestMapping("/search")
public class SearchController {

    private final Logger log = LoggerFactory.getLogger(SearchController.class);

    @RequestMapping("")
    public String index(@RequestParam(value = "q", required = false) String query,
    		@RequestParam(value = "mimetype", required = false) String contentType,
    		Model model, Locale locale) {

        if (query != null) {
           log.info("/search searching for: " + query);

            Search archiveit = new Search();
            Channel searchResults = archiveit.SearchQuery(query, contentType);

            SearchMetadata metadata = archiveit.hydrateMetadata(searchResults);
            List<SearchItem> items = archiveit.hydrateResults(searchResults);

            model.addAttribute("metadata", metadata);
            model.addAttribute("items", items);
            model.addAttribute("mimetype", contentType);
        }

        return "search/search";
    }
    
}
