package com.nuoxin.data.validation;

import bean.Departments;
import com.alibaba.fastjson.JSON;
import com.nuoxin.data.validation.dao.main.HciAliasRepository;
import com.nuoxin.data.validation.dao.main.HciRepository;
import com.nuoxin.data.validation.dao.main.HcpRepository;
import com.nuoxin.data.validation.entity.eapp.Doctor;
import com.nuoxin.data.validation.entity.main.Hci;
import com.nuoxin.data.validation.entity.main.HciAlias;
import com.nuoxin.data.validation.entity.main.Hcp;
import com.nuoxin.data.validation.service.DoctorService;
import com.nuoxin.data.validation.service.HcpService;
import com.nuoxin.data.validation.service.MatchDeptService;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.print.Doc;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NuoxinDataValidationApplicationTests {

    @Autowired
    private HcpService hcpService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private HciAliasRepository hciAliasRepository;
    @Autowired
    private HciRepository hciRepository;
    @Autowired
    private MatchDeptService matchDeptService;

    @Test
    public void contextLoads() {
        Hcp hcp = hcpService.findOne();
        System.out.print(JSON.toJSONString(hcp));
    }


    @Test
    public void list() {
        Integer count = 0;
        List<Doctor> doctors = doctorService.checkAll();
        for (Doctor doctor : doctors) {
            if (StringUtils.isEmpty(doctor.getDepart())) {
                continue;
            }
            List<Hcp> hcpList = hcpService.findBy(doctor.getName(), doctor.getHospitalName());
            System.out.println(JSON.toJSONString(hcpList));
            if (hcpList != null && !hcpList.isEmpty()) {
                count += 1;
            }
        }
        System.out.println(count);
    }

    @Test
    public void checkDept() {
        List<Doctor> doctors = doctorService.checkAll();
        for (Doctor doctor : doctors) {
            if (StringUtils.isEmpty(doctor.getDepartcheck())) {
                continue;
            }
            Departments departments = matchDeptService.find(doctor.getId().intValue(), doctor.getDepartcheck());
            System.out.println(doctor.getDepartcheck());
            doctor.setDepart(departments.getParent());
            doctorService.save(doctor);
        }
    }

    @Test
    public void checkHospital() {
        List<String> list = new ArrayList<>();
        File file = new File("/Users/fenggang/Downloads/2017-doctor.csv");
        String txt = "";
        try {

            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));//构造一个BufferedReader类来读取文件
            String s = null;
            while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
                System.out.println(s);
                if(StringUtils.isNotBlank(s))
                    list.add(s);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> ok = new ArrayList<>();
        List<String> no = new ArrayList<>();
        if(list!=null && !list.isEmpty()){
            for (int i = 0,leng=list.size(); i < leng; i++) {
                String s = list.get(i);
                String[] ss = s.split(",");
                String hospital = ss[2];
                System.out.println(hospital);
                List<HciAlias> hciAlias = hciAliasRepository.findByAlias(hospital);
                if(hciAlias!=null && !hciAlias.isEmpty()){
                    Hci hci = hciRepository.findOne(hciAlias.get(0).getHci_id());
                    ok.add(ss[2]);
                }else{
                    no.add(s);
                }
            }
        }

        File okFile = new File("/Users/fenggang/Downloads/0208okFile.csv");
        File noFile = new File("/Users/fenggang/Downloads/0208noFile.csv");
        FileWriter okfw=null;
        BufferedWriter okbw=null;
        try{
            if(!okFile.exists()){
                okFile.createNewFile();
            }
            okfw=new FileWriter(okFile.getAbsoluteFile(),true);  //true表示可以追加新内容
            //fw=new FileWriter(f.getAbsoluteFile()); //表示不追加
            okbw=new BufferedWriter(okfw);
            for (String content:ok
                 ) {

                okbw.write(content+",\r\n");
            }
            okbw.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        FileWriter nofw=null;
        BufferedWriter nobw=null;
        try{
            if(!noFile.exists()){
                noFile.createNewFile();
            }
            nofw=new FileWriter(noFile.getAbsoluteFile(),true);  //true表示可以追加新内容
            //fw=new FileWriter(f.getAbsoluteFile()); //表示不追加
            nobw=new BufferedWriter(nofw);
            for (String content:no
                    ) {

                nobw.write(content+",\r\n");
            }
            nobw.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }



    @Test
    public void doctor2017() {
        List<String> list = new ArrayList<>();
        File file = new File("/Users/fenggang/Downloads/2017-doctor.csv");
        String txt = "";
        try {

            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));//构造一个BufferedReader类来读取文件
            String s = null;
            while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
                System.out.println(s);
                if (StringUtils.isNotBlank(s))
                    list.add(s);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
