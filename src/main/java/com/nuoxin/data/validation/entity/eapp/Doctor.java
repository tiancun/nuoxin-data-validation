package com.nuoxin.data.validation.entity.eapp;

import com.nuoxin.data.validation.entity.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PersistenceUnit;
import javax.persistence.Table;

/**
 * Created by fenggang on 1/8/18.
 *
 * @author fenggang
 * @date 1/8/18
 */
@PersistenceUnit(unitName="eappPersistenceUnit")
@Entity
@Table(name = "doctor_0202")
public class Doctor extends IdEntity {

    private static final long serialVersionUID = 2264326975261946739L;

    @Column(name = "name")
    private String name;

    @Column(name = "hospital_id")
    private Long hospitalId;
    @Column(name = "hospital_name")
    private String hospitalName;
    @Column(name = "depart")
    private String depart;
    @Column(name = "depart_check")
    private String departcheck;
    @Column(name = "subdivision_depart")
    private String subdivisionDepart;
    @Column(name = "telephone")
    private String mobile;

    @Column(name="email")
    private String email;

    @Column(name="provice")
    private String province;
    @Column(name="city")
    private String city;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Long hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getSubdivisionDepart() {
        return subdivisionDepart;
    }

    public void setSubdivisionDepart(String subdivisionDepart) {
        this.subdivisionDepart = subdivisionDepart;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDepartcheck() {
        return departcheck;
    }

    public void setDepartcheck(String departcheck) {
        this.departcheck = departcheck;
    }
}
