package com.nuoxin.data.validation.dao.main;

import com.nuoxin.data.validation.entity.main.Hcp;
import com.nuoxin.data.validation.entity.main.HcpSocialInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Map;

/**
 * Created by fenggang on 1/8/18.
 *
 * @author fenggang
 * @date 1/8/18
 */
public interface HcpSocialInfoRepository  extends JpaRepository<HcpSocialInfo,Long>,JpaSpecificationExecutor<HcpSocialInfo> {

    @Query(value = "select hi.dept,hi.department,hi.standard_dept,hi.standard_subdept from hcp_social_info hi where hi.id=:id",nativeQuery = true)
    Map<String,Object> findByHcpId(@Param("id") Long id);

}
