/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月16日 下午7:33:31
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.user.api;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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
import com.azz.platform.user.pojo.bo.EditSignUpCourseParam;
import com.azz.platform.user.pojo.bo.SearchArticleParam;
import com.azz.platform.user.pojo.bo.SearchCourseParam;
import com.azz.platform.user.pojo.bo.SearchImageParam;
import com.azz.platform.user.pojo.vo.ArticleInfo;
import com.azz.platform.user.pojo.vo.ColumnInfo;
import com.azz.platform.user.pojo.vo.ImageInfo;
import com.azz.platform.user.pojo.vo.SignUpCourse;

@FeignClient("azz-user-service")
public interface IndexService {
    
    /**
     * <p>新增栏目</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月27日 下午9:38:56
     */
    @PostMapping("/azz/api/index/addColumn")
    JsonResult<String> addColumn(@RequestBody AddColumn param);
    
    /**
     * <p>获取所有栏目集合倒序</p>
     * @return
     * @author 彭斌  2018年11月27日 下午9:38:59
     */
    @PostMapping("/azz/api/index/getColumnLsit")
    JsonResult<List<ColumnInfo>> getColumnLsit();
    
    /**
     * <p>编辑栏目</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月27日 下午9:39:02
     */
    @PostMapping("/azz/api/index/editColumn")
    JsonResult<String> editColumn(@RequestBody EditColumn param);
    
    /**
     * <p>删除栏目</p>
     * @param columnId
     * @return
     * @author 彭斌  2018年11月27日 下午9:39:04
     */
    @GetMapping("/azz/api/index/delColumn")
    JsonResult<String> delColumn(@RequestParam("columnId") Long columnId);
    
    /**
     * <p>获取栏目详情</p>
     * @param columnId
     * @return
     * @author 彭斌  2018年11月27日 下午9:39:07
     */
    @GetMapping("/azz/api/index/getColumnInfo")
    JsonResult<PlatformIndexColumn> getColumnInfo(@RequestParam("columnId") Long columnId);

    /**
     * <p>获取图片管理分页列表</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月29日 下午1:50:57
     */
    @PostMapping("/azz/api/index/getImageList")
    JsonResult<Pagination<ImageInfo>> getImageList(@RequestBody SearchImageParam param);

    /**
     * <p>新增图片</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月29日 下午1:51:37
     */
    @PostMapping("/azz/api/index/addImage")
    JsonResult<String> addImage(@RequestBody AddImage param);
    
    /**
     * <p>编辑图片管理</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月29日 下午1:52:06
     */
    @PostMapping("/azz/api/index/editImage")
    JsonResult<String> editImage(@RequestBody EditImage param);

    /**
     * <p>获取图片详情</p>
     * @param imageId
     * @return
     * @author 彭斌  2018年11月29日 下午1:52:54
     */
    @GetMapping("/azz/api/index/getImageInfo")
    JsonResult<PlatformIndexImage> getImageInfo(@RequestParam("imageId") Long imageId);

    /**
     * <p>删除图片管理</p>
     * @param imageId
     * @return
     * @author 彭斌  2018年11月29日 下午1:53:36
     */
    @GetMapping("/azz/api/index/delImage")
    JsonResult<String> delImage(@RequestParam("imageId") Long imageId);
    
    /**
     * <p>获取文章分页列表</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月29日 下午1:54:23
     */
    @PostMapping("/azz/api/index/getArticleList")
    JsonResult<Pagination<ArticleInfo>> getArticleList(@RequestBody SearchArticleParam param);

    /**
     * <p>获取文章详情</p>
     * @param articleId
     * @return
     * @author 彭斌  2018年11月29日 下午1:54:57
     */
    @GetMapping("/azz/api/index/getArticleInfo")
    JsonResult<PlatformIndexArticle> getArticleInfo(@RequestParam("articleId") Long articleId);
    
    /**
     * <p>新增文章</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月29日 下午1:55:40
     */
    @PostMapping("/azz/api/index/addArticle")
    JsonResult<String> addArticle(@RequestBody AddArticle param);
    
    /**
     * <p>修改文章</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月29日 下午1:56:02
     */
    @PostMapping("/azz/api/index/editArticle")
    JsonResult<String> editArticle(@RequestBody EditArticle param);

    /**
     * <p>删除文章</p>
     * @param articleId
     * @return
     * @author 彭斌  2018年11月29日 下午1:57:22
     */
    @GetMapping("/azz/api/index/delArticle")
    JsonResult<String> delArticle(@RequestParam("articleId") Long articleId);
    
    /**
     * <p>获取报名管理列表</p>
     * @param param
     * @return
     * @author 彭斌  2018年12月6日 上午11:28:54
     */
    @PostMapping("/azz/api/index/getClientSignUpList")
    JsonResult<Pagination<SignUpCourse>> getClientSignUpList(@RequestBody SearchCourseParam param);
    
    /**
     * <p>处理报名管理</p>
     * @param param
     * @return
     * @author 彭斌  2018年12月6日 上午11:28:57
     */
    @PostMapping("/azz/api/index/editSignUp")
    JsonResult<String> editSignUp(@RequestBody EditSignUpCourseParam param);
}

