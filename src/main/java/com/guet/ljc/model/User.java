package com.guet.ljc.model;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author hresh
 * @date 2021/5/4 21:13
 * @description
 */
@TableName("user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements Serializable {

  @TableId(value = "id", type = IdType.AUTO)
  private Long id;
  private String username;
  private String password;
  private String phone;

  @Schema(description = "创建时间")
  @TableField(value = "created_date", fill = FieldFill.INSERT)
  @DateTimeFormat(
      pattern = "yyyy-MM-dd HH:mm:ss"
  )
  @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss"
  )
  private LocalDateTime createdDate;

  @Schema(description = "修改时间")
  @TableField(value = "last_modified_date", fill = FieldFill.INSERT_UPDATE)
  @DateTimeFormat(
      pattern = "yyyy-MM-dd HH:mm:ss"
  )
  @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss"
  )
  private LocalDateTime lastModifiedDate;

}
