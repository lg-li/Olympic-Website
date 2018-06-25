package com.neu.demo.conf;


import com.neu.demo.inter.AuthorizationInterceptor;
import com.neu.demo.inter.ProcessInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    @Autowired
    private AuthorizationInterceptor authorizationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //解决跨域问题拦截器
        registry.addInterceptor(new ProcessInterceptor());
        //Token验证拦截器，凡不是游客路由均会验证
        registry.addInterceptor(authorizationInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/general/**")
                .excludePathPatterns("/user/login/**");
    }

    @Bean
    public WebMvcConfigurationSupport forwardToIndex() {
        return new WebMvcConfigurationSupport() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                // forward requests to /admin and /user to their index.html
                registry.addViewController("/back").setViewName(
                        "forward:/back/index");
                registry.addViewController("/").setViewName(
                        "forward:/back/index");
                /*registry.addViewController("/app").setViewName(
                        "forward:/WEB-INF/app/index.html");*/
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
        //registry.addResourceHandler("/admin/static/**").addResourceLocations("classpath:/static/");
        //registry.addResourceHandler("/upload/**").addResourceLocations(ResourceUtils.FILE_URL_PREFIX+System.getProperty("user.dir")+"/web/upload/");
    }

    @Bean
    public InternalResourceViewResolver jspViewResolver() {
        InternalResourceViewResolver bean = new InternalResourceViewResolver();
        bean.setPrefix("/WEB-INF/jsp/");
        bean.setSuffix(".jsp");
        return bean;
    }
}
