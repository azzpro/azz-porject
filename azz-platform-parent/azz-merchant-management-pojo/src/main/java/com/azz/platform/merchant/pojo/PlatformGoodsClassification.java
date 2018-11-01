package com.azz.platform.merchant.pojo;


import java.io.Serializable;
import java.util.Date;

public class PlatformGoodsClassification implements Serializable {
    private Long id;

    /**
     * 分类编码
     */
    private String assortmentCode;
    
    /**
     * 上级分类编号，可为空
     *
     * @mbg.generated
     */
    private String assortmentParentCode;

    /**
     * 分类名称
     *
     * @mbg.generated
     */
    private String assortmentName;

    /**
     * 分类层级
     *
     * @mbg.generated
     */
    private Byte assortmentTop;


    /**
     * 分类排序(默认0)
     *
     * @mbg.generated
     */
    private Integer assortmentSort;

    /**
     * 分类图片url
     *
     * @mbg.generated
     */
    private String assortmentPicUrl;

    /**
     * 分类图片名称
     *
     * @mbg.generated
     */
    private String assortmentPicName;

    /**
     * 创建人
     *
     * @mbg.generated
     */
    private String creator;

    /**
     * 创建时间
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     * 修改人
     *
     * @mbg.generated
     */
    private String modifier;

    /**
     * 修改时间
     *
     * @mbg.generated
     */
    private Date modifyTime;
    
    /**
     * 状态
     */
    private Integer status;

    private static final long serialVersionUID = 1L;

    
    public PlatformGoodsClassification(Long id, String assortmentCode, String assortmentParentCode,
			String assortmentName, Byte assortmentTop, Integer assortmentSort,
			String assortmentPicUrl, String assortmentPicName, String creator, Date createTime, String modifier,
			Date modifyTime, Integer status) {
		super();
		this.id = id;
		this.assortmentCode = assortmentCode;
		this.assortmentParentCode = assortmentParentCode;
		this.assortmentName = assortmentName;
		this.assortmentTop = assortmentTop;
		this.assortmentSort = assortmentSort;
		this.assortmentPicUrl = assortmentPicUrl;
		this.assortmentPicName = assortmentPicName;
		this.creator = creator;
		this.createTime = createTime;
		this.modifier = modifier;
		this.modifyTime = modifyTime;
		this.status = status;
	}

	public PlatformGoodsClassification() {
		super();
	}

	/**
     * <br/>
     * 返回值对应的表列名 platform_goods_classification.id
     *
     * @return 返回值对应 platform_goods_classification.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * <br/>
     * platform_goods_classification.id
     *
     * @param id 值对应 platform_goods_classification.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    
    public String getAssortmentCode() {
        return assortmentCode;
    }

    public void setAssortmentCode(String assortmentCode) {
        this.assortmentCode = assortmentCode;
    }

    /**
     * 上级分类编号，可为空<br/>
     * 返回值对应的表列名 platform_goods_classification.assortment_parent_code
     *
     * @return 返回值对应 platform_goods_classification.assortment_parent_code
     *
     * @mbg.generated
     */
    public String getAssortmentParentCode() {
        return assortmentParentCode;
    }

    /**
     * 上级分类编号，可为空<br/>
     * platform_goods_classification.assortment_parent_code
     *
     * @param assortmentParentCode 值对应 platform_goods_classification.assortment_parent_code
     *
     * @mbg.generated
     */
    public void setAssortmentParentCode(String assortmentParentCode) {
        this.assortmentParentCode = assortmentParentCode == null ? null : assortmentParentCode.trim();
    }

    /**
     * 分类名称<br/>
     * 返回值对应的表列名 platform_goods_classification.assortment_name
     *
     * @return 返回值对应 platform_goods_classification.assortment_name
     *
     * @mbg.generated
     */
    public String getAssortmentName() {
        return assortmentName;
    }

    /**
     * 分类名称<br/>
     * platform_goods_classification.assortment_name
     *
     * @param assortmentName 值对应 platform_goods_classification.assortment_name
     *
     * @mbg.generated
     */
    public void setAssortmentName(String assortmentName) {
        this.assortmentName = assortmentName == null ? null : assortmentName.trim();
    }

    /**
     * 分类层级<br/>
     * 返回值对应的表列名 platform_goods_classification.assortment_top
     *
     * @return 返回值对应 platform_goods_classification.assortment_top
     *
     * @mbg.generated
     */
    public Byte getAssortmentTop() {
        return assortmentTop;
    }

