package com.nuoxin.data.validation.entity.eapp;

import com.nuoxin.data.validation.entity.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PersistenceUnit;
import javax.persistence.Table;

/**
 * Created by fenggang on 1/9/18.
 *
 * @author fenggang
 * @date 1/9/18
 */
@PersistenceUnit(unitName="eappPersistenceUnit")
@Entity
@Table(name = "hospital")
public class Hospital extends IdEntity {
    private static final long serialVersionUID = -8396704901138000686L;

    @Column(name = "name")
    private String name;
    @Column(name = "province")
    private String province;
    @Column(name = "city")
    private String city;
    @Column(name = "level")
    private String level;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
