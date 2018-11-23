package com.azz.order.client.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.azz.order.client.pojo.ClientOrderPersonal;
import com.azz.order.client.pojo.bo.SearchAddInvoiceApplyParam;
import com.azz.order.client.pojo.bo.SearchClientOrderParam;
import com.azz.order.client.pojo.vo.ClientAddInvoice;
import com.azz.order.client.pojo.vo.ClientOrderInfo;
import com.azz.order.client.pojo.vo.DeliveryInfo;
import com.azz.order.client.pojo.vo.SignInfo;
import com.azz.order.platform.bo.SearchPlatformClientOrderParam;
import com.azz.order.platform.vo.AllocatedMerchantOrderInfo;
import com.azz.order.platform.vo.MerchantOrderInfo;
import com.azz.order.platform.vo.PlatformClientOrderInfo;
@Mapper
public interface ClientOrderPersonalMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClientOrderPersonal record);

    int insertSelective(ClientOrderPersonal record);

    ClientOrderPersonal selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClientOrderPersonal record);

    int updateByPrimaryKey(ClientOrderPersonal record);
    
    /***************************************************************************************/
    /************************************以下为客户端sql***************************************/
    /***************************************************************************************/
    /**
     * 
     * <p>查询客户订单列表</p>
     * @param param
     * @return
     * @author 黄智聪  2018年11月12日 下午4:47:45
     */
    List<ClientOrderInfo> getClientOrderInfoList(SearchClientOrderParam param);
    
    /**
     * 
     * <p>查询订单详情</p>
     * @param clientOrderCode
     * @return
     * @author 黄智聪  2018年11月13日 上午11:06:57
     */
    ClientOrderPersonal getClientOrderPersonalByClientOrderCode(String clientOrderCode);
    
    /**
     * 
     * <p>查询订单详情</p>
     * @param clientOrderCode
     * @return
     * @author 黄智聪  2018年11月13日 上午11:06:57
     */
    ClientOrderInfo getClientOrderDetailByClientOrderCode(String clientOrderCode);
    
    /**
     * 
     * <p>查询订单详情</p>
     * @param clientOrderCode
     * @return
     * @author 黄智聪  2018年11月13日 上午11:06:57
     */
    List<DeliveryInfo> getDeliveryInfoByClientOrderCode(String clientOrderCode);
    
    /**
     * 
     * <p>查询签收信息</p>
     * @param clientOrderCode
     * @return
     * @author 黄智聪  2018年11月13日 下午2:04:36
     */
    SignInfo getSignInfoByClientOrderCode(String clientOrderCode);
    
    /**
     * 
     * <p>查询6小时未支付的客户订单id集合</p>
     * @param clientOrderCode
     * @return
     * @author 黄智聪  2018年11月13日 上午11:06:57
     */
    List<Long> getSixHoursNotPaidClientOrderIds(Integer orderStatusId);
    
    /**
     * <p>获取新增开票申请</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月19日 下午5:04:12
     */
    List<ClientAddInvoice> getInvoiceClient(SearchAddInvoiceApplyParam param);
    
    /**
     * <p>获取订单明细支持订单编码与客户编码索取</p>
     * @param orderCode
     * @param clientUserCode
     * @return
     * @author 彭斌  2018年11月22日 下午3:29:44
     */
    List<ClientOrderInfo> getClientOrderInfoListByParam(@Param("orderCode") String orderCode, @Param("clientUserCode") String clientUserCode);
    /***************************************************************************************/
    /************************************以上为客户端sql***************************************/
    /***************************************************************************************/
    
    
    
    
    /***************************************************************************************/
    /************************************以下为平台端sql***************************************/
    /***************************************************************************************/
    
    /**
     * 
     * <p>查询平台客户订单列表</p>
     * @param param
     * @return
     * @author 黄智聪  2018年11月13日 下午7:35:54
     */
    List<PlatformClientOrderInfo> getPlatformClientOrderInfoList(SearchPlatformClientOrderParam param);
    
    /**
     * 
     * <p>查询分配后的商户订单信息</p>
     * @param clientOrderCode
     * @return
     * @author 黄智聪  2018年11月14日 下午4:26:41
     */
    AllocatedMerchantOrderInfo getAllocatedMerchantOrderInfoByClientOrderCode(String clientOrderCode);
    
    /**
     * 
     * <p>查询拆单后的商户订单列表</p>
     * @param clientOrderCode
     * @return
     * @author 黄智聪  2018年11月14日 下午4:55:06
     */
    List<MerchantOrderInfo> getMerchantOrderListByClientOrderCode(@Param("clientOrderCode")String clientOrderCode);
    
    /**
     * 
     * <p>查询客户订单拆单后的生成的商户订单列表</p>
     * @param clientOrderCode
     * @return
     * @author 黄智聪  2018年11月14日 下午4:55:06
     */
    List<MerchantOrderInfo> getGeneratedMerchantOrderListByClientOrderCode(@Param("clientOrderId")Long clientOrderId);
    
    /**
     * 
     * <p>查询拆单后的具体某个商户的订单列表</p>
     * @param clientOrderCode
     * @param merchantCode
     * @return
     * @author 黄智聪  2018年11月14日 下午4:55:06
     */
    MerchantOrderInfo getMerchantOrderListByClientOrderCodeAndMerchantCode(@Param("clientOrderCode")String clientOrderCode, @Param("merchantCode")String merchantCode);
    
    /***************************************************************************************/
    /************************************以上为平台端sql***************************************/
    /***************************************************************************************/
}