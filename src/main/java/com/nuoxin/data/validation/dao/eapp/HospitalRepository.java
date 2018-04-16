package com.nuoxin.data.validation.dao.eapp;

import com.nuoxin.data.validation.entity.eapp.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by fenggang on 1/9/18.
 *
 * @author fenggang
 * @date 1/9/18
 */
public interface HospitalRepository extends JpaRepository<Hospital,Long>,JpaSpecificationExecutor<Hospital> {
}
