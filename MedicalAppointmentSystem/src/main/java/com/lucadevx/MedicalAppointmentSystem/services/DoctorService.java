package com.lucadevx.MedicalAppointmentSystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucadevx.MedicalAppointmentSystem.model.Doctor;
import com.lucadevx.MedicalAppointmentSystem.repository.DoctorRepository;

@Service
public class DoctorService {
	
	@Autowired
	private DoctorRepository repository;
	
	public Doctor create(Doctor doctor) {
		
		return repository.save(doctor);
	}
	
	public Doctor findById(Long id) {
		
		return repository.findById(id).orElseThrow(()-> new IllegalArgumentException("Object not found"));
	}
	
	public List<Doctor> findAll(){
		return repository.findAll();
	}
	
	public Doctor update(Doctor doctor) {
		
		Doctor doctorRepository = findById(doctor.getId());
		
		doctorRepository.setFirstName(doctor.getFirstName());
		doctorRepository.setLastName(doctor.getLastName());
		doctorRepository.setEmail(doctor.getEmail());
		doctorRepository.setPhone(doctor.getPhone());
		doctorRepository.setSpeciality(doctor.getSpeciality());
		doctorRepository.setDepartment(doctor.getDepartment());
		return repository.save(doctorRepository);
	}
	
	
	public void delete(Long id) {
		repository.deleteById(id);
	}

}
