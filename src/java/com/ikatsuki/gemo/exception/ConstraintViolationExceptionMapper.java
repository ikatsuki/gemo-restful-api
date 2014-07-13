/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ikatsuki.gemo.exception;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author ik2721
 */
@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        List<String> messages = new ArrayList<>();

        // 入力チェックエラー情報取得        
        for (ConstraintViolation<?> cv : exception.getConstraintViolations()) {
            messages.add(cv.getMessage());
        }
        ErrorModel errorModel = new ErrorModel(messages);
        return Response.status(Response.Status.BAD_REQUEST).entity(errorModel).build();
    }
}
