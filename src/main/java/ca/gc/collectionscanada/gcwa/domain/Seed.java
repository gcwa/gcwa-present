package ca.gc.collectionscanada.gcwa.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Seed {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String url;
    private Boolean access;
    @ManyToOne()
    @JoinColumn(name = "collection_id")
    private Collection collection;

    protected Seed() {
    };

    public Seed(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return String.format("Seed[url='%s', access='%b'", url, access);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getHumanReadableUrl() {
        String tmpUrl = url.replaceFirst("https?://", "");
        tmpUrl = tmpUrl.replaceAll("/$", "");
        //TODO add elipse (...) on long url
        return tmpUrl;
    }

    public Boolean getAccess() {
        return access;
    }

    public void setAccess(Boolean access) {
        this.access = access;
    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }
    
    public long getId() {
        return id;
    }

}
