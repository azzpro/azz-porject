package com.azz.wx.course.pojo.vo;

import java.util.Date;

import lombok.Data;

@Data
public class SolicitContributionInfo {

    /**
     * 征稿编码
     *
     * @mbg.generated
     */
    private String solicitContributionCode;

    /**
     * 征稿名称
     *
     * @mbg.generated
     */
    private String solicitContributionName;

    /**
     * 征稿主图名称
     *
     * @mbg.generated
     */
    private String solicitContributionPicName;

    /**
     * 征稿主图url
     *
     * @mbg.generated
     */
    private String solicitContributionPicUrl;

    /**
     * 征稿状态 0删除 1上架  2下架 
     *
     * @mbg.generated
     */
    private Byte solicitContributionStatus;

    /**
     * 投票数
     *
     * @mbg.generated
     */
    private Integer voteCount;

    /**
     * 备注
     *
     * @mbg.generated
     */
    private String remark;

    /**
     * 创建时间
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     * 征稿内容
     *
     * @mbg.generated
     */
    private String solicitContributionContent;
}