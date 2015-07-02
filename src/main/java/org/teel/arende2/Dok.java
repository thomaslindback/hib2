package org.teel.arende2;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "zeel_dok")
//@XmlJavaTypeAdapter(ObjectWrapperAdapter.class)
public class Dok {

    @Id
    @GenericGenerator(name = "table", strategy = "enhanced-table", parameters = {
            @org.hibernate.annotations.Parameter(name = "table_name", value = "sequence_table")})
    @GeneratedValue(generator = "table", strategy = GenerationType.TABLE)
    private Long id;

    @XmlAttribute
    @XmlID
    private String xid;

    private long dokId;
    private String name;

    @OneToMany(targetEntity = Hand.class, cascade = CascadeType.ALL, mappedBy = "dok")
    @LazyCollection(LazyCollectionOption.FALSE)
    @XmlIDREF
    @XmlAttribute
    private List<Hand> hand = new ArrayList<>();

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

    public List<Hand> getHand() {
        return hand;
    }

    public void setHand(List<Hand> hand) {
        this.hand = hand;
    }

    public long getDokId() {
        return dokId;
    }

    public void setDokId(long dokId) {
        this.dokId = dokId;
    }

    public String getXid() {
        return xid;
    }

    public void setXid(String xid) {
        this.xid = xid;
    }


}
