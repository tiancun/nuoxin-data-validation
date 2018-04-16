//package com.nuoxin.data.validation.config;
//
//import org.springframework.boot.web.servlet.MultipartConfigFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.servlet.MultipartConfigElement;
//
///**
// * Created by fenggang on 1/13/18.
// *
// * @author fenggang
// * @date 1/13/18
// */
//@Configuration
//public class AppBeanFactary {
//
//
//    /**
//     * 上传文件大小限制
//     *
//     * @return
//     */
//    @Bean
//    public MultipartConfigElement multipartConfigElement() {
//        MultipartConfigFactory configFactory = new MultipartConfigFactory();
//        configFactory.setMaxFileSize("1MB");
//        configFactory.setMaxRequestSize("100MB");
//        return configFactory.createMultipartConfig();
//    }
//}
