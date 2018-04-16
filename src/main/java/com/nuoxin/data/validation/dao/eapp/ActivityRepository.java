package com.nuoxin.data.validation.dao.eapp;

import com.nuoxin.data.validation.entity.eapp.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by fenggang on 1/13/18.
 *
 * @author fenggang
 * @date 1/13/18
 */
public interface ActivityRepository extends JpaRepository<Activity,Long> {
}
