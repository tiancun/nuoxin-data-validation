package com.nuoxin.data.validation.service;

import bean.Departments;
import com.alibaba.fastjson.JSON;
import com.nuoxin.data.validation.dao.eapp.DoctorRecommendRepository;
import com.nuoxin.data.validation.dao.eapp.DoctorRepository;
import com.nuoxin.data.validation.dao.eapp.DrugUserDoctorRepository;
import com.nuoxin.data.validation.dao.eapp.ProductLineRepository;
import com.nuoxin.data.validation.dao.main.HcpSocialInfoRepository;
import com.nuoxin.data.validation.entity.eapp.*;
import com.nuoxin.data.validation.entity.main.Hcp;
import com.nuoxin.data.validation.entity.main.HcpSocialInfo;
import com.nuoxin.data.validation.util.CheckUtil;
import com.nuoxin.data.validation.util.excel.DoctorExcel;
import com.nuoxin.data.validation.web.response.DoctorNameHospitalBean;
import com.nuoxin.data.validation.web.response.RecommendListBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fenggang on 1/8/18.
 *
 * @author fenggang
 * @date 1/8/18
 */
@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private MatchDeptService matchDeptService;
    @Autowired
    private HcpService hcpService;
    @Autowired
    private DrugUserDoctorRepository drugUserDoctorRepository;
    @Autowired
    private ProductLineRepository productLineRepository;

    @Autowired
    private HcpSocialInfoRepository hcpSocialInfoRepository;

    @Autowired
    private DoctorRecommendRepository doctorRecommendRepository;
    @Autowired
    private ActivityService activityService;


    public Doctor findOne(){
        return doctorRepository.findOne(3724l);
    }

    public void checkDept(){
        List<Doctor> doctors = this.checkAll();
        for (Doctor doctor:doctors) {
            if(StringUtils.isEmpty(doctor.getSubdivisionDepart())){
                continue;
            }
            Departments departments = matchDeptService.find(doctor.getId().intValue(),doctor.getSubdivisionDepart());
            doctor.setSubdivisionDepart(departments.getSon());
        }
    }

    public List<Doctor> checkAll(){
        return doctorRepository.findAll();
    }

    public Map<String,ArrayList<Map<String,Object>>> checkEappMain(){
        Map<String,ArrayList<Map<String,Object>>> result = new HashMap<>();
        ArrayList<Map<String,Object>> ok = new ArrayList<>();
        ArrayList<Map<String,Object>> no = new ArrayList<>();
        List<Doctor> doctors = this.checkAll();
        for (Doctor doctor:doctors) {
            List<Hcp> hcpList = hcpService.findBy(doctor.getName(),doctor.getHospitalName(),doctor.getDepart(),doctor.getSubdivisionDepart());
            Map<String,Object> map = new HashMap<>();
            map.put("id",doctor.getId());
            map.put("name",doctor.getName());
            map.put("hospital",doctor.getHospitalName());
            map.put("depart",doctor.getDepart());
            map.put("mobile",doctor.getMobile());
            if(hcpList==null || hcpList.isEmpty()){
                no.add(map);
            }else{
                ok.add(map);
            }
        }
        result.put("ok",ok);
        result.put("no",no);
        return result;
    }

    public ArrayList<Map<String,Object>> checkNameEmailMobile(){
        ArrayList<Map<String,Object>> result = new ArrayList<>();
        List<Doctor> doctors = this.checkAll();
        for (Doctor doctor:doctors) {
            boolean mobile = CheckUtil.isPhone(doctor.getMobile());
            boolean email = CheckUtil.isEmail(doctor.getEmail());
            boolean mobileNo = CheckUtil.isMobileNO(doctor.getMobile());
            boolean name = CheckUtil.isLetterDigitOrChinese(CheckUtil.replaceBlank(doctor.getName()));
            if(!name){
                Map<String,Object> map = new HashMap<>();
                map.put("id",doctor.getId());
                map.put("name",doctor.getName());
                map.put("hospital",doctor.getHospitalName());
                map.put("depart",doctor.getDepart());
                map.put("mobile",doctor.getMobile());
                map.put("email",doctor.getEmail());

                StringBuffer drugUserName = new StringBuffer("");
                StringBuffer productName = new StringBuffer("");

                List<DrugUserDoctor> drugUserDoctors = drugUserDoctorRepository.findByDoctorId(doctor.getId());
                if(drugUserDoctors!=null && !drugUserDoctors.isEmpty()){
                    for (DrugUserDoctor drugUserDoctor:drugUserDoctors) {
                        ProductLine productLine = productLineRepository.findOne(drugUserDoctor.getProdId());
                        drugUserName.append(drugUserDoctor.getDrugUserName()).append(",");
                        productName.append(productLine.getName()).append(",");
                    }
                }
                map.put("drugUserName",drugUserName);
                map.put("productName",productName);
                result.add(map);
            }
        }
        return result;
    }

    public Doctor save(Doctor doctor){
        return doctorRepository.saveAndFlush(doctor);
    }

    public List<RecommendListBean> recommend(List<DoctorExcel> list){
        List<RecommendListBean> responseList = new ArrayList<>();
        if(list!=null && !list.isEmpty()){
            for (DoctorExcel excel:list) {
                if(StringUtils.isBlank(excel.getDoctorId())){
                    continue;
                }
                if(excel.getDoctorId().indexOf(".")>0){
                    excel.setDoctorId(Double.valueOf(excel.getDoctorId()).intValue()+"");
                }
                Doctor doctor = doctorRepository.findOne(Long.valueOf(excel.getDoctorId()));
                if(doctor==null){
                    continue;
                }
                List<DoctorRecommend> doctorRecommends = doctorRecommendRepository.findByDoctorId(doctor.getId());
                if(doctorRecommends!=null && !doctorRecommends.isEmpty()){
                    for (DoctorRecommend doctorRecommend:doctorRecommends) {
                        Activity activity = activityService.findById(doctorRecommend.getContentId());
                        if(activity!=null){
                            RecommendListBean recommendListBean = new RecommendListBean();
                            recommendListBean.setDoctorId(doctor.getId());
                            recommendListBean.setDoctorName(doctor.getName());
                            recommendListBean.setTitle(activity.getTitle());
                            recommendListBean.setSource(new BigDecimal(doctorRecommend.getSource()).setScale(4,BigDecimal.ROUND_HALF_UP)+"");
                            responseList.add(recommendListBean);
//                            recommendListBean.
                        }
                    }
                }
            }
        }
        return responseList;
    }

    public ArrayList<Map<String,Object>> nameHospital() {
        Integer count = 0;
        ArrayList<Map<String,Object>> list = new ArrayList<>();
        List<Doctor> doctors = this.checkAll();
        for (Doctor doctor:doctors) {
            Map<String,Object> bean = new HashMap<String,Object>();
            bean.put("id",doctor.getId());
            bean.put("depart",doctor.getDepartcheck());
            bean.put("hospital",doctor.getHospitalName());
            bean.put("name",doctor.getName());
            bean.put("departCheck",doctor.getDepart());
            List<Hcp> hcpList = hcpService.findBy(doctor.getName(), doctor.getHospitalName());
            if(hcpList!=null && !hcpList.isEmpty()){
                List<String> depts = new ArrayList<>();
//                for (Hcp hcp:hcpList) {
//                    Map<String,Object> map = hcpSocialInfoRepository.findByHcpId(hcpList.get(0).getHciId());
//                    depts.add(map.get("department")+"="+map.get("dept")+map.get("standard_dept")+map.get("standard_subdept"));
//                }
                List<Hcp> hcpList2 = hcpService.findBy(doctor.getName(), doctor.getHospitalName(),doctor.getDepart(),doctor.getSubdivisionDepart());
                if(hcpList2!=null && !hcpList2.isEmpty()){
                    bean.put("xx","0");
                    HcpSocialInfo info = hcpSocialInfoRepository.findOne(hcpList2.get(0).getHciId());
                    if(info!=null){
                        //bean.put("xx","1");
                        bean.put("check",info.getDepartment()+"="+info.getDept()+"="+info.getStandardDept()+"="+info.getStandardSubdept());
//                        if(StringUtils.isNotBlank(doctor.getDepart())){
//                            if(bean.get("check").toString().indexOf(doctor.getDepart())>=0){
//                                bean.put("xx","0");
//                            }else if(bean.get("check").toString().indexOf(doctor.getSubdivisionDepart()+"")>=0){
//                                bean.put("xx","0");
//                            }else {
//                                bean.put("xx","1");
//                            }
//                        }else{
//                            bean.put("xx","1");
//                        }
                    }
                }else{
                    HcpSocialInfo info = hcpSocialInfoRepository.findOne(hcpList.get(0).getHciId());
                    if(info!=null){
                        bean.put("xx","1");
                        bean.put("check",info.getDepartment()+"="+info.getDept()+"="+info.getStandardDept()+"="+info.getStandardSubdept());
                        if(StringUtils.isNotBlank(doctor.getDepart())){
                            if(bean.get("check").toString().indexOf(doctor.getDepart())>=0){
                                bean.put("xx","0");
                            }else if(bean.get("check").toString().indexOf(doctor.getSubdivisionDepart()+"")>=0){
                                bean.put("xx","0");
                            }else {
                                bean.put("xx","1");
                            }
                        }else{
                            bean.put("xx","1");
                        }
                    }
                }

                bean.put("xxx","-1");
            }else{
               // bean.put("xxx",hcpService.getName(doctor.getName()));
            }
            list.add(bean);
        }
        System.out.println(count);
        return list;
    }
}

