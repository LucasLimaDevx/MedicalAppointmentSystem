package com.lucadevx.MedicalAppointmentSystem.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucadevx.MedicalAppointmentSystem.dto.DepartmentDTO;
import com.lucadevx.MedicalAppointmentSystem.model.Department;
import com.lucadevx.MedicalAppointmentSystem.repository.DepartmentRepository;

@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentRepository repository;
	
	@Autowired
	private AppointmentService appointmentService;
	
	public Department create(Department department) {
		
		return repository.save(department);
	}
	
	public Department findById(Long id) {
		
		return repository.findById(id).orElseThrow(()-> new IllegalArgumentException("Object not found"));
	}
	
	public List<Department> findAll(){
		
		return repository.findAll();
	}
	
	public Department update(Department department) {
		
		Department departmentRepository = findById(department.getId());
		
		departmentRepository.setDepartmentName(department.getDepartmentName());
		
		return repository.save(departmentRepository);
	}
	
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public Department parseToDepartment(DepartmentDTO departmentDTO) {
		Department department = new Department();
		department.setId(departmentDTO.id());
		department.setDepartmentName(departmentDTO.departmentName());
		
		return department;
	}
	public DepartmentDTO parseToDTO(Department department) {
		
		return new DepartmentDTO(
				department.getId(),
				department.getDepartmentName(),
				department.getAppointments().stream().map(appointment -> appointmentService.parseToDTO(appointment)).collect(Collectors.toSet()),
				department.getDoctors()
			);
		

	}

}
