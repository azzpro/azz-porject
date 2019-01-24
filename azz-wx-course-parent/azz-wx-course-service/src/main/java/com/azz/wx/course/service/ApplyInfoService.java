/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年1月22日 上午10:40:05
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.wx.course.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.JSR303ErrorCode;
import com.azz.exception.JSR303ValidationException;
import com.azz.util.JSR303ValidateUtils;
import com.azz.wx.course.mapper.WxCourseApplyInfoMapper;
import com.azz.wx.course.pojo.WxCourseApplyInfo;
import com.azz.wx.course.pojo.bo.AddCourseApplyParam;
import com.azz.wx.course.pojo.bo.EditCourseApplyIsDefaultParam;
import com.azz.wx.course.pojo.bo.EditCourseApplyParam;
import com.azz.wx.course.pojo.vo.CourseSignUpInfo;

/**
 * <P>报名信息业务</P>
 * @version 1.0
 * @author 黄智聪  2019年1月22日 上午10:40:05
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class ApplyInfoService {
	
	@Autowired
	private WxCourseApplyInfoMapper wxCourseApplyInfoMapper;
	
	
	/**
     * <p>获取课程报名信息</p>
     * @param userCode
     * @return
     * @author 彭斌  2019年1月23日 下午6:23:29
     */
    public JsonResult<List<CourseSignUpInfo>> getCourseSignUp(String userCode){
        List<CourseSignUpInfo> listInfo = wxCourseApplyInfoMapper.getCourseListByUserCode(userCode);
        return JsonResult.successJsonResult(listInfo);
    }
    
    /**
     * <p>新增课程报名信息</p>
     * @param param
     * @return
     * @author 彭斌  2019年1月24日 上午10:57:50
     */
    public JsonResult<String> addCourseApply(@RequestBody AddCourseApplyParam param){
        // 基础参数非空校验
        JSR303ValidateUtils.validate(param);
        
        WxCourseApplyInfo record = new WxCourseApplyInfo();
        Long currentTime = System.currentTimeMillis();
        record.setApplyInfoCode("A" + String.valueOf(currentTime));
        record.setCompany(param.getCompany());
        record.setCreateTime(new Date());
        record.setCreator(param.getUserCode());
        record.setEmail(param.getEmail());
        record.setGraduateSchool(param.getGraduateSchool());
        record.setPersonName(param.getPersonName());
        record.setPhoneNumber(param.getPhoneNumber());
        record.setUserCode(param.getUserCode());
        wxCourseApplyInfoMapper.insertSelective(record);
        return JsonResult.successJsonResult();
    }


    /**
     * <p>编辑申请课程信息</p>
     * @param param
     * @return
     * @author 彭斌  2019年1月24日 上午11:12:10
     */
    public JsonResult<String> editCourseApply(@RequestBody EditCourseApplyParam param){
        // 基础参数非空校验
        JSR303ValidateUtils.validate(param);
        
        CourseSignUpInfo csui = wxCourseApplyInfoMapper.getCourseInfoByApplyCode(param.getApplyCode());
        if(null == csui) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,
                    "课程申请信息不存在,不允许修改");
        }
        WxCourseApplyInfo record = new WxCourseApplyInfo();
        record.setApplyInfoCode(param.getApplyCode());
        record.setCompany(param.getCompany());
        record.setEmail(param.getEmail());
        record.setGraduateSchool(param.getGraduateSchool());
        record.setModifier(param.getUserCode());
        record.setModifyTime(new Date());
        record.setPersonName(param.getPersonName());
        record.setPhoneNumber(param.getPhoneNumber());
        wxCourseApplyInfoMapper.updateByApplyInfoCode(record);
        return JsonResult.successJsonResult();
    }

    /**
     * 
     * <p>删除申请信息</p>
     * @param applyCode
     * @return
     * @author 彭斌  2019年1月24日 上午11:20:27
     */
    public JsonResult<String> deletCourseApply(String applyCode){
        if(null == applyCode) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,
                    "参数异常，请检查");
        }
        CourseSignUpInfo csui = wxCourseApplyInfoMapper.getCourseInfoByApplyCode(applyCode);
        if(null == csui) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,
                    "课程申请信息不存在,不允许修改");
        }
        wxCourseApplyInfoMapper.deleteApplyInfo(applyCode);
        return JsonResult.successJsonResult();
    }
    
    /**
     * <p>设置默认</p>
     * @param applyCode
     * @return
     * @author 彭斌  2019年1月24日 上午11:22:09
     */
    public JsonResult<String> setDefault(String applyCode){
        if(null == applyCode) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,
                    "参数异常，请检查");
        }
        
        CourseSignUpInfo csui = wxCourseApplyInfoMapper.getCourseInfoByApplyCode(applyCode);
        if(null == csui) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,
                    "课程申请信息不存在,不允许设置默认");
        }
        
        List<CourseSignUpInfo> listInfo = wxCourseApplyInfoMapper.getCourseListByUserCode(csui.getUserCode());
        // 将之前的申请取消为否默认
        for (int i = 0; i < listInfo.size(); i++) {
            Byte isDefault = listInfo.get(i).getIsDefault();
            String dbApplyCode = listInfo.get(i).getApplyCode();
            if(1 == isDefault) {
                if(dbApplyCode.equals(applyCode)) {
                    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,
                            "已经为默认设置");
                }
                EditCourseApplyIsDefaultParam param = new EditCourseApplyIsDefaultParam();
                param.setApplyCode(dbApplyCode);
                param.setIsDefualt((byte)0);
                wxCourseApplyInfoMapper.updateIsDefaultByApplyInfoCode(param);
            }
        }
        
        // 设置新的默认
        EditCourseApplyIsDefaultParam param = new EditCourseApplyIsDefaultParam();
        param.setApplyCode(applyCode);
        param.setIsDefualt((byte)1);
        wxCourseApplyInfoMapper.updateIsDefaultByApplyInfoCode(param);
        
        return JsonResult.successJsonResult();
    }


    /**
     * <p>获取课程申请详情</p>
     * @param applyCode
     * @return
     * @author 彭斌  2019年1月24日 下午2:09:40
     */
    public JsonResult<CourseSignUpInfo> getCourseSignUpInfo(String applyCode){
        CourseSignUpInfo csui = wxCourseApplyInfoMapper.getCourseInfoByApplyCode(applyCode);
        return JsonResult.successJsonResult(csui);
    }
}

