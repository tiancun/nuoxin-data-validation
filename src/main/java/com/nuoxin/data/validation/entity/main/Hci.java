package com.nuoxin.data.validation.entity.main;

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
@PersistenceUnit(unitName="mainPersistenceUnit")
@Entity
@Table(name = "hci")
public class Hci extends IdEntity {

    private static final long serialVersionUID = 4808765771080154413L;
    @Column(name = "name")
    private String name;
    @Column(name = "medical_grade")
    private Integer medicalGrade;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMedicalGrade() {
        return medicalGrade;
    }

    public void setMedicalGrade(Integer medicalGrade) {
        this.medicalGrade = medicalGrade;
    }
}
