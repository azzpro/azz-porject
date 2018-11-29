/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月28日 下午2:16:50
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.user.pojo.vo;

import java.util.Date;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年11月28日 下午2:16:50
 */
@Data
public class ImageInfo {
      private Long imageId;
      private String picUrl;
      private String jumpLink;
      private String columnName;
      private String creator;
      private Date createTime;
}

