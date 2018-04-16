package com.nuoxin.data.validation.dao.eapp;

import com.nuoxin.data.validation.entity.eapp.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.persistence.PersistenceUnit;
import javax.print.Doc;

/**
 * Created by fenggang on 1/8/18.
 *
 * @author fenggang
 * @date 1/8/18
 */
//@PersistenceUnit(unitName = "eappPersistenceUnit")
public interface DoctorRepository extends JpaRepository<Doctor,Long>,JpaSpecificationExecutor<Doctor> {
}
