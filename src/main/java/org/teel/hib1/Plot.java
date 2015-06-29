package org.teel.hib1;

import javax.persistence.*;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "teel_plot")
public class Plot {

    @Id
    @GeneratedValue
    private Long id;
    private String plot;

    @OneToOne(optional = false)
    @XmlTransient
    private Book book;

    public Plot() {
    }
    public Plot(String plot/*, Book book*/) {
        this.plot = plot;
        //this.book = book;
    }

    public void afterUnmarshal(Unmarshaller u, Object parent) {
        this.book = (Book) parent;
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
