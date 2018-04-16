package com.nuoxin.data.validation.entity.eapp;

import com.nuoxin.data.validation.entity.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PersistenceUnit;
import javax.persistence.Table;

/**
 * Created by fenggang on 1/14/18.
 *
 * @author fenggang
 * @date 1/14/18
 */
@PersistenceUnit(unitName="eappPersistenceUnit")
@Entity
@Table(name = "doctor_recommend")
public class DoctorRecommend extends IdEntity {

    private static final long serialVersionUID = 7002875721577415771L;
    @Column(name = "doctor_id")
    private Long doctorId;
    @Column(name = "content_id")
    private Long contentId;
    @Column(name = "type")
    private Integer type;
    @Column(name = "source")
    private Double source;

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Double getSource() {
        return source;
    }

    public void setSource(Double source) {
        this.source = source;
    }
}
