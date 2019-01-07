/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年1月5日 上午10:17:42
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.wx.course.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.JSR303ErrorCode;
import com.azz.core.common.errorcode.PlatformGoodsErrorCode;
import com.azz.core.common.page.Pagination;
import com.azz.core.exception.BaseException;
import com.azz.exception.JSR303ValidationException;
import com.azz.util.JSR303ValidateUtils;
import com.azz.util.SystemSeqUtils;
import com.azz.wx.course.mapper.WxCourseClassificationMapper;
import com.azz.wx.course.mapper.WxCourseParamMapper;
import com.azz.wx.course.mapper.WxCourseParamRelMapper;
import com.azz.wx.course.mapper.WxCourseParamTermMapper;
import com.azz.wx.course.mapper.WxCourseParamTermValueMapper;
import com.azz.wx.course.pojo.WxCourseClassification;
import com.azz.wx.course.pojo.WxCourseParam;
import com.azz.wx.course.pojo.WxCourseParamTerm;
import com.azz.wx.course.pojo.WxCourseParamTermValue;
import com.azz.wx.course.pojo.bo.Param;
import com.azz.wx.course.pojo.bo.ParamsData;
import com.azz.wx.course.pojo.bo.SearchParams;
import com.azz.wx.course.pojo.vo.Params;
import com.azz.wx.course.pojo.vo.ParamsAll;
import com.github.pagehelper.PageHelper;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2019年1月5日 上午10:17:42
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class ParameterService {
    private  final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private WxCourseParamMapper courseParamMapper;
	
	@Autowired
	private WxCourseParamRelMapper courseParamRelMapper;
	
	@Autowired
	private WxCourseClassificationMapper classificationMapper;
	
	@Autowired
	private WxCourseParamTermMapper courseParamTermMapper;
	
	@Autowired
	private WxCourseParamTermValueMapper courseParamTermValueMapper;
	/**
	 * <p>参数列表</p>
	 * @param param
	 * @return
	 * @author 刘建麟  2018年10月31日 上午11:29:49
	 */
	public JsonResult<Pagination<Params>> searchParamsList(@RequestBody SearchParams param) {
	        PageHelper.startPage(param.getPageNum(), param.getPageSize());
	        List<Params> paramsList = courseParamMapper.searchParamsList(param);
	        for (Params params : paramsList) {
	        	int countByParamsCode = courseParamRelMapper.selectCountByParamsCode(params.getParamCode());
	        	if(countByParamsCode > 0) {
					params.setFlag((byte)1); //不能修改
				}else {
					params.setFlag((byte)0);
				}
			}
	        return JsonResult.successJsonResult(new Pagination<>(paramsList));
	}
	
	/**
	 * <p>新增参数</p>
	 * @param ppt
	 * @return
	 * @author 刘建麟  2018年10月31日 下午5:32:45
	 */
	@Transactional(rollbackFor=Exception.class)
	public JsonResult<String> insertParams(ParamsData ppt){
		if(null != ppt) {
			List<Param> list = ppt.getParams();
			if(null != list && list.size() > 0) {
				TreeSet<String> set = new TreeSet<>();
				for(Param paramsData1 : list) {
					set.add(paramsData1.getParamName());
				}
				if(set.size() != list.size()) {
					throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_INVALID_PARAMS);
				}
				List<Param> params = ppt.getParams();
				for (Param param : params) {
					TreeSet<String> set2 = new TreeSet<>();
					String[] param2 = param.getParam();
					for (int i = 0; i < param2.length; i++) {
						set2.add(param2[i]);
					}
					if(set2.size() != param2.length) {
						throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_INVALID_VALUES);
					}
					set2 = null;
				}
				//根据分类CODE查询分类
				WxCourseClassification classificationCode = classificationMapper.selectByClassificationCode(ppt.getAssortmentCode());
				if(null == classificationCode)
					throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_CODE_NOTEXIST);
				//当前分类只能存在一次
				int count = classificationMapper.countClassification(classificationCode.getClassificationCode());
				LOG.info("新增参数分类存在次数------------>"+count);
				if(count >= 1) {
					throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_TOOMANY);
				}
				//TODO
				//String codes = dbSequenceService.getParameterCodeNumber();
				WxCourseParam wParam = new WxCourseParam();
				wParam.setClassificationCode(classificationCode.getClassificationCode());
				wParam.setCreateTime(new Date());
				wParam.setCreator(ppt.getCreator());
				wParam.setParamCode("222");
				//goodsParams.setParamsCode(SystemSeqUtils.getSeq(codes));
				courseParamMapper.insertSelective(wParam);
				String code = wParam.getParamCode();
				for (Param paramsData : list) {
					JSR303ValidateUtils.validate(paramsData);
					if(null == paramsData) {
						throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_INVALID_NULL);
					}else {
						//TODO
						//String codea = dbSequenceService.getParameterItemCodeNumber();
						WxCourseParamTerm wParamTerm = new WxCourseParamTerm();
						wParamTerm.setCreateTime(new Date());
						wParamTerm.setCreator(ppt.getCreator());
						wParamTerm.setParamChoice(paramsData.getParamsChoice());
						wParamTerm.setParamCode(code);
						wParamTerm.setParamName(paramsData.getParamName());
						wParamTerm.setParamTermCode("11");
						wParamTerm.setParamType(paramsData.getParamsType());
						//goodsParamsTerm.setParamsCode(SystemSeqUtils.getSeq(codea));
						courseParamTermMapper.insertSelective(wParamTerm);
						//获取主键
						String codeTerm = wParamTerm.getParamTermCode();
						
						String[] param = paramsData.getParam();
						//String[] split = param.split(",");
						for (String string : param) {
							WxCourseParamTermValue ppv = new WxCourseParamTermValue();
							ppv.setCreateTime(new Date());
							ppv.setCreator(ppt.getCreator());
							ppv.setParamTermCode(codeTerm);
							ppv.setParamValue(string);
							courseParamTermValueMapper.insertSelective(ppv);
							ppv = null;
						}
					}
				}
			}else {
				throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_INVALID_NULL);
			}
		}
		return JsonResult.successJsonResult();
	}
	
	/**
	 * <p>去更新参数页面</p>
	 * @param code
	 * @return
	 * @author 刘建麟  2018年10月31日 下午7:50:34
	 */
	@SuppressWarnings("unused")
	public JsonResult<List<ParamsAll>> toUpdateParams(String code){
		if(StringUtils.isBlank(code)) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,"参数CODE不能为空");
		}
		List<ParamsAll> list = new ArrayList<>();
		StringBuffer sb = new StringBuffer();
		StringBuffer sb1 = new StringBuffer();
		//查询 属于 参数的参数项
		WxCourseParam paramsByCode = courseParamMapper.selectParamsByCode(code);
		if(null == paramsByCode)
			throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_CODE_NOTEXIST);
		//查询分类名称
		WxCourseClassification classificationCode = classificationMapper.selectByClassificationCode(paramsByCode.getClassificationCode());
		if(null == classificationCode)
			throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_ASSORTMENT_EXIST);
		if(null != paramsByCode) {
			List<WxCourseParamTerm> paramsTermByCode = courseParamTermMapper.selectParamsTermByCode(paramsByCode.getParamCode());
			if(null != paramsTermByCode && paramsTermByCode.size() > 0) {
				for (WxCourseParamTerm term : paramsTermByCode) {
					ParamsAll pa = new ParamsAll();
					pa.setParentCode(code);
					pa.setAssortName(classificationCode.getClassificationName());
					pa.setAssortCode(classificationCode.getClassificationCode());
					pa.setParamsName(term.getParamName());
					pa.setParamsChoice(term.getParamChoice());
					pa.setParamsType(term.getParamType());
					pa.setParamsCode(term.getParamTermCode());
					List<WxCourseParamTermValue> valueByCode = courseParamTermValueMapper.selectValueByCode(term.getParamTermCode());
					if(null != valueByCode && valueByCode.size() > 0 && term.getParamType() == 1) {
						for (WxCourseParamTermValue tv : valueByCode) {
							sb.append(tv.getParamValue());
							if(tv != valueByCode.get(valueByCode.size()-1)) {
								sb.append(",");
							}
						}
						pa.setValues(sb.toString());
						sb = new StringBuffer();
					}
					list.add(pa);
					pa = null;
				}
			}else {
				throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_INVALID_NULL);
			}
		}else {
			throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_INVALID_NULL);
		}
		
		return new JsonResult<List<ParamsAll>>(list);
	}
	
	
	/**
	 * <p>更新参数</p>
	 * @param ppt
	 * @return
	 * @author 刘建麟  2018年10月31日 下午5:32:45
	 */
	@Transactional(rollbackFor=Exception.class)
	public JsonResult<String> updateParams(ParamsData ppt){
		//校验参数
		JSR303ValidateUtils.validate(ppt);
		if(null != ppt) {
			List<Param> list = ppt.getParams();
			if(null != list && list.size() > 0) {
				TreeSet<String> set = new TreeSet<>();
				TreeSet<String> set2 = new TreeSet<>();
				for(Param paramsData1 : list) {
					set.add(paramsData1.getParamName());
				}
				if(set.size() != list.size()) {
					throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_INVALID_PARAMS);
				}
				List<Param> params = ppt.getParams();
				for (Param param : params) {
					String[] param2 = param.getParam();
					for (int i = 0; i < param2.length; i++) {
						set2.add(param2[i]);
					}
					if(set2.size() != param2.length) {
						throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_INVALID_VALUES);
					}
				}
			}
		}
		//根据父参数编码 查询父参数
		WxCourseParam paramsByCode = courseParamMapper.selectParamsByCode(ppt.getParentCode());
		if(null == paramsByCode)
			throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_INVALID_NULL);
		//根据分类CODE 查询分类
		WxCourseClassification classificationCode = classificationMapper.selectByClassificationCode(ppt.getAssortmentCode());
		if(null == classificationCode)
			throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_ASSORTMENT_EXIST);	
		
		List<WxCourseParamTerm> paramsTermByCode = courseParamTermMapper.selectParamsTermByCode(paramsByCode.getParamCode());
		StringBuilder sb = new StringBuilder();
		if(null == paramsTermByCode || paramsTermByCode.size() <=0)
			throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_INVALID_NULL);
		
		for (WxCourseParamTerm term : paramsTermByCode) {
			sb.append(term.getParamTermCode());
			if(term != paramsTermByCode.get(paramsTermByCode.size()-1)) {
				sb.append(",");
			}
			// 清空 参数项 
			courseParamTermMapper.deleteByPrimaryKey(term.getId());
		}
		String[] split = sb.toString().split(",");
		courseParamTermValueMapper.deleteValue(split);//删除参数值
			
		//参数主表更新 分类ID
		int idById = courseParamMapper.updateAssormentIdById(classificationCode.getClassificationCode(),paramsByCode.getParamCode());
		
		if(idById != 1) 
			throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_UPDATE_ERROR);
		//获取参数值
		List<Param> list = ppt.getParams();
			if(null != list && list.size() > 0) {
				//参数名不能重复
				TreeSet<String> set = new TreeSet<>();
				for(Param paramsData1 : list) {
					set.add(paramsData1.getParamName());
				}
				if(set.size() != list.size()) {
					throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_INVALID_PARAMS);
				}
				for (Param paramsData : list) {
					JSR303ValidateUtils.validate(paramsData);
					if(null == paramsData) {
						throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_INVALID_NULL);
					}else {
						//TODO 序列编号
						//String code = dbSequenceService.getParameterItemCodeNumber();
						WxCourseParamTerm term = new WxCourseParamTerm();
						term.setParamName(paramsData.getParamName());
						term.setParamChoice(paramsData.getParamsChoice());
						term.setParamType(paramsData.getParamsType());
						term.setParamTermCode("1111");
						term.setCreateTime(new Date());
						term.setCreator(ppt.getCreator());
						term.setParamCode(paramsByCode.getParamCode());
						courseParamTermMapper.insertSelective(term);
						//获取主键
						String code = term.getParamTermCode();
						String[] param = paramsData.getParam();
						for (String string : param) {
							WxCourseParamTermValue ppv = new WxCourseParamTermValue();
							ppv.setCreateTime(new Date());
							ppv.setCreator(ppt.getCreator());
							ppv.setParamTermCode(term.getParamTermCode());
							ppv.setParamValue(string);
							courseParamTermValueMapper.insertSelective(ppv);
							ppv = null;
						}
					}
				}
			}else {
				throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_INVALID_NULL);
			}
		return JsonResult.successJsonResult();
	}
	
	
	/**
	 * <p>删除参数</p>
	 * @param code
	 * @return
	 * @author 刘建麟  2018年11月1日 下午2:18:47
	 */
	@Transactional(rollbackFor=Exception.class)
	public JsonResult<String> deleteParams(String code){
		JSR303ValidateUtils.validate(code);
		WxCourseParam paramsByCode = courseParamMapper.selectParamsByCode(code);
		if(null == paramsByCode)
			throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_CODE_NOTEXIST);
		
		//删除参数值
		WxCourseClassification classificationCode = classificationMapper.selectByClassificationCode(paramsByCode.getClassificationCode());
		if(null == classificationCode)
			throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_ASSORTMENT_EXIST);
		int countByParamsCode = courseParamRelMapper.selectCountByParamsCode(paramsByCode.getParamCode());
		if(countByParamsCode > 0)
			throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_PRODUCT_EXIST);
		
		List<WxCourseParamTerm> paramsTermByCode = courseParamTermMapper.selectParamsTermByCode(paramsByCode.getParamCode());
		
		StringBuilder sb = new StringBuilder();
		if(null == paramsTermByCode || paramsTermByCode.size() <=0)
			throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_INVALID_NULL);
		
		for (WxCourseParamTerm term : paramsTermByCode) {
			sb.append(term.getParamTermCode());
			if(term != paramsTermByCode.get(paramsTermByCode.size()-1)) {
				sb.append(",");
			}
		}
		String[] split = sb.toString().split(",");
		courseParamTermValueMapper.deleteValue(split);
		//删除参数项
		courseParamTermMapper.deleteByParamsCode(paramsByCode.getParamCode());
		//删除参数
		courseParamMapper.deleteByCode(code);
		return JsonResult.successJsonResult();
	}
}

