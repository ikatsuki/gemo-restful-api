/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ikatsuki.gemo.service;

import com.ikatsuki.gemo.exception.ResourceNotFoundException;
import com.ikatsuki.gemo.model.MUser;
import com.ikatsuki.gemo.model.MUser_;
import com.ikatsuki.gemo.model.TUserPlace;
import com.ikatsuki.gemo.model.TUserPlace_;
import com.ikatsuki.gemo.model.TUserPlacePK_;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author ik2721
 */
@Stateless
public class UserService {

    @PersistenceContext
    protected EntityManager entityManager;

    public MUser getUser(Integer userId) {

        // Create Query
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MUser> query = builder.createQuery(MUser.class);
        Root<MUser> m_user = query.from(MUser.class);
        query.select(m_user).where(builder.equal(m_user.get(MUser_.userId), userId));

        // Execute Query
        TypedQuery<MUser> typedquery = entityManager.createQuery(query);
        MUser user = typedquery.getSingleResult();

        // Check Result
        if (user == null) {
            throw new ResourceNotFoundException(String.format("[E404] getUser (userId=%1$s)", userId));
        }

        return user;
    }

    public MUser createUser(MUser user) {

        // Set Data
        Date currentDt = new Date();
        user.setCreatedDate(currentDt);
        user.setModifiedDate(currentDt);

        entityManager.persist(user);

        return user;

    }

    public MUser updateUser(MUser user) {

        // Set Data
        Date currentDt = new Date();
        user.setModifiedDate(currentDt);

        entityManager.merge(user);

        return user;

    }

    public Integer deleteUser(Integer userId) {

        entityManager.remove(new MUser(userId));

        // Create Query
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<TUserPlace> query = builder.createQuery(TUserPlace.class);
        Root<TUserPlace> t_user_plcae = query.from(TUserPlace.class);
        query.where(builder.equal(t_user_plcae.get(TUserPlace_.TUserPlacePK).get(TUserPlacePK_.userId), userId));

        // Execute Query
        TypedQuery<TUserPlace> typedquery = entityManager.createQuery(query);
        return typedquery.executeUpdate();

    }

    public TUserPlace getUserPlace(Integer userId, Long placeId) {

        // Create Query
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<TUserPlace> query = builder.createQuery(TUserPlace.class);
        Root<TUserPlace> t_user_plcae = query.from(TUserPlace.class);
        query.select(t_user_plcae)
                .where(builder.equal(t_user_plcae.get(TUserPlace_.TUserPlacePK).get(TUserPlacePK_.userId), userId),
                        builder.equal(t_user_plcae.get(TUserPlace_.TUserPlacePK).get(TUserPlacePK_.placeId), placeId));

        // Execute Query
        TypedQuery<TUserPlace> typedquery = entityManager.createQuery(query);
        TUserPlace place = typedquery.getSingleResult();

        if (place == null) {
            throw new ResourceNotFoundException(String.format("[E404] getUserPlace (placeId=%1$s)", placeId));
        }

        return place;
    }

    public List<TUserPlace> getUserPlaceList(Integer userId) {

        // Create Query
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<TUserPlace> query = builder.createQuery(TUserPlace.class);
        Root<TUserPlace> t_user_plcae = query.from(TUserPlace.class);
        query.select(t_user_plcae)
                .where(builder.equal(t_user_plcae.get(TUserPlace_.TUserPlacePK).get(TUserPlacePK_.userId), userId));

        // Execute Query
        TypedQuery<TUserPlace> typedquery = entityManager.createQuery(query);
        List<TUserPlace> placeList = typedquery.getResultList();

        if (placeList.isEmpty()) {
            throw new ResourceNotFoundException(String.format("[E404] getUserPlaceList (userId=%1$s)", userId));
        }

        return placeList;
    }

    public TUserPlace createUserPlace(TUserPlace place) {

        // Set Data
        Date currentDt = new Date();
        place.setCreatedDate(currentDt);
        place.setModifiedDate(currentDt);

        entityManager.persist(place);

        return place;
    }

    public TUserPlace updateUserPlace(TUserPlace place) {

        // Set Data
        Date currentDt = new Date();
        place.setModifiedDate(currentDt);

        entityManager.merge(place);

        return place;
    }

    public Boolean deleteUserPlace(Integer userId, Long placeId) {

        // Create Query
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<TUserPlace> query = builder.createQuery(TUserPlace.class);
        Root<TUserPlace> t_user_plcae = query.from(TUserPlace.class);
        query.select(t_user_plcae)
                .where(builder.equal(t_user_plcae.get(TUserPlace_.TUserPlacePK).get(TUserPlacePK_.userId), userId),
                        builder.equal(t_user_plcae.get(TUserPlace_.TUserPlacePK).get(TUserPlacePK_.placeId), placeId));

        // Execute Query
        TypedQuery<TUserPlace> typedquery = entityManager.createQuery(query);
        TUserPlace resultPlace = typedquery.getSingleResult();

        if (resultPlace == null) {
            throw new ResourceNotFoundException(String.format("[E404] deleteUserPlace (userId=%1$s,palceId=%2$s)", userId, placeId));
        }

        entityManager.remove(resultPlace);

        return true;
    }

}
