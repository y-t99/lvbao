package cn.lvbao.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lvbao
 * #create 2019-09-22-20:54
 */
@WebFilter(urlPatterns = {"/*"})
public class EncodeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 1、设置请求编码为UTF-8
        servletRequest.setCharacterEncoding("UTF-8");
        // 2、设置响应编码为UTF-8
        ((HttpServletResponse) servletResponse).setContentType("application/json;charset=UTF-8");
        ((HttpServletResponse) servletResponse).setCharacterEncoding("UTF-8");
        // 3、放行
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
