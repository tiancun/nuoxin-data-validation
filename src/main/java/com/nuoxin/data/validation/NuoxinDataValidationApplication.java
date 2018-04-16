package com.nuoxin.data.validation;


import com.nuoxin.data.validation.config.intercept.CrossDomainFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NuoxinDataValidationApplication {

	public static void main(String[] args) {
		SpringApplication.run(NuoxinDataValidationApplication.class, args);
	}

	/**
	 * 注册跨域支持过滤器
	 */
	@Bean
	public FilterRegistrationBean registerCrossDomainFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		CrossDomainFilter crossDomainFilter = new CrossDomainFilter();
		// 设置是否允许跨域访问
		crossDomainFilter.setAllowCrossDomain(true);
		registrationBean.setFilter(crossDomainFilter);
		registrationBean.setOrder(1);
		return registrationBean;
	}

}
