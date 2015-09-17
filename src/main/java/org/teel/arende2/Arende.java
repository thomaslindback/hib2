package org.teel.arende2;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
//@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "zeel_arende")
public class Arende {

    @Id
    @GenericGenerator(name = "table", strategy = "enhanced-table", parameters = {
            @org.hibernate.annotations.Parameter(name = "table_name", value = "sequence_table")})
    @GeneratedValue(generator = "table", strategy = GenerationType.TABLE)
    private Long id;

    private String title;

    @OneToMany(targetEntity = Dok.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "arende_id", referencedColumnName = "id", nullable = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Dok> dok = new ArrayList<>();

    @OneToMany(targetEntity = Bes.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "arende_id", referencedColumnName = "id", nullable = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Bes> bes = new ArrayList<>();

    @OneToMany(targetEntity = Hand.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "arende_id", referencedColumnName = "id", nullable = false)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Hand> hand = new ArrayList<>();

    public Arende() {
    }

    public Arende(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Dok> getDok() {
        return dok;
    }

    public void setDok(List<Dok> dok) {
        this.dok = dok;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Bes> getBes() {
        return bes;
    }

    public void setBes(List<Bes> bes) {
        this.bes = bes;
    }

    public List<Hand> getHand() {
        return hand;
    }

    public void setHand(List<Hand> hand) {
        this.hand = hand;
    }
}
