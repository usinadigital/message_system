package br.usinadigital.msgsystemwebapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProvaController {

	private static final Logger logger = LoggerFactory.getLogger(ProvaController.class);

	@RequestMapping(value = "/provacss", method = RequestMethod.GET)
	public String provacss() {
		return "provacss";
	}

}
