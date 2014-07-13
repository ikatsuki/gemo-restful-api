/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ikatsuki.gemo.exception;

import java.util.Arrays;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import com.ikatsuki.gemo.exception.BusinessException;

/**
 *
 * @author ik2721
 */
@Provider
public class BusinessExceptionMapper implements ExceptionMapper<BusinessException>{
    
    @Override
    public Response toResponse(BusinessException exception) {
        ErrorModel errorModel = new ErrorModel(Arrays.asList(exception.getMessage()));
        return Response.status(Response.Status.CONFLICT)
                .entity(errorModel).build();
    }
    
}
