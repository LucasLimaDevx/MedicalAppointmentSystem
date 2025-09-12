package com.lucadevx.MedicalAppointmentSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucadevx.MedicalAppointmentSystem.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>{

}
