package com.sx.blog.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.sx.blog.common.interceptor.MyInterceptor;

/** 
 * @author 邓宇阳
 * @date 2020年1月2日
 * 拦截器配置
 */
@Configuration
public class MvcWebConfiguration implements WebMvcConfigurer {
	@Autowired
	private MyInterceptor myInterceptor;

	//配置图片服务器映射路径
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**").addResourceLocations("file:D:/GitWorkesp/blog/images/");
	}

    /*    // 配置路径拦截 前拦截
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor).addPathPatterns("/article/**", "/user/power/**", "/user/**")
            .excludePathPatterns("/user/login/**", "/user/regist/**", "/user/article/**", "/user/activate/**");
    }*/

    /* @Override // 拦截器注册器
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> patterns = new ArrayList<>();
        patterns.add("/users/reg");
        patterns.add("/users/login");
        registry.addInterceptor(myInterceptor).addPathPatterns("/**").excludePathPatterns(patterns);
    }*/

}