    /**
     * 分类层级<br/>
     * platform_goods_classification.assortment_top
     *
     * @param assortmentTop 值对应 platform_goods_classification.assortment_top
     *
     * @mbg.generated
     */
    public void setAssortmentTop(Byte assortmentTop) {
        this.assortmentTop = assortmentTop;
    }


    /**
     * 分类排序(默认0)<br/>
     * 返回值对应的表列名 platform_goods_classification.assortment_sort
     *
     * @return 返回值对应 platform_goods_classification.assortment_sort
     *
     * @mbg.generated
     */
    public Integer getAssortmentSort() {
        return assortmentSort;
    }

    /**
     * 分类排序(默认0)<br/>
     * platform_goods_classification.assortment_sort
     *
     * @param assortmentSort 值对应 platform_goods_classification.assortment_sort
     *
     * @mbg.generated
     */
    public void setAssortmentSort(Integer assortmentSort) {
        this.assortmentSort = assortmentSort;
    }

    /**
     * 分类图片url<br/>
     * 返回值对应的表列名 platform_goods_classification.assortment_pic_url
     *
     * @return 返回值对应 platform_goods_classification.assortment_pic_url
     *
     * @mbg.generated
     */
    public String getAssortmentPicUrl() {
        return assortmentPicUrl;
    }

    /**
     * 分类图片url<br/>
     * platform_goods_classification.assortment_pic_url
     *
     * @param assortmentPicUrl 值对应 platform_goods_classification.assortment_pic_url
     *
     * @mbg.generated
     */
    public void setAssortmentPicUrl(String assortmentPicUrl) {
        this.assortmentPicUrl = assortmentPicUrl == null ? null : assortmentPicUrl.trim();
    }

    /**
     * 分类图片名称<br/>
     * 返回值对应的表列名 platform_goods_classification.assortment_pic_name
     *
     * @return 返回值对应 platform_goods_classification.assortment_pic_name
     *
     * @mbg.generated
     */
    public String getAssortmentPicName() {
        return assortmentPicName;
    }

    /**
     * 分类图片名称<br/>
     * platform_goods_classification.assortment_pic_name
     *
     * @param assortmentPicName 值对应 platform_goods_classification.assortment_pic_name
     *
     * @mbg.generated
     */
    public void setAssortmentPicName(String assortmentPicName) {
        this.assortmentPicName = assortmentPicName == null ? null : assortmentPicName.trim();
    }

    /**
     * 创建人<br/>
     * 返回值对应的表列名 platform_goods_classification.creator
     *
     * @return 返回值对应 platform_goods_classification.creator
     *
     * @mbg.generated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建人<br/>
     * platform_goods_classification.creator
     *
     * @param creator 值对应 platform_goods_classification.creator
     *
     * @mbg.generated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 创建时间<br/>
     * 返回值对应的表列名 platform_goods_classification.create_time
     *
     * @return 返回值对应 platform_goods_classification.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间<br/>
     * platform_goods_classification.create_time
     *
     * @param createTime 值对应 platform_goods_classification.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改人<br/>
     * 返回值对应的表列名 platform_goods_classification.modifier
     *
     * @return 返回值对应 platform_goods_classification.modifier
     *
     * @mbg.generated
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 修改人<br/>
     * platform_goods_classification.modifier
     *
     * @param modifier 值对应 platform_goods_classification.modifier
     *
     * @mbg.generated
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * 修改时间<br/>
     * 返回值对应的表列名 platform_goods_classification.modify_time
     *
     * @return 返回值对应 platform_goods_classification.modify_time
     *
     * @mbg.generated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 修改时间<br/>
     * platform_goods_classification.modify_time
     *
     * @param modifyTime 值对应 platform_goods_classification.modify_time
     *
     * @mbg.generated
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", assortmentParentCode=").append(assortmentParentCode);
        sb.append(", assortmentName=").append(assortmentName);
        sb.append(", assortmentTop=").append(assortmentTop);
        sb.append(", assortmentSort=").append(assortmentSort);
        sb.append(", assortmentPicUrl=").append(assortmentPicUrl);
        sb.append(", assortmentPicName=").append(assortmentPicName);
        sb.append(", creator=").append(creator);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifier=").append(modifier);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}