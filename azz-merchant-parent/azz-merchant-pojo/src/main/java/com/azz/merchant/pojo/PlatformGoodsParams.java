package com.azz.merchant.pojo;


import java.io.Serializable;
import java.util.Date;

public class PlatformGoodsParams implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * 参数编号
     *
     * @mbg.generated
     */
    private String paramsCode;
    
    private Long assortmentId;
    
    private String creator;
    
    private Date createTime;

    private static final long serialVersionUID = 1L;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getParamsCode() {
		return paramsCode;
	}

	public void setParamsCode(String paramsCode) {
		this.paramsCode = paramsCode;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getAssortmentId() {
		return assortmentId;
	}

	public void setAssortmentId(Long assortmentId) {
		this.assortmentId = assortmentId;
	}

	@Override
	public String toString() {
		return "PlatformGoodsParams [id=" + id + ", paramsCode=" + paramsCode + ", creator=" + creator + ", createTime="
				+ createTime + "]";
	}
    
    

}