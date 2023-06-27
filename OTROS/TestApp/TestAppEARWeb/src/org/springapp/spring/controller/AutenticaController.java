package org.springapp.spring.controller;

 
import org.springapp.spring.dto.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AutenticaController {

	 

	@RequestMapping(value = {"/"}, method = RequestMethod.GET)
	public String lookuplogin(Model model) {

		 

		return "index";

	}

	@RequestMapping(value = {"/json"}, method = RequestMethod.GET)
	public @ResponseBody Usuario jsonTest() {

		return new Usuario("alexis", "Mendez") ;

	}
	 
}
