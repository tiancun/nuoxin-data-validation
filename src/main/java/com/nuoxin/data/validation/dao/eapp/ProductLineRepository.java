package com.nuoxin.data.validation.dao.eapp;

import com.nuoxin.data.validation.entity.eapp.DrugUserDoctor;
import com.nuoxin.data.validation.entity.eapp.ProductLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by fenggang on 1/10/18.
 *
 * @author fenggang
 * @date 1/10/18
 */
public interface ProductLineRepository extends JpaRepository<ProductLine,Long>,JpaSpecificationExecutor<ProductLine> {

}
