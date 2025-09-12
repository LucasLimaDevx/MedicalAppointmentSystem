package com.lucadevx.MedicalAppointmentSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucadevx.MedicalAppointmentSystem.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{

}
