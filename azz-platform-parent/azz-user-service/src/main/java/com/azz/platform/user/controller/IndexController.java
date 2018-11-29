/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月27日 上午11:02:10
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.platform.user.pojo.PlatformIndexArticle;
import com.azz.platform.user.pojo.PlatformIndexColumn;
import com.azz.platform.user.pojo.PlatformIndexImage;
import com.azz.platform.user.pojo.bo.AddArticle;
import com.azz.platform.user.pojo.bo.AddColumn;
import com.azz.platform.user.pojo.bo.AddImage;
import com.azz.platform.user.pojo.bo.EditArticle;
import com.azz.platform.user.pojo.bo.EditColumn;
import com.azz.platform.user.pojo.bo.EditImage;
import com.azz.platform.user.pojo.bo.SearchArticleParam;
import com.azz.platform.user.pojo.bo.SearchImageParam;
import com.azz.platform.user.pojo.vo.ArticleInfo;
import com.azz.platform.user.pojo.vo.ColumnInfo;
import com.azz.platform.user.pojo.vo.ImageInfo;
import com.azz.platform.user.service.IndexService;

@RestController
@RequestMapping("/azz/api/index")
public class IndexController {

	
	@Autowired
	private IndexService indexService;
	
	/**
	 * <p>新增栏目</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年11月27日 下午9:33:43
	 */
	@RequestMapping(value="addColumn",method=RequestMethod.POST)
	JsonResult<String> addColumn(@RequestBody AddColumn param){
		return indexService.addColumn(param);
	}
	
	/**
	 * <p>获取所有栏目集合倒序</p>
	 * @return
	 * @author 彭斌  2018年11月27日 下午9:33:39
	 */
	@RequestMapping(value="getColumnLsit",method=RequestMethod.POST)
	JsonResult<List<ColumnInfo>> getColumnLsit(){
	    return indexService.getColumnLsit();
	}
	
	/**
	 * <p>编辑栏目</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年11月27日 下午9:33:37
	 */
	@RequestMapping(value="editColumn",method=RequestMethod.POST)
	JsonResult<String> editColumn(@RequestBody EditColumn param){
        return indexService.editColumn(param);
    }
	
	/**
	 * <p>删除栏目</p>
	 * @param columnId
	 * @return
	 * @author 彭斌  2018年11月27日 下午9:33:33
	 */
    @RequestMapping(value="delColumn",method=RequestMethod.GET)
    JsonResult<String> delColumn(@RequestParam("columnId") Long columnId){
    	return indexService.delColumn(columnId);
    }
    
    /**
     * <p>获取栏目详情</p>
     * @param columnId
     * @return
     * @author 彭斌  2018年11月27日 下午9:33:31
     */
    @RequestMapping(value="getColumnInfo",method=RequestMethod.GET)
    JsonResult<PlatformIndexColumn> getColumnInfo(@RequestParam("columnId") Long columnId){
        return indexService.getColumnInfo(columnId);
    }
    
    /**
     * <p>获取图片管理分页列表</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月29日 下午1:36:26
     */
    @RequestMapping(value="getImageList",method=RequestMethod.POST)
    JsonResult<Pagination<ImageInfo>> getImageList(@RequestBody SearchImageParam param){
        return indexService.getImageList(param);
    }
    
    /**
     * <p>新增图片</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月29日 下午1:38:51
     */
    @RequestMapping(value="addImage",method=RequestMethod.POST)
    JsonResult<String> addImage(@RequestBody AddImage param){
        return indexService.addImage(param);
    }
    
    /**
     * <p>编辑图片管理</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月29日 下午1:40:06
     */
    @RequestMapping(value="editImage",method=RequestMethod.POST)
    JsonResult<String> editImage(@RequestBody EditImage param){
        return indexService.editImage(param);
    }
    
    /**
     * <p>获取图片详情</p>
     * @param imageId
     * @return
     * @author 彭斌  2018年11月29日 下午1:41:12
     */
    @RequestMapping(value="getImageInfo",method=RequestMethod.GET)
    JsonResult<PlatformIndexImage> getImageInfo(@RequestParam("imageId") Long imageId){
        return indexService.getImageInfo(imageId);
    }
    
    /**
     * <p>删除图片管理</p>
     * @param imageId
     * @return
     * @author 彭斌  2018年11月29日 下午1:42:54
     */
    @RequestMapping(value="delImage",method=RequestMethod.GET)
    JsonResult<String> delImage(@RequestParam("imageId") Long imageId){
        return indexService.delImage(imageId);
    }
    
    /**
     * <p>获取文章分页列表</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月29日 下午1:43:30
     */
    @RequestMapping(value="getArticleList",method=RequestMethod.POST)
    JsonResult<Pagination<ArticleInfo>> getArticleList(@RequestBody SearchArticleParam param){
        return indexService.getArticleList(param);
    }
    
    /**
     * <p>获取文章详情</p>
     * @param articleId
     * @return
     * @author 彭斌  2018年11月29日 下午1:45:56
     */
    @RequestMapping(value="getArticleInfo",method=RequestMethod.GET)
    JsonResult<PlatformIndexArticle> getArticleInfo(@RequestParam("articleId") Long articleId){
        return indexService.getArticleInfo(articleId);
    }
    
    /**
     * <p>新增文章</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月29日 下午1:46:42
     */
    @RequestMapping(value="addArticle",method=RequestMethod.POST)
    JsonResult<String> addArticle(@RequestBody AddArticle param){
        return indexService.addArticle(param);
    }
    
    /**
     * <p>修改文章</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月29日 下午1:47:11
     */
    @RequestMapping(value="editArticle",method=RequestMethod.POST)
    JsonResult<String> editArticle(@RequestBody EditArticle param){
        return indexService.editArticle(param);
    }
    
    /**
     * <p>删除文章</p>
     * @param articleId
     * @return
     * @author 彭斌  2018年11月29日 下午1:48:17
     */
    @RequestMapping(value="delArticle",method=RequestMethod.GET)
    JsonResult<String> delArticle(@RequestParam("articleId") Long articleId){
        return indexService.delArticle(articleId);
    }
}

