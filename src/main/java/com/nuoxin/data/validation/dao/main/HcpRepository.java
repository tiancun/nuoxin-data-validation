package com.nuoxin.data.validation.dao.main;

import com.nuoxin.data.validation.entity.main.Hcp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by fenggang on 1/8/18.
 *
 * @author fenggang
 * @date 1/8/18
 */
public interface HcpRepository extends JpaRepository<Hcp,Long>,JpaSpecificationExecutor<Hcp> {

    @Query(value = "select hcp.id,hcp.name,hcp.hci_id from hcp hcp join hci_alias hci on hci.hci_id=hcp.hci_id join hcp_social_info hsi on hsi.id=hcp.id where hci.alias=:hospital and hcp.name=:name and (hsi.dept=:depart1 or hsi.standard_dept=:depart1 or hsi.standard_subdept=:depart1 or hsi.dept=:depart or hsi.standard_dept=:depart or hsi.standard_subdept=:depart)",nativeQuery = true)
    List<Hcp> findByNameHospitalDepart(@Param("name") String name, @Param("hospital") String hospital, @Param("depart") String depart, @Param("depart1") String depart1);

    @Query(value = "select hcp.id,hcp.name,hcp.hci_id from hcp hcp join hci_alias hci on hci.hci_id=hcp.hci_id where hci.alias=:hospital and hcp.name=:name ",nativeQuery = true)
    List<Hcp> findByNameHospital(@Param("name") String name, @Param("hospital") String hospital);

    List<Hcp> findByName(String name);
}
