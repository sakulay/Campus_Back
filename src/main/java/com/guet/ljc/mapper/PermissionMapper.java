package com.guet.ljc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guet.ljc.model.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author hresh
 * @date 2021/5/5 10:45
 * @description
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

    List<Permission> findPermissionsByUserId(Long userId);

    List<Permission> findAll();
}
