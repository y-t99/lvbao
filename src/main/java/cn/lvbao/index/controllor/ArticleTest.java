package cn.lvbao.index.controllor;

import cn.lvbao.util.JDBCUtils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;
import sun.security.provider.certpath.BuildStep;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yuanyuan
 * #create 2019-10-10-0:48
 */
@WebServlet("/articleTest")
public class ArticleTest extends BaseServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、得到json对象
        JSONObject json= (JSONObject) req.getAttribute("requestbody");

        //2、把html内容存到数据库中
        String htmlcontent="<p class=\"article-p\">你好</p>" +
                "<img class=\"article-pic\" src=\"\\picTest\\123\"></img>";
        JdbcTemplate template=new JdbcTemplate(JDBCUtils.getPool());
        String sql=" INSERT INTO " +
                    " test(id,content) " +
                    " values(?,?) ";
        template.update(sql,12,htmlcontent);

        //3、把数据库的文件读取,放回给前端
        sql="SELECT content FROM " +
            " test " +
            " WHERE id=? ";
        String content=template.queryForObject(sql,String.class,12);
        //4、result返回给前端
        Map<String,String> result=new HashMap<>();
        result.put("content",content);
        req.setAttribute("result",result);
        super.doPost(req, resp);
    }
}
