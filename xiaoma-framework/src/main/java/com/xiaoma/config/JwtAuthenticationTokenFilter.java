/**
 * @file: JwtAuthenticationTokenFilter
 * @author: MaWenjun
 * @version: 0.0.1
 * @date: 2023-Nov-14
 */
package com.xiaoma.config;

import com.alibaba.fastjson.JSON;
import com.xiaoma.domain.entity.LoginUser;
import com.xiaoma.utils.JwtUtil;
import com.xiaoma.utils.WebUtils;
import com.xiaoma.vo.AppHttpCodeEnum;
import com.xiaoma.vo.ResponseResult;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.Objects;

@Component
//继承该抽象类，确保每次请求只经过一次过滤器
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    RedisTemplate redisTemplate;


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        //获取请求头中的Token
        String token = httpServletRequest.getHeader("token");
        //如果token为null或者空值，说明该接口不需要登录，直接放行
        if(!StringUtils.hasText(token)){
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        //解析获取userId
        try {
            byte[] encodedKey = Base64.getDecoder().decode(JwtUtil.JWT_KEY);
            SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
            Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
            String userId = claims.getSubject();

//            在redis中根据该userId查找用户对象
            LoginUser loginUser = (LoginUser) redisTemplate.opsForValue().get("bloglogin:"+userId);

            //判断是否查找到，无则需要登录
            if(Objects.isNull(loginUser)){
                ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
                httpServletResponse.setStatus(401);
                httpServletResponse.setContentType("application/json");
                httpServletResponse.setCharacterEncoding("utf-8");
                httpServletResponse.getWriter().print(JSON.toJSONString(result));
                return;
            }

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, null);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);



        } catch (Exception e) {
            e.printStackTrace();
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_ERROR);
            httpServletResponse.setStatus(505);
            httpServletResponse.setContentType("application/json");
            httpServletResponse.setCharacterEncoding("utf-8");
            httpServletResponse.getWriter().print(JSON.toJSONString(result));
            return ;
        }

    }
}
