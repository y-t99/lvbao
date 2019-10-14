package cn.lvbao.filter;


import com.alibaba.fastjson.JSONObject;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author lvbao
 * #create 2019-09-22-20:54
 */
public class RequestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        // 1、读取json字符串
        BufferedReader br = request.getReader();
        String buffer;
        StringBuilder jsonBuilder = new StringBuilder();
        while ((buffer = br.readLine()) != null) {
            jsonBuilder.append(buffer);
        }
        System.out.println("requestfilter-json:"+jsonBuilder);
        // 2、将JSONObject对象存进request
        if (jsonBuilder.length() > 0) {
            //把json字符串改为json对象
            JSONObject json = JSONObject.parseObject(jsonBuilder.toString());
            request.setAttribute("requestBody", json);
        }
        //3、放行
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
