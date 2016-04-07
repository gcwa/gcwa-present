package ca.gc.collectionscanada.gcwa.domain;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Seed {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    private String url;
    
    @ManyToOne()
    @JoinColumn(name = "collection_id")
    private Collection collection;
    
    public Seed() {
        super();
    };

    public Seed(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return String.format("Seed[url='%s'", url);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getHumanReadableUrl() {
        if (url == null) {
            return "";
        }
        String tmpUrl = url.replaceFirst("https?://", "");
        tmpUrl = tmpUrl.replaceAll("/$", "");
        //TODO add elipse (...) on long url
        return tmpUrl;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }
}
