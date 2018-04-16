package com.nuoxin.data.validation.dao.main;

import com.nuoxin.data.validation.entity.main.Hci;
import com.nuoxin.data.validation.entity.main.HciAlias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by fenggang on 1/8/18.
 *
 * @author fenggang
 * @date 1/8/18
 */
public interface HciRepository extends JpaRepository<Hci,Long>,JpaSpecificationExecutor<Hci> {

}
