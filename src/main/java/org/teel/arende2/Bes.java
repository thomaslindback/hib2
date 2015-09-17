package org.teel.arende2;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "zeel_bes")
//@XmlJavaTypeAdapter(ObjectWrapperAdapter.class)
public class Bes extends AbstractDokBase {

    @OneToMany(targetEntity = Hand.class, cascade = CascadeType.ALL, mappedBy = "bes")
    @LazyCollection(LazyCollectionOption.FALSE)
    @XmlIDREF
    @XmlAttribute
    private List<Hand> hand = new ArrayList<>();

    public List<Hand> getHand() {
        return hand;
    }

    public void setHand(List<Hand> hand) {
        this.hand = hand;
    }
}
