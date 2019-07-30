package com.zjf.security_jwt.util;


import com.sun.org.apache.xpath.internal.operations.Bool;
import com.zjf.security_jwt.comm.Const;
import com.zjf.security_jwt.model.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * JWT工具类
 *
 * 主要用于对JWT Token进行各项操作，
 * 生成Token，验证Token，刷新Token;
 *
 *
 */

@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -5625635588908941275L;

    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";

     public String getUsernameFromToken(String token){
         String username;
         try{
             final Claims claims =  getClaimsFromToken(token);
             username = claims.getSubject();
         }catch(Exception e){
             username = null;
         }

         return username;
     }

     public Date getCreatedDateFromToken(String token){
         Date created;
         try{
             final Claims claims = getClaimsFromToken(token);
             created = new Date((Long)claims.get(CLAIM_KEY_CREATED));
         }catch (Exception e){
             created = null;
         }

         return created;

     }

     public Date getExpirationDateFromToken(String token){
         Date expiration;

         try{
             final Claims claims = getClaimsFromToken(token);
             expiration = claims.getExpiration();

         }catch(Exception e){
             expiration = null;

         }
         return expiration;

     }

    /**
     * Claims extends Map<String, Object>, ClaimsMutator<Claims>
     * claims是个map，可以传入其他的信息，比如权限，角色等信息
     */
     private Claims getClaimsFromToken(String token){
         Claims claims;
         try{
             claims = Jwts.parser()
                     .setSigningKey( Const.SECRET )
                     .parseClaimsJws(token)
                     .getBody();
         }catch (Exception s){
             claims = null;
         }

         return claims;
     }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + Const.EXPIRATION_TIME * 1000);
    }

    String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, Const.SECRET )
                .compact();
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }

    public Boolean canTokenBeRefreshed(String token) {
        return !isTokenExpired(token);
    }

    public String refreshToken(String token){
         String refreshedToken;

         try{
             final Claims claims = getClaimsFromToken(token);
             claims.put(CLAIM_KEY_CREATED,new Date());
             refreshedToken = generateToken(claims);
         }catch (Exception e){
             refreshedToken = null;
         }
         return refreshedToken;

    }

    public Boolean validateToken(String token,UserDetails userDetails){
        User user = (User) userDetails;

        final String username = getUsernameFromToken(token);
        return (username.equals(user.getUsername())
                && !isTokenExpired(token));
    }


}


