/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月22日 上午10:27:34
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/

package com.azz.merchant.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.JSR303ErrorCode;
import com.azz.core.common.errorcode.PlatformUserErrorCode;
import com.azz.core.constants.MerchantConstants;
import com.azz.core.exception.BaseException;
import com.azz.exception.JSR303ValidationException;
import com.azz.merchant.mapper.MerchantDeptMapper;
import com.azz.merchant.pojo.MerchantDept;
import com.azz.merchant.pojo.bo.AddMerchantDeptParam;
import com.azz.merchant.pojo.bo.DelDeptParam;
import com.azz.merchant.pojo.bo.EditDeptIsEnableParam;
import com.azz.merchant.pojo.bo.EditMerchantDeptParam;
import com.azz.merchant.pojo.bo.ImportMerchantDeptParam;
import com.azz.merchant.pojo.bo.SearchMerchantChildDeptParam;
import com.azz.merchant.pojo.bo.SearchMerchantDeptInfo;
import com.azz.merchant.pojo.bo.SearchMerchantDeptInfoParam;
import com.azz.merchant.pojo.bo.SearchMerchantDeptListParam;
import com.azz.merchant.pojo.vo.MerchantDeptInfo;
import com.azz.merchant.pojo.vo.MerchantDeptList;
import com.azz.system.sequence.api.DbSequenceService;
import com.azz.util.ExcelUtils;
import com.azz.util.JSR303ValidateUtils;
import com.azz.util.ObjectUtils;
import com.azz.util.StringUtils;
import com.azz.util.SystemSeqUtils;
import com.google.common.collect.Lists;

import lombok.Cleanup;
import sun.misc.BASE64Decoder;

