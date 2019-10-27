package cn.lvbao.controllor;

import cn.lvbao.code.ResultEnum;
import cn.lvbao.domain.Result;
import cn.lvbao.user.domain.User;
import cn.lvbao.util.JjwtUtils;
import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yuanyuan
 * #create 2019-10-26-14:28
 */
@WebServlet("/public/isLoginServlet")
public class IsLoginServlet extends BaseServlet{
    public static Map<String,User> users=new HashMap<>();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONObject json= (JSONObject) req.getAttribute("requestBody");
        Result<User> result;
        try {
            Claims claims = JjwtUtils.parseJWT((String) json.get("token"));
            if(claims!=null){
                result=new Result(ResultEnum.HAVE_MESSAGE,"用户登录过");
                result.setData(users.get(json.get("id")));
            }else{
                throw new RuntimeException();
            }
        } catch (Exception e) {
            result=new Result(ResultEnum.NO_MESSAGE,"用户为登录过");
        }
        req.setAttribute("result",result);
        super.doPost(req, resp);
    }
}
