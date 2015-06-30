package org.teel.arende;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "zeel_dok")
public class Dok {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "table", strategy = "enhanced-table", parameters = {
            @org.hibernate.annotations.Parameter(name = "table_name", value = "sequence_table")})
    @GeneratedValue(generator = "table", strategy = GenerationType.TABLE)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "arende_id", nullable = false)
    @XmlTransient
    private Arende arende;

    @OneToMany(targetEntity = Hand.class, cascade = CascadeType.ALL, mappedBy = "dok")
    @LazyCollection(LazyCollectionOption.FALSE)
    //@XmlTransient
    private List<Hand> hand = new ArrayList<>();


    public Dok() {
    }

//    public void afterUnmarshal(Unmarshaller u, Object parent) {
//        if (parent instanceof Arende)
//            this.arende = (Arende) parent;
//        if (parent instanceof Hand)
//            this.hand.add((Hand) parent);
//    }

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

    public Arende getArende() {
        return arende;
    }

    public void setArende(Arende arende) {
        this.arende = arende;
    }

    public List<Hand> getHand() {
        return hand;
    }

    public void setHand(List<Hand> hand) {
        this.hand = hand;
    }

}