/**
 * <P>部门服务类</P>
 * @version 1.0
 * @author 彭斌  2018年10月26日 下午3:42:58
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class DeptService {

    @Autowired
    private MerchantDeptMapper merchantDeptMapper;
    
    @Autowired
    private DbSequenceService dbSequenceService;
    
    public JsonResult<String> addFirstLevelDept(@RequestBody AddMerchantDeptParam param){
        // 部门信息非空校验
        JSR303ValidateUtils.validate(param);
        SearchMerchantDeptInfo smd = new SearchMerchantDeptInfo();
        smd.setMerchantId(param.getMerchantId());
        smd.setDeptName(param.getDeptName());
        int mdiObj = merchantDeptMapper.selectFirstLevelExist(smd);
        
        if(mdiObj>0) {
            throw new BaseException(PlatformUserErrorCode.PLATFORM_DEPT_ERROR_EXIST);
        }
        String code= dbSequenceService.getMerchantDepartmentNumber();
        MerchantDept md = new MerchantDept();
        md.setDeptName(param.getDeptName());
        md.setCreateTime(new Date());
        md.setMerchantId(param.getMerchantId());
        md.setParentCode("0");
        md.setCreator(param.getCreator());
        md.setStatus(param.getStatus());
        md.setDeptCode(SystemSeqUtils.getSeq(code));
        md.setDescription("0"); // 部门等级  0 一级为 1 二级  2三级
        merchantDeptMapper.insertSelective(md);
        
        return JsonResult.successJsonResult();
    }

    
    public JsonResult<String> addChildDept(@RequestBody AddMerchantDeptParam param){
        // 部门信息非空校验
        JSR303ValidateUtils.validate(param);
        
        SearchMerchantDeptInfoParam smdIp = new SearchMerchantDeptInfoParam();
        smdIp.setDeptCode(param.getParentCode());
        smdIp.setMerchantId(param.getMerchantId());
        MerchantDept mdObj = merchantDeptMapper.selectByDeptCode(smdIp);
        if(ObjectUtils.isNull(mdObj)) {
            throw new BaseException(PlatformUserErrorCode.PLATFORM_DEPT_ERROR_NO_EXIST);
        }
        
        SearchMerchantDeptInfo smd = new SearchMerchantDeptInfo();
        smd.setMerchantId(param.getMerchantId());
        smd.setDeptName(param.getDeptName());
        smd.setParentCode(param.getParentCode());
        MerchantDeptInfo mdi = merchantDeptMapper.selectByMerchantIdAndName(smd);
        if(ObjectUtils.isNotNull(mdi)) {
            throw new BaseException(PlatformUserErrorCode.PLATFORM_DEPT_ERROR_EXIST);
        }
        
        MerchantDept md = new MerchantDept();
        
        if(mdObj.getDescription().equals("0")) {
            md.setDescription("1");
        } else if(mdObj.getDescription().equals("1")) {
            md.setDescription("2");
        } else if(mdObj.getDescription().equals("2")) {
            throw new BaseException(PlatformUserErrorCode.PLATFORM_DEPT_LEVEL_ERROR);
        }
        String code =dbSequenceService.getMerchantDepartmentNumber(); 
        md.setDeptName(param.getDeptName());
        md.setCreateTime(new Date());
        md.setCreator(param.getCreator());
        md.setMerchantId(param.getMerchantId());
        md.setParentCode(param.getParentCode());
        md.setStatus(param.getStatus());
        md.setDeptCode(SystemSeqUtils.getSeq(code)); 
        merchantDeptMapper.insertSelective(md);
        return JsonResult.successJsonResult();
    }
    
    public JsonResult<MerchantDept> getDeptInfo(SearchMerchantDeptInfoParam param){
        return JsonResult.successJsonResult(merchantDeptMapper.selectByDeptCode(param));
    }
    
    public JsonResult<String> editDept(@RequestBody EditMerchantDeptParam param){
        JSR303ValidateUtils.validate(param);
        
        
        
        SearchMerchantDeptInfo smd = new SearchMerchantDeptInfo();
        smd.setMerchantId(param.getMerchantId());
        smd.setDeptName(param.getDeptName().trim());
        smd.setDeptCode(param.getDeptCode());
        smd.setParentCode(param.getParentCode());
        
        
        SearchMerchantDeptInfoParam smdIp = new SearchMerchantDeptInfoParam();
        smdIp.setDeptCode(param.getDeptCode());
        smdIp.setMerchantId(param.getMerchantId());
        MerchantDept mdObj = merchantDeptMapper.selectByDeptCode(smdIp);
        
        // 校验编辑是否在同级目录下有同名     再校验编辑信息父级编码不能超过三级
        if(param.getDeptCode().equals(param.getParentCode())) {
            throw new BaseException(PlatformUserErrorCode.PLATFORM_DEPT_CODE_ERROR);
        }
        
        MerchantDeptInfo mdi = merchantDeptMapper.selectByMerchantIdAndName(smd);
        if(ObjectUtils.isNotNull(mdi)) {
            if(!mdObj.getDeptName().equals(param.getDeptName())) {
                throw new BaseException(PlatformUserErrorCode.PLATFORM_DEPT_ERROR_EXIST);
            }
        }
        
        
        MerchantDept md = merchantDeptMapper.selectByDeptAllInfo(smd);
        md.setDeptName(param.getDeptName());
        md.setLastModifyTime(new Date());
        md.setModifier(param.getModifier());
        md.setParentCode(param.getParentCode());
        md.setStatus(param.getStatus());
        merchantDeptMapper.updateByPrimaryKeySelective(md);
        return JsonResult.successJsonResult();
    }
    
    public JsonResult<List<MerchantDeptList>> searchDeptList(@RequestBody SearchMerchantDeptListParam param){
        JSR303ValidateUtils.validate(param);
        List<MerchantDeptList> merchantDept = merchantDeptMapper.selectFirstDeptList(param);
        return JsonResult.successJsonResult(merchantDept);
    }
    
    public JsonResult<List<MerchantDeptList>> searchChildDeptList(@RequestBody SearchMerchantChildDeptParam param){
        JSR303ValidateUtils.validate(param);
        List<MerchantDeptList> merchantChildDept = merchantDeptMapper.selectChildDeptList(param);
        return JsonResult.successJsonResult(merchantChildDept);
    }
    
    public JsonResult<String> isEnableDept(@RequestBody EditDeptIsEnableParam param){
        JSR303ValidateUtils.validate(param);
        SearchMerchantDeptInfoParam deptParam = new SearchMerchantDeptInfoParam();
        deptParam.setMerchantId(param.getMerchantId());
        deptParam.setDeptCode(param.getDeptCode());
        MerchantDept record = merchantDeptMapper.selectByDeptCode(deptParam);
        if(ObjectUtils.isNull(record)) {
            throw new BaseException(PlatformUserErrorCode.PLATFORM_DEPT_ERROR_NO_EXIST);
        }
        if(param.getStatus().equals(MerchantConstants.DeptStatus.DISABLE.getValue())) {
            // 禁用
            record.setStatus(MerchantConstants.DeptStatus.DISABLE.getValue());
        } else if(param.getStatus().equals(MerchantConstants.DeptStatus.ENABLE.getValue())) {
            // 启用
            record.setStatus(MerchantConstants.DeptStatus.ENABLE.getValue());
        } else {
            throw new BaseException(PlatformUserErrorCode.PLATFORM_DEPT_STATUS_ERROR_NO_EXIST);
        }
        record.setModifier(param.getModifier());
        record.setLastModifyTime(new Date());
        merchantDeptMapper.updateByPrimaryKeySelective(record);
        return JsonResult.successJsonResult();
    }
    
    public JsonResult<String> delDept(@RequestBody DelDeptParam param){
        JSR303ValidateUtils.validate(param);
        
        // 校验部门是否存在用户
        SearchMerchantDeptInfoParam deptParam = new SearchMerchantDeptInfoParam();
        deptParam.setMerchantId(param.getMerchantId());
        deptParam.setDeptCode(param.getDeptCode());
        
        int size = merchantDeptMapper.countDeptMerchantUser(deptParam);
        if(size > 0) {
            throw new BaseException(PlatformUserErrorCode.PLATFORM_DEPT_USER_EXIST);
        }
        
        int subDept = merchantDeptMapper.countSubDept(param.getDeptCode());
        if(subDept > 0) {
            throw new BaseException(PlatformUserErrorCode.PLATFORM_DEPT_EXIST);
        }
        
        MerchantDept record = merchantDeptMapper.selectByDeptCode(deptParam);
        record.setStatus(MerchantConstants.DeptStatus.INVALID.getValue());
        merchantDeptMapper.updateByPrimaryKeySelective(record);
        return JsonResult.successJsonResult();
    }
    
    public JsonResult<String> importMerchantDept(@RequestBody ImportMerchantDeptParam param) throws IOException{
            // 记录出错行数
           int errorRowNum = 1;
           String creator = param.getCreator();
           Long merchantId = param.getMerchantId();
           String base64Str = param.getBase64Str();
           //将字符串转换为byte数组
           byte[] bytes = new BASE64Decoder().decodeBuffer(base64Str);
           //转化为输入流
           ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
           
           @Cleanup
           HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
           HSSFSheet sheet = workbook.getSheetAt(0);
           int lastRowNum = sheet.getLastRowNum();
           if(lastRowNum == 0) {// 未填写数据
               throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "导入数据不可为空");
           }
           
           for (int i = 1; i <= lastRowNum; i++) {
               HSSFRow row = sheet.getRow(i);
               errorRowNum++;
               if (ObjectUtils.isNotNull(row)) {
                   // 获取当前行的元素信息
                   ArrayList<Cell> rowCells = Lists.newArrayList(row.cellIterator());
                   String deptName = null;
                   String parentDeptCode = null;
                   String deptStatus = null;
                   
                   // 部门名称校验
                   if (Cell.CELL_TYPE_BLANK != rowCells.get(0).getCellType()) {
                       String cell_1 = ExcelUtils.getStringValue(row.getCell(0));
                       if (StringUtils.isNotBlank(cell_1)) {
                           deptName = cell_1;
                       }else {
                           throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "第" + errorRowNum + "行数据部门名称不允许为空");
                       }
                   }else {
                       throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "第" + errorRowNum + "行数据部门名称不允许为空");
                   }
                   
                   // 上级部门编码校验
                   if (Cell.CELL_TYPE_BLANK != rowCells.get(1).getCellType()) {
                       String cell_2 = ExcelUtils.getStringValue(row.getCell(1));
                       if (StringUtils.isNotBlank(cell_2)) {
                           parentDeptCode = cell_2;
                       }else {
                           throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "第" + errorRowNum + "行数据上级部门编码不允许为空");
                       }
                   }else {
                       throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "第" + errorRowNum + "行数据上级部门编码不允许为空");
                   }
                   
                   // 部门状态校验
                   if (Cell.CELL_TYPE_BLANK != rowCells.get(2).getCellType()) {
                       String cell_3 = ExcelUtils.getStringValue(row.getCell(2));
                       if (StringUtils.isNotBlank(cell_3)) {
                           deptStatus = cell_3;
                       }else {
                           throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "第" + errorRowNum + "行数据上级部门编码不允许为空");
                       }
                   }else {
                       throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "第" + errorRowNum + "行数据上级部门编码不允许为空");
                   }
                   
                   MerchantDept md = new MerchantDept();
                   
                   if(!"0".equals(parentDeptCode)) {
                       
                       SearchMerchantDeptInfoParam smdIp = new SearchMerchantDeptInfoParam();
                       smdIp.setDeptCode(parentDeptCode);
                       smdIp.setMerchantId(merchantId);
                       MerchantDept mdObj = merchantDeptMapper.selectByDeptCode(smdIp);
                       if(ObjectUtils.isNull(mdObj)) {
                           throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "第" + errorRowNum + "行数据部门编码不存在");
                       }
                       
                       SearchMerchantDeptInfo smd = new SearchMerchantDeptInfo();
                       smd.setMerchantId(param.getMerchantId());
                       smd.setDeptName(deptName);
                       smd.setParentCode(parentDeptCode);
                       MerchantDeptInfo mdi = merchantDeptMapper.selectByMerchantIdAndName(smd);
                       if(ObjectUtils.isNotNull(mdi)) {
                           throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "第" + errorRowNum + "行数据部门已存在");
                       }
                       
                       if(mdObj.getDescription().equals("0")) {
                           md.setDescription("1");
                       } else if(mdObj.getDescription().equals("1")) {
                           md.setDescription("2");
                       } else if(mdObj.getDescription().equals("2")) {
                           throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "第" + errorRowNum + "行数据已是三级部门");
                       }
                       
                       md.setParentCode(parentDeptCode);
                   } else {
                       // 系统自动生成部门编码
                       md.setParentCode("0");
                       // 部门等级 0 一级 1 二级 2 三级
                       md.setDescription("0");
                   }
                   
                   String code =dbSequenceService.getMerchantDepartmentNumber(); 
                   md.setDeptName(deptName);
                   md.setCreateTime(new Date());
                   md.setCreator(creator);
                   md.setMerchantId(merchantId);
                   md.setStatus(Integer.parseInt(deptStatus));
                   md.setDeptCode(SystemSeqUtils.getSeq(code)); 
                   merchantDeptMapper.insertSelective(md);
               }
           }
               return JsonResult.successJsonResult();
       }
}
