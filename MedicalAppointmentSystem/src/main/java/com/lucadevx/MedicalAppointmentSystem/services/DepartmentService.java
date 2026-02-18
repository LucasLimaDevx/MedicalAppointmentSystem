package com.lucadevx.MedicalAppointmentSystem.services;

import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.lucadevx.MedicalAppointmentSystem.repository.PatientRepository;

@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentRepository repository;
	
	private final static DateTimeFormatter formatter_local_date_time = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	private final static Logger logger = LoggerFactory.getLogger(DepartmentService.class.getName());
	
	public DepartmentResponseDTO create(DepartmentRequestDTO departmentRequestDTO) {
		logger.info("Starting the service's create method.");
		Department department = parseToDepartment(departmentRequestDTO);
		
		DepartmentResponseDTO departmentResponseDTO = parseToDTO(repository.save(department));
		
		logger.info("Department saved.");
		logger.info("Returning departmentResponseDTO.");
		return departmentResponseDTO ;
	}
	
	public DepartmentResponseDTO findById(Long id) {
		logger.info("Starting the service's findById method.");
		logger.debug("Fetching department by Id from database.");
		DepartmentResponseDTO departmentResponseDTO = parseToDTO(repository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Object not found")));

		logger.info("Returning DepartmentResponseDTO.");
		return departmentResponseDTO;
	}
	
	public List<DepartmentResponseDTO> findAll(){
		logger.info("Starting the service's findAll method.");
		
		logger.debug("Fetching all departments from database.");
		List<Department> departments = repository.findAll();
		
		logger.debug("Parsing all departments to departmentsResponseDTO.");
		List<DepartmentResponseDTO> departmentsDTO = departments.stream()
				.map(department -> parseToDTO(department)).collect(Collectors.toList());
		
		logger.info("Returning departmentsDTO.");
		return departmentsDTO;
	}
	
	public DepartmentResponseDTO update(DepartmentRequestDTO DepartmentResponseDTO, Long id) {
		logger.info("Starting the service's update method.");
		
		logger.debug("Fetching department by Id from database.");
		Department departmentRepository = repository.findById(id)
				.orElseThrow(()-> new ObjectNotFoundException("Object not found"));
				
		logger.info("Updating department object.");
		departmentRepository.setDepartmentName(DepartmentResponseDTO.departmentName());
		DepartmentResponseDTO departmentResponseDTO = parseToDTO(repository.save(departmentRepository));
		
		logger.info("Updated and Saved");
		logger.info("Returning departmentResponseDTO.");
		return departmentResponseDTO;
	}
	
	
	public void delete(Long id) {
		logger.info("Starting the service's delete method.");
		
		logger.debug("Fetching doctor by Id from database.");
		Department departmentRepository = repository.findById(id)
				.orElseThrow(()-> new ObjectNotFoundException("Object not found"));
		
		logger.debug("Checking if the appointment list is empty.");
		if(!departmentRepository.getAppointments().isEmpty()) {
			throw new DatabaseException();
		}
		
		
		repository.deleteById(id);
		logger.info("The department was deleted.");
	}
	
	public static Department parseToDepartment(DepartmentRequestDTO departmentRequestDTO) {
		logger.debug("Parsing departmentRequestDTO to department object.");

		Department department = new Department();
		
		department.setDepartmentName(departmentRequestDTO.departmentName());
		
		logger.debug("The departmentRequestDTO was converted to department object.");
		return department;
	}
	
	public static DepartmentResponseDTO parseToDTO(Department department) {
		logger.debug("Parsing department object to departmentResponseDTO.");
		Set<DoctorResponseDTO> doctorsDTO = department.getDoctors().stream()
				.map((doctor) -> DoctorService.parseToDTO(doctor))
				.collect(Collectors.toSet());
		
		Set<AppointmentResponseDTO> appointmentsDTO = new HashSet<>();
		
		for(Appointment appointment : department.getAppointments()) {
			/*logger.debug("Parsing department object to DepartmentResponseDTO.");
			PatientResponseDTO patientResponseDTO = new PatientResponseDTO(
					appointment.getPatient().getId(),
					appointment.getPatient().getFirstName(),
					appointment.getPatient().getLastName(),
					appointment.getPatient().getEmail(),
					appointment.getPatient().getPhone(),
					appointment.getPatient().getBirthDate().format(formatter_local_date));
			*/
			PatientResponseDTO patientResponseDTO = PatientService.parseToDTO(appointment.getPatient());
			appointmentsDTO.add(
					new AppointmentResponseDTO(
						appointment.getId(),
						appointment.getAppointmentDateTime().format(formatter_local_date_time),
						appointment.getStatus().name(),
						patientResponseDTO,
					DoctorService.parseToDTO(appointment.getDoctor())));
		}
		
		
		
		DepartmentResponseDTO departmentResponseDTO =  new DepartmentResponseDTO(
				department.getId(),
				department.getDepartmentName(),
				appointmentsDTO,
				doctorsDTO);
		
		logger.debug("The department object was converted to departmentResponseDTO.");
		return departmentResponseDTO;
		
		

	}

}
