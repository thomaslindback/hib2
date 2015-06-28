package org.teel.hib1;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "teel_author")
public class Autor {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	@ManyToOne
	@JoinColumn(name="book_id", nullable=false)
	private Book book;
	
	@OneToMany(targetEntity=Handelse.class, cascade=CascadeType.ALL, mappedBy="author")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Handelse> handelser;

	
	public Autor() {
	}

	public Autor(String name, Book book) {
		this.name = name;
		this.book = book;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public List<Handelse> getHandelser() {
		if(handelser == null) {
			handelser = new ArrayList<>();
		}
		return handelser;
	}

	public void setHandelser(List<Handelse> handelser) {
		this.handelser = handelser;
	}

}
