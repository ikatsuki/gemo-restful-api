/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ikatsuki.gemo.controller;

import com.ikatsuki.gemo.model.MUser;
import com.ikatsuki.gemo.model.TUserPlace;
import com.ikatsuki.gemo.service.UserService;
import java.net.URI;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author ik2721
 */
@Path("user")
public class UserResource {

    @EJB
    protected UserService userService;

    @GET
    @Path("/{userId}/")
    @Produces(MediaType.APPLICATION_JSON)
    public MUser getUserInfo(@PathParam("userId") Integer userId) {
        return userService.getUser(userId);
    }

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public MUser registerUserInfo(MUser userInfo) {
        return userService.createUser(userInfo);
    }

    @PUT
    @Path("/{userId}/")
    @Produces(MediaType.APPLICATION_JSON)
    public MUser editUserInfo(@PathParam("userId") Integer userId, MUser userInfo) {
        if (!Objects.equals(userId, userInfo.getUserId())) {
            return null;
        }
        return userService.updateUser(userInfo);
    }

    @DELETE
    @Path("/{userId}/")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer deleteUserInfo(@PathParam("userId") Integer userId) {
        return userService.deleteUser(userId);
    }

    @GET
    @Path("/{userId}/places/{placeId}")
    @Produces(MediaType.APPLICATION_JSON)
    public TUserPlace getPlaceInfo(
            @PathParam("userId") Integer userId,
            @PathParam("placeId") Long placeId) {
        return userService.getUserPlace(userId, placeId);
    }

    @GET
    @Path("/{userId}/places/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TUserPlace> getPlaceInfoList(
            @PathParam("userId") Integer userId) {
        return userService.getUserPlaceList(userId);
    }

    @POST
    @Path("/{userId}/places/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerPlaceInfo(
            @PathParam("userId") Integer userId,
            TUserPlace placeInfo,
            @Context UriInfo uriInfo) {
        TUserPlace registeredPlaceInfo = userService.createUserPlace(placeInfo);
        Long placeId = registeredPlaceInfo.getTUserPlacePK().getPlaceId();
        URI newUri = uriInfo.getRequestUriBuilder().path(placeId.toString()).build();
        return Response.created(newUri).entity(registeredPlaceInfo).build();
    }

    @PUT
    @Path("/{userId}/places/{placeId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response editPlaceInfo(
            @PathParam("userId") Integer userId,
            @PathParam("placeId") Long placeId,
            TUserPlace placeInfo,
            @Context UriInfo uriInfo) {

        TUserPlace editededPlaceInfo = userService.updateUserPlace(placeInfo);
        Long editedPlaceId = editededPlaceInfo.getTUserPlacePK().getPlaceId();
        URI newUri = uriInfo.getRequestUriBuilder().path(editedPlaceId.toString()).build();

        return Response.created(newUri).entity(editededPlaceInfo).build();
    }

    @DELETE
    @Path("/{userId}/places/{placeId}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deletePlaceInfo(
            @PathParam("userId") Integer userId,
            @PathParam("placeId") Long placeId) {

        Boolean isDeleted = userService.deleteUserPlace(userId, placeId);
        if (isDeleted) {

        }
    }

}
