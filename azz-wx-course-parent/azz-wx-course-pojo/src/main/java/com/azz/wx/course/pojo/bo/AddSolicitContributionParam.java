package com.azz.wx.course.pojo.bo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
public class AddSolicitContributionParam {

	@NotBlank(message = "请填写征稿名称")
    private String solicitContributionName;
	
    @NotNull(message = "请上传征稿主图")
	private SolicitContributionPic solicitContributionPicFile;
    
    @NotBlank(message = "请填写征稿详情")
    private String solicitContributionContent;
    
    private String remark;
    
    private String creator;

}