package com.nuoxin.data.validation.entity.eapp;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by fenggang on 1/10/18.
 *
 * @author fenggang
 * @date 1/10/18
 */
@PersistenceUnit(unitName="eappPersistenceUnit")
@Entity
@Table(name = "product_line")
public class ProductLine implements Serializable {

    private static final long serialVersionUID = -8261528791448067903L;
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prod_id")
    private Long id;

    @Column(name = "prod_name")
    private String name;

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
}
