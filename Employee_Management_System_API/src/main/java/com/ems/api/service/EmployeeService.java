package com.ems.api.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ems.api.dto.EmployeeDetailsDTO;
import com.ems.api.dto.EmployeeRequest;

public interface EmployeeService {
	
	
	public String saveEmp(EmployeeRequest employeeDto,String createdBy,Integer organizationId
			,Integer branchId, Integer designationId, Integer deptId,MultipartFile file) throws  IOException;
	
	
	
	public String updateEmployee(Integer employeeId, EmployeeRequest employeeDto, Integer organizationId,
            Integer branchId, Integer designationId, Integer deptId);
	
   //public String updateEmployee(int employeeId, EmployeeRequest updatedEmployee, String updatedBy) ;

	EmployeeRequest getEmployeeById(Integer employeeId);

   List<EmployeeRequest> getAllEmployees();

   
   public EmployeeDetailsDTO getEmployeeDetailsById(Integer employeeId) ;

	
	 
	
	public String deleteEmpployee(Integer EmployeeId,boolean status);
	
	
	
	
	

}
