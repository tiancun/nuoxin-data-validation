package com.nuoxin.data.validation.entity.eapp;

import com.nuoxin.data.validation.entity.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PersistenceUnit;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by fenggang on 1/10/18.
 *
 * @author fenggang
 * @date 1/10/18
 */
@PersistenceUnit(unitName="eappPersistenceUnit")
@Entity
@Table(name = "drug_user_doctor")
public class DrugUserDoctor extends IdEntity {
    private static final long serialVersionUID = -2002307078110125315L;

    @Column(name = "doctor_id")
    private Long doctorId;
    @Column(name = "drug_user_id")
    private Long drugUserId;
    @Column(name = "drug_user_name")
    private String drugUserName;
    @Column(name = "prod_id")
    private Long prodId;

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Long getDrugUserId() {
        return drugUserId;
    }

    public void setDrugUserId(Long drugUserId) {
        this.drugUserId = drugUserId;
    }

    public Long getProdId() {
        return prodId;
    }

    public void setProdId(Long prodId) {
        this.prodId = prodId;
    }

    public String getDrugUserName() {
        return drugUserName;
    }

    public void setDrugUserName(String drugUserName) {
        this.drugUserName = drugUserName;
    }
}
