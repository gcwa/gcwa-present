package ca.gc.collectionscanada.gcwa.domain;

import com.rometools.rome.feed.rss.Channel;
import org.apache.commons.lang3.text.WordUtils;
import org.jdom2.Element;

import java.util.ArrayList;
import java.util.List;

public class SearchMetadata {
    private String description;
    private String title;
    private Long startIndex;
    private Long itemsPerPage;
    private String query;
    private Long totalResults;
    private Long responseTime;

    public SearchMetadata() {
    }

    public SearchMetadata(Channel result) {
        // Deal with the foreign elements first
        for (Element element : result.getForeignMarkup()) {
            switch (element.getName()) {
                case "query":
                    setQuery(element.getValue());
                    break;
                case "startIndex":
                    setStartIndex(Long.parseLong(element.getValue()));
                    break;
                case "itemsPerPage":
                    setItemsPerPage(Long.parseLong(element.getValue()));
                    break;
                case "totalResults":
                    setTotalResults(Long.parseLong(element.getValue()));
                    break;
            }
        }
        setDescription(result.getDescription());
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Long startIndex) {
        this.startIndex = startIndex;
    }

    public Long getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(Long itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Long getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Long totalResults) {
        this.totalResults = totalResults;
    }

    public Long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Long responseTime) {
        this.responseTime = responseTime;
    }
}
