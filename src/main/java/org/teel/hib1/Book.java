package org.teel.hib1;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "teel_book")
public class Book {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    @OneToMany(targetEntity = Autor.class, cascade = CascadeType.ALL, mappedBy = "book")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Autor> autors;

    @OneToMany(targetEntity = Handelse.class, cascade = CascadeType.ALL, mappedBy = "book")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Handelse> handelser;

    @OneToOne(optional = true, mappedBy = "book")
    @JoinColumn(name = "plot_id", unique = true, nullable = true, updatable = false)
    private Plot plot;

    public Book() {
    }

    public Book(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Autor> getAutors() {
        if (autors == null) {
            autors = new ArrayList<>();
        }
        return autors;
    }

    public void setAutors(List<Autor> autors) {
        this.autors = autors;
    }

    public Plot getPlot() {
        return plot;
    }

    public void setPlot(Plot plot) {
        this.plot = plot;
    }

    public List<Handelse> getHandelser() {
        if (handelser == null) {
            handelser = new ArrayList<>();
        }
        return handelser;
    }

    public void setHandelser(List<Handelse> handelser) {
        this.handelser = handelser;
    }
}
