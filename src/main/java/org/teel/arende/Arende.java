package org.teel.arende;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "zeel_arende")
public class Arende {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "table", strategy = "enhanced-table", parameters = {
            @org.hibernate.annotations.Parameter(name = "table_name", value = "sequence_table")})
    @GeneratedValue(generator = "table", strategy = GenerationType.TABLE)
    private Long id;
    private String title;
    @OneToMany(targetEntity = Dok.class, cascade = CascadeType.ALL, mappedBy = "arende")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Dok> dok = new ArrayList<>();

    @OneToMany(targetEntity = Hand.class, cascade = CascadeType.ALL, mappedBy = "arende")
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

    public List<Hand> getHand() {
        return hand;
    }

    public void setHand(List<Hand> hand) {
        this.hand = hand;
    }
}
