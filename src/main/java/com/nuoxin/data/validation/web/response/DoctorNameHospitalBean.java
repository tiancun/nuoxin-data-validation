package com.nuoxin.data.validation.web.response;

import java.io.Serializable;

/**
 * Created by fenggang on 18/2/2.
 *
 * @author fenggang
 * @date 18/2/2
 */
public class DoctorNameHospitalBean implements Serializable {

    private Long id;
    private String name;
    private String hospitalName;
    private String depart;
    private String mdmHospital;
    private String mdmDepart;
    private String json;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getMdmHospital() {
        return mdmHospital;
    }

    public void setMdmHospital(String mdmHospital) {
        this.mdmHospital = mdmHospital;
    }

    public String getMdmDepart() {
        return mdmDepart;
    }

    public void setMdmDepart(String mdmDepart) {
        this.mdmDepart = mdmDepart;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
