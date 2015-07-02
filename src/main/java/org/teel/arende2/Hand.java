package org.teel.arende2;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "zeel_hand")
//@XmlJavaTypeAdapter(ObjectWrapperAdapter.class)
public class Hand {
    String description;
    @Id
    @GenericGenerator(name = "table", strategy = "enhanced-table", parameters = {
            @org.hibernate.annotations.Parameter(name = "table_name", value = "sequence_table")})
    @GeneratedValue(generator = "table", strategy = GenerationType.TABLE)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dok_id", nullable = true)
    @XmlIDREF
    @XmlAttribute
    private Dok dok;

    @XmlID
    @XmlAttribute
    private String xid;

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

    public Dok getDok() {
        return dok;
    }

    public void setDok(Dok dok) {
        this.dok = dok;
    }

    public String getXid() {
        return xid;
    }

    public void setXid(String xid) {
        this.xid = xid;
    }
}
