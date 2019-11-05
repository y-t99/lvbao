package cn.lvbao.filter;


import cn.lvbao.util.SensitiveWordUtil;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author yuanyuan
 * #create 2019-10-28-20:04
 */
public class SensitiveWordsFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String pathInfo = ((HttpServletRequest) servletRequest).getRequestURI();
        if(pathInfo.contains("articlePageServlet")) {
            HttpServletRequest req = (HttpServletRequest) servletRequest;
            if(req.getParameter("method").equals("comment")) {
                String review = (String) ((JSONObject) req.getAttribute("requestBody")).get("review");
                if(review!=null){
                    String safe = SensitiveWordUtil.replaceSensitiveWord(review, "[和谐]");
                    ((JSONObject) req.getAttribute("requestBody")).put("review", safe);
                    System.out.println("SensitiveWordsFilter:" + safe);
                }
            }
        }
        //3、放行
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
