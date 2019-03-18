/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年1月22日 下午1:52:22
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.wx.course.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.JSR303ErrorCode;
import com.azz.core.constants.ClientConstants;
import com.azz.core.constants.SmsConstants;
import com.azz.core.constants.SmsConstants.SmsCode;
import com.azz.core.constants.WxCourseConstants.CourseOrderStatus;
import com.azz.exception.JSR303ValidationException;
import com.azz.model.Password;
import com.azz.system.api.SystemSmsSendService;
import com.azz.system.bo.SmsCheck;
import com.azz.system.bo.SmsCodeValidation;
import com.azz.system.bo.SmsParams;
import com.azz.system.sequence.api.DbSequenceService;
import com.azz.system.vo.SmsInfo;
import com.azz.util.JSR303ValidateUtils;
import com.azz.util.ObjectUtils;
import com.azz.util.PasswordHelper;
import com.azz.util.RandomStringUtils;
import com.azz.util.SystemSeqUtils;
import com.azz.wx.course.mapper.ClientUserMapper;
import com.azz.wx.course.mapper.ClientWxUserMapper;
import com.azz.wx.course.mapper.WxCourseApplyInfoMapper;
import com.azz.wx.course.mapper.WxCourseOrderMapper;
import com.azz.wx.course.mapper.WxCourseSuggestionsMapper;
import com.azz.wx.course.pojo.ClientUser;
import com.azz.wx.course.pojo.ClientWxUser;
import com.azz.wx.course.pojo.WxCourseSuggestions;
import com.azz.wx.course.pojo.bo.AddClientWxUserParam;
import com.azz.wx.course.pojo.bo.AddCourseSuggestionsParam;
import com.azz.wx.course.pojo.bo.AddWxBindingRecordParam;
import com.azz.wx.course.pojo.bo.BindingPhomeParam;
import com.azz.wx.course.pojo.bo.SearchOrderTotalParam;
import com.azz.wx.course.pojo.vo.PersonalCenterInfo;

