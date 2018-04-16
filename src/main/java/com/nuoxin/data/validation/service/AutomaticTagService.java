package com.nuoxin.data.validation.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nuoxin.data.validation.common.DefaultTagResponseBean;
import com.nuoxin.data.validation.enums.ErrorEnum;
import com.nuoxin.data.validation.exception.BusinessException;
import com.nuoxin.data.validation.util.EncodedUtil;
import com.nuoxin.data.validation.util.FileTypeUtil;
import com.nuoxin.data.validation.util.RestUtils;
import com.nuoxin.data.validation.web.request.AutomicTagRequestBean;
import com.nuoxin.data.validation.web.response.AutomicTagListResponseBean;
import org.apache.poi.extractor.ExtractorFactory;
import org.apache.poi.hslf.extractor.PowerPointExtractor;
import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.xslf.extractor.XSLFPowerPointExtractor;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xssf.extractor.XSSFExcelExtractor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.xmlbeans.XmlException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 自动化标签相关
 * @author tiancun
 * @date 2018-04-13
 */
@Service
public class AutomaticTagService {

    private static Logger logger = LoggerFactory.getLogger(AutomaticTagService.class);

    /**
     * 除字符串中的空格、回车、换行符、制表符正则
     */
    private Pattern p = Pattern.compile("\\s*|\t|\r|\n");

    @Value("${automatic.tag.request.url}")
    private String automaticTagRequestUrl;


    /**
     * 判断2003 版word 格式
     */
    private static final String DOC = "doc";

    /**
     * 判断2007 版以上word 格式
     */
    private static final String DOCX = "docx";

    /**
     * 判断2003 版excel 文件格式
     */
    private static final String XLS = "xls";

    /**
     * 判断2007 版以上excel 文件格式
     */
    private static final String XLSX = "xlsx";


    /**
     * 判断2003 版ppt 文件格式
     */
    private static final String PPT = "ppt";


    /**
     * 判断2007 版以上ppt 文件格式
     */
    private static final String PPTX = "pptx";





    /**
     * 根据上传的文件返回分词后的标签
     * @param file 上传的文件
     * @return 成功后返回标签列表，失败抛出异常
     */
    public AutomicTagListResponseBean getTags(MultipartFile file){

        String text = handleFile(file);
        AutomicTagRequestBean automicTagRequest = new AutomicTagRequestBean(text);
        String jsonParams = JSONObject.toJSONString(automicTagRequest);
        String jsonString = null;
        try {
            jsonString = RestUtils.post(automaticTagRequestUrl, jsonParams, null);
        }catch (Exception e){
            throw new BusinessException(ErrorEnum.ERROR.getCode(), "分词服务器挂机！！");
        }

        if (StringUtils.isEmpty(jsonString)){
            throw new BusinessException(ErrorEnum.ERROR.getCode(), "该文章暂无分词！！");
        }

        DefaultTagResponseBean automicTagtResponseBean = JSONObject.parseObject(jsonString, DefaultTagResponseBean.class);
        if (automicTagtResponseBean != null){
            Integer code = automicTagtResponseBean.getCode();
            AutomicTagListResponseBean data = automicTagtResponseBean.getData();
            if (code !=null && (ErrorEnum.SUCCESS.getCode().equals(code))){
                return data;
            }else{
                String message = automicTagtResponseBean.getMessage();
                logger.error("AutomaticTagService getTags(MultipartFile file) request automic tag from python error !!! " +
                        "code={} message={} data={}", code, message, JSONObject.toJSONString(data));
            }
        }
        throw new BusinessException(ErrorEnum.ERROR.getCode(), "从文本提取标签失败！！");
    }



    /**
     * 提取文件中的文字，现在只支持提取office格式的文件
     * @param file 要处理的文件
     * @return 成功返回文字字符串，否则返回空字符串
     */
    private String handleFile(MultipartFile file){
        String result = "";
        // 检查文件格式
        String suffix = checkMultipartFile(file);

        InputStream is = getMultipartFileInputStream(file);

        if (DOC.equals(suffix)){
            result = handleDocFile(is);
        }

        if (DOCX.equals(suffix)){
            result = handleDocxFile(is);
        }

        if (XLS.equals(suffix)){
            result = handleXlsFile(is);
        }

        if (XLSX.equals(suffix)){
            result = handleXlsxFile(is);
        }

        if (PPT.equals(suffix)){
            result = handlePptFile(is);

        }

        if (PPTX.equals(suffix)){
            result = handlePptx(is);
        }


        return result;

    }

