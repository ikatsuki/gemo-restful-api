/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ikatsuki.gemo.exception;

import javax.ejb.ApplicationException;

/**
 *
 * @author ik2721
 */
@ApplicationException
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
    
}
