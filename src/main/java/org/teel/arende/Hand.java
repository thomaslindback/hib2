package org.teel.arende;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "zeel_hand")
public class Hand {
    String description;
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "table", strategy = "enhanced-table", parameters = {
            @org.hibernate.annotations.Parameter(name = "table_name", value = "sequence_table")})
    @GeneratedValue(generator = "table", strategy = GenerationType.TABLE)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "arende_id", nullable = true)
    @XmlTransient
    private Arende arende;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dok_id", nullable = true)
    @XmlTransient
    private Dok dok;

    public void afterUnmarshal(Unmarshaller u, Object parent) {
        if (parent instanceof Arende) {
            this.arende = (Arende) parent;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Arende getArende() {
        return arende;
    }

    public void setArende(Arende arende) {
        this.arende = arende;
    }

    public Dok getDok() {
        return dok;
    }

    public void setDok(Dok dok) {
        this.dok = dok;
    }
}
