package com.lopes.expenses.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;

import javax.servlet.Filter;
import java.util.Arrays;

/**
 * The type Browser security bean config.
 */
@Configuration
public class BrowserSecurityBeanConfig {

    /**
     * Hidden http method filter filter registration bean.
     *
     * @return the filter registration bean
     */
//For DELETE as POST
    @Bean
    public FilterRegistrationBean<Filter> hiddenHttpMethodFilter() {

        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<Filter>(new HiddenHttpMethodFilter());
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
        return filterRegistrationBean;
    }

}
