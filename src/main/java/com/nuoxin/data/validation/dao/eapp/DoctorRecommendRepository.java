package com.nuoxin.data.validation.dao.eapp;

import com.nuoxin.data.validation.entity.eapp.DoctorRecommend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by fenggang on 1/14/18.
 *
 * @author fenggang
 * @date 1/14/18
 */
public interface DoctorRecommendRepository extends JpaRepository<DoctorRecommend,Long> {

    List<DoctorRecommend> findByDoctorId(Long doctorId);
}