    /**
     * 处理pptx 后缀文件
     * @param inputStream
     * @return 成功返回提取的文本，否则返回空串
     */
    private String handlePptx(InputStream inputStream) {


        String result = "";
        XSLFPowerPointExtractor xSLFPowerPointExtractor = null;
        try {
            XMLSlideShow xmlSlideShow = new XMLSlideShow(inputStream);
            xSLFPowerPointExtractor = new XSLFPowerPointExtractor(xmlSlideShow);
            if (xSLFPowerPointExtractor == null){
                return result;
            }
            String text = xSLFPowerPointExtractor.getText();
            if (StringUtils.isEmpty(text)){
                return result;
            }
            return replaceBlank(text);
        } catch (Exception e) {
            logger.error("AutomaticTagService handlePptx(InputStream inputStream) createExtractor error !!!", e.getMessage(), e);
            throw new BusinessException(ErrorEnum.ERROR.getCode(), "文件读取失败，请检查一下文件格式");
        }finally {
            if (inputStream !=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.error("AutomaticTagService handlePptx(InputStream inputStream) inputStream.close() error !!!", e.getMessage(), e);
                }
            }
        }

    }


    /**
     * 处理ppt 后缀文件
     * @param inputStream
     * @return 成功返回提取的文本，否则返回空串
     */
    private String handlePptFile(InputStream inputStream) {

        String result = "";
        PowerPointExtractor powerPointExtractor = null;
        try {
            powerPointExtractor = new PowerPointExtractor(inputStream);
            if (powerPointExtractor == null){
                return result;
            }
            String text = powerPointExtractor.getText();
            if (StringUtils.isEmpty(text)){
                return result;
            }
            return replaceBlank(text);
        } catch (Exception e) {
            logger.error("AutomaticTagService handlePptFile(InputStream inputStream) createExtractor error !!!", e.getMessage(), e);
            throw new BusinessException(ErrorEnum.ERROR.getCode(), "文件读取失败，请检查一下文件格式");
        }finally {
            if (inputStream !=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.error("AutomaticTagService handlePptFile(InputStream inputStream) inputStream.close() error !!!", e.getMessage(), e);
                }
            }
        }

    }

    /**
     * 处理xlsx 后缀文件
     * @param inputStream
     * @return 成功返回提取的文本，否则返回空串
     */
    private String handleXlsxFile(InputStream inputStream) {


        String result = "";
        XSSFExcelExtractor xSSFExcelExtractor = null;
        try {
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);
            xSSFExcelExtractor = new XSSFExcelExtractor(xssfWorkbook);
            if (xSSFExcelExtractor == null){
                return result;
            }
            String text = xSSFExcelExtractor.getText();
            if (StringUtils.isEmpty(text)){
                return result;
            }
            return replaceBlank(text);
        } catch (Exception e) {
            logger.error("AutomaticTagService handleXlsxFile(InputStream inputStream) createExtractor error !!!", e.getMessage(), e);
            throw new BusinessException(ErrorEnum.ERROR.getCode(), "文件读取失败，请检查一下文件格式");
        }finally {
            if (inputStream !=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.error("AutomaticTagService handleXlsxFile(InputStream inputStream) inputStream.close() error !!!", e.getMessage(), e);
                }
            }
        }

    }


    /**
     * 处理xls 后缀文件
     * @param inputStream
     * @return 成功返回提取的文本，否则返回空串
     */
    private String handleXlsFile(InputStream inputStream) {

        String result = "";
        ExcelExtractor excelExtractor = null;
        try {
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
            excelExtractor = new ExcelExtractor(hssfWorkbook);
            if (excelExtractor == null){
                return result;
            }
            String text = excelExtractor.getText();
            if (StringUtils.isEmpty(text)){
                return result;
            }
            return replaceBlank(text);
        } catch (Exception e) {
            logger.error("AutomaticTagService handleXlsFile(InputStream inputStream) createExtractor error !!!", e.getMessage(), e);
            throw new BusinessException(ErrorEnum.ERROR.getCode(), "文件读取失败，请检查一下文件格式");
        }finally {
            if (inputStream !=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.error("AutomaticTagService handleXlsFile(InputStream inputStream) inputStream.close() error !!!", e.getMessage(), e);
                }
            }
        }
    }

    /**
     * 处理docx 后缀文件
     * @param inputStream
     * @return 成功返回提取的文本，否则返回空串
     */
    private String handleDocxFile(InputStream inputStream) {

        String result = "";
        XWPFWordExtractor xwpfWordExtractor = null;
        try {
            XWPFDocument xWPFDocument = new XWPFDocument(inputStream);
            xwpfWordExtractor = new XWPFWordExtractor(xWPFDocument);
            if (xwpfWordExtractor == null){
                return result;
            }
            String text = xwpfWordExtractor.getText();
            if (StringUtils.isEmpty(text)){
                return result;
            }
            return replaceBlank(text);
        } catch (Exception e) {
            logger.error("AutomaticTagService handleDocxFile(InputStream inputStream) createExtractor error !!!", e.getMessage(), e);
            throw new BusinessException(ErrorEnum.ERROR.getCode(), "文件读取失败，请检查一下文件格式");
        }finally {
            if (inputStream !=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.error("AutomaticTagService handleDocxFile(InputStream inputStream) inputStream.close() error !!!", e.getMessage(), e);
                }
            }
        }

    }

    /**
     * 处理doc 后缀文件
     * @param inputStream
     * @return 成功返回提取的文本，否则返回空串
     */
    private String handleDocFile(InputStream inputStream) {
        String result = "";
        WordExtractor extractor = null;
        try {
            extractor = new WordExtractor(inputStream);
            if (extractor == null){
                return result;
            }

            String text = extractor.getText();
            if (StringUtils.isEmpty(text)){
                return result;
            }

            return replaceBlank(text);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("AutomaticTagService handleDocFile(InputStream inputStream) createExtractor error !!!", e.getMessage(), e);
            throw new BusinessException(ErrorEnum.ERROR.getCode(), "文件读取失败，请检查一下文件格式");
        }finally {
            if (inputStream !=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.error("AutomaticTagService handleDocFile(InputStream inputStream) inputStream.close() error !!!", e.getMessage(), e);
                }
            }
        }

    }

    /**
     * 除字符串中的空格、回车、换行符、制表符
     * @param str 要去除的字符串
     * @return 成功返回去掉空格、回车、换行符、制表符字符串，否则返回空串
     */
    private  String replaceBlank(String str) {
        String result = "";
        if (StringUtils.isEmpty(str)){
            return result;
        }

        if (str!=null) {
            Matcher m = p.matcher(str);
            result = m.replaceAll("");
        }

        if (result == null){
            return "";
        }

        return result;
    }


    /**
     * 得到 MultipartFile 的输入流
     * @param file
     * @return 成功返回文件的输入流，失败返回 null 或者抛出异常
     */
    private InputStream getMultipartFileInputStream(MultipartFile file){

        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("AutomaticTagService getMultipartFileInputStream(MultipartFile file)  error !!!", e.getMessage(), e);
            throw new BusinessException(ErrorEnum.ERROR.getCode(), "文件读取失败，请检查一下文件格式");
        }

        return inputStream;
    }

    /**
     * 检查文件格式
     * @param file 要检查的文件
     * @return 成功返回文件的后缀名，否则抛出异常
     */
    private String checkMultipartFile(MultipartFile file){
        if (file == null){
            throw new BusinessException(ErrorEnum.ERROR.getCode(), "上传文件不能为空");
        }

        String originalFilename = file.getOriginalFilename();
        if (StringUtils.isEmpty(originalFilename)){
            throw new BusinessException(ErrorEnum.ERROR.getCode(), "上传文件文件名不能为空");
        }

        InputStream inputStream = getMultipartFileInputStream(file);

        // 文件流方式判断文件类型
        String fileHeader = FileTypeUtil.getFileHeaderByFileInputStream(inputStream);
        if (StringUtils.isEmpty(fileHeader) || (!FileTypeUtil.OFFICE_FILE_HEADER.contains(fileHeader))){
            throw new BusinessException(ErrorEnum.ERROR.getCode(), "目前只支持office格式文件！！");
        }

        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1, originalFilename.length());
        boolean flag = (!StringUtils.isEmpty(suffix)) && ((DOC.equals(suffix)) || (DOCX.equals(suffix))
                || (XLS.equals(suffix)) || (XLSX.equals(suffix)) || (PPT.equals(suffix)) || (PPTX.equals(suffix)));

        if (flag){
            return suffix;
        }

        throw new BusinessException(ErrorEnum.ERROR.getCode(), "不是office文件格式的后缀名！！");
    }
}
