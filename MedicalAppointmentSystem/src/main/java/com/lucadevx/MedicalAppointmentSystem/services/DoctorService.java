package com.lucadevx.MedicalAppointmentSystem.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucadevx.MedicalAppointmentSystem.dto.DoctorDTO;
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
	public DoctorResponseDTO update(DoctorDTO doctorDTO) {
		
		
		Doctor doctorRepository = repository.findById(doctorDTO.id())
				.orElseThrow(()-> new ObjectNotFoundException("Object not found"));
		
		Department department = departmentRepository.findById(doctorDTO.department().id())
				.orElseThrow(()-> new ObjectNotFoundException("Object not found"));
		
		doctorRepository.setFirstName(doctorDTO.firstName());
		doctorRepository.setLastName(doctorDTO.lastName());
		doctorRepository.setEmail(doctorDTO.email());
		doctorRepository.setPhone(doctorDTO.phone());
		doctorRepository.setCrm(doctorDTO.crm());
		doctorRepository.setSpeciality(doctorDTO.speciality());
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
		doctor.setDepartment(doctorRequestDTO.department());
		doctor.setEmail(doctorRequestDTO.email());
		doctor.setCrm(doctorRequestDTO.crm());
		doctor.setSpeciality(doctorRequestDTO.speciality());
		
		return doctor;
	}
	
	public static Doctor parseToDoctor(DoctorDTO doctorDTO) {
		Doctor doctor = new Doctor();
		
		doctor.setFirstName(doctorDTO.firstName());
		doctor.setLastName(doctorDTO.lastName());
		doctor.setPhone(doctorDTO.phone());
		doctor.setEmail(doctorDTO.email());
		doctor.setCrm(doctorDTO.crm());
		doctor.setSpeciality(doctorDTO.speciality());
		
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
