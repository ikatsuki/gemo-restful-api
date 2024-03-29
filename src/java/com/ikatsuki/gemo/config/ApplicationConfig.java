/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ikatsuki.gemo.config;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author ik2721
 */
@javax.ws.rs.ApplicationPath("wr")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        try {
            Class jsonProvider = Class.forName("org.glassfish.jersey.jackson.JacksonFeature");
            resources.add(jsonProvider);
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.ikatsuki.gemo.controller.UserResource.class);
        resources.add(com.ikatsuki.gemo.exception.BusinessExceptionMapper.class);
        resources.add(com.ikatsuki.gemo.exception.ConstraintViolationExceptionMapper.class);
        resources.add(com.ikatsuki.gemo.exception.ResourceNotFoundExceptionMapper.class);
    }
    
}
