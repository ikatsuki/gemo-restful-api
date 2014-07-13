/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ikatsuki.gemo.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ik2721
 */
@Embeddable
public class TUserPlacePK implements Serializable {

    private int userId;
    private long placeId;

    public TUserPlacePK() {
    }

    public TUserPlacePK(int userId, long placeId) {
        this.userId = userId;
        this.placeId = placeId;
    }

    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic(optional = false)
    @Column(name = "place_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(long placeId) {
        this.placeId = placeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) userId;
        hash += (int) placeId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TUserPlacePK)) {
            return false;
        }
        TUserPlacePK other = (TUserPlacePK) object;
        if (this.userId != other.userId) {
            return false;
        }
        if (this.placeId != other.placeId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ikatsuki.gemo.model.TUserPlacePK[ userId=" + userId + ", placeId=" + placeId + " ]";
    }

}
