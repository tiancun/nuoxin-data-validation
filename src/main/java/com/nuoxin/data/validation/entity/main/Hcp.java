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
@Table(name = "hcp")
public class Hcp extends IdEntity {

    private static final long serialVersionUID = -6196592487096664576L;
    @Column(name = "name")
    private String name;

    @Column(name = "hci_id")
    private Long hciId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getHciId() {
        return hciId;
    }

    public void setHciId(Long hciId) {
        this.hciId = hciId;
    }
}
