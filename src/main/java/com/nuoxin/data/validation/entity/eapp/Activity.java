package com.nuoxin.data.validation.entity.eapp;

import com.nuoxin.data.validation.entity.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PersistenceUnit;
import javax.persistence.Table;

/**
 * Created by fenggang on 1/13/18.
 *
 * @author fenggang
 * @date 1/13/18
 */
@PersistenceUnit(unitName="eappPersistenceUnit")
@Entity
@Table(name = "activity_info")
public class Activity extends IdEntity {
    private static final long serialVersionUID = -5877520702570426790L;

    @Column(name = "title")
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
