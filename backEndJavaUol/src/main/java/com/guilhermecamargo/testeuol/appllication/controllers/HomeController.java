package com.guilhermecamargo.testeuol.appllication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
		
import com.guilhermecamargo.testeuol.domain.services.JogadorService;

@Controller
@RequestMapping(value = "/home")
public class HomeController {

	@Autowired
	private JogadorService jogadorService;

	@GetMapping
	public ModelAndView index() {
		return new ModelAndView("home/index");
	}

}
