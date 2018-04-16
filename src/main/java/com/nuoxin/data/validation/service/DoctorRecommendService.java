package com.nuoxin.data.validation.service;

import com.nuoxin.data.validation.util.excel.DoctorExcel;
import com.nuoxin.data.validation.util.excel.ExcelUtils;
import com.nuoxin.data.validation.web.response.RecommendListBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * 医生推荐相关方法
 * @author tiancun
 * @date 2018-04-13
 */
@Service
public class DoctorRecommendService {

    private static Logger logger = LoggerFactory.getLogger(DoctorRecommendService.class);

    @Autowired
    private DoctorService doctorService;

    /**
     * 导入excel文件返回医生的推荐数据数据
     * @param file 包含医生id的excel文件
     * @return 成功返回医生推荐list,否则返回 null
     */
    public List<RecommendListBean> getRecommendList(MultipartFile file){

        ExcelUtils<DoctorExcel> excelUtils = new ExcelUtils<>(new DoctorExcel());
        List<DoctorExcel> list = new ArrayList<>();
        try{
            list = excelUtils.readFromFile(null,file.getInputStream());
        }catch (Exception e){
            logger.error("DoctorRecommendService getRecommendList(MultipartFile file) import file error !!!", e.getMessage(), e);

        }

        List<RecommendListBean> recommendList = doctorService.recommend(list);

        return recommendList;
    }

}
