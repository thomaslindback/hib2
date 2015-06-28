package org.teel.hib1;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "teel_plot")
public class Plot {

	@Id
	@GeneratedValue
	private Long id;
	private String plot;
	
	@OneToOne(optional=false)
	private Book book;
	
	public Plot(String plot/*, Book book*/) {
		this.plot = plot;
		//this.book = book;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
	
}
