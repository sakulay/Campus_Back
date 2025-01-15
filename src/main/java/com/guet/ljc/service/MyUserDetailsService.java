package com.guet.ljc.service;

import com.guet.ljc.dto.MyUserDetails;
import com.guet.ljc.mapper.PermissionMapper;
import com.guet.ljc.mapper.UserMapper;
import com.guet.ljc.model.Permission;
import com.guet.ljc.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * @author hresh
 * @date 2021/5/4 20:35
 * @description 自定义UserDetailsService
 */
@Component
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

  private final UserMapper userMapper;
  private final PermissionMapper permissionMapper;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    //根据账号去数据库查询...
    User user = userMapper.selectByUserName(username);
    if (!Objects.isNull(user)) {
      List<Permission> permissionList = permissionMapper.findPermissionsByUserId(user.getId());
      return MyUserDetails.builder().user(user).permissionList(permissionList).build();
    }

    throw new UsernameNotFoundException("用户名或密码错误");
  }

}
