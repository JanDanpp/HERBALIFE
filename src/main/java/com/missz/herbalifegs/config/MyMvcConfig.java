package com.missz.herbalifegs.config;

import com.missz.herbalifegs.component.LoginHandlerInterceptor;
import com.missz.herbalifegs.component.MyLocaleResolver;
import org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Locale;

//使用WebMvcConfigurerAdapter可以扩展SpringMVC的功能
//@EnableWebMvc   //spring mvc全面接管 Spring boot自动配置类失效
@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {

//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        System.out.println("-----执行过滤-------");
//        //浏览器发送请求转发到相应的链接
////        super.addViewControllers(registry);
//        //发送那个请求映射到那个页面
////        比如浏览器发送login.html也来到login请求下
//        registry.addViewController("/login.html").setViewName("login");
//        //测试若同时存在success页面和test页面都可以正常访问
////        1。两个页面正常访问的同时将success指向test页面。结果访问success依旧访问到success.html页面。
////        registry.addViewController("/success").setViewName("test");
////        2。若将success过滤，test通过。则来到登录页面，success被过滤
////        registry.addViewController("/success").setViewName("test");
////        3。若将success在Hellocontroller类中备注掉。并通过success访问test。则success路径正常访问test页面
////        registry.addViewController("/success").setViewName("test");
////        3。若将success在Hellocontroller类中备注掉。并将test过滤拿掉。让success访问test。则正常访问test页面。
////        registry.addViewController("/success").setViewName("test");
////        理解：是将success链接地址直接指向test页面，并不是将success链接地址跳转到test链接地址在访问test页面。
////        registry.addViewController("/success").setViewName("test");
//        registry.addViewController("/login").setViewName("login");
//        registry.addViewController("/index.html").setViewName("index");
//        registry.addViewController("/main.html").setViewName("/admin/index");
//        registry.addViewController("a").setViewName("test");
//    }

//    //过滤器
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
////        super.addInterceptors(registry);
//        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
////                .excludePathPatterns("/","/webjars/bootstrap/**","/asserts/**","login.html");
//    .excludePathPatterns("/","/user/login","/login.html","/asserts/**","/success/**","/webapp/**"
//                        ,"/webjars/bootstrap/**","/css/**","/js/**","/lib/**","/search/**","/comments/**"
//                ,"/types/**","/tags/**","/archives/**","/about/**","/blog/**","/index.html","/images/**","/renxi/**");
//
//    }

    @Bean
    public FilterRegistrationBean<HiddenHttpMethodFilter> testFilterRegistration3() {
        FilterRegistrationBean<HiddenHttpMethodFilter> registration = new FilterRegistrationBean<HiddenHttpMethodFilter>();
        registration.setFilter(new HiddenHttpMethodFilter());//添加过滤器
        registration.addUrlPatterns("/*");//设置过滤路径，/*所有路径
        registration.setName("HiddenHttpMethodFilter");//设置优先级
        registration.setOrder(2);//设置优先级
        return registration;
    }

//    所有的WebMvcConfigurerAdapter组件都会一起起作用
    @Bean //将组件注册在容器中
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter() {
        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter() {

            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                System.out.println("-----转发器-------");
                //浏览器发送请求转发到相应的链接
                registry.addViewController("/login.html").setViewName("login");
                registry.addViewController("/").setViewName("index");
                registry.addViewController("/index.html").setViewName("index");
//              使用转发防止用户重复多次登录
                registry.addViewController("/main.html").setViewName("/admin/index"); //用户名密码正确通过后执行
                registry.addViewController("/a").setViewName("test");
            }

            //注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                        //所有请求都拦截，登录后所有请求都可以访问，后面的表示这部分请求不用登录也可以访问的到。
                        .excludePathPatterns("/","/user/login","/login.html",""
//                                ,"/types/**","/tags/**","/archives/**","/about/**","/blog/**"
                           ,"/footer/**","/webapp/**","index.html","/asserts/**","/css/**","/images/**"
                        ,"/renxi/**","/blog/**","/types/**","/tags/**","/about/**","/archives/**"
                                ,"/lib/**","/comments/**"
                        );
//                        .excludePathPatterns("/","/user/login","/login.html","/asserts/**","/success/**"
//                                ,"/webapp/**","/a/**"
//                                ,"/webjars/bootstrap/**","/css/**","/js/**","/lib/**","/search/**"
//                                ,"/comments/**"
//                                ,"/types/**","/tags/**","/archives/**","/about/**","/blog/**","/index.html"
//                                ,"/images/**","/renxi/**");
            }
        };
        return adapter;
    }

    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }

}





