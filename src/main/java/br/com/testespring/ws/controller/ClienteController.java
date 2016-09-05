package br.com.testespring.ws.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.testespring.ws.model.Cliente;
import br.com.testespring.ws.service.ClienteService;

@RestController
public class ClienteController {
	
	@Autowired
	ClienteService clienteService;
	
	//End points
	@RequestMapping(method=RequestMethod.POST, value="/cliente", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> cadastrarCliente(@RequestBody Cliente cliente){
		Cliente clienteCadastrado = clienteService.cadastrar(cliente);
		return new ResponseEntity<Cliente>(clienteCadastrado, HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/cliente", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Cliente>> buscarTodosClientes(){
		Collection<Cliente> todosClientes = clienteService.buscarTodos();
		return new ResponseEntity<>(todosClientes, HttpStatus.OK);
	}
	
	
	@RequestMapping(method=RequestMethod.DELETE, value="/cliente/{id}")
	public ResponseEntity<Cliente> excluirCliente(@PathVariable Integer id){
		
		Cliente clienteEncontrado = clienteService.buscaPorId(id);
		
		if(clienteEncontrado == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		clienteService.excluir(clienteEncontrado);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/cliente", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> alterarCliente(@RequestBody Cliente cliente){
		Cliente clienteAlterado = clienteService.alterar(cliente);
		return new ResponseEntity<Cliente>(clienteAlterado, HttpStatus.CREATED);
	}

}
