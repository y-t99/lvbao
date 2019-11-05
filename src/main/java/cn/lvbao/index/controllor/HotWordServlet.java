package cn.lvbao.index.controllor;

import cn.lvbao.code.ResultEnum;
import cn.lvbao.index.domain.Result;
import cn.lvbao.util.JedisUtils;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author yuanyuan
 * #create 2019-10-23-17:23
 */
@WebServlet("/index/hotWordServlet")
public class HotWordServlet extends BaseServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取判断内容
        Jedis jedis= JedisUtils.getJedis();
        /**
         * 将关键词保存到榜单中
         */
        Set<String> hotwords = jedis.zrevrange("hotword", 0, 9);
        jedis.close();
        //把榜单内容封装到result中
        List<String> list=new ArrayList<>(hotwords);
        System.out.println("hotwordServlet");
        Result result=new Result(ResultEnum.HAVE_MESSAGE,list.size(),list);
        req.setAttribute("result",result);
        super.doPost(req, resp);
    }
}
