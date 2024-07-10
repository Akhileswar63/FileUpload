package com.ems.api.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ems.api.dto.EmployeeDetailsDTO;
import com.ems.api.dto.EmployeeRequest;
import com.ems.api.exception.NotFoundException;
import com.ems.api.service.EmployeeService;

@RestController
@CrossOrigin(origins = "*")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/empsave/{createdBy}/{organizationId}/{branchId}/{designationId}/{deptId}")
	public ResponseEntity<String> saveEmployee(@RequestPart("emp") EmployeeRequest employeeDto,
			@RequestPart("file") MultipartFile file, @PathVariable String createdBy,
			@PathVariable Integer organizationId, @PathVariable Integer branchId, @PathVariable Integer designationId,
			@PathVariable Integer deptId

	) throws IOException {

		String result = employeeService.saveEmp(employeeDto, createdBy, organizationId, branchId, designationId, deptId,
				file);
		return new ResponseEntity<>(result, HttpStatus.CREATED);

	}

	@PutMapping("/change/{employeeId}/{status}")
	public ResponseEntity<String> changeStatusEmp(@PathVariable Integer employeeId, @PathVariable boolean status) {
		String msg = employeeService.deleteEmpployee(employeeId, status);
		return ResponseEntity.status(HttpStatus.OK).body(msg);
	}

	@PutMapping("/update/{employeeId}/{organizationId}/{branchId}/{designationId}/{deptId}")
	public ResponseEntity<String> updateEmployee(@PathVariable Integer employeeId,
			@RequestBody EmployeeRequest employeeRequest, @PathVariable Integer organizationId,
			@PathVariable Integer branchId, @PathVariable Integer designationId, @PathVariable Integer deptId) {
		try {
			String response = employeeService.updateEmployee(employeeId, employeeRequest, organizationId, branchId,
					designationId, deptId);
			return ResponseEntity.ok(response);
		} catch (NotFoundException e) {
			return ResponseEntity.status(404).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Internal Server Error");
		}
	}


	@GetMapping("/employee/{employeeId}")
	public ResponseEntity<EmployeeRequest> getEmployeeById(@PathVariable Integer employeeId) {
		try {
			EmployeeRequest employee = employeeService.getEmployeeById(employeeId);
			return new ResponseEntity<>(employee, HttpStatus.OK);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/employees")
	public ResponseEntity<List<EmployeeRequest>> getAllEmployees() {
		try {
			List<EmployeeRequest> employees = employeeService.getAllEmployees();
			return new ResponseEntity<>(employees, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/EmployeeDeatils/{employeeId}")
	public EmployeeDetailsDTO getEmployeeDetails(@PathVariable Integer employeeId) {
		return employeeService.getEmployeeDetailsById(employeeId);
	}

}
