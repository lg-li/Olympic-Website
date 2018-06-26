package cn.edu.neu.assignment.conf;


import cn.edu.neu.assignment.inter.AuthorizationInterceptor;
import cn.edu.neu.assignment.inter.ProcessInterceptor;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.List;

/**
 * @author: CCM 20164969
 * @description: The config of this application
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    @Autowired
    private AuthorizationInterceptor authorizationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //Interceptor to solve cross-domain issues
        registry.addInterceptor(new ProcessInterceptor());
        //Token interceptor
//        registry.addInterceptor(authorizationInterceptor)
//                .addPathPatterns("/**")
//                .excludePathPatterns("/general/**")
//                .excludePathPatterns("/user/login/**")
//                .excludePathPatterns("/pages/**");
    }

    @Bean
    public WebMvcConfigurationSupport forwardToIndex() {
        return new WebMvcConfigurationSupport() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/back").setViewName(
                        "forward:/back/index");
                registry.addViewController("/").setViewName(
                        "forward:/back/index");
            }
        };
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    @Bean
    public InternalResourceViewResolver jspViewResolver() {
        InternalResourceViewResolver bean = new InternalResourceViewResolver();
        bean.setPrefix("/WEB-INF/jsp/");
        bean.setSuffix(".jsp");
        return bean;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //Inherit parent configuration
        super.configureMessageConverters(converters);
        //create fastJson converter
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        //create a config class
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        //Modify the config
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullStringAsEmpty
        );
        fastConverter.setFastJsonConfig(fastJsonConfig);
        //add the converter into application
        converters.add(fastConverter);
    }
}
