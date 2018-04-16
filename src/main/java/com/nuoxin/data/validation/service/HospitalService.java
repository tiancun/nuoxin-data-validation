package com.nuoxin.data.validation.service;

import com.nuoxin.data.validation.dao.eapp.HospitalRepository;
import com.nuoxin.data.validation.dao.main.HciAliasRepository;
import com.nuoxin.data.validation.entity.eapp.Hospital;
import com.nuoxin.data.validation.entity.main.HciAlias;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fenggang on 1/9/18.
 *
 * @author fenggang
 * @date 1/9/18
 */
@Service
public class HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;
    @Autowired
    private HciAliasRepository hciAliasRepository;

    public List<Hospital> checkAll(){
        return hospitalRepository.findAll();
    }

    /**
     * 检查科室
     */
    public void checkName(){
        List<Hospital> list = this.checkAll();
        for (int i = 0,leng=list.size(); i < leng; i++) {
            Hospital hospital = list.get(i);
            String name = hospital.getName();
            if(StringUtils.isNotBlank(name)){
                List<HciAlias> hciAliasList = hciAliasRepository.findByAlias(name);
                if(hciAliasList!=null && !hciAliasList.isEmpty()){

                }
            }
        }
    }
}
