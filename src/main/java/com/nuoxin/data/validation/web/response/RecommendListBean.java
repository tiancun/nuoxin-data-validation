package com.nuoxin.data.validation.web.response;

import java.io.Serializable;

/**
 * Created by fenggang on 1/14/18.
 *
 * @author fenggang
 * @date 1/14/18
 */
public class RecommendListBean implements Serializable {

    private static final long serialVersionUID = 2228088891868257245L;
    private Long doctorId;
    private String doctorName;
    private String title;
    private String source;
    private String typeName;

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
