/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月28日 下午7:15:50
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.user.pojo.bo;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年11月28日 下午7:15:50
 */
@Data
public class AddArticleWebParam {
    @NotNull(message = "参数不许为空")
    private Long indexColumnId;
    @NotBlank(message = "参数不许为空")
    private String articleTitle;
    private BigDecimal price;
    @NotBlank(message = "参数不许为空")
    private String articleDetail;
    private String remark1;
    private String remark2;
    @NotNull(message = "请上传主图")
    private MultipartFile mainPicture;
}

