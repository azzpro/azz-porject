package com.azz.wx.course.pojo.bo;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class EditActivityWebParam {
	
	@NotBlank(message = "请选择活动")
	private String activityCode;

	@NotBlank(message = "请填写活动名称")
    private String activityName;
	
	@NotBlank(message = "请填写活动地点")
    private String activityAddress;

	@NotNull(message = "请选择活动时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date activityTime;

    private Integer signUpLimit;

    @NotNull(message = "请选择活动状态")
    private Byte status;
    
    // 是否换了主图
 	@NotNull(message = "缺少请求参数")
 	private Integer isChangeActivityPic;
    
	private MultipartFile activityPicFile;
    
    @NotBlank(message = "请填写活动详情")
    private String activityContent;
    
    @NotNull(message = "请选择报名截止时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date deadline;
    
    @NotNull(message = "请输入价格")
    private BigDecimal price;
    
    private String modifier;

}