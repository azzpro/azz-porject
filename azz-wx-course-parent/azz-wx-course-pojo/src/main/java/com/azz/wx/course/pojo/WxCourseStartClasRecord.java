package com.azz.wx.course.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxCourseStartClasRecord implements Serializable {
    /**
     * 主键id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * 所属课程编码
     *
     * @mbg.generated
     */
    private String courseCode;

    /**
     * 开课编码
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
     * 开课课时
     *
     * @mbg.generated
     */
    private Integer hours;

    /**
     * 开课人数
     *
     * @mbg.generated
     */
    private Integer peopleNumber;

    /**
     * 开课价格
     *
     * @mbg.generated
     */
    private BigDecimal price;

    /**
     * 开课房间
     *
     * @mbg.generated
     */
    private String room;

    /**
     * 开课地点
     *
     * @mbg.generated
     */
    private String location;

    /**
     * 经度
     *
     * @mbg.generated
     */
    private BigDecimal longitude;

    /**
     * 纬度
     *
     * @mbg.generated
     */
    private BigDecimal latitude;

    /**
     * 课程状态   删除0  上架1  下架2  
     *
     * @mbg.generated
     */
    private Byte status;

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

    private static final long serialVersionUID = 1L;

    /**
     * 主键id<br/>
     * 返回值对应的表列名 wx_course_start_class_record.id
     *
     * @return 返回值对应 wx_course_start_class_record.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键id<br/>
     * wx_course_start_class_record.id
     *
     * @param id 值对应 wx_course_start_class_record.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 所属课程编码<br/>
     * 返回值对应的表列名 wx_course_start_class_record.course_code
     *
     * @return 返回值对应 wx_course_start_class_record.course_code
     *
     * @mbg.generated
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * 所属课程编码<br/>
     * wx_course_start_class_record.course_code
     *
     * @param courseCode 值对应 wx_course_start_class_record.course_code
     *
     * @mbg.generated
     */
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode == null ? null : courseCode.trim();
    }

    /**
     * 开课编码<br/>
     * 返回值对应的表列名 wx_course_start_class_record.start_class_code
     *
     * @return 返回值对应 wx_course_start_class_record.start_class_code
     *
     * @mbg.generated
     */
    public String getStartClassCode() {
        return startClassCode;
    }

    /**
     * 开课编码<br/>
     * wx_course_start_class_record.start_class_code
     *
     * @param startClassCode 值对应 wx_course_start_class_record.start_class_code
     *
     * @mbg.generated
     */
    public void setStartClassCode(String startClassCode) {
        this.startClassCode = startClassCode == null ? null : startClassCode.trim();
    }

    /**
     * 开课名称<br/>
     * 返回值对应的表列名 wx_course_start_class_record.start_class_name
     *
     * @return 返回值对应 wx_course_start_class_record.start_class_name
     *
     * @mbg.generated
     */
    public String getStartClassName() {
        return startClassName;
    }

    /**
     * 开课名称<br/>
     * wx_course_start_class_record.start_class_name
     *
     * @param startClassName 值对应 wx_course_start_class_record.start_class_name
     *
     * @mbg.generated
     */
    public void setStartClassName(String startClassName) {
        this.startClassName = startClassName == null ? null : startClassName.trim();
    }

    /**
     * 开课时间<br/>
     * 返回值对应的表列名 wx_course_start_class_record.start_class_time
     *
     * @return 返回值对应 wx_course_start_class_record.start_class_time
     *
     * @mbg.generated
     */
    public Date getStartClassTime() {
        return startClassTime;
    }

    /**
     * 开课时间<br/>
     * wx_course_start_class_record.start_class_time
     *
     * @param startClassTime 值对应 wx_course_start_class_record.start_class_time
     *
     * @mbg.generated
     */
    public void setStartClassTime(Date startClassTime) {
        this.startClassTime = startClassTime;
    }

    /**
     * 开课课时<br/>
     * 返回值对应的表列名 wx_course_start_class_record.hours
     *
     * @return 返回值对应 wx_course_start_class_record.hours
     *
     * @mbg.generated
     */
    public Integer getHours() {
        return hours;
    }

    /**
     * 开课课时<br/>
     * wx_course_start_class_record.hours
     *
     * @param hours 值对应 wx_course_start_class_record.hours
     *
     * @mbg.generated
     */
    public void setHours(Integer hours) {
        this.hours = hours;
    }

    /**
     * 开课人数<br/>
     * 返回值对应的表列名 wx_course_start_class_record.people_number
     *
     * @return 返回值对应 wx_course_start_class_record.people_number
     *
     * @mbg.generated
     */
    public Integer getPeopleNumber() {
        return peopleNumber;
    }

    /**
     * 开课人数<br/>
     * wx_course_start_class_record.people_number
     *
     * @param peopleNumber 值对应 wx_course_start_class_record.people_number
     *
     * @mbg.generated
     */
    public void setPeopleNumber(Integer peopleNumber) {
        this.peopleNumber = peopleNumber;
    }

    /**
     * 开课价格<br/>
     * 返回值对应的表列名 wx_course_start_class_record.price
     *
     * @return 返回值对应 wx_course_start_class_record.price
     *
     * @mbg.generated
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 开课价格<br/>
     * wx_course_start_class_record.price
     *
     * @param price 值对应 wx_course_start_class_record.price
     *
     * @mbg.generated
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 开课房间<br/>
     * 返回值对应的表列名 wx_course_start_class_record.room
     *
     * @return 返回值对应 wx_course_start_class_record.room
     *
     * @mbg.generated
     */
    public String getRoom() {
        return room;
    }

    /**
     * 开课房间<br/>
     * wx_course_start_class_record.room
     *
     * @param room 值对应 wx_course_start_class_record.room
     *
     * @mbg.generated
     */
    public void setRoom(String room) {
        this.room = room == null ? null : room.trim();
    }

    /**
     * 开课地点<br/>
     * 返回值对应的表列名 wx_course_start_class_record.location
     *
     * @return 返回值对应 wx_course_start_class_record.location
     *
     * @mbg.generated
     */
    public String getLocation() {
        return location;
    }

    /**
     * 开课地点<br/>
     * wx_course_start_class_record.location
     *
     * @param location 值对应 wx_course_start_class_record.location
     *
     * @mbg.generated
     */
    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    /**
     * 经度<br/>
     * 返回值对应的表列名 wx_course_start_class_record.longitude
     *
     * @return 返回值对应 wx_course_start_class_record.longitude
     *
     * @mbg.generated
     */
    public BigDecimal getLongitude() {
        return longitude;
    }

    /**
     * 经度<br/>
     * wx_course_start_class_record.longitude
     *
     * @param longitude 值对应 wx_course_start_class_record.longitude
     *
     * @mbg.generated
     */
    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    /**
     * 纬度<br/>
     * 返回值对应的表列名 wx_course_start_class_record.latitude
     *
     * @return 返回值对应 wx_course_start_class_record.latitude
     *
     * @mbg.generated
     */
    public BigDecimal getLatitude() {
        return latitude;
    }

    /**
     * 纬度<br/>
     * wx_course_start_class_record.latitude
     *
     * @param latitude 值对应 wx_course_start_class_record.latitude
     *
     * @mbg.generated
     */
    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    /**
     * 课程状态   删除0  上架1  下架2  <br/>
     * 返回值对应的表列名 wx_course_start_class_record.status
     *
     * @return 返回值对应 wx_course_start_class_record.status
     *
     * @mbg.generated
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 课程状态   删除0  上架1  下架2  <br/>
     * wx_course_start_class_record.status
     *
     * @param status 值对应 wx_course_start_class_record.status
     *
     * @mbg.generated
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 创建人<br/>
     * 返回值对应的表列名 wx_course_start_class_record.creator
     *
     * @return 返回值对应 wx_course_start_class_record.creator
     *
     * @mbg.generated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建人<br/>
     * wx_course_start_class_record.creator
     *
     * @param creator 值对应 wx_course_start_class_record.creator
     *
     * @mbg.generated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 创建时间<br/>
     * 返回值对应的表列名 wx_course_start_class_record.create_time
     *
     * @return 返回值对应 wx_course_start_class_record.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间<br/>
     * wx_course_start_class_record.create_time
     *
     * @param createTime 值对应 wx_course_start_class_record.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改人<br/>
     * 返回值对应的表列名 wx_course_start_class_record.modifier
     *
     * @return 返回值对应 wx_course_start_class_record.modifier
     *
     * @mbg.generated
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 修改人<br/>
     * wx_course_start_class_record.modifier
     *
     * @param modifier 值对应 wx_course_start_class_record.modifier
     *
     * @mbg.generated
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * 修改时间<br/>
     * 返回值对应的表列名 wx_course_start_class_record.modify_time
     *
     * @return 返回值对应 wx_course_start_class_record.modify_time
     *
     * @mbg.generated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 修改时间<br/>
     * wx_course_start_class_record.modify_time
     *
     * @param modifyTime 值对应 wx_course_start_class_record.modify_time
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
        WxCourseStartClasRecord other = (WxCourseStartClasRecord) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCourseCode() == null ? other.getCourseCode() == null : this.getCourseCode().equals(other.getCourseCode()))
            && (this.getStartClassCode() == null ? other.getStartClassCode() == null : this.getStartClassCode().equals(other.getStartClassCode()))
            && (this.getStartClassName() == null ? other.getStartClassName() == null : this.getStartClassName().equals(other.getStartClassName()))
            && (this.getStartClassTime() == null ? other.getStartClassTime() == null : this.getStartClassTime().equals(other.getStartClassTime()))
            && (this.getHours() == null ? other.getHours() == null : this.getHours().equals(other.getHours()))
            && (this.getPeopleNumber() == null ? other.getPeopleNumber() == null : this.getPeopleNumber().equals(other.getPeopleNumber()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getRoom() == null ? other.getRoom() == null : this.getRoom().equals(other.getRoom()))
            && (this.getLocation() == null ? other.getLocation() == null : this.getLocation().equals(other.getLocation()))
            && (this.getLongitude() == null ? other.getLongitude() == null : this.getLongitude().equals(other.getLongitude()))
            && (this.getLatitude() == null ? other.getLatitude() == null : this.getLatitude().equals(other.getLatitude()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
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
        result = prime * result + ((getCourseCode() == null) ? 0 : getCourseCode().hashCode());
        result = prime * result + ((getStartClassCode() == null) ? 0 : getStartClassCode().hashCode());
        result = prime * result + ((getStartClassName() == null) ? 0 : getStartClassName().hashCode());
        result = prime * result + ((getStartClassTime() == null) ? 0 : getStartClassTime().hashCode());
        result = prime * result + ((getHours() == null) ? 0 : getHours().hashCode());
        result = prime * result + ((getPeopleNumber() == null) ? 0 : getPeopleNumber().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getRoom() == null) ? 0 : getRoom().hashCode());
        result = prime * result + ((getLocation() == null) ? 0 : getLocation().hashCode());
        result = prime * result + ((getLongitude() == null) ? 0 : getLongitude().hashCode());
        result = prime * result + ((getLatitude() == null) ? 0 : getLatitude().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
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
        sb.append(", courseCode=").append(courseCode);
        sb.append(", startClassCode=").append(startClassCode);
        sb.append(", startClassName=").append(startClassName);
        sb.append(", startClassTime=").append(startClassTime);
        sb.append(", hours=").append(hours);
        sb.append(", peopleNumber=").append(peopleNumber);
        sb.append(", price=").append(price);
        sb.append(", room=").append(room);
        sb.append(", location=").append(location);
        sb.append(", longitude=").append(longitude);
        sb.append(", latitude=").append(latitude);
        sb.append(", status=").append(status);
        sb.append(", creator=").append(creator);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifier=").append(modifier);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}