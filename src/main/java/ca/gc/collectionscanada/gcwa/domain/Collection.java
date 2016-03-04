package ca.gc.collectionscanada.gcwa.domain;

import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.springframework.context.i18n.LocaleContextHolder;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * (W)eb (A)rchive (Collection), to avoid confusion with Java Collection
 *
 */
@Entity
public class Collection {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String titleEn;
	private String titleFr;
	
    @Column(length = 1024)
    private String descriptionEn;
    @Column(length = 1024)
    private String descriptionFr;
    
	@ManyToOne()
	@JoinColumn(name = "subcategory_id")
	private Subcategory subcategory;
	
	@ManyToMany(mappedBy = "collection")
	@JsonIgnore
	private java.util.Collection<Seed> seed;
	
    private Boolean enabled;
    
	protected Collection() {};
	
	@Override
	public String toString() {
		return String.format(
				"Collection[id=%d, title='%s']",
				id, getTitle());
	}

	public String getTitle() {
        Locale locale = LocaleContextHolder.getLocale();
        if (locale.getISO3Language().equals("fra")) {
            return getTitleFr();
        } else {
            return getTitleEn();
        }
    }
    
    public String getTitleEn() {
        return titleEn;
    }

    public String getTitleFr() {
        return titleFr;
    }

    public void setTitleFr(String titleFr) {
        this.titleFr = titleFr;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    public String getDescriptionFr() {
        return descriptionFr;
    }

    public void setDescriptionFr(String descriptionFr) {
        this.descriptionFr = descriptionFr;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public String getDescription() {
        Locale locale = LocaleContextHolder.getLocale();
        if (locale.getISO3Language().equals("fra")) {
            return getDescriptionFr();
        } else {
            return getDescriptionEn();
        }

    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
        this.id = id;
    }

    public Subcategory getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(Subcategory subcategory) {
		this.subcategory = subcategory;
	}

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

}
