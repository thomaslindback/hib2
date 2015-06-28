package org.teel.hib1;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "teel_handelse")
public class Handelse {
	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	@JoinColumn(name = "book_id", nullable = true)
	private Book book;
	@ManyToOne
	@JoinColumn(name = "author_id", nullable = true)
	private Autor author;

	String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Autor getAuthor() {
		return author;
	}

	public void setAuthor(Autor author) {
		this.author = author;
	}

}
