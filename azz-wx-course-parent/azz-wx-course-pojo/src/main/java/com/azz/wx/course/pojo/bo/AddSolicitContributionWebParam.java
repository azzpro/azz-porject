package com.azz.wx.course.pojo.bo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class AddSolicitContributionWebParam {

	@NotBlank(message = "请填写征稿名称")
    private String solicitContributionName;
	
    @NotNull(message = "请上传征稿主图")
	private MultipartFile solicitContributionPicFile;
    
    @NotBlank(message = "请填写征稿详情")
    private String solicitContributionContent;
    
    private String remark;
    
    private String creator;

}