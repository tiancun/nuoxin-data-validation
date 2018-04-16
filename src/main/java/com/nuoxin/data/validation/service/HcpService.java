package com.nuoxin.data.validation.service;

import com.nuoxin.data.validation.dao.main.HcpRepository;
import com.nuoxin.data.validation.entity.main.Hcp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fenggang on 1/8/18.
 *
 * @author fenggang
 * @date 1/8/18
 */
@Service
public class HcpService {

    @Autowired
    private HcpRepository hcpRepository;

    public Hcp findOne(){
        return hcpRepository.findOne(1l);
    }

    public Integer getName(String name){
        List<Hcp> list= hcpRepository.findByName(name);
        return list==null ? 0:list.size();

    }

    public List<Hcp> findBy(String name, String hospitalName){

        return hcpRepository.findByNameHospital(name,hospitalName);
    }

    public List<Hcp> findBy(String name,String hospitalName,String depart,String depart1){

        return hcpRepository.findByNameHospitalDepart(name, hospitalName, depart, depart1);
    }
}
