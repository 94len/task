package com.erafollower.task.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @describe Web MVC 配置适配器
 * @auth len
 * @createTime 2019/4/4
 *
 * WebAppConfigurer extends WebMvcConfigurerAdapter 在Spring Boot2.0版本已过时了，用官网说的新的类替换
 */
@Configuration
public class WebAppConfig implements WebMvcConfigurer {

    /**
     * SpringBoot设置首页
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        WebMvcConfigurer.super.addViewControllers(registry);
        registry.addViewController("/").setViewName("index");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/pages/**")
                .addResourceLocations("classpath:/templates/");
        registry.addResourceHandler("/static/**").
                addResourceLocations("classpath:/static/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new ReqInterceptor()).addPathPatterns("/**");
    }


//
//
//    /**
//     * 配置静态页面请求处理
//     */
//    @Override
//    public void configureViewResolvers(ViewResolverRegistry registry) {
//        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//        viewResolver.setPrefix(null);
//        viewResolver.setSuffix(null);
//
//        registry.viewResolver(viewResolver);
//        registry.order(1);
//    }

}
