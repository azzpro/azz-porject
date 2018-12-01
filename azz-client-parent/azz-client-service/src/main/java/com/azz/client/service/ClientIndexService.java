/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月24日 下午1:24:28
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.client.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.azz.client.mapper.PlatformIndexArticleMapper;
import com.azz.client.mapper.PlatformIndexColumnMapper;
import com.azz.client.pojo.vo.ArticleDetail;
import com.azz.client.pojo.vo.HomeNav;
import com.azz.client.pojo.vo.HomeNavDetail;
import com.azz.client.pojo.vo.HomeSlide;
import com.azz.core.common.JsonResult;
import com.azz.core.constants.ClientConstants;


@Service
public class ClientIndexService {

    
    @Autowired
    private PlatformIndexArticleMapper platformIndexArticleMapper;
    
    @Autowired
    private PlatformIndexColumnMapper platformIndexColumnMapper;
    
    
    /**
     * <p>查询首页轮播图片</p>
     * @return
     * @author 彭斌  2018年12月1日 下午5:28:31
     */
    public JsonResult<List<HomeSlide>> searchHomeSlide(){
        List<HomeSlide> list = platformIndexColumnMapper.getHomeSlideList(ClientConstants.HOME_SLIDE);
        return JsonResult.successJsonResult(list);
    }
    
    /**
     * <p>查询首页导航1栏</p>
     * @return
     * @author 彭斌  2018年12月1日 下午6:00:27
     */
    public JsonResult<List<HomeNav>> searchNavOne(){
        List<HomeNav> list = platformIndexColumnMapper.getNavListByColumnCode(ClientConstants.HOME_NAV1);
        return JsonResult.successJsonResult(list);
    }
    
    /**
     * <p>查询首页导航2栏</p>
     * @return
     * @author 彭斌  2018年12月1日 下午6:00:27
     */
    public JsonResult<List<HomeNav>> searchNavTwo(){
        List<HomeNav> list = platformIndexColumnMapper.getNavListByColumnCode(ClientConstants.HOME_NAV2);
        return JsonResult.successJsonResult(list);
    }
    
    /**
     * <p>根据栏目编码获取栏目列表</p>
     * @param columnCode
     * @return
     * @author 彭斌  2018年12月1日 下午9:40:21
     */
    public JsonResult<HomeNavDetail> searchNavList(String columnCode){
        HomeNavDetail hd = platformIndexColumnMapper.getColumnList(columnCode);
        return JsonResult.successJsonResult(hd);
    }
    
    /**
     * <p>获取文章详情</p>
     * @param articleId
     * @return
     * @author 彭斌  2018年12月1日 下午10:02:29
     */
    public JsonResult<ArticleDetail> searchNavDetail(Long articleId){
        ArticleDetail adObj = platformIndexArticleMapper.getArticleDetail(articleId);
        return JsonResult.successJsonResult(adObj);
    }
}

