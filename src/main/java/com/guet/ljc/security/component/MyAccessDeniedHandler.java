package com.guet.ljc.security.component;

import cn.hutool.json.JSONUtil;
import com.guet.ljc.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author hresh
 * @博客 https://juejin.cn/user/2664871918047063
 * @网站 https://www.hreshhao.com/
 * @date 2022/11/9 10:20 下午
 * @description 自定义返回结果：没有权限访问时
 */
@Slf4j
public class MyAccessDeniedHandler implements AccessDeniedHandler {

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response,
      AccessDeniedException accessDeniedException) throws IOException, ServletException {
    log.info("Access Deny: " + accessDeniedException.getMessage());
    response.setHeader("Access-Control-Allow-Origin", "*");
    response.setHeader("Cache-Control", "no-cache");
    response.setCharacterEncoding("UTF-8");
    response.setContentType("application/json");
    response.getWriter()
        .println(JSONUtil.parse(Result.forbidden(accessDeniedException.getMessage())));
    response.getWriter().flush();
  }
}
