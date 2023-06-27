package com.alexis.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alexis.model.Role;
import com.alexis.model.Usuario;
import com.alexis.service.MailService;
import com.alexis.service.UsuarioService;


@Controller
@RequestMapping("/")
public class InitController {
	
	@Autowired
	private UsuarioService usuarioservice;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@RequestMapping(value = { "/"}, method = RequestMethod.GET)
	public String listUsers() {

		return "index";
	}

	@RequestMapping(value = "/password", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> changePassword(@RequestBody Usuario email) {

		
		
		
		if (email.getEmail().equals("")) {

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		 

		Usuario usuario = usuarioservice.getUserByEmail(email.getEmail());

		if (usuario == null) {
			
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		String cadena = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890_";
		
		String ret = ""; 
		
		for (int i=0; i <= 6; i++ ) {
			
			Random ran = new Random();
			
			int r = ran.nextInt(cadena.length() - 1);
			
			char car = cadena.charAt(r);
			
			ret = ret + String.valueOf(car);
			
		}
		 
		
		usuarioservice.updatePassword(email.getEmail(), encoder.encode(ret));
		
		mailService.sendMail(email.getEmail(), ret, usuario.getNombres());
		
		List<Role> role = new ArrayList<Role>();
		
		usuario.setRoles(role);
 
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);  
		
	}
}
