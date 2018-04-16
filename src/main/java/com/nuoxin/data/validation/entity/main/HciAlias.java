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
@Table(name = "hci_alias")
public class HciAlias extends IdEntity {

    private static final long serialVersionUID = -1696757564757530622L;
    @Column(name ="alias")
    private String alias;
    @Column(name ="hci_id")
    private Long hci_id;

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Long getHci_id() {
        return hci_id;
    }

    public void setHci_id(Long hci_id) {
        this.hci_id = hci_id;
    }
}
