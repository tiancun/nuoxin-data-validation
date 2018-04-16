package com.nuoxin.data.validation.util.excel;

import java.io.Serializable;

/**
 * Created by fenggang on 9/20/17.
 */
public class DoctorExcel implements Serializable {

    private static final long serialVersionUID = -8101729441861723175L;

    @Excel(name = "医生Id",width=100)
    private String doctorId;

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }
}
