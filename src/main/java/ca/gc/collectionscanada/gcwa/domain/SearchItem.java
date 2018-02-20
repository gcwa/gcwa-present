package ca.gc.collectionscanada.gcwa.domain;

import com.rometools.rome.feed.rss.Item;
import org.jdom2.Element;

public class SearchItem {
    public SearchItem() {
    }

    /**
     * Hydrate a single item of a Rome RSS search result (from Spring RestTemplate)
     */
    public SearchItem(Item item) {
        // Deal with the foreign elements first
        for (Element element : item.getForeignMarkup()) {
            switch (element.getName()) {
                case "site":
                    setSite(element.getValue());
                    break;
                case "type":
                    setType(element.getValue());
                    break;
                case "collection":
                    setCollection(Long.parseLong(element.getValue()));
                    break;
            }
        }
        setLink(item.getLink());
        setDescription(item.getDescription().getValue());
        setTitle(item.getTitle());
        setUrl(item.getUri());
    }

    private String link;
    private String description;
    private String title;
    private String url;
    private String site;
    private String type;
    private Long collection;
    private Long index;
    private String captureDate;

    public String getCaptureDate() {
        return captureDate;
    }

    public void setCaptureDate(String captureDate) {
        this.captureDate = captureDate;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getCollection() {
        return collection;
    }

    public void setCollection(Long collection) {
        this.collection = collection;
    }

    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
    }
}
