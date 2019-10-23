package yt.xssAttached;

import org.owasp.esapi.ESAPI;

/**
 * @author yuanyuan
 * #create 2019-10-22-14:51
 */
public class Demo {
    public static void main(String[] args) {
        String safe = ESAPI.encoder().encodeForHTML("<script>alert('xss')</script>");
        System.out.println(safe);
    }
}
