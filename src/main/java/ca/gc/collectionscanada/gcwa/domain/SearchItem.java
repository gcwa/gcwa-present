package ca.gc.collectionscanada.gcwa.domain;

public class SearchItem {


    private String title;
    private String description;
    private String link;
    private String wrappedLink;
    private String site;
    private String arcDate;
    private String cleanDate;
    private String cleanTime;
    private String moreFromSiteLink;

    public SearchItem() {
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public String getLink() {
        return this.link;
    }

    public String getSite() {
        return this.site;
    }

    public String getArcDate() {
        return this.arcDate;
    }

    public String getCleanDate() {
        return this.cleanDate;
    }

    public String getCleanTime() {
        return this.cleanTime;
    }

    public String getMoreFromSiteLink() {
        return this.moreFromSiteLink;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public void setArcDate(String arcDate) {
        this.arcDate = arcDate;
    }

    public void setCleanDate(String cleanDate) {
        this.cleanDate = cleanDate;
    }

    public void setCleanTime(String cleanTime) {
        this.cleanTime = cleanTime;
    }

    public void setMoreFromSiteLink(String moreFromSiteLink) {
        this.moreFromSiteLink = moreFromSiteLink;
    }

    public String getWrappedLink() {
        return wrappedLink;
    }

    public void setWrappedLink(String wrappedLink) {
        this.wrappedLink = wrappedLink;
    }
}
