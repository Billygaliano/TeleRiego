/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teleriego.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author inftel12
 */
@Entity
@Table(name = "MEMBERSHIP_LAND")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MembershipLand.findAll", query = "SELECT m FROM MembershipLand m"),
    @NamedQuery(name = "MembershipLand.findByIdMemLand", query = "SELECT m FROM MembershipLand m WHERE m.idMemLand = :idMemLand")})
public class MembershipLand implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_MEM_LAND")
    private BigDecimal idMemLand;
    @JoinColumn(name = "LAND_ID", referencedColumnName = "LAND_ID")
    @ManyToOne(optional = false)
    private Land landId;
    @JoinColumn(name = "MEMBER_NUMBER", referencedColumnName = "MEMBER_NUMBER")
    @ManyToOne(optional = false)
    private Membership memberNumber;

    public MembershipLand() {
    }

    public MembershipLand(BigDecimal idMemLand) {
        this.idMemLand = idMemLand;
    }

    public BigDecimal getIdMemLand() {
        return idMemLand;
    }

    public void setIdMemLand(BigDecimal idMemLand) {
        this.idMemLand = idMemLand;
    }

    public Land getLandId() {
        return landId;
    }

    public void setLandId(Land landId) {
        this.landId = landId;
    }

    public Membership getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(Membership memberNumber) {
        this.memberNumber = memberNumber;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMemLand != null ? idMemLand.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MembershipLand)) {
            return false;
        }
        MembershipLand other = (MembershipLand) object;
        if ((this.idMemLand == null && other.idMemLand != null) || (this.idMemLand != null && !this.idMemLand.equals(other.idMemLand))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "teleriego.model.MembershipLand[ idMemLand=" + idMemLand + " ]";
    }
    
}
