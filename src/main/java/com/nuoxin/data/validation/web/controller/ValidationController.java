package com.nuoxin.data.validation.web.controller;

import com.nuoxin.data.validation.service.DoctorService;
import com.nuoxin.data.validation.util.ExcelExprotUtil;
import com.nuoxin.data.validation.util.RemoteExecuteCommand;
import com.nuoxin.data.validation.util.excel.DoctorExcel;
import com.nuoxin.data.validation.util.excel.ExcelUtils;
import com.nuoxin.data.validation.util.sftp.FtpUtil;
import com.nuoxin.data.validation.web.response.RecommendListBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * Created by fenggang on 1/9/18.
 *
 * @author fenggang
 * @date 1/9/18
 */
@RestController
@RequestMapping("/")
public class ValidationController {

    @Autowired
    private DoctorService doctorService;

    private static List<RecommendListBean> recommendList = new ArrayList<>();

    @RequestMapping(value = "validation/eapp", method = RequestMethod.GET)
    public void validation(HttpServletRequest request, HttpServletResponse response){
        ArrayList<Map<String,Object>> list = doctorService.checkNameEmailMobile();
        this.attentionExport(list,response,request,"eapp");
    }

    private void attentionExport(List<Map<String, Object>> list, HttpServletResponse response,
                                 HttpServletRequest request,String name){
        String[] keys = {"id","name","hospital","mobile","depart","email","drugUserName","productName"};
        String[] columns = {"id","姓名","医院","电话","科室","邮箱","销售","产品"};
        ExcelExprotUtil.exprot(list, name, keys, columns, response, request);
    }

    @RequestMapping(value = "validation/hospital", method = RequestMethod.GET)
    public void hospital(HttpServletRequest request, HttpServletResponse response){
        ArrayList<Map<String,Object>> list = doctorService.nameHospital();
        this.nameHospital(list,response,request,"hospital0208");
    }

    private void nameHospital(List<Map<String, Object>> list, HttpServletResponse response,
                                 HttpServletRequest request,String name){
        String[] keys = {"id","name","hospital","departCheck","depart","check","xx","xxx"};
        String[] columns = {"id","姓名","医院","标准化之后的科室","科室","主数据科室","xx","xxx"};
        ExcelExprotUtil.exprot(list, name, keys, columns, response, request);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView showLoginPage(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("index");
        String type = request.getParameter("type");
        List<RecommendListBean> list = recommendList;
        recommendList=new ArrayList<>();
        mv.addObject("list",list);
        return mv;
    }

    @RequestMapping(value = "eapp/file", method = RequestMethod.POST)
    public ModelAndView validationFile(MultipartFile file,HttpServletRequest request, HttpServletResponse response){
        ExcelUtils<DoctorExcel> excelUtils = new ExcelUtils<>(new DoctorExcel());
        List<DoctorExcel> list = new ArrayList<>();
        try{
            list = excelUtils.readFromFile(null,file.getInputStream());
        }catch (Exception e){
            e.printStackTrace();
        }
        List<RecommendListBean> recommendList = doctorService.recommend(list);
        ValidationController.recommendList = recommendList;
        ModelAndView mv = new ModelAndView("redirect:/");
        return mv;
    }
    @RequestMapping(value = "shell", method = RequestMethod.GET)
    public ModelAndView shellRemote(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("shell");
        String path = "/Users/fenggang";
        String fileNme = request.getParameter("fileName");
        this.remote(fileNme);
        try{
            this.downlad(fileNme,path);

        }catch (Exception e){
            e.printStackTrace();
        }
        List<String> list = new ArrayList<>();
        File file = new File(path+"/"+fileNme+".log.txt");
        String txt = "";
        try {

            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));//构造一个BufferedReader类来读取文件
            String s = null;
            int count = 0;
            while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
                System.out.println(s);
                if(txt.indexOf("word : tf-idf")>=0){
                    list.add(s);
                }else{
                    txt = s;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mv.addObject("list",list);
        return mv;
    }

    private void remote(String fileName){
        RemoteExecuteCommand rec=new RemoteExecuteCommand("47.95.22.53", "root","rec2017Nx!");
        //执行命令
        System.out.println(rec.execute("ifconfig"));
        //执行脚本
        String shell = "/root/anaconda3/bin/python /usr/local/tfidf/temp/tf_idf.py /usr/local/tfidf/temp/input/"+fileName+".txt > /usr/local/tfidf/temp/input/"+fileName+".log.txt";
        rec.execute(shell);
        //这个方法与上面最大的区别就是，上面的方法，不管执行成功与否都返回，
        //这个方法呢，如果命令或者脚本执行错误将返回空字符串
        rec.executeSuccess("ifconfig");
    }

    private void downlad(String file,String localhostPath) throws Exception{
        String ftpHost = "47.95.22.53";
        String ftpUserName = "root";
        String ftpPassword = "rec2017Nx!";
        int ftpPort = 22;
        String ftpPath = "/usr/local/tfidf/temp/input/";
        String localPath = localhostPath;
        String fileName = file+".log.txt";
        FtpUtil.downloadSftpFile(ftpHost, ftpUserName, ftpPassword, ftpPort, ftpPath, localPath, fileName);
    }
}
