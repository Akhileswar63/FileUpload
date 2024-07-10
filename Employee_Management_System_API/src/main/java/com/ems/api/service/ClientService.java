package com.ems.api.service;


import java.util.List;

import com.ems.api.dto.ClientDto;

public interface ClientService {

	
	public String addClient(ClientDto client, int departmentId);
	
	public String updateClient(int clientId, ClientDto client);
	
	public List<ClientDto>getAllClients();
	
	public ClientDto getClient(int clientId);
	
	
	
	
}

