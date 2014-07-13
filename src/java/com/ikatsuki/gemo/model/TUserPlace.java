/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ikatsuki.gemo.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ik2721
 */
@Entity
@Table(name = "t_user_place")
@XmlRootElement
public class TUserPlace implements Serializable {

    protected TUserPlacePK tUserPlacePK;
    private double latitude;
    private double longitude;
    private String placeName;
    private Date placeRegistered;
    private Date createdDate;
    private Date modifiedDate;

    public TUserPlace() {
    }

    public TUserPlace(TUserPlacePK tUserPlacePK) {
        this.tUserPlacePK = tUserPlacePK;
    }

    public TUserPlace(TUserPlacePK tUserPlacePK, double latitude, double longitude, Date createdDate, Date modifiedDate) {
        this.tUserPlacePK = tUserPlacePK;
        this.latitude = latitude;
        this.longitude = longitude;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public TUserPlace(int userId, long placeId) {
        this.tUserPlacePK = new TUserPlacePK(userId, placeId);
    }

    @EmbeddedId
    public TUserPlacePK getTUserPlacePK() {
        return tUserPlacePK;
    }

    public void setTUserPlacePK(TUserPlacePK tUserPlacePK) {
        this.tUserPlacePK = tUserPlacePK;
    }

    @Basic(optional = false)
    @NotNull
    @Column(name = "latitude")
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Basic(optional = false)
    @NotNull
    @Column(name = "longitude")
    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Size(max = 500)
    @Column(name = "place_name")
    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    @Column(name = "place_registered_date")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getPlaceRegisteredDate() {
        return placeRegistered;
    }

    public void setPlaceRegisteredDate(Date placeRegistered) {
        this.placeRegistered = placeRegistered;
    }

    @Basic(optional = false)
    @Column(name = "created_date", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Basic(optional = false)
    @Column(name = "modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tUserPlacePK != null ? tUserPlacePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TUserPlace)) {
            return false;
        }
        TUserPlace other = (TUserPlace) object;
        if ((this.tUserPlacePK == null && other.tUserPlacePK != null) || (this.tUserPlacePK != null && !this.tUserPlacePK.equals(other.tUserPlacePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ikatsuki.gemo.model.TUserPlace[ tUserPlacePK=" + tUserPlacePK + " ]";
    }

}
