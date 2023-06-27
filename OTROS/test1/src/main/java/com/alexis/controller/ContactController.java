package com.alexis.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;
import com.alexis.model.Contacto;
import com.alexis.service.ContactoService;

@RestController
@RequestMapping("/admin")
public class ContactController {

	@Autowired
	private ContactoService contactoService;

	private static final String rutaImg = "http://localhost:8090/static/upload/";
	private static final String rutaServ = "H:/Workspace/test1/src/main/webapp/static/upload/";

	private static final String rutaImghost = "http://mantenedor.hrlxdeveloper.com/test1-0.1/static/upload/";
	private static final String rutaServHost = "/home3/alex2019/jvm/apache-tomcat-8.5.30/domains/mantenedor.hrlxdeveloper.com/test1-0.1/static/upload/";
	
	@RequestMapping(value = "/addContact", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<?> createContact(@RequestBody Contacto contacto, UriComponentsBuilder uriComponentsBuilder) {
		if (contacto.getNombre().isEmpty() || contacto.getNombre() == null) {

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		contacto.setActivo(1);

		contactoService.save(contacto);

		return new ResponseEntity<>(contacto, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/contacto", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<Contacto>> getContact() {

		List<Contacto> contacto = new ArrayList<>();

		contacto = contactoService.findAll();

		if (contacto.isEmpty()) {

			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(contacto, HttpStatus.OK);
	}

	@RequestMapping(value = "/contacto/{id}", method = RequestMethod.PATCH, headers = "Accept=application/json")
	public ResponseEntity<?> updateContact(@PathVariable("id") Long id, @RequestBody Contacto contacto) {

		if (id == null || id <= 0) {

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		Contacto contact = contactoService.findById(id);

		if (contact == null) {

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		contact.setNombre(contacto.getNombre());
		contact.setApellidos(contacto.getApellidos());
		contact.setEmail(contacto.getEmail());
		contact.setDireccion(contacto.getDireccion());
		contact.setTelefono(contacto.getTelefono());

		contactoService.update(contact);

		return new ResponseEntity<>(contact, HttpStatus.OK);
	}

	@RequestMapping(value = "/contacto/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public ResponseEntity<?> deleteContact(@PathVariable("id") Long id) {

		if (id == null || id < 0) {

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		Contacto contacto = contactoService.findById(id);

		if (contacto == null) {

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		contactoService.delete(id);

		return new ResponseEntity<Contacto>(contacto, HttpStatus.OK);
	}

	@RequestMapping(value = "/contacto/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> getContactById(@PathVariable("id") Long id) {

		if (id == null || id < 0) {

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		Contacto contacto = contactoService.findById(id);

		if (contacto == null) {

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(contacto, HttpStatus.OK);
	}

	@RequestMapping(value = "/addPicture", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<?> AddPicture(@RequestParam("imagenPropia") MultipartFile avatar)
			throws IllegalStateException, IOException {

		String name = avatar.getOriginalFilename();
		String ext = name.split(Pattern.quote("."))[1];

		String uid = UUID.randomUUID().toString();

		File convFile = new File(rutaServHost + uid + "." + ext);
		avatar.transferTo(convFile);

		Contacto contacto = new Contacto();
		contacto.setAvatar(rutaImghost + uid + "." + ext);

		return new ResponseEntity<>(contacto, HttpStatus.OK);

	}

	@RequestMapping(value = "/updatePicture/{id}", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<?> updatePicture(@PathVariable("id") Long id, @RequestBody Contacto contacto) {

		if (id == null || id <= 0) {

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		Contacto contacto2 = contactoService.findById(id);

		if (contacto2 == null) {

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		contacto2.setAvatar(contacto.getAvatar());

		contactoService.update(contacto2);

		return new ResponseEntity<>(contacto, HttpStatus.OK);

	}

	@RequestMapping(value = "/getPicture/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> getePicture(@PathVariable("id") Long id) {

		if (id == null || id <= 0) {

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		Contacto contacto = contactoService.findById(id);

		if (contacto == null) {

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(contacto, HttpStatus.OK);

	}
}
