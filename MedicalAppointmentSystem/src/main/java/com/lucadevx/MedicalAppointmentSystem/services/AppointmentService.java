package com.lucadevx.MedicalAppointmentSystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucadevx.MedicalAppointmentSystem.dto.AppointmentDTO;
import com.lucadevx.MedicalAppointmentSystem.dto.PatientDTO;
import com.lucadevx.MedicalAppointmentSystem.exception.ObjectNotFoundException;
import com.lucadevx.MedicalAppointmentSystem.model.Appointment;
import com.lucadevx.MedicalAppointmentSystem.model.Patient;
import com.lucadevx.MedicalAppointmentSystem.model.enums.Status;
import com.lucadevx.MedicalAppointmentSystem.repository.AppointmentRepository;

@Service
public class AppointmentService {

	@Autowired
	private AppointmentRepository repository;
	
	@Autowired
	private PatientService patientService;
	//private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	
	public Appointment create(Appointment appointment) {
		
		
		return repository.save(appointment);
	}
	
	public Appointment findById(Long id) {
		
		
		
		return repository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Object not found"));
	}
	
	public List<Appointment> findAll(){
		return repository.findAll();
	}
	
	public Appointment update(Appointment appointment) {
		Appointment appointmentCurrent = findById(appointment.getId());
		
		appointmentCurrent.setAppointmentDateTime(appointment.getAppointmentDateTime());
		appointmentCurrent.setStatus(appointment.getStatus());
		appointmentCurrent.setDepartment(appointment.getDepartment());
		appointmentCurrent.setDoctor(appointment.getDoctor());
		appointmentCurrent.setPatient(appointment.getPatient());
		
		return repository.save(appointmentCurrent);
	}
	
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public Appointment parseToAppointment(AppointmentDTO appointmentDTO) {
		Patient patient = patientService.parseToPatient(appointmentDTO.patient());
		
		Appointment appointment = new Appointment();
		
		appointment.setId(appointmentDTO.id());
		appointment.setAppointmentDateTime(appointmentDTO.appointmentDateTime());
		//appointment.setAppointmentDateTime(LocalDateTime.parse(appointmentDTO.appointmentDateTime(), dateFormatter));
		appointment.setDoctor(appointmentDTO.doctor());
		appointment.setDepartment(appointmentDTO.department());
		appointment.setStatus(Status.valueOf(appointmentDTO.status().toUpperCase()));
		appointment.setPatient(patient);
	
		return appointment;
	}
	
	public AppointmentDTO parseToDTO(Appointment appointment) {
		//appointment.getAppointmentDateTime().format(dateFormatter)
		PatientDTO patientDTO = patientService.parseToDTO(appointment.getPatient());
		return new AppointmentDTO(
				appointment.getId(),
				appointment.getAppointmentDateTime(),
				appointment.getStatus().name(),
				patientDTO,
				appointment.getDepartment(),
				appointment.getDoctor()
				);
	}
}
