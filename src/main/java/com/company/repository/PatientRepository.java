package com.company.repository;

import com.company.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
List<Patient> findAllByActive(Integer active);
Patient findPatientByIdAndActive(Long id,Integer active );

}
