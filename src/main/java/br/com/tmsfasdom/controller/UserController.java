package br.com.tmsfasdom.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.tmsfasdom.model.UserRole;
import br.com.tmsfasdom.model.Usuario;

@RestController
@RequestMapping(value = "/api/admin")
public class UserController {

	/**
	 * GET /create --> Create a new user and save it in the database.
	 */

	@RequestMapping(value = "/usuario/criar", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> create(@RequestBody Usuario user) {
		return null;
	}

	/**
	 * GET /delete --> Delete the user having the passed id.
	 */
	@RequestMapping(value = "/usuario/deletar/{id}", method = RequestMethod.DELETE, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		return null;
	}

	@RequestMapping(value = "/usuario/obter/{id}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getById(@PathVariable("id") long id) {
		return null;
	}

	/**
	 * GET /get-by-email --> Return the id for the user having the passed email.
	 */
	@RequestMapping(value = "/usuario/obterpornome", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getByUserName(@RequestParam(value = "userName", required = true) String userName) {
		return null;
	}

	@RequestMapping(value = "/usuario/obtertodos", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getAll() {

		return null;
	}

	/**
	 * GET /update --> Update the email and the name for the user in the
	 * database having the passed id.
	 */
	@RequestMapping(value = "/usuario/atualizar", method = RequestMethod.PUT, produces = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> updateUser(@RequestBody Usuario user) {

		return null;
	}

	@RequestMapping(value = "/usuario/obterlogado", method = RequestMethod.GET)
	public Usuario getCurrent() {
		return null; // anonymous user support
	}

	@RequestMapping(value = "/usuario/alterarsenha", method = RequestMethod.PATCH)
	public ResponseEntity<String> changePassword(@RequestBody final Usuario user) {

		return null;
	}

	@RequestMapping(value = "/usuario/{userName}/grant/role/{roleName}", method = RequestMethod.GET)
	public ResponseEntity<String> grantRole(@PathVariable String userName, @PathVariable String roleName) {
		return null;
	}

	@RequestMapping(value = "/usuario/{user}/revoke/role/{role}", method = RequestMethod.POST)
	public ResponseEntity<String> revokeRole(@PathVariable Usuario user, @PathVariable UserRole role) {

		return null;
	}

}