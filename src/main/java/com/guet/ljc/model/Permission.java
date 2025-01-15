package com.guet.ljc.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author hresh
 * @date 2021/5/5 10:44
 * @description 权限表
 */
@TableName("permission")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Permission implements Serializable {

  @TableId(value = "id", type = IdType.AUTO)
  private Long id;
  private String name;
  private String url;
  private LocalDateTime createdDate;
  private LocalDateTime lastModifiedDate;
}
