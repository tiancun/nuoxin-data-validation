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
@Table(name = "hcp_social_info")
public class HcpSocialInfo extends IdEntity{

    private static final long serialVersionUID = 2565115827064330746L;
    @Column(name = "department")
    private String department;
    @Column(name = "dept")
    private String dept;
    @Column(name = "title")
    private String title;
    @Column(name = "standard_dept")
    private String standardDept;
    @Column(name = "standard_subdept")
    private String standardSubdept;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStandardDept() {
        return standardDept;
    }

    public void setStandardDept(String standardDept) {
        this.standardDept = standardDept;
    }

    public String getStandardSubdept() {
        return standardSubdept;
    }

    public void setStandardSubdept(String standardSubdept) {
        this.standardSubdept = standardSubdept;
    }
}
