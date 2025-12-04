package com.lucadevx.MedicalAppointmentSystem.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucadevx.MedicalAppointmentSystem.dto.AppointmentDTO;
import com.lucadevx.MedicalAppointmentSystem.dto.DepartmentDTO;
import com.lucadevx.MedicalAppointmentSystem.dto.PatientDTO;
import com.lucadevx.MedicalAppointmentSystem.exception.DatabaseException;
import com.lucadevx.MedicalAppointmentSystem.exception.ObjectNotFoundException;
import com.lucadevx.MedicalAppointmentSystem.model.Appointment;
import com.lucadevx.MedicalAppointmentSystem.model.Department;
import com.lucadevx.MedicalAppointmentSystem.repository.DepartmentRepository;

@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentRepository repository;
	
	public Department create(Department department) {
		
		return repository.save(department);
	}
	
	public Department findById(Long id) {
		
		return repository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Object not found"));
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
		Department department = findById(id);
		
		if(!department.getAppointments().isEmpty()) {
			throw new DatabaseException();
		}
		repository.deleteById(id);
	}
	
	public Department parseToDepartment(DepartmentDTO departmentDTO) {
		Department department = new Department();
		department.setId(departmentDTO.id());
		department.setDepartmentName(departmentDTO.departmentName());
		
		return department;
	}
	public DepartmentDTO parseToDTO(Department department) {
		
		Set<AppointmentDTO> appointmentsDTO = new HashSet<>();
		
		for(Appointment appointment : department.getAppointments()) {
			PatientDTO patientDTO = new PatientDTO(
					appointment.getPatient().getId(),
					appointment.getPatient().getFirstName(),
					appointment.getPatient().getLastName(),
					appointment.getPatient().getEmail(),
					appointment.getPatient().getPhone(),
					appointment.getPatient().getBirthDate(),
					appointment.getPatient().getAppointments());
			
			appointmentsDTO.add(
					new AppointmentDTO(
					appointment.getId(),
					appointment.getAppointmentDateTime(),
					appointment.getStatus().name(),
					patientDTO,
					appointment.getDepartment(),
					appointment.getDoctor()));
		}
		
		return new DepartmentDTO(
				department.getId(),
				department.getDepartmentName(),
				appointmentsDTO,
				department.getDoctors()
			);
		

	}

}
