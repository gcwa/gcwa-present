package ca.gc.collectionscanada.gcwa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String title;
	@Column(length=1024)
	private String description;
	private String thumbnail;
	
	protected Category() {};
	
	public Category(String title) {
		this.title = title;
	}
	
	@Override
	public String toString() {
		return String.format(
				"Category[id=%d, title='%s']",
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

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public long getId() {
		return id;
	}
	
	
}
