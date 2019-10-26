package cn.lvbao.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * jjwt工具类，用于生成token
 * @author JimZiSing
 * 依赖包
 * <dependency>
 *   <groupId>io.jsonwebtoken</groupId>
 *   <artifactId>jjwt</artifactId>
 *   <version>0.9.0</version>
 * </dependency>
 */
public class JjwtUtils {

    private static final String JWT_SECRET = "gjal*jkjljo_dsgf_pbwei_65fa9sdf_jcewd112gdsfd161bfof_1564d16";

    /**
     * 生成token
     * @param userId
     * @param ttlMillis
     * @return
     * @throws Exception
     */
    public static String createJWT(Integer userId, long ttlMillis) throws Exception {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //签发jwt的时间
        long nowMillis=System.currentTimeMillis();
        SecretKey key = generalKey();

        Map<String,Object> map = new HashMap<String, Object>();
        JwtBuilder builder = Jwts.builder()
                .addClaims(map)
                //存入用户信息
                .claim("userId", userId)
                .setIssuedAt(new Date())
                .signWith(signatureAlgorithm, key)
                .setExpiration(new Date(nowMillis + ttlMillis));
        return builder.compact();
    }

    /**
     * 生成key
     * @return
     */
    private static SecretKey generalKey() {
        //秘钥
        String stringKey =JjwtUtils.JWT_SECRET;
        byte[] encodedKey = Base64.decodeBase64(stringKey);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }
//    public static String createJWT(String email, long ttlMillis) throws Exception {
//        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
//        //签发jwt的时间
//        long nowMillis=System.currentTimeMillis();
//        SecretKey key = generalKey();
//
//        Map<String,Object> map = new HashMap<String, Object>();
//        JwtBuilder builder = Jwts.builder()
//                .addClaims(map)
//                //存入用户信息
//                .claim("email", email)
//                .setSubject(email)
//                .setIssuedAt(new Date())
//                .signWith(signatureAlgorithm, key)
//                .setExpiration(new Date(nowMillis + ttlMillis));
//        return builder.compact();
//    }

    /**
     *
     * 解析token
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) throws Exception{
        SecretKey key = generalKey();
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwt).getBody();
        return claims;
    }

}
