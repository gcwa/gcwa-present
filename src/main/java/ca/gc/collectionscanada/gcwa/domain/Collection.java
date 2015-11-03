package ca.gc.collectionscanada.gcwa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Collection {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String title;
	
	@Column(length=1024)
	private String description;
	
	@ManyToOne()
	@JoinColumn(name = "subcategory_id")
	private Subcategory subcategory;
	
	protected Collection() {};
	
	public Collection(String title) {
		this.title = title;
	}
	
	@Override
	public String toString() {
		return String.format(
				"Collection[id=%d, title='%s']",
				id, title);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public Subcategory getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(Subcategory subcategory) {
		this.subcategory = subcategory;
	}

}
