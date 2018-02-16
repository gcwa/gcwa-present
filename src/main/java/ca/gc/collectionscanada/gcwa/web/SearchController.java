package ca.gc.collectionscanada.gcwa.web;

import ca.gc.collectionscanada.gcwa.service.Search;
import com.rometools.rome.feed.rss.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;

/**
 * Full text search into the archives
 */
@Controller
@RequestMapping("/search")
public class SearchController {

    private final Logger log = LoggerFactory.getLogger(SearchController.class);

    @RequestMapping("")
    public String index(@RequestParam(value = "q", required = false) String query,
    		Model model, Locale locale) {

        Search archiveit = new Search();
        Channel searchResults = archiveit.SearchQuery(query);

    	model.addAttribute("results", searchResults);

        return "search/search";
    }
    
}
