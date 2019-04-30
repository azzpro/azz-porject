package com.azz.wx.course.pojo.vo;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class ActivityInfo {

    private String activityCode;

    private String activityName;

    private Integer activityStatus;

    private String activityAddress;

    private Date activityTime;

    private Integer signUpLimit;

    private Integer signUpCount;
    
    private String activityPicName;

    private String activityPicUrl;
    
    private String activityContent;
    
    private Date deadline;

    private BigDecimal price;
    
    private Byte status;

    private Date createTime;
    
    private String remark;
    
    private Integer isEvaluated;
    
    private Integer isSignUp;
    
}