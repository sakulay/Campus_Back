package com.guet.ljc.security.component;


import com.guet.ljc.security.util.JwtTokenUtil;
import com.guet.ljc.service.MyUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author hresh
 * @博客 https://juejin.cn/user/2664871918047063
 * @网站 https://www.hreshhao.com/
 * @date 2022/11/10 10:00 上午
 * @description 动态权限过滤器，用于实现基于路径的动态权限过滤
 */
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

  @Autowired
  private MyUserDetailsService userDetailsService;
  @Autowired
  private JwtTokenUtil jwtTokenUtil;
  @Value("${jwt.tokenHeader}")
  private String tokenHeader;
  @Value("${jwt.tokenHead}")
  private String tokenHead;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    //先从authorization中获取token
    String authHeader = request.getHeader(this.tokenHeader);
    if (authHeader != null && authHeader.startsWith(this.tokenHead)) {
      String authToken = authHeader.substring(this.tokenHead.length());// The part after "Bearer "
      //从token中获取用户名
      String username = jwtTokenUtil.getUserNameFromToken(authToken);
      logger.info("checking username:" + username);
      if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        //myUserDetail 用户信息和权限列表
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
        //只进行token验证，验证是否超时
        if (jwtTokenUtil.validateToken(authToken, userDetails)) {
          //保存认证信息
          UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
              userDetails, null, userDetails.getAuthorities());
          authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
          logger.info("authenticated user:" + username);
          SecurityContextHolder.getContext().setAuthentication(authentication);
        }
      }
    }
    // 将请求传给下一个过滤器
    filterChain.doFilter(request, response);
  }
}
