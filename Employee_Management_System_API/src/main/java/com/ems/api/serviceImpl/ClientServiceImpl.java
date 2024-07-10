package com.ems.api.serviceImpl;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.api.dto.ClientDto;
import com.ems.api.entity.ClientEntity;
import com.ems.api.entity.DepartmentEntity;
import com.ems.api.exception.NotFoundException;
import com.ems.api.repository.ClientRepository;
import com.ems.api.repository.DepartmentRepo;
import com.ems.api.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	ClientRepository clientRepo;

	@Autowired
	private DepartmentRepo departmentRepo;

	@Override
	public String addClient(ClientDto client, int departementId) {
		// TODO Auto-generated method stub
		Optional<DepartmentEntity> depart = departmentRepo.findById(departementId);
		if (!depart.isPresent()) {
			return "department not found";
		}
		Optional<ClientEntity> name = clientRepo.findByName(client.getName());
		if (name.isPresent()) {
			return "Client  name already exists. ";
		}
		ClientEntity cli = new ClientEntity();
		cli.setName(client.getName());
		cli.setCountry(client.getCountry());
		cli.setAddress(client.getAddress());
		cli.setEmailId(client.getEmailId());
		cli.setLocation(client.getLocation());
		cli.setPhoneNumber(client.getPhoneNumber());
		cli.setWebsiteURL(client.getWebsiteURL());
		//cli.setEmployee(client.getEmployee());
		cli.setDepartmentEntity(client.getDepartmentEntity());
		cli.setAddedBy(client.getAddedBy());
		cli.setCreatedAt(LocalDateTime.now());
		cli.setStatus(client.getStatus());
		cli.setCreatedBy(client.getCreatedBy());

		clientRepo.save(cli);

		return "Client Registered Succesfully";

	}

	

	@Override
	public String updateClient(int clientId, ClientDto client) {
		// TODO Auto-generated method stub
		Optional<ClientEntity> updated = clientRepo.findById(clientId);
		if (updated.isPresent()) {
			ClientEntity cli = updated.get();
			cli.setWebsiteURL(client.getWebsiteURL());
			cli.setAddress(client.getAddress());
			cli.setCountry(client.getCountry());
			cli.setEmailId(client.getEmailId());
			cli.setLocation(client.getLocation());
			cli.setPhoneNumber(client.getPhoneNumber());
			cli.setStatus(client.getStatus());
			cli.setUpdatedAt(LocalDateTime.now());
			cli.setUpdatedBy(client.getUpdatedBy());

			clientRepo.save(cli);

			return "Client updated Succesfully";

		}
		throw new NotFoundException("Client Id not Found");

	}

	@Override
	public List<ClientDto> getAllClients() {

		List<ClientEntity> allCli = clientRepo.findAll();

		List<ClientDto> allCliDto = new ArrayList<ClientDto>();

		for (ClientEntity a : allCli) {
			
			ClientDto d = new ClientDto();

			BeanUtils.copyProperties(a, d);

			allCliDto.add(d);
		}

		return allCliDto;
	}

	@Override
	public ClientDto getClient(int clientId) {
		// TODO Auto-generated method stub
		Optional<ClientEntity> opt = clientRepo.findById(clientId);
		if (opt.isPresent()) {
			ClientEntity client = opt.get();
			ClientDto dto = new ClientDto();
//			dto.setName(client.getName());
//			dto.setWebsiteURL(client.getWebsiteURL());
//			dto.setAddress(client.getAddress());
//			dto.setCountry(client.getCountry());
//			dto.setEmailId(client.getEmailId());
//			dto.setLocation(client.getLocation());
//			dto.setEmployee(client.getEmployee());
//			dto.setPhoneNumber(client.getPhoneNumber());
//			dto.setStatus(client.isStatus());

			BeanUtils.copyProperties(client, dto);
			
			return dto;
		}
		throw new NotFoundException("Client Id not Found");

	}

}

