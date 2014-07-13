/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ikatsuki.gemo.service;

import com.ikatsuki.gemo.model.TUserPlace;
import com.ikatsuki.gemo.model.TUserPlacePK;
import java.util.Date;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author ik2721
 */
public class UserServiceTest {

    private EJBContainer container;
    private Context context;

    public UserServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        context = container.getContext();
    }

    @After
    public void tearDown() {
        container.close();
    }

    /**
     * Test of getUserPlace method, of class UserService.
     */
    @Test
    public void testGetUserPlace() throws Exception {
        System.out.println("getLocationInfo");
        UserService instance = (UserService) context.lookup("java:global/classes/UserService");
        TUserPlace result = instance.getUserPlace(1, 1L);
        System.out.println(result);
        assertNotNull(result);
    }

    /**
     * Test of getUserPlaceList method, of class UserService.
     */
    @Test
    public void testGetUserPlaceList() throws Exception {
        System.out.println("getUserPlaceList");
        UserService instance = (UserService) context.lookup("java:global/classes/UserService");
        List<TUserPlace> result = instance.getUserPlaceList(1);
        System.out.println(result);
        assertNotNull(result);
    }

    /**
     * Test of createUserPlace method, of class UserService.
     */
    @Test
    public void testCreateUserPlace() throws Exception {
        System.out.println("createUserPlace");
        Date currentDt = new Date();
        TUserPlacePK pk = new TUserPlacePK(1, -1);
        TUserPlace placeInfo = new TUserPlace(pk, 35.721D, 139.721D, currentDt, currentDt);
        placeInfo.setPlaceName("test" + currentDt.toString());
        placeInfo.setPlaceRegisteredDate(currentDt);
        UserService instance = (UserService) context.lookup("java:global/classes/UserService");
        TUserPlace result = instance.createUserPlace(placeInfo);
        assertNotNull(result);
    }

    /**
     * Test of updateUserPlace method, of class UserService.
     */
    @Test
    public void testUpdateUserPlace() throws Exception {
        System.out.println("updateUserPlace");
        Date currentDt = new Date();
        TUserPlacePK pk = new TUserPlacePK(1, -1);
        TUserPlace placeInfo = new TUserPlace(pk, 35.721D, 139.721D, null, currentDt);
        placeInfo.setPlaceName("test" + currentDt.toString());
        placeInfo.setPlaceRegisteredDate(currentDt);
        UserService instance = (UserService) context.lookup("java:global/classes/UserService");
        TUserPlace result = instance.updateUserPlace(placeInfo);
        assertNotNull(result);
    }

    /**
     * Test of deleteUserPlace method, of class UserService.
     */
    @Test
    public void testDeleteUserPlace() throws Exception {
        System.out.println("deleteUserPlace");
        UserService instance = (UserService) context.lookup("java:global/classes/UserService");
        Boolean result = instance.deleteUserPlace(1,-1L);
        assertNotNull(result);
    }

}
