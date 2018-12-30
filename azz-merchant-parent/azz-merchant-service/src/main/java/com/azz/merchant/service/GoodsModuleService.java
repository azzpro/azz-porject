/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月1日 下午2:23:23
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.merchant.service;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.alibaba.fastjson.JSONObject;
import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.JSR303ErrorCode;
import com.azz.core.common.errorcode.SystemErrorCode;
import com.azz.core.common.page.Pagination;
import com.azz.core.constants.FileConstants;
import com.azz.core.constants.MerchantConstants;
import com.azz.core.constants.MerchantConstants.GoodsModuleStatus;
import com.azz.core.constants.MerchantConstants.IsChangeGoodsModulePic;
import com.azz.core.exception.BaseException;
import com.azz.exception.JSR303ValidationException;
import com.azz.merchant.mapper.MerchantGoodsModuleMapper;
import com.azz.merchant.mapper.MerchantGoodsProductMapper;
import com.azz.merchant.mapper.MerchantGoodsProductParamsMapper;
import com.azz.merchant.mapper.MerchantGoodsProductPriceMapper;
import com.azz.merchant.mapper.MerchantMapper;
import com.azz.merchant.mapper.PlatformGoodsBrandMapper;
import com.azz.merchant.mapper.PlatformGoodsClassificationMapper;
import com.azz.merchant.mapper.PlatformGoodsParamsMapper;
import com.azz.merchant.mapper.PlatformGoodsParamsValueMapper;
import com.azz.merchant.pojo.Merchant;
import com.azz.merchant.pojo.MerchantGoodsModule;
import com.azz.merchant.pojo.MerchantGoodsProduct;
import com.azz.merchant.pojo.MerchantGoodsProductParams;
import com.azz.merchant.pojo.MerchantGoodsProductPrice;
import com.azz.merchant.pojo.PlatformGoodsBrand;
import com.azz.merchant.pojo.PlatformGoodsClassification;
import com.azz.merchant.pojo.PlatformGoodsParams;
import com.azz.merchant.pojo.PlatformGoodsParamsTerm;
import com.azz.merchant.pojo.bo.AddGoodsModuleParam;
import com.azz.merchant.pojo.bo.AddModuleProductParam;
import com.azz.merchant.pojo.bo.EditGoodsModuleParam;
import com.azz.merchant.pojo.bo.GoodsModulePic;
import com.azz.merchant.pojo.bo.PutOnOrPutOffOrDelGoodsModuleParam;
import com.azz.merchant.pojo.bo.SearchGoodsModuleParam;
import com.azz.merchant.pojo.bo.SearchProductForImportParam;
import com.azz.merchant.pojo.vo.GoodsModuleInfo;
import com.azz.merchant.pojo.vo.ImportedProductInfo;
import com.azz.merchant.pojo.vo.ModuleProduct;
import com.azz.merchant.pojo.vo.ProductForImport;
import com.azz.merchant.pojo.vo.UploadFileInfo;
import com.azz.platform.merchant.pojo.PlatformGoodsParamsValue;
import com.azz.system.api.SystemImageUploadService;
import com.azz.system.sequence.api.DbSequenceService;
import com.azz.util.JSR303ValidateUtils;
import com.azz.util.ObjectUtils;
import com.azz.util.StringUtils;
import com.azz.util.SystemSeqUtils;
import com.github.pagehelper.PageHelper;

