package com.ems.api.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ems.api.dto.ClientDto;
import com.ems.api.exception.NotFoundException;
import com.ems.api.service.ClientService;

@RestController
@CrossOrigin(origins = "*")
public class ClientController {

	
	@Autowired
	ClientService clientservice;
	
	@PostMapping("/Addclients/{departmentId}")
    public ResponseEntity<String> addClient(@RequestBody ClientDto client, @PathVariable int departmentId)
    {
		
		  String result = clientservice.addClient(client, departmentId);
	      return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
	
	@PutMapping("/clientupdate/{clientId}")
	public ResponseEntity<String> updateClient(@PathVariable int clientId,@RequestBody ClientDto client)
	{
		String update=clientservice.updateClient(clientId, client);
		return ResponseEntity.status(HttpStatus.CREATED).body(update);
	}
	
	
	@GetMapping("/getClients")
	public ResponseEntity<List<ClientDto>>getAllClients()
	{
		List<ClientDto>allclients=clientservice.getAllClients();
		
		return ResponseEntity.status(HttpStatus.OK).body(allclients);
	}
	
	
	@GetMapping("/getClient/{clientId}")
	public ResponseEntity<ClientDto> getClient(@PathVariable int clientId)
	{
		
		ClientDto client = clientservice.getClient(clientId);
		if (client == null) {
            throw new NotFoundException("Client not found with id: " + clientId);
        }
        return ResponseEntity.status(HttpStatus.OK).body(client);
		
		
	}
	
	
	
}

