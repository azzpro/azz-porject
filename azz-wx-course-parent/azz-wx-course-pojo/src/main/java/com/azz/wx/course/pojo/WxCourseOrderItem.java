package com.azz.wx.course.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class WxCourseOrderItem implements Serializable {
    /**
     * 主键id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * 订单id
     *
     * @mbg.generated
     */
    private String orderCode;

    /**
     * 所属产品编码
     *
     * @mbg.generated
     */
    private String courseCode;

    /**
     * 课程名称
     *
     * @mbg.generated
     */
    private String courseName;

    /**
     * 分类名称
     *
     * @mbg.generated
     */
    private String classificationName;

    /**
     * 产品参数名称
     *
     * @mbg.generated
     */
    private String courseParamsName;

    /**
     * 品牌名称
     *
     * @mbg.generated
     */
    private String brandName;

    /**
     * 课程价格
     *
     * @mbg.generated
     */
    private BigDecimal price;

    /**
     * 开课信息编码
     *
     * @mbg.generated
     */
    private String startClassCode;

    /**
     * 开课名称
     *
     * @mbg.generated
     */
    private String startClassName;

    /**
     * 开课时间
     *
     * @mbg.generated
     */
    private Date startClassTime;

    /**
     * 开课人数
     *
     * @mbg.generated
     */
    private Integer peopleNumber;

    /**
     * 开课地点
     *
     * @mbg.generated
     */
    private String location;

    /**
     * 下单数量
     *
     * @mbg.generated
     */
    private Integer quantity;

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
     * 最后修改时间
     *
     * @mbg.generated
     */
    private Date modifyTime;

    private static final long serialVersionUID = 1L;

    /**
     * 主键id<br/>
     * 返回值对应的表列名 wx_course_order_item.id
     *
     * @return 返回值对应 wx_course_order_item.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键id<br/>
     * wx_course_order_item.id
     *
     * @param id 值对应 wx_course_order_item.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 订单id<br/>
     * 返回值对应的表列名 wx_course_order_item.order_code
     *
     * @return 返回值对应 wx_course_order_item.order_code
     *
     * @mbg.generated
     */
    public String getOrderCode() {
        return orderCode;
    }

    /**
     * 订单id<br/>
     * wx_course_order_item.order_code
     *
     * @param orderCode 值对应 wx_course_order_item.order_code
     *
     * @mbg.generated
     */
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
    }

    /**
     * 所属产品编码<br/>
     * 返回值对应的表列名 wx_course_order_item.course_code
     *
     * @return 返回值对应 wx_course_order_item.course_code
     *
     * @mbg.generated
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * 所属产品编码<br/>
     * wx_course_order_item.course_code
     *
     * @param courseCode 值对应 wx_course_order_item.course_code
     *
     * @mbg.generated
     */
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode == null ? null : courseCode.trim();
    }

    /**
     * 课程名称<br/>
     * 返回值对应的表列名 wx_course_order_item.course_name
     *
     * @return 返回值对应 wx_course_order_item.course_name
     *
     * @mbg.generated
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * 课程名称<br/>
     * wx_course_order_item.course_name
     *
     * @param courseName 值对应 wx_course_order_item.course_name
     *
     * @mbg.generated
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName == null ? null : courseName.trim();
    }

    /**
     * 分类名称<br/>
     * 返回值对应的表列名 wx_course_order_item.classification_name
     *
     * @return 返回值对应 wx_course_order_item.classification_name
     *
     * @mbg.generated
     */
    public String getClassificationName() {
        return classificationName;
    }

    /**
     * 分类名称<br/>
     * wx_course_order_item.classification_name
     *
     * @param classificationName 值对应 wx_course_order_item.classification_name
     *
     * @mbg.generated
     */
    public void setClassificationName(String classificationName) {
        this.classificationName = classificationName == null ? null : classificationName.trim();
    }

    /**
     * 产品参数名称<br/>
     * 返回值对应的表列名 wx_course_order_item.course_params_name
     *
     * @return 返回值对应 wx_course_order_item.course_params_name
     *
     * @mbg.generated
     */
    public String getCourseParamsName() {
        return courseParamsName;
    }

    /**
     * 产品参数名称<br/>
     * wx_course_order_item.course_params_name
     *
     * @param courseParamsName 值对应 wx_course_order_item.course_params_name
     *
     * @mbg.generated
     */
    public void setCourseParamsName(String courseParamsName) {
        this.courseParamsName = courseParamsName == null ? null : courseParamsName.trim();
    }

    /**
     * 品牌名称<br/>
     * 返回值对应的表列名 wx_course_order_item.brand_name
     *
     * @return 返回值对应 wx_course_order_item.brand_name
     *
     * @mbg.generated
     */
    public String getBrandName() {
        return brandName;
    }

    /**
     * 品牌名称<br/>
     * wx_course_order_item.brand_name
     *
     * @param brandName 值对应 wx_course_order_item.brand_name
     *
     * @mbg.generated
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName == null ? null : brandName.trim();
    }

    /**
     * 课程价格<br/>
     * 返回值对应的表列名 wx_course_order_item.price
     *
     * @return 返回值对应 wx_course_order_item.price
     *
     * @mbg.generated
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 课程价格<br/>
     * wx_course_order_item.price
     *
     * @param price 值对应 wx_course_order_item.price
     *
     * @mbg.generated
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 开课信息编码<br/>
     * 返回值对应的表列名 wx_course_order_item.start_class_code
     *
     * @return 返回值对应 wx_course_order_item.start_class_code
     *
     * @mbg.generated
     */
    public String getStartClassCode() {
        return startClassCode;
    }

    /**
     * 开课信息编码<br/>
     * wx_course_order_item.start_class_code
     *
     * @param startClassCode 值对应 wx_course_order_item.start_class_code
     *
     * @mbg.generated
     */
    public void setStartClassCode(String startClassCode) {
        this.startClassCode = startClassCode == null ? null : startClassCode.trim();
    }

    /**
     * 开课名称<br/>
     * 返回值对应的表列名 wx_course_order_item.start_class_name
     *
     * @return 返回值对应 wx_course_order_item.start_class_name
     *
     * @mbg.generated
     */
    public String getStartClassName() {
        return startClassName;
    }

    /**
     * 开课名称<br/>
     * wx_course_order_item.start_class_name
     *
     * @param startClassName 值对应 wx_course_order_item.start_class_name
     *
     * @mbg.generated
     */
    public void setStartClassName(String startClassName) {
        this.startClassName = startClassName == null ? null : startClassName.trim();
    }

    /**
     * 开课时间<br/>
     * 返回值对应的表列名 wx_course_order_item.start_class_time
     *
     * @return 返回值对应 wx_course_order_item.start_class_time
     *
     * @mbg.generated
     */
    public Date getStartClassTime() {
        return startClassTime;
    }

    /**
     * 开课时间<br/>
     * wx_course_order_item.start_class_time
     *
     * @param startClassTime 值对应 wx_course_order_item.start_class_time
     *
     * @mbg.generated
     */
    public void setStartClassTime(Date startClassTime) {
        this.startClassTime = startClassTime;
    }

    /**
     * 开课人数<br/>
     * 返回值对应的表列名 wx_course_order_item.people_number
     *
     * @return 返回值对应 wx_course_order_item.people_number
     *
     * @mbg.generated
     */
    public Integer getPeopleNumber() {
        return peopleNumber;
    }

    /**
     * 开课人数<br/>
     * wx_course_order_item.people_number
     *
     * @param peopleNumber 值对应 wx_course_order_item.people_number
     *
     * @mbg.generated
     */
    public void setPeopleNumber(Integer peopleNumber) {
        this.peopleNumber = peopleNumber;
    }

    /**
     * 开课地点<br/>
     * 返回值对应的表列名 wx_course_order_item.location
     *
     * @return 返回值对应 wx_course_order_item.location
     *
     * @mbg.generated
     */
    public String getLocation() {
        return location;
    }

    /**
     * 开课地点<br/>
     * wx_course_order_item.location
     *
     * @param location 值对应 wx_course_order_item.location
     *
     * @mbg.generated
     */
    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    /**
     * 下单数量<br/>
     * 返回值对应的表列名 wx_course_order_item.quantity
     *
     * @return 返回值对应 wx_course_order_item.quantity
     *
     * @mbg.generated
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * 下单数量<br/>
     * wx_course_order_item.quantity
     *
     * @param quantity 值对应 wx_course_order_item.quantity
     *
     * @mbg.generated
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * 创建人<br/>
     * 返回值对应的表列名 wx_course_order_item.creator
     *
     * @return 返回值对应 wx_course_order_item.creator
     *
     * @mbg.generated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建人<br/>
     * wx_course_order_item.creator
     *
     * @param creator 值对应 wx_course_order_item.creator
     *
     * @mbg.generated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 创建时间<br/>
     * 返回值对应的表列名 wx_course_order_item.create_time
     *
     * @return 返回值对应 wx_course_order_item.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间<br/>
     * wx_course_order_item.create_time
     *
     * @param createTime 值对应 wx_course_order_item.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改人<br/>
     * 返回值对应的表列名 wx_course_order_item.modifier
     *
     * @return 返回值对应 wx_course_order_item.modifier
     *
     * @mbg.generated
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 修改人<br/>
     * wx_course_order_item.modifier
     *
     * @param modifier 值对应 wx_course_order_item.modifier
     *
     * @mbg.generated
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * 最后修改时间<br/>
     * 返回值对应的表列名 wx_course_order_item.modify_time
     *
     * @return 返回值对应 wx_course_order_item.modify_time
     *
     * @mbg.generated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 最后修改时间<br/>
     * wx_course_order_item.modify_time
     *
     * @param modifyTime 值对应 wx_course_order_item.modify_time
     *
     * @mbg.generated
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        WxCourseOrderItem other = (WxCourseOrderItem) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrderCode() == null ? other.getOrderCode() == null : this.getOrderCode().equals(other.getOrderCode()))
            && (this.getCourseCode() == null ? other.getCourseCode() == null : this.getCourseCode().equals(other.getCourseCode()))
            && (this.getCourseName() == null ? other.getCourseName() == null : this.getCourseName().equals(other.getCourseName()))
            && (this.getClassificationName() == null ? other.getClassificationName() == null : this.getClassificationName().equals(other.getClassificationName()))
            && (this.getCourseParamsName() == null ? other.getCourseParamsName() == null : this.getCourseParamsName().equals(other.getCourseParamsName()))
            && (this.getBrandName() == null ? other.getBrandName() == null : this.getBrandName().equals(other.getBrandName()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getStartClassCode() == null ? other.getStartClassCode() == null : this.getStartClassCode().equals(other.getStartClassCode()))
            && (this.getStartClassName() == null ? other.getStartClassName() == null : this.getStartClassName().equals(other.getStartClassName()))
            && (this.getStartClassTime() == null ? other.getStartClassTime() == null : this.getStartClassTime().equals(other.getStartClassTime()))
            && (this.getPeopleNumber() == null ? other.getPeopleNumber() == null : this.getPeopleNumber().equals(other.getPeopleNumber()))
            && (this.getLocation() == null ? other.getLocation() == null : this.getLocation().equals(other.getLocation()))
            && (this.getQuantity() == null ? other.getQuantity() == null : this.getQuantity().equals(other.getQuantity()))
            && (this.getCreator() == null ? other.getCreator() == null : this.getCreator().equals(other.getCreator()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getModifier() == null ? other.getModifier() == null : this.getModifier().equals(other.getModifier()))
            && (this.getModifyTime() == null ? other.getModifyTime() == null : this.getModifyTime().equals(other.getModifyTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOrderCode() == null) ? 0 : getOrderCode().hashCode());
        result = prime * result + ((getCourseCode() == null) ? 0 : getCourseCode().hashCode());
        result = prime * result + ((getCourseName() == null) ? 0 : getCourseName().hashCode());
        result = prime * result + ((getClassificationName() == null) ? 0 : getClassificationName().hashCode());
        result = prime * result + ((getCourseParamsName() == null) ? 0 : getCourseParamsName().hashCode());
        result = prime * result + ((getBrandName() == null) ? 0 : getBrandName().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getStartClassCode() == null) ? 0 : getStartClassCode().hashCode());
        result = prime * result + ((getStartClassName() == null) ? 0 : getStartClassName().hashCode());
        result = prime * result + ((getStartClassTime() == null) ? 0 : getStartClassTime().hashCode());
        result = prime * result + ((getPeopleNumber() == null) ? 0 : getPeopleNumber().hashCode());
        result = prime * result + ((getLocation() == null) ? 0 : getLocation().hashCode());
        result = prime * result + ((getQuantity() == null) ? 0 : getQuantity().hashCode());
        result = prime * result + ((getCreator() == null) ? 0 : getCreator().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getModifier() == null) ? 0 : getModifier().hashCode());
        result = prime * result + ((getModifyTime() == null) ? 0 : getModifyTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderCode=").append(orderCode);
        sb.append(", courseCode=").append(courseCode);
        sb.append(", courseName=").append(courseName);
        sb.append(", classificationName=").append(classificationName);
        sb.append(", courseParamsName=").append(courseParamsName);
        sb.append(", brandName=").append(brandName);
        sb.append(", price=").append(price);
        sb.append(", startClassCode=").append(startClassCode);
        sb.append(", startClassName=").append(startClassName);
        sb.append(", startClassTime=").append(startClassTime);
        sb.append(", peopleNumber=").append(peopleNumber);
        sb.append(", location=").append(location);
        sb.append(", quantity=").append(quantity);
        sb.append(", creator=").append(creator);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifier=").append(modifier);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}