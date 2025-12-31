package com.lucadevx.MedicalAppointmentSystem.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucadevx.MedicalAppointmentSystem.dto.request.DoctorRequestDTO;
import com.lucadevx.MedicalAppointmentSystem.dto.response.DoctorResponseDTO;
import com.lucadevx.MedicalAppointmentSystem.exception.DatabaseException;
import com.lucadevx.MedicalAppointmentSystem.exception.ObjectNotFoundException;
import com.lucadevx.MedicalAppointmentSystem.model.Department;
import com.lucadevx.MedicalAppointmentSystem.model.Doctor;
import com.lucadevx.MedicalAppointmentSystem.repository.DepartmentRepository;
import com.lucadevx.MedicalAppointmentSystem.repository.DoctorRepository;

import jakarta.transaction.Transactional;

@Service
public class DoctorService {
	
	@Autowired
	private DoctorRepository repository;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	public DoctorResponseDTO create(DoctorRequestDTO doctorRequestDTO) {
		Doctor doctor = parseToDoctor(doctorRequestDTO);
		Department department = departmentRepository.findById(doctorRequestDTO.departmentId())
				.orElseThrow(()-> new ObjectNotFoundException("Object not found"));
		
		doctor.setDepartment(department);
		
		return parseToDTO(repository.save(doctor));
	}
	
	public DoctorResponseDTO findById(Long id) {
		
		return parseToDTO(repository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Object not found")));
	}
	
	public List<DoctorResponseDTO> findAll(){
		List<Doctor> doctors = repository.findAll();
		List<DoctorResponseDTO> doctorsResponseDTO = doctors.stream()
				.map(doctor -> parseToDTO(doctor))
				.collect(Collectors.toList());
		
		return doctorsResponseDTO;
		
	}
	
	@Transactional
	public DoctorResponseDTO update(DoctorRequestDTO doctorRequestDTO, Long id) {
		
		
		Doctor doctorRepository = repository.findById(id)
				.orElseThrow(()-> new ObjectNotFoundException("Object not found"));
		
		Department department = departmentRepository.findById(doctorRequestDTO.departmentId())
				.orElseThrow(()-> new ObjectNotFoundException("Object not found"));
		
		doctorRepository.setFirstName(doctorRequestDTO.firstName());
		doctorRepository.setLastName(doctorRequestDTO.lastName());
		doctorRepository.setEmail(doctorRequestDTO.email());
		doctorRepository.setPhone(doctorRequestDTO.phone());
		doctorRepository.setCrm(doctorRequestDTO.crm());
		doctorRepository.setSpeciality(doctorRequestDTO.speciality());
		doctorRepository.setDepartment(department);
		
		return parseToDTO(repository.save(doctorRepository));
	}
	
	
	public void delete(Long id) {
		Doctor doctor = repository.findById(id)
				.orElseThrow(()-> new ObjectNotFoundException("Object not found"));
	
		if(!doctor.getAppointments().isEmpty()) {
			throw new DatabaseException();
		}
		
		repository.deleteById(id);
	}
	
	public static Doctor parseToDoctor(DoctorRequestDTO doctorRequestDTO) {
		Doctor doctor = new Doctor();
		
		doctor.setFirstName(doctorRequestDTO.firstName());
		doctor.setLastName(doctorRequestDTO.lastName());
		doctor.setPhone(doctorRequestDTO.phone());
		doctor.setEmail(doctorRequestDTO.email());
		doctor.setCrm(doctorRequestDTO.crm());
		doctor.setSpeciality(doctorRequestDTO.speciality());
		
		return doctor;
	}
	
	public static DoctorResponseDTO parseToDTO(Doctor doctor) {
		
		return new DoctorResponseDTO(
				doctor.getId(),
				doctor.getFirstName(),
				doctor.getLastName(),
				doctor.getPhone(),
				doctor.getEmail(),
				doctor.getCrm(),
				doctor.getSpeciality()
				
			);
	}
}
