package cn.lvbao.filter;


import com.alibaba.fastjson.JSONObject;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Proxy;

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
        //1、获取content type截取形式字符串
        String type=null;
        if (request.getContentType()!=null){
            type=request.getContentType().split(";",2)[0];
        }
        //2、判断请求是否包含文件
        if("multipart/form-data".equals(type)){
            //3、如果有数据请求（非网页资源请求）且包含文件则放行
            System.out.println("包含文件");
            filterChain.doFilter(servletRequest, servletResponse);
        } else{
            // 4、读取json字符串
            BufferedReader br = request.getReader();
            String buffer;
            StringBuilder jsonBuilder = new StringBuilder();
            while ((buffer = br.readLine()) != null) {
                jsonBuilder.append(buffer);
            }
            System.out.println("requestfilter-json:"+jsonBuilder);
            // 5、将JSONObject对象存进request
            if (jsonBuilder.length() > 0) {
                //把json字符串改为json对象
                JSONObject json = JSONObject.parseObject(jsonBuilder.toString());
                request.setAttribute("requestBody", json);
            }
            //6、放行
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
