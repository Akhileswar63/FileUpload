package com.ems.api.serviceImpl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ems.api.dto.EmployeeDetailsDTO;
import com.ems.api.dto.EmployeeRequest;
import com.ems.api.entity.BranchEntity;
import com.ems.api.entity.DepartmentEntity;
import com.ems.api.entity.DesignationEntity;
import com.ems.api.entity.EmployeeEntity;
import com.ems.api.entity.OrganizationEntity;
import com.ems.api.exception.DuplicateDataException;
import com.ems.api.exception.NotFoundException;
import com.ems.api.repository.BranchRepository;
import com.ems.api.repository.DepartmentRepo;
import com.ems.api.repository.DesignationRepo;
import com.ems.api.repository.EmployeeRepository;
import com.ems.api.repository.OrganizationRepo;
import com.ems.api.service.EmployeeService;
import com.ems.api.utils.CommonFileUploadAndDownloadUtils;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private BranchRepository branchRepository;

	@Autowired
	private OrganizationRepo organizationRepo;

	@Autowired
	private DesignationRepo designationRepo;

	@Autowired
	private DepartmentRepo departmentRepo;

	@Autowired
	private CommonFileUploadAndDownloadUtils commonFileUploadAndDownloadUtils;

	@Override
	public String saveEmp(EmployeeRequest employeeDto, String createdBy, Integer organizationId, Integer branchId,
			Integer designationId, Integer deptId, MultipartFile file) throws IOException {
		if (createdBy.isBlank() || "Null".equalsIgnoreCase(createdBy)) {
			throw new NotFoundException("User email not found.");
		}

		Optional<OrganizationEntity> organazationObj = organizationRepo.findById(organizationId);

		if (organazationObj.isEmpty()) {
			throw new NotFoundException("Organization  not found.");
		}
		OrganizationEntity organizationEntity = organazationObj.get();
		Optional<BranchEntity> branchOj = branchRepository.findById(branchId);
		if (branchOj.isEmpty()) {
			throw new NotFoundException("BranchId  not found.");

		}
		BranchEntity branchEntity = branchOj.get();

		Optional<DepartmentEntity> departmentObj = departmentRepo.findById(deptId);
		if (departmentObj.isEmpty()) {
			throw new NotFoundException("deptmentId  not found.");

		}
		DepartmentEntity departmentEntity = departmentObj.get();

		Optional<DesignationEntity> designationObj = designationRepo.findById(designationId);
		if (designationObj.isEmpty()) {
			throw new NotFoundException("desinationId  not found.");
		}
		DesignationEntity designationEntity = designationObj.get();

		Optional<EmployeeEntity> duplicateEmail = employeeRepository.findByEmailId(employeeDto.getEmailId());
		if (duplicateEmail.isPresent()) {
			throw new DuplicateDataException("Already email exists");
		}

		Optional<EmployeeEntity> phoneNumber = employeeRepository.findByPhoneNumber(employeeDto.getPhoneNumber());

		if (phoneNumber.isPresent()) {
			throw new DuplicateDataException("Already phoneNumber  exists");
		}

		Optional<EmployeeEntity> secondName = employeeRepository.findBySecondName(employeeDto.getSecondName());

		if (secondName.isPresent()) {
			throw new DuplicateDataException("Already secondName  exists");

		}

		String uploadFile = commonFileUploadAndDownloadUtils.uploadFile(file);

		EmployeeEntity entity = new EmployeeEntity();
		BeanUtils.copyProperties(employeeDto, entity);
		entity.setCreatedDate(LocalDateTime.now());
		entity.setCreatedBy(createdBy);
		entity.setImgUrl(uploadFile);
		entity.setBranchEntity(branchEntity);
		entity.setOrganization(organizationEntity);
		entity.setDepartmentEntity(departmentEntity);
		entity.setProbationPeriodEnd(employeeDto.getProbationPeriedEnd());
		entity.setLocation(employeeDto.getLocation());
		entity.setProbationPeriodStart(employeeDto.getProbationPeriedEnd());
		entity.setJoiningDate(employeeDto.getJoingDate());
		entity.setDesignationEntity(designationEntity);
		entity.setReportingTo(employeeDto.getReportingTo());
		employeeRepository.save(entity);

		return "successfully saved.";
	}



	@Override
	public String deleteEmpployee(Integer EmployeeId, boolean status) {

		Optional<EmployeeEntity> empObj = employeeRepository.findById(EmployeeId);
		if (empObj.isPresent()) {
			EmployeeEntity employeeEntity = empObj.get();
			employeeEntity.setStatus(status);
			employeeRepository.save(employeeEntity);
			return " employee is " + employeeEntity.isStatus();
		}

		throw new NotFoundException("Employee not found");
	}


	@Override
    public String updateEmployee(Integer employeeId, EmployeeRequest employeeDto, Integer organizationId,
                                 Integer branchId, Integer designationId, Integer deptId) {
        Optional<EmployeeEntity> employeeObj = employeeRepository.findById(employeeId);
        if (employeeObj.isEmpty()) {
            throw new NotFoundException("Employee not found.");
        }

        EmployeeEntity employeeEntity = employeeObj.get();

        Optional<OrganizationEntity> organizationObj = organizationRepo.findById(organizationId);
        if (organizationObj.isEmpty()) {
            throw new NotFoundException("Organization not found.");
        }
        OrganizationEntity organizationEntity = organizationObj.get();

        Optional<BranchEntity> branchObj = branchRepository.findById(branchId);
        if (branchObj.isEmpty()) {
            throw new NotFoundException("BranchId not found.");
        }
        BranchEntity branchEntity = branchObj.get();

        Optional<DepartmentEntity> departmentObj = departmentRepo.findById(deptId);
        if (departmentObj.isEmpty()) {
            throw new NotFoundException("DepartmentId not found.");
        }
        DepartmentEntity departmentEntity = departmentObj.get();

        Optional<DesignationEntity> designationObj = designationRepo.findById(designationId);
        if (designationObj.isEmpty()) {
            throw new NotFoundException("DesignationId not found.");
        }
        DesignationEntity designationEntity = designationObj.get();

    

        BeanUtils.copyProperties(employeeDto, employeeEntity, "id", "createdDate", "createdBy");
        employeeEntity.setUpdatedDate(LocalDateTime.now());
        employeeEntity.setBranchEntity(branchEntity);
        employeeEntity.setOrganization(organizationEntity);
        employeeEntity.setDepartmentEntity(departmentEntity);
        employeeEntity.setDesignationEntity(designationEntity);

        employeeRepository.save(employeeEntity);
        return "Employee updated successfully.";
    }
	/*
	
	public String updateEmployee(int employeeId, EmployeeRequest updatedEmployee, String updatedBy) {
        if (updatedBy.isEmpty() || "Null".equalsIgnoreCase(updatedBy)) {
            throw new NotFoundException("User email not found.");
        }

        // Fetch the existing employee from the database if exists
        EmployeeEntity existingEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new NotFoundException("Employee not found."));

        // Update the fields from updatedEmployee if they are not null
        if (updatedEmployee.getImgUrl() != null) {
            existingEmployee.setImgUrl(updatedEmployee.getImgUrl());
        }
        if (updatedEmployee.getDob() != null) {
            existingEmployee.setDob(updatedEmployee.getDob());
        }
        if (updatedEmployee.getFirstName() != null) {
            existingEmployee.setFirstName(updatedEmployee.getFirstName());
        }
        if (updatedEmployee.getSecondName() != null) {
            existingEmployee.setSecondName(updatedEmployee.getSecondName());
        }
        if (updatedEmployee.getGender() != null) {
            existingEmployee.setGender(updatedEmployee.getGender());
        }
        if (updatedEmployee.getCountry() != null) {
            existingEmployee.setCountry(updatedEmployee.getCountry());
        }
        if (updatedEmployee.getJoingDate() != null) {
            existingEmployee.setJoiningDate(updatedEmployee.getJoingDate());
        }
        if (updatedEmployee.getMaritalStatus() != null) {
            existingEmployee.setMaritalStatus(updatedEmployee.getMaritalStatus());
        }
        if (updatedEmployee.getProbationPeriedStart() != null) {
            existingEmployee.setProbationPeriodStart(updatedEmployee.getProbationPeriedStart());
        }
        if (updatedEmployee.getProbationPeriedEnd() != null) {
            existingEmployee.setProbationPeriodEnd(updatedEmployee.getProbationPeriedEnd());
        }
        if (updatedEmployee.isStatus()) {
            existingEmployee.setStatus(updatedEmployee.isStatus());
        }
        if (updatedEmployee.getLocation() != null) {
            existingEmployee.setLocation(updatedEmployee.getLocation());
        }
        if (updatedEmployee.getReligion() != null) {
            existingEmployee.setReligion(updatedEmployee.getReligion());
        }
        if (updatedEmployee.getAddress() != null) {
            existingEmployee.setAddress(updatedEmployee.getAddress());
        }
        if (updatedEmployee.getPincode() != null) {
            existingEmployee.setPincode(updatedEmployee.getPincode());
        }
        if (updatedEmployee.getState() != null) {
            existingEmployee.setState(updatedEmployee.getState());
        }
        if (updatedEmployee.getDistrict() != null) {
            existingEmployee.setDistrict(updatedEmployee.getDistrict());
        }
        if (updatedEmployee.getCity() != null) {
            existingEmployee.setCity(updatedEmployee.getCity());
        }
        if (updatedEmployee.getPhoneNumber() != null) {
            existingEmployee.setPhoneNumber(updatedEmployee.getPhoneNumber());
        }
        if (updatedEmployee.getEmailId() != null) {
            existingEmployee.setEmailId(updatedEmployee.getEmailId());
        }

        // Update audit fields
        existingEmployee.setUpdatedBy(updatedBy);
        existingEmployee.setUpdatedDate(LocalDateTime.now());

        // Save the updated employee entity
        employeeRepository.save(existingEmployee);

        return "Employee updated successfully.";
    }
*/

    @Override
    public EmployeeRequest getEmployeeById(Integer employeeId) {
        Optional<EmployeeEntity> employeeObj = employeeRepository.findById(employeeId);
        if (employeeObj.isEmpty()) {
            throw new NotFoundException("Employee not found.");
        }

        EmployeeRequest employeeRequest = new EmployeeRequest();
        BeanUtils.copyProperties(employeeObj.get(), employeeRequest);
        return employeeRequest;
    }

    @Override
    public List<EmployeeRequest> getAllEmployees() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return employeeEntities.stream().map(employee -> {
            EmployeeRequest employeeRequest = new EmployeeRequest();
            BeanUtils.copyProperties(employee, employeeRequest);
            return employeeRequest;
        }).collect(Collectors.toList());
    }


    
    @Override
    public EmployeeDetailsDTO getEmployeeDetailsById(Integer employeeId) {
        EmployeeEntity employee = employeeRepository.findById(employeeId).orElse(null);
        if (employee == null) {
            return null;
        }

        EmployeeDetailsDTO employeeDetailsDTO = new EmployeeDetailsDTO();
        employeeDetailsDTO.setEmployeeName(employee.getFirstName() + " " + employee.getSecondName());
        employeeDetailsDTO.setDesignation(employee.getDesignationEntity().getName());
        employeeDetailsDTO.setEmployeeId(employee.getEmployeeId());

        employeeDetailsDTO.setBranch(employee.getBranchEntity().getName());
        employeeDetailsDTO.setDepartment(employee.getDepartmentEntity().getName());
        employeeDetailsDTO.setOrganization(employee.getOrganization().getName());
        employeeDetailsDTO.setStatus(employee.isStatus() ? "Active" : "Inactive");
        employeeDetailsDTO.setAction(employee.getUpdatedBy()); 
        return employeeDetailsDTO;
    }


}

