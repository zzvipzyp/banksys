package com.qfedu.interceptor;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TimeInterceptor implements HandlerInterceptor {
    // 创建ThreadLocal对象
    private ThreadLocal<Long> threadLocal = new ThreadLocal<>();
    // 线程安全问题
//    private long beginTime;
    // 调用springmvc配置的资源之前
    // 返回true，表示放行，返回false，表示不再继续访问
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("prehandler");
        long beginTime = System.currentTimeMillis();
        // ThreadLocal中存的值，多线程之间相互独立
        // 设置值
        threadLocal.set(beginTime);
//        Thread.sleep(5000);
        return true;
    }

    // 访问过相关的资源，页面渲染之前
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        System.out.println("postHandler");
    }
    // 页面渲染后
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        System.out.println("afterCompletion");
        long endTime = System.currentTimeMillis();
        // 获取值
        long beginTime = threadLocal.get();
        long v = endTime - beginTime;
        System.out.println(v);

    }
}
