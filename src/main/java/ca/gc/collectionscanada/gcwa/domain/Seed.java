package ca.gc.collectionscanada.gcwa.domain;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Seed {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    private String url;
    
    @ManyToMany()
    @JoinTable(name = "collection_seed",
                    joinColumns = @JoinColumn(name = "seed_id"),
                    inverseJoinColumns = @JoinColumn(name = "collection_id")
    )
    @JoinColumn(name = "collection_id")
    @JsonIgnore
    private java.util.Collection<Collection> collection = new ArrayList<Collection>();

    
    protected Seed() {
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
        String tmpUrl = url.replaceFirst("https?://", "");
        tmpUrl = tmpUrl.replaceAll("/$", "");
        //TODO add elipse (...) on long url
        return tmpUrl;
    }

    public java.util.Collection<Collection> getCollection() {
        return collection;
    }

    public void setCollection(java.util.Collection<Collection> collection) {
        this.collection = collection;
    }
    
    public long getId() {
        return id;
    }

}