/**
 * <P>模组业务</P>
 * @version 1.0
 * @author 黄智聪  2018年11月1日 下午2:23:23
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class GoodsModuleService {
	
	@Autowired
	private MerchantGoodsModuleMapper merchantGoodsModuleMapper;

	@Autowired
	private PlatformGoodsClassificationMapper platformGoodsClassificationMapper;
	
	@Autowired
	private MerchantMapper merchantMapper;
	
	@Autowired
    private SystemImageUploadService systemImageUploadService;
	
	@Autowired
	private DbSequenceService dbSequenceService;
	
	@Autowired
	private MerchantGoodsProductMapper merchantGoodsProductMapper;
	
	@Autowired
	private PlatformGoodsBrandMapper platformGoodsBrandMapper;
	
	@Autowired
	private PlatformGoodsParamsMapper goodsParamsMapper;
	
	@Autowired
	private MerchantGoodsProductPriceMapper merchantGoodsProductPriceMapper;
	
	@Autowired
	private MerchantGoodsProductParamsMapper merchantGoodsProductParamsMapper;
	
	@Autowired
	private PlatformGoodsParamsValueMapper platformGoodsParamsValueMapper;
	/**
	 * 
	 * <p>查询模组列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月1日 下午3:33:53
	 */
	public JsonResult<Pagination<GoodsModuleInfo>> getGoodModuleInfoList(@RequestBody SearchGoodsModuleParam param){
		PageHelper.startPage(param.getPageNum(), param.getPageSize());
		List<GoodsModuleInfo> infos = merchantGoodsModuleMapper.getGoodsModuleInfoList(param);
		return JsonResult.successJsonResult(new Pagination<>(infos));
	}
	
	/**
	 * 
	 * <p>查询模组详情</p>
	 * @param moduleCode
	 * @return
	 * @author 黄智聪  2018年11月1日 下午8:45:15
	 */
	public JsonResult<GoodsModuleInfo> getGoodModuleInfo(String moduleCode){
		return JsonResult.successJsonResult(merchantGoodsModuleMapper.getGoodsModuleInfo(moduleCode));
	}
	
	/**
	 * 
	 * <p>新增商品模组</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月1日 下午4:08:42
	 */
	public JsonResult<String> addGoodsModule(@RequestBody AddGoodsModuleParam param){
		// 参数校验
		JSR303ValidateUtils.validate(param);
		
		PlatformGoodsClassification goodsClassification = platformGoodsClassificationMapper
				.selectByAssortmentCode(param.getAssortmentCode());
		if(goodsClassification == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "分类不存在");
		}
		if(goodsClassification.getAssortmentTop() != 2) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "只允许选择三级分类");
		}
		String merchantCode = param.getMerchantCode();
		Merchant merchant = merchantMapper.selectByMerchantCode(merchantCode);
		if(merchant == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "商户不存在");
		}
		int count = merchantGoodsModuleMapper.countGoodsModule(merchant.getId(), param.getModuleName(), null);
		if(count > 0) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "已存在相同模组请修改模组名称");
		}
		GoodsModulePic pic = param.getGoodsModulePic();
		String moduleCode = dbSequenceService.getModuleCodeNumber();
		// 上传模组图片
		UploadFileInfo fileInfo = uploadModulePic(pic, SystemSeqUtils.getSeq(moduleCode));
		MerchantGoodsModule goodsModuleRecord = MerchantGoodsModule.builder()
				.creator(param.getCreator())
				.createTime(new Date())
				.classificationId(goodsClassification.getId())
				.merchantId(merchant.getId())
				.moduleCode(SystemSeqUtils.getSeq(moduleCode))
				.moduleInfo(param.getModuleInfo())
				.moduleName(param.getModuleName())
				.modulePicName(fileInfo.getOriginalFileName())
				.modulePicUrl(fileInfo.getImgUrl())
				.moduleStatus(param.getModuleStatus())
				.moduleRemark(param.getModuleRemark())
				.build();
		merchantGoodsModuleMapper.insertSelective(goodsModuleRecord);
		return JsonResult.successJsonResult();
	}
	
	/**
	 * 
	 * <p>修改商品模组</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月1日 下午5:16:03
	 */
	public JsonResult<String> editGoodsModule(@RequestBody EditGoodsModuleParam param){
		// 参数校验
		JSR303ValidateUtils.validate(param);
		String moduleCode = param.getModuleCode();
		MerchantGoodsModule module = merchantGoodsModuleMapper.selectByModuleCode(moduleCode);
		if(module == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "模组不存在");
		}
		PlatformGoodsClassification goodsClassification = platformGoodsClassificationMapper
						.selectByAssortmentCode(param.getAssortmentCode());
		if(goodsClassification == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "分类不存在");
		}
		if(goodsClassification.getAssortmentTop() != 2) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "只允许选择三级分类");
		}
		String merchantCode = param.getMerchantCode();
		Merchant merchant = merchantMapper.selectByMerchantCode(merchantCode);
		if(merchant == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "商户不存在");
		}
		// 查询除自己以外的模组是否存在相同的模组名
		int count = merchantGoodsModuleMapper.countGoodsModule(merchant.getId(), param.getModuleName(), moduleCode);
		if(count > 0) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "已存在相同模组请修改模组名称");
		}
		MerchantGoodsModule merchantGoodsModule = MerchantGoodsModule.builder()
				.modifier(param.getModifier())
				.modifyTime(new Date())
				.moduleCode(moduleCode)
				.moduleInfo(param.getModuleInfo())
				.moduleName(param.getModuleName())
				.classificationId(goodsClassification.getId())
				.moduleStatus(param.getModuleStatus())
				.build();
		// 修改模组图片，则重新上传
		int isChangeGoodsModulePic = param.getIsChangeGoodsModulePic();
		if(isChangeGoodsModulePic == IsChangeGoodsModulePic.Y.getValue()) {
			GoodsModulePic pic = param.getGoodsModulePic();
			// 上传模组图片
			UploadFileInfo fileInfo = uploadModulePic(pic, moduleCode);
			merchantGoodsModule.setModulePicName(fileInfo.getOriginalFileName());
			merchantGoodsModule.setModulePicUrl(fileInfo.getImgUrl());
		}
		merchantGoodsModuleMapper.updateByModuleCode(merchantGoodsModule);
		return JsonResult.successJsonResult();
	}
	
	/**
	 * 
	 * <p>上架、下架或删除模组</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月1日 下午8:02:16
	 */
	public JsonResult<String> putOnOrPutOffOrDelGoodsModule(@RequestBody PutOnOrPutOffOrDelGoodsModuleParam param){
		// 参数校验
		JSR303ValidateUtils.validate(param);
		String moduleCode = param.getModuleCode();
		MerchantGoodsModule module = merchantGoodsModuleMapper.selectByModuleCode(moduleCode);
		if(module == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "模组不存在");
		}
		boolean yes = GoodsModuleStatus.checkStatusExist(param.getModuleStatus());
		if(!yes) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "模组状态不存在");
		}
		MerchantGoodsModule merchantGoodsModuleRecord = MerchantGoodsModule.builder()
				.modifier(param.getModifier())
				.modifyTime(new Date())
				.moduleCode(moduleCode)
				.moduleStatus(param.getModuleStatus())
				.build();
		merchantGoodsModuleMapper.updateByModuleCode(merchantGoodsModuleRecord);
		
		return JsonResult.successJsonResult();
	}
	
	/**
	 * 
	 * <p>查询模组已导入的产品信息</p>
	 * @param moduleCode
	 * @return
	 * @author 黄智聪  2018年12月13日 下午2:45:42
	 */
	public JsonResult<ImportedProductInfo> getImportedProductInfos(String moduleCode){
		if(StringUtils.isBlank(moduleCode)) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "模组编码不允许为空");
		}
		// 查询模组详情
		GoodsModuleInfo moduleInfo = merchantGoodsModuleMapper.getGoodsModuleInfo(moduleCode);
		if(moduleInfo == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "模组不存在");
		}
		// 查询模组下的产品列表
		List<ModuleProduct> moduleProducts = merchantGoodsModuleMapper.getModuleProducts(moduleCode);
		return JsonResult.successJsonResult(new ImportedProductInfo(moduleInfo, moduleProducts));
	}
	
	/**
	 * 
	 * <p>查询当前模组能导入的产品信息</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年12月13日 下午4:16:39
	 */
	public JsonResult<Pagination<ProductForImport>> getProductsForImport(@RequestBody SearchProductForImportParam param){
		JSR303ValidateUtils.validate(param);
		PageHelper.startPage(param.getPageNum(), param.getPageSize());
		List<ProductForImport> products = merchantGoodsModuleMapper.getProductsForImport(param);
		return JsonResult.successJsonResult(new Pagination<>(products));
	}
	
	/**
	 * 
	 * <p>保存模组产品</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年12月13日 下午4:19:46
	 */
	public JsonResult<String> saveModuleProducts(@RequestBody AddModuleProductParam param){
		JSR303ValidateUtils.validate(param);
		List<String> productCodes = param.getProductCodes();
		List<String> invalidProductCodes = merchantGoodsModuleMapper.getInvalidImportProductCodes(productCodes);
		if(invalidProductCodes.size() > 0) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, 
					"产品编码[" + StringUtils.join(invalidProductCodes, ",") + "]的产品已被删除或已关联模组，请移除后保存");
		}
		int count = merchantGoodsModuleMapper.countProducts(productCodes);
		if(count != productCodes.size()) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "所选商品编码中存在错误数据");
		}
		String moduleCode = param.getModuleCode();
		MerchantGoodsModule module = merchantGoodsModuleMapper.selectByModuleCode(moduleCode);
		if(module == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "所选模组不存在");
		}
		List<ModuleProduct> orginalProducts = merchantGoodsModuleMapper.getModuleProducts(moduleCode);
		List<String> orginalProductCodes = new ArrayList<>();
		if(orginalProducts.size() > 0) {
			for (ModuleProduct moduleProduct : orginalProducts) {
				orginalProductCodes.add(moduleProduct.getProductCode());
			}
			// 要把原来模组下的产品也加入
			productCodes.addAll(orginalProductCodes);
		}
		Long moduleId = module.getId();
		// 先解除原先产品所绑定的模组
		merchantGoodsProductMapper.setNullModule(moduleId);
		// 再重新为产品绑定模组/
		merchantGoodsProductMapper.updateProductModule(productCodes, moduleId);
		return JsonResult.successJsonResult();
	}
	
	/**
	 * 
	 * <p>上传模组主图文件</p>
	 * @param pic
	 * @param moduleCode
	 * @return
	 * @author 黄智聪  2018年11月1日 下午4:49:22
	 */
	public UploadFileInfo uploadModulePic(GoodsModulePic pic, String moduleCode) {
		String originalFileName = pic.getFileName();
	    if(StringUtils.isBlank(originalFileName)) {
	    	throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "模组主图文件名为空");
	    }
	    if(pic.getFileSize() > MerchantConstants.GOODS_MODULE_FILE_SIZE_LIMIT) {
	    	throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "模组主图文件大小不能超过2M");
	    }
	    String filedata = pic.getFileBase64Str();
	    if(StringUtils.isBlank(filedata)) {
	    	throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "模组主图文件内容为空");
	    }
	    int dotIndex = originalFileName.lastIndexOf(".");
	    String fileNameNoSufix = originalFileName.substring(0, dotIndex);
	    String sufix = originalFileName.substring(dotIndex + 1, originalFileName.length());
	    // 新名称为文件名 + 模组编码
	    String newFileName = fileNameNoSufix + "_" + moduleCode;
	    // 图片url
	    JsonResult<String> jr = systemImageUploadService.uploadImage(FileConstants.IMAGE_BUCKETNAME, newFileName, sufix,
		    filedata, FileConstants.AZZ_MERCHANT, FileConstants.AZZ_MODULE_IMAGE_TYPE);
	    UploadFileInfo file = new UploadFileInfo();
	    if(jr.getCode() == SystemErrorCode.SUCCESS.getCode()) {
	    	file.setImgUrl(jr.getData());
	    	file.setOriginalFileName(originalFileName);
	    }else {
	    	throw new BaseException(SystemErrorCode.SYS_ERROR_SERVICE_NOT_USE,"模组主图上传失败，请重试");
	    }
	    return file;
	}
	
	
	public static void main(String[] args) {
		
		//batchAddModule(0L,"");
		//batchAddParam("");
		
	}
	
	public JsonResult<String> batchAddParam(String creator) {
		File file = new File("D:\\param.json");
		BufferedReader reader = null;
		Date nowDate = new Date();
		Map<String,Map<String,String>> results = new HashMap<>();
		try {
			reader = new BufferedReader(new FileReader(file));
			String eachLine = null;
			int line = 1;
			// 一次读一行，读入null时文件结束
			while ((eachLine = reader.readLine()) != null) {
				try {
					JSONObject json = JSONObject.parseObject(eachLine);
					// 分类名称
					String classificationName = json.getString("goodfenlid");
					//System.out.println("classificationName:"+classificationName);
					Map<String, String> params = null;
					if(results.get(classificationName) != null) {// 分类已存在
						params = results.get(classificationName);
					}else {
						params = new HashMap<>();
						results.put(classificationName, params);
					}
					Set<String> keys = json.keySet();
					for (String key : keys) {
						if(isParamKey(key)) {
							String value = json.getString(key);
							params.put(key, value);
							//System.out.println("key:"+ key + "  value:"+value);
						}
					}
				} catch (Exception e) {
					System.out.println("第"+line+"行出错:"+e.getMessage());
				}
				line++;
			}
			System.out.println("最后结果："+JSONObject.toJSONString(results));
			Set<String> resultsKeySet = results.keySet();
			for (String eachKey : resultsKeySet) {
				String c = eachKey;// key 即分类名称
				try {
					int strIndex = c.lastIndexOf(":");
					String classificationName = c.substring(strIndex + 1);
					PlatformGoodsClassification classification = platformGoodsClassificationMapper.selectLevel2AssortmentByName(classificationName);
					//PlatformGoodsClassification classification = new PlatformGoodsClassification();
					//classification.setId(10000L);
					if(classification == null) {
						throw new BaseException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,"分类不存在");
					}
					Long classificationId = classification.getId();
					PlatformGoodsParams goodsParams = new PlatformGoodsParams();
					goodsParams.setParamsCode(SystemSeqUtils.getSeq(dbSequenceService.getParameterCodeNumber()));
					//goodsParams.setParamsCode(System.currentTimeMillis()+"");
					goodsParams.setCreator(creator);
					goodsParams.setCreateTime(nowDate);
					goodsParams.setAssortmentId(classificationId);
					goodsParamsMapper.insertSelective(goodsParams);
					//Long id = System.currentTimeMillis(); //TODO
					//goodsParams.setId(id); //TODO
					System.out.println("StepOne,新增参数："+JSONObject.toJSONString(goodsParams));
					Map<String, String> params = results.get(eachKey);
					Set<String> paramsKeySet = params.keySet();
					for (String paramKey : paramsKeySet) {// paramKey为参数code
						PlatformGoodsParamsTerm goodsParamsTerm = new PlatformGoodsParamsTerm();
						goodsParamsTerm.setParamsName(params.get(paramKey));
						goodsParamsTerm.setParamsChoice((byte)2);
						goodsParamsTerm.setParamsType((byte)1);
						goodsParamsTerm.setCreateTime(nowDate);
						goodsParamsTerm.setCreator(creator);
						goodsParamsTerm.setParamsId(goodsParams.getId());
						goodsParamsTerm.setParamsCode(paramKey);
						goodsParamsMapper.insertTermSelective(goodsParamsTerm);
						System.out.println("StepTwo,新增参数项："+JSONObject.toJSONString(goodsParamsTerm));
					}
					System.out.println("--------------------------");
				} catch (Exception e) {
					System.out.println("新增参数出错:" + e.getMessage());
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return JsonResult.successJsonResult();
	}
	
	private static boolean isParamKey(String key) {
		if("goodname".equals(key) || "goodfenlid".equals(key) || "goodpinp".equals(key)) {
			return false;
		}
		return true;
	}

	public JsonResult<String> batchAddModule(Long merchantId,String creator) {
		File file = new File("D:\\json.json");
		BufferedReader reader = null;
		Date nowDate = new Date();
		try {
			reader = new BufferedReader(new FileReader(file));
			String eachLine = null;
			int line = 1;
			// 一次读一行，读入null时文件结束
			while ((eachLine = reader.readLine()) != null) {
				try {
					JSONObject json = JSONObject.parseObject(eachLine);
					String modulePicUrl = json.getString("goodmzzt");
					URL url = new URL(modulePicUrl);
					String base64Str = getBase64FromInputStream(url.openStream());
					System.out.println("size:"+imageSize(base64Str)+"   line " + line + ": "+base64Str);
					GoodsModulePic pic = new GoodsModulePic();
					pic.setFileBase64Str(base64Str);
					pic.setFileName(line +"_"+System.currentTimeMillis()+".jpg");
					pic.setFileSize(imageSize(base64Str));
					String moduleCode = SystemSeqUtils.getSeq(dbSequenceService.getModuleCodeNumber());// TODO
					//String moduleCode = System.currentTimeMillis()+"";
					// 上传模组图片
					UploadFileInfo fileInfo = uploadModulePic(pic, moduleCode); // TODO
					//UploadFileInfo fileInfo = new UploadFileInfo("XXX", "YYYY");
					String c = json.getString("goodfenlid");
					int strIndex = c.lastIndexOf(":");
					String classificationName = c.substring(strIndex + 1);
					PlatformGoodsClassification classification = platformGoodsClassificationMapper.selectLevel2AssortmentByName(classificationName);
					if(classification == null) {
						throw new BaseException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,"分类不存在");
					}
					Long classificationId = classification.getId();
					String moduleName = json.getString("goodname");
					MerchantGoodsModule goodsModuleRecord = MerchantGoodsModule.builder()
							.creator(creator)
							.createTime(nowDate)
							.classificationId(classificationId)
							.merchantId(merchantId)
							.moduleCode(SystemSeqUtils.getSeq(moduleCode))
							.moduleName(moduleName)
							.modulePicName(fileInfo.getOriginalFileName())
							.modulePicUrl(fileInfo.getImgUrl())
							.moduleStatus((byte) 1)
							.build();
					merchantGoodsModuleMapper.insertSelective(goodsModuleRecord);
					//System.out.println("对象:"+JSON.toJSONString(goodsModuleRecord));
				} catch (Exception e) {
					System.out.println("第"+line+"行出错:"+e.getMessage());
				}
				line++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return JsonResult.successJsonResult();
	}
	
	public static String getBase64FromInputStream(InputStream in) {
        // 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        byte[] data = null;
        // 读取图片字节数组
        try {
            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
            byte[] buff = new byte[100];
            int rc = 0;
            while ((rc = in.read(buff, 0, 100)) > 0) {
                swapStream.write(buff, 0, rc);
            }
            data = swapStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new String(Base64.encodeBase64(data));
    }
	
	public static Integer imageSize(String image){
        String str = image; 
        Integer equalIndex= str.indexOf("=");//2.找到等号，把等号也去掉
        if(str.indexOf("=")>0) {
            str=str.substring(0, equalIndex);
        }
        Integer strLength=str.length();//3.原来的字符流大小，单位为字节
        Integer size=strLength-(strLength/8)*2;//4.计算后得到的文件流大小，单位为字节
        return size;
    }

	
	public JsonResult<String> batchAddPrduct(Long merchantId,String creator) {
        File file = new File("D:\\productInfo.json");
        BufferedReader reader = null;
        Date nowDate = new Date();
        try {
            reader = new BufferedReader(new FileReader(file));
            String eachLine = null;
            int line = 1;
            // 一次读一行，读入null时文件结束
            while ((eachLine = reader.readLine()) != null) {
                try {
                    JSONObject json = JSONObject.parseObject(eachLine);
                    String goodfenl = json.getString("goodfenl");
                    // product_system_code
                    String productSystemCode = SystemSeqUtils.getSeq(dbSequenceService.getProductCodeNumber());
                    int strIndex = goodfenl.lastIndexOf(":");
                    String classificationName = goodfenl.substring(strIndex + 1);
                    PlatformGoodsClassification classification = platformGoodsClassificationMapper.selectLevel2AssortmentByName(classificationName);
                    if(classification == null) {
                        throw new BaseException(SystemErrorCode.SYS_ERROR_SERVICE_NOT_USE,"分类不存在");
                    }
                    Long classificationId = classification.getId();
                    String productCode = json.getString("goodcpxh");
                    String brandName = json.getString("goodpinp");
                    String price = json.getString("goodcpprice");
                    Integer deliveryDate = json.getInteger("goodfhr");
                    
                    Long brandId = this.addBrand(brandName, creator);
                    
                    String moduleName = json.getString("goodmzname");
                    
                    
                    // 商品数据整理
                    MerchantGoodsProduct goods = new MerchantGoodsProduct();
                    goods.setAssortmentId(classificationId);
                    goods.setProductCode(productCode);
                    goods.setBrandId(brandId);
                    goods.setProductSystemCode(productSystemCode);
                    goods.setCreator(creator);
                    goods.setCreateTime(nowDate);
                    goods.setMerchantId(merchantId);
                    Long moduleId = merchantGoodsModuleMapper.selectIdByModuleName(moduleName);
                    if(moduleId != null) {
                        goods.setModuleId(moduleId);
                    }
                    merchantGoodsProductMapper.insertSelective(goods);
                    
                    
                    // 产品价格表插入
                    MerchantGoodsProductPrice mgpp = new MerchantGoodsProductPrice();
                    mgpp.setDeliveryDate(deliveryDate);
                    mgpp.setPrice(new BigDecimal(price));
                    mgpp.setProductId(goods.getId());
                    merchantGoodsProductPriceMapper.insertSelective(mgpp);
                    
                    
                    // 产品参数
                    MerchantGoodsProductParams obj = new MerchantGoodsProductParams();
                    
                    Set<String> keys = json.keySet();
                    for (String key : keys) {// key 即参数项编码
                        if(isParamTermKey(key)) {
                            String value = json.getString(key);
                            // 查询是否已经存在相同的参数项值了
                            int count = goodsParamsMapper.countParamsValue(value, key);
                            if(count == 0) { // 不存在此参数值才新增平台参数值
                                Long paramTermId = goodsParamsMapper.selectParamTermId(key);
                                if(paramTermId != null) {
                                    PlatformGoodsParamsValue ppv = new PlatformGoodsParamsValue();
                                    ppv.setParamsValue(value);
                                    ppv.setParamsParentId(paramTermId);
                                    //System.out.println("参数项值：" + JSONObject.toJSONString(ppv));
                                    goodsParamsMapper.insertTermValueSelective(ppv);
                                }
                            }
                            PlatformGoodsParamsTerm paramTerm = goodsParamsMapper.selectParamTerm(key);
                            if(paramTerm != null) {
                                obj.setParamsChoice((byte)2);
                                obj.setParamsChoice((byte)2);
                                obj.setParamsType((byte)1);
                                obj.setProductId(goods.getId());
                                obj.setParamsId(paramTerm.getParamsId());
                                obj.setParamsName(paramTerm.getParamsName());
                                obj.setParamsTermId(paramTerm.getId());
                                obj.setParamsValue(value);
                                merchantGoodsProductParamsMapper.insertSelective(obj);
                            }
                        }
                    }
                    
                } catch (Exception e) {
                    System.out.println("第"+line+"行出错:"+e.getMessage());
                }
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return JsonResult.successJsonResult();
    }
	
	public Long addBrand(String brandName, String creator) {
	    PlatformGoodsBrand isBrand = platformGoodsBrandMapper.countGoodsBrandByBrandName(brandName);
        Long brandId = null;
        if(ObjectUtils.isNull(isBrand)) {
            // 品牌库中未找到品牌信息需先添加品牌信息，品牌图片暂时先默认
            PlatformGoodsBrand record = new PlatformGoodsBrand();
            record.setBrandCode(dbSequenceService.getBrandCodeNumber());
            record.setBrandName(brandName);
            record.setBrandPicUrl("http://azz-image.oss-cn-shenzhen.aliyuncs.com/plat-image/brand_pic/ico1_BD00000281.png");
            record.setCreateTime(new Date());
            record.setBrandPicName("ico1.png");
            record.setStatus(1);
            record.setCreator(creator);
            platformGoodsBrandMapper.insertSelective(record);
            brandId = record.getId();
        } else {
            brandId = isBrand.getId();
        }
	    return brandId;
	}
	
	
	private static boolean isParamTermKey(String key) {
		if ("goodfhr".equals(key) 
				|| "goodcpprice".equals(key) 
				|| "goodfenl".equals(key) 
				|| "goodpinp".equals(key)
				|| "goodmzname".equals(key) 
				|| "goodstatus".equals(key) 
				|| "goodcpxh".equals(key)) {
			return false;
		}
		return true;
	}
	
	public JsonResult<String> batchUpdateProductModule() {
        File file = new File("D:\\productInfo.json");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String eachLine = null;
            int line = 1;
            // 一次读一行，读入null时文件结束
            while ((eachLine = reader.readLine()) != null) {
                try {
                    JSONObject json = JSONObject.parseObject(eachLine);
                    String productCode = json.getString("goodcpxh");
                    String moduleName = json.getString("goodmzname");
                    Long moduleId = merchantGoodsModuleMapper.selectIdByModuleName(moduleName);
                    if(moduleId != null) {
                        merchantGoodsProductMapper.updateModuleIdByProductCode(moduleId, productCode);
                    }
                } catch (Exception e) {
                    System.out.println("第"+line+"行出错:"+e.getMessage());
                }
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return JsonResult.successJsonResult();
    }

}

