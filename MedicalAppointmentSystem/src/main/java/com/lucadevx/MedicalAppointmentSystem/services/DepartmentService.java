package com.lucadevx.MedicalAppointmentSystem.services;

import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucadevx.MedicalAppointmentSystem.dto.DepartmentDTO;
import com.lucadevx.MedicalAppointmentSystem.dto.request.DepartmentRequestDTO;
import com.lucadevx.MedicalAppointmentSystem.dto.response.AppointmentResponseDTO;
import com.lucadevx.MedicalAppointmentSystem.dto.response.DepartmentResponseDTO;
import com.lucadevx.MedicalAppointmentSystem.dto.response.DoctorResponseDTO;
import com.lucadevx.MedicalAppointmentSystem.dto.response.PatientResponseDTO;
import com.lucadevx.MedicalAppointmentSystem.exception.DatabaseException;
import com.lucadevx.MedicalAppointmentSystem.exception.ObjectNotFoundException;
import com.lucadevx.MedicalAppointmentSystem.model.Appointment;
import com.lucadevx.MedicalAppointmentSystem.model.Department;
import com.lucadevx.MedicalAppointmentSystem.repository.DepartmentRepository;

@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentRepository repository;
	
	private final static DateTimeFormatter formatter_local_date_time = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	private final static DateTimeFormatter formatter_local_date = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public DepartmentResponseDTO create(DepartmentRequestDTO departmentRequestDTO) {
		Department department = parseToDepartment(departmentRequestDTO);
		
		return parseToDTO(repository.save(department));
	}
	
	public DepartmentResponseDTO findById(Long id) {
		
		return parseToDTO(repository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Object not found")));
	}
	
	public List<DepartmentResponseDTO> findAll(){
		
		List<Department> departments = repository.findAll();
		List<DepartmentResponseDTO> departmentsDTO = departments.stream()
				.map(department -> parseToDTO(department)).collect(Collectors.toList());
	
		return departmentsDTO;
	}
	
	public DepartmentResponseDTO update(DepartmentDTO departmentDTO) {
		
		Department departmentRepository = repository.findById(departmentDTO.id())
				.orElseThrow(()-> new ObjectNotFoundException("Object not found"));
				
		departmentRepository.setDepartmentName(departmentDTO.departmentName());
		
		return parseToDTO(repository.save(departmentRepository));
	}
	
	
	public void delete(Long id) {
		Department departmentRepository = repository.findById(id)
				.orElseThrow(()-> new ObjectNotFoundException("Object not found"));
		
		if(!departmentRepository.getAppointments().isEmpty()) {
			throw new DatabaseException();
		}
		repository.deleteById(id);
	}
	
	public static Department parseToDepartment(DepartmentRequestDTO departmentRequestDTO) {
		Department department = new Department();
		
		department.setDepartmentName(departmentRequestDTO.departmentName());
		
		return department;
	}
	
	public static Department parseToDepartment(DepartmentDTO departmentDTO) {
		Department department = new Department();
		
		department.setDepartmentName(departmentDTO.departmentName());
		department.setId(departmentDTO.id());
		
		return department;
	}
	
	public static DepartmentResponseDTO parseToDTO(Department department) {
		Set<DoctorResponseDTO> doctorsDTO = department.getDoctors().stream()
				.map((doctor) -> DoctorService.parseToDTO(doctor))
				.collect(Collectors.toSet());
		
		Set<AppointmentResponseDTO> appointmentsDTO = new HashSet<>();
		
		for(Appointment appointment : department.getAppointments()) {
			PatientResponseDTO patientResponseDTO = new PatientResponseDTO(
					appointment.getPatient().getId(),
					appointment.getPatient().getFirstName(),
					appointment.getPatient().getLastName(),
					appointment.getPatient().getEmail(),
					appointment.getPatient().getPhone(),
					appointment.getPatient().getBirthDate().format(formatter_local_date));
			
			appointmentsDTO.add(
					new AppointmentResponseDTO(
						appointment.getId(),
						appointment.getAppointmentDateTime().format(formatter_local_date_time),
						appointment.getStatus().name(),
						patientResponseDTO,
					DoctorService.parseToDTO(appointment.getDoctor())));
		}
		
		
		
		return new DepartmentResponseDTO(
				department.getId(),
				department.getDepartmentName(),
				appointmentsDTO,
				doctorsDTO);
		

	}

}
