package com.company.repository;

import com.company.entity.Doctor;
import com.company.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {

    List<Doctor> findAllByActive(Integer active);
    List<Doctor> findDoctorListByPositionAndActive(Position position, Integer active);
    Doctor findDoctorByIdAndActive(Long doctorId,Integer active);

}
