package com.missz.herbalifegs.component;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*

登录检查，

 */

public class LoginHandlerInterceptor implements HandlerInterceptor {

    //目标发放执行之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String uri = request.getRequestURI();
        System.out.println("-----转发之前的链接uri-----"+uri);

            Object user = request.getSession().getAttribute("loginUser");
            if(user == null){
                //未登录，返回登录页面
                request.setAttribute("msg","没有权限请先登录");
                //这是个转发器，转发到login.html
                System.out.println("----request------"+request+"-----response-----"+response);
                request.getRequestDispatcher("/login.html").forward(request,response);
                String uri1 = request.getRequestURI();
                System.out.println("-----转发完之后的uri-----"+uri1);
                return false;
            }else{
                //已登录，放行请求
                String uri2 = request.getRequestURI();
                System.out.println("----放行请求-----"+uri2);
                return true;
            }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