/**
 * <P>个人中心</P>
 * @version 1.0
 * @author 彭斌  2019年1月22日 下午1:52:22
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class PersonalCenterService {
    
    @Autowired
    private ClientWxUserMapper clientWxUserMapper;
    
    @Autowired
    private WxCourseOrderMapper wxCourseOrderMapper;
    
    @Autowired
    private WxCourseApplyInfoMapper wxCourseAapplyInfoMapper;
    
    @Autowired
    private ClientUserMapper clientUserMapper;

    @Autowired
    private SystemSmsSendService systemSmsSendService;
    
    @Autowired
    private DbSequenceService dbSequenceService;
    
    @Autowired
    private WxCourseSuggestionsMapper wxCourseSuggestionsMapper;
    /**
     * <p>获取个人中心首页相关信息</p>
     * @param wechatId
     * @return
     * @author 彭斌  2019年1月22日 下午4:40:37
     */
    public JsonResult<PersonalCenterInfo> getPersonlCenter(String openId){
        PersonalCenterInfo info = null;
        if(ObjectUtils.isNotNull(openId)) {
            ClientWxUser cwu = clientWxUserMapper.selectWxUserByOpenid(openId);
            if(null != cwu) {
                info = new PersonalCenterInfo();
                String userCode = cwu.getUserCode();
                
                // 客户头部基础信息
                info.setWechatId(openId);
                info.setAvatarUrl(cwu.getAvatarUrl());
                info.setNickName(cwu.getNickName());
                info.setUserCode(cwu.getUserCode());
                
                // 统计数值
                int allOrders = wxCourseOrderMapper.getCountOrders(userCode);
                int notPaid = wxCourseOrderMapper.getCountOrdersStatus(new SearchOrderTotalParam(CourseOrderStatus.NOT_PAID.getValue(),userCode));
                int notConfirmed = wxCourseOrderMapper.getCountOrdersStatus(new SearchOrderTotalParam(CourseOrderStatus.NOT_CONFIRMED.getValue(),userCode));
                int notEvaluated = wxCourseOrderMapper.getCountOrdersStatus(new SearchOrderTotalParam(CourseOrderStatus.FINISHED_NOT_EVALUATED.getValue(),userCode));
                String phone = wxCourseOrderMapper.getUserPhone(userCode);
                int applyCount = wxCourseAapplyInfoMapper.getCountApplyInfo(userCode);
                // 全部订单
                info.setAllOrders(allOrders);
                // 手机号码
                info.setCellphoneNumber(phone);
                // 待支付
                info.setPendingOrder(notPaid);
                // 待确认
                info.setToBeConfirmed(notConfirmed);
                // 待评价
                info.setComment(notEvaluated);
                // 待确认
                info.setSignUp(applyCount);
            }
        }
        return JsonResult.successJsonResult(info);
    }
    
    /**
     * <p>绑定手机号码</p>
     * @param param
     * @return
     * @author 彭斌  2019年1月23日 上午11:16:14
     */
    public JsonResult<String> bindingPhone(@RequestBody BindingPhomeParam param){
        // 基础参数非空校验
        JSR303ValidateUtils.validate(param);
        
        String phoneNumber = param.getPhoneNumber().trim();
        
        String openid = param.getOpenId();
        
        // 验证码有效校验
        this.checkVerificationCode(phoneNumber, param.getVerificationCode());
        
        ClientWxUser cwu = clientWxUserMapper.selectWxUserByOpenid(openid);
        if(null != cwu) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,
                    "请先解绑，再重新绑定新手机");
        }
        
        AddClientWxUserParam wxUser = new AddClientWxUserParam();
        // 添加绑定用户记录
        AddWxBindingRecordParam bindingRecord = new AddWxBindingRecordParam();
        ClientUser user = clientUserMapper.getClientUserByClientUserPhoneNumber(phoneNumber);
        if(null == user) {
            // 用户暂不是平台客户需先注册用户信息
            // 随机生成6位数密码
            String password = RandomStringUtils.randomNumeric(6);
            // 生成盐值加密的密码
            String clientUserCode = dbSequenceService.getClientCustomerNumber();
            Password pwd = PasswordHelper.encryptPasswordByModel(password);
            ClientUser clientUserRecord = new ClientUser();
            clientUserRecord.setCreateTime(new Date());
            clientUserRecord.setClientUserCode(SystemSeqUtils.getSeq(clientUserCode));
            clientUserRecord.setPassword(pwd.getPassword());
            clientUserRecord.setPhoneNumber(phoneNumber);
            clientUserRecord.setSalt(pwd.getSalt());
            clientUserRecord.setRemark("来自微信课程手机绑定");
            clientUserRecord.setCreator(clientUserCode);
            clientUserMapper.insertSelective(clientUserRecord);
            // 发送密码至客户手机
            this.sendPasswordMsg(phoneNumber, password);
            
            // 绑定微信客户表
            wxUser.setUserCode(clientUserCode);
            bindingRecord.setUserCode(clientUserCode);
        } else {
            wxUser.setUserCode(user.getClientUserCode());
            bindingRecord.setUserCode(user.getClientUserCode());
        }
        wxUser.setOpenid(openid);
        wxUser.setAvatarUrl(param.getAvatarUrl());
        wxUser.setNickName(param.getNickName());
        clientWxUserMapper.insertUser(wxUser);
        
        bindingRecord.setOpenid(openid);
        bindingRecord.setBindingTime(new Date());
        clientWxUserMapper.insertWxBindingRecord(bindingRecord);
        return JsonResult.successJsonResult();
    }
    
    /**
     * <p>解绑手机号码</p>
     * @param param
     * @return
     * @author 彭斌  2019年1月23日 下午5:10:00
     */
    public JsonResult<String> untiedPhone(@RequestBody BindingPhomeParam param){
        // 基础参数非空校验
        JSR303ValidateUtils.validate(param);
        
        // 校验微信用户表是否存在该用户
        String phoneNumber = param.getPhoneNumber().trim();
        String openid = param.getOpenId();
        ClientWxUser cwu = clientWxUserMapper.selectWxUserByOpenid(openid);
        if(null == cwu) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,
                    "解绑失败，用户不存在");
        }
        // 验证码有效校验
        this.checkVerificationCode(phoneNumber, param.getVerificationCode());
        
        // 删除微信客户信息
        clientWxUserMapper.deleteByPrimaryKey(openid);
        return JsonResult.successJsonResult();
    }
    
    /**
     * <p>新增课程投诉建议</p>
     * @param param
     * @return
     * @author 彭斌  2019年1月24日 下午3:46:20
     */
    public JsonResult<String> addSuggestions(@RequestBody AddCourseSuggestionsParam param){
        // 基础参数非空校验
        JSR303ValidateUtils.validate(param);
        WxCourseSuggestions record = new WxCourseSuggestions();
        record.setOpenid(param.getOpenid());
        record.setUserCode(param.getUserCode());
        record.setQuestionType(param.getQuestionType());
        record.setContact(param.getContact());
        record.setQuestionDescription(param.getQuestionDescription());
        record.setCreateTime(new Date());
        wxCourseSuggestionsMapper.insertSelective(record);
        return JsonResult.successJsonResult();
    }
    
    
    /**
     * 
     * <p>
     * 校验验证码
     * </p>
     * 
     * @param param
     * @return
     * @author 黄智聪 2018年11月26日 下午7:10:22
     */
    public void checkVerificationCode(String phoneNumber, String verificationCode) {
        // 先校验验证码是否已失效
        SmsCodeValidation sv = new SmsCodeValidation();
        sv.setPhone(phoneNumber);
        sv.setSec(ClientConstants.CLIENT_REGIST_SMS_TIME_OUT);
        JsonResult<SmsInfo> jr = systemSmsSendService.checkMsgCodeTime(sv);
        if (jr.getData() == null || !jr.getData().getCode().equals(SmsCode.SUCCESS.getCode())) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "短信验证码已失效，请重新获取");
        }
        // 再校验验证码是否正确
        SmsCheck sc = new SmsCheck();
        sc.setCode(verificationCode);
        sc.setPhone(phoneNumber);
        jr = systemSmsSendService.checkMsgCode(sc);
        if (jr.getData() == null || !jr.getData().getCode().equals(SmsCode.SUCCESS.getCode())) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "验证码错误");
        }
    }
    
    
    // 发送短信通知成员
    private void sendPasswordMsg(String phoneNumber, String password) {
        SmsParams sms = new SmsParams();
        sms.setPhone(phoneNumber);
        sms.setMsgType(SmsConstants.WX_REG.getMsgType());
        systemSmsSendService.sendSmsCode(sms);
    }
}

