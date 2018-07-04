package com.guilhermecamargo.testeuol.appllication.controllers;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExceptionController {
	
	private static final String PAGE_404 = "error/404";
	private static final String PAGE_500 = "error/500";

    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handlePageNotFound(EntityNotFoundException exception){
        return new ModelAndView(PAGE_404);
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(HttpServletRequest request, Exception e)   {
        return new ModelAndView(PAGE_500);
    }


}
