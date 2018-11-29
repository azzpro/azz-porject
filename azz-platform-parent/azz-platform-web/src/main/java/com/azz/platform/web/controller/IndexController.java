/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月25日 下午3:23:51
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.web.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.platform.user.api.IndexService;
import com.azz.platform.user.pojo.PlatformIndexArticle;
import com.azz.platform.user.pojo.PlatformIndexColumn;
import com.azz.platform.user.pojo.PlatformIndexImage;
import com.azz.platform.user.pojo.bo.AddArticle;
import com.azz.platform.user.pojo.bo.AddArticleWebParam;
import com.azz.platform.user.pojo.bo.AddColumn;
import com.azz.platform.user.pojo.bo.AddColumnWebParam;
import com.azz.platform.user.pojo.bo.AddImage;
import com.azz.platform.user.pojo.bo.AddImageWebParam;
import com.azz.platform.user.pojo.bo.EditArticle;
import com.azz.platform.user.pojo.bo.EditArticleWebParam;
import com.azz.platform.user.pojo.bo.EditColumn;
import com.azz.platform.user.pojo.bo.EditColumnWebParam;
import com.azz.platform.user.pojo.bo.EditImage;
import com.azz.platform.user.pojo.bo.EditImageWebParam;
import com.azz.platform.user.pojo.bo.MainPicture;
import com.azz.platform.user.pojo.bo.SearchArticleParam;
import com.azz.platform.user.pojo.bo.SearchImageParam;
import com.azz.platform.user.pojo.vo.ArticleInfo;
import com.azz.platform.user.pojo.vo.ColumnInfo;
import com.azz.platform.user.pojo.vo.ImageInfo;
import com.azz.util.Base64;
import com.azz.utils.WebUtils;

/**
 * <P>首页管理</P>
 * @version 1.0
 * @author 彭斌  2018年11月29日 下午2:10:20
 */
@RestController
@RequestMapping("/azz/api/index")
public class IndexController {
	
	
	@Autowired
	private IndexService indexService;
	
	/**
	 * <p>新增栏目</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年11月29日 下午2:39:12
	 */
	@RequestMapping("/addColumn")
	public JsonResult<String> addColumn(AddColumnWebParam param)  throws IOException{
	    String userCode = WebUtils.getLoginUser().getUserInfo().getUserCode();
	    MultipartFile classificationFile = param.getMainPicture();
	    MainPicture mp = new MainPicture(classificationFile.getOriginalFilename(), classificationFile.getSize(),Base64.encode(classificationFile.getBytes()));
	    return indexService.addColumn(new AddColumn(param.getColumnName(), param.getColumnCode(), param.getColumnType(),userCode,mp));
	}
	
	/**
	 * <p>获取所有栏目集合倒序</p>
	 * @return
	 * @author 彭斌  2018年11月29日 下午2:39:04
	 */
	@RequestMapping("/getColumnLsit")
	public JsonResult<List<ColumnInfo>> getColumnLsit(){
	    return indexService.getColumnLsit();
	}
	
	/**
	 * <p>编辑栏目</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年11月29日 下午2:38:57
	 */
	@RequestMapping("/editColumn")
	public JsonResult<String> editColumn(EditColumnWebParam param) throws IOException{
	    String userCode = WebUtils.getLoginUser().getUserInfo().getUserCode();
	    MainPicture mp = null;
	    if(1 == param.getEditStatus()) {
	        MultipartFile classificationFile = param.getMainPicture();
	        mp = new MainPicture(classificationFile.getOriginalFilename(), classificationFile.getSize(),Base64.encode(classificationFile.getBytes()));
	    }
	    return indexService.editColumn(new EditColumn(param.getColumnId(), param.getColumnName(), param.getColumnCode(), param.getColumnType(),userCode,param.getEditStatus(),mp));
	}
	
	/**
	 * <p>删除栏目</p>
	 * @param columnId
	 * @return
	 * @author 彭斌  2018年11月29日 下午2:38:49
	 */
	@RequestMapping("/delColumn")
	public JsonResult<String> delColumn(@RequestParam("columnId") Long columnId) {
		return indexService.delColumn(columnId);
	}
	
	/**
	 * <p>获取栏目详情</p>
	 * @param columnId
	 * @return
	 * @author 彭斌  2018年11月29日 下午2:38:41
	 */
	@RequestMapping("/getColumnInfo")
    public JsonResult<PlatformIndexColumn> getColumnInfo(@RequestParam("columnId") Long columnId){
        return indexService.getColumnInfo(columnId);
    }
	
	/**
	 * <p>获取图片管理分页列表</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年11月29日 下午2:38:28
	 */
	@RequestMapping("/getImageList")
	public JsonResult<Pagination<ImageInfo>> getImageList(SearchImageParam param){
	    return indexService.getImageList(param);
	}
	
