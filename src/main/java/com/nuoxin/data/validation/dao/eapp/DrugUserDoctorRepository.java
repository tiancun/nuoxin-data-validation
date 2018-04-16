package com.nuoxin.data.validation.dao.eapp;

import com.nuoxin.data.validation.entity.eapp.Doctor;
import com.nuoxin.data.validation.entity.eapp.DrugUserDoctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by fenggang on 1/10/18.
 *
 * @author fenggang
 * @date 1/10/18
 */
public interface DrugUserDoctorRepository extends JpaRepository<DrugUserDoctor,Long>,JpaSpecificationExecutor<DrugUserDoctor> {

    List<DrugUserDoctor> findByDoctorId(Long doctorId);
}
