package ca.gc.collectionscanada.gcwa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Subcategory {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String title;
	@Column(length=1024)
	private String description;
	
	@ManyToOne()
	@JoinColumn(name = "category_id")
	private Category category;
	
	protected Subcategory() {};
	
	public Subcategory(String title) {
		this.title = title;
	}
	
	@Override
	public String toString() {
		return String.format(
				"Subcategory[id=%d, title='%s']",
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
}