	/**
	 * <p>新增图片</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年11月29日 下午2:38:19
	 */
	@RequestMapping("/addImage")
	public JsonResult<String> addImage(AddImageWebParam param) throws IOException{
	    String userCode = WebUtils.getLoginUser().getUserInfo().getUserCode();
	    MultipartFile classificationFile = param.getMainPicture();
        MainPicture mp = new MainPicture(classificationFile.getOriginalFilename(), classificationFile.getSize(),Base64.encode(classificationFile.getBytes()));
	    return indexService.addImage(new AddImage(param.getJumpLink(),userCode,param.getIndexColumnId(),mp));
	}
	
	/**
	 * <p>编辑图片管理</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年11月29日 下午2:38:05
	 */
	@RequestMapping("/editImage")
	public JsonResult<String> editImage(EditImageWebParam param) throws IOException{
	    String userCode = WebUtils.getLoginUser().getUserInfo().getUserCode();
	    MainPicture mp = null;
	    if(1 == param.getEditStatus()) {
	        MultipartFile classificationFile = param.getMainPicture();
	        mp = new MainPicture(classificationFile.getOriginalFilename(), classificationFile.getSize(),Base64.encode(classificationFile.getBytes()));
	    }
	    return indexService.editImage(new EditImage(param.getImageId(), param.getColumnId(), param.getJumpLink(), userCode, param.getEditStatus(), mp));
	}
	
	/**
	 * <p>获取图片详情</p>
	 * @param imageId
	 * @return
	 * @author 彭斌  2018年11月29日 下午2:37:58
	 */
	@RequestMapping("/getImageInfo")
	public JsonResult<PlatformIndexImage> getImageInfo(@RequestParam("imageId") Long imageId){
	    return indexService.getImageInfo(imageId);
	}
	
	/**
	 * <p>删除图片管理</p>
	 * @param imageId
	 * @return
	 * @author 彭斌  2018年11月29日 下午2:37:53
	 */
	@RequestMapping("/delImage")
	public JsonResult<String> delImage(@RequestParam("imageId") Long imageId){
	    return indexService.delImage(imageId);
	}
	
	/**
	 * <p>获取文章分页列表</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年11月29日 下午2:37:43
	 */
	@RequestMapping("/getArticleList")
	public JsonResult<Pagination<ArticleInfo>> getArticleList(SearchArticleParam param){
	    return indexService.getArticleList(param);
	}
	
	/**
	 * <p>获取文章详情</p>
	 * @param articleId
	 * @return
	 * @author 彭斌  2018年11月29日 下午2:37:28
	 */
	@RequestMapping("/getArticleInfo")
	public JsonResult<PlatformIndexArticle> getArticleInfo(@RequestParam("articleId") Long articleId){
	    return indexService.getArticleInfo(articleId);
	}
	
	/**
	 * <p>新增文章</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年11月29日 下午2:37:21
	 */
	@RequestMapping("/addArticle")
	public JsonResult<String> addArticle(AddArticleWebParam param) throws IOException{
	    String userCode = WebUtils.getLoginUser().getUserInfo().getUserCode();
	    MultipartFile classificationFile = param.getMainPicture();
        MainPicture mp = new MainPicture(classificationFile.getOriginalFilename(), classificationFile.getSize(),Base64.encode(classificationFile.getBytes()));
	    return indexService.addArticle(new AddArticle(param.getIndexColumnId(),param.getArticleTitle(),param.getPrice(),param.getArticleDetail(),param.getRemark1(),param.getRemark2(),userCode,mp));
	}
	
	/**
	 * <p>修改文章</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年11月29日 下午2:37:15
	 */
	@RequestMapping("/editArticle")
	public JsonResult<String> editArticle(EditArticleWebParam param) throws IOException{
	    String userCode = WebUtils.getLoginUser().getUserInfo().getUserCode();
	    MainPicture mp = null;
	    if(1 == param.getEditStatus()) {
	        MultipartFile classificationFile = param.getMainPicture();
	        mp = new MainPicture(classificationFile.getOriginalFilename(), classificationFile.getSize(),Base64.encode(classificationFile.getBytes()));
	    }
	    return indexService.editArticle(new EditArticle(param.getArticleId(),param.getIndexColumnId(), param.getArticleTitle(),param.getPrice(),param.getArticleDetail(),param.getRemark1(),param.getRemark2(),userCode, param.getEditStatus(),mp));
	}
	
	/**
	 * <p>删除文章</p>
	 * @param articleId
	 * @return
	 * @author 彭斌  2018年11月29日 下午2:36:44
	 */
	@RequestMapping("/delArticle")
	public JsonResult<String> delArticle(@RequestParam("articleId") Long articleId){
	    return indexService.delArticle(articleId);
	}
}

