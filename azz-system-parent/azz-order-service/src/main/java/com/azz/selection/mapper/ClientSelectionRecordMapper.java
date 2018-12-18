package com.azz.selection.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.azz.order.selection.ClientSelectionRecord;
import com.azz.order.selection.bo.SearchSelectionRecordParam;
import com.azz.order.selection.vo.SelectionRecord;

@Mapper
public interface ClientSelectionRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClientSelectionRecord record);

    int insertSelective(ClientSelectionRecord record);

    ClientSelectionRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClientSelectionRecord record);

    int updateByPrimaryKeyWithBLOBs(ClientSelectionRecord record);

    int updateByPrimaryKey(ClientSelectionRecord record);
    
    /**
     * 
     * <p>查询选型记录</p>
     * @param param
     * @return
     * @author 黄智聪  2018年11月23日 下午3:24:37
     */
    List<SelectionRecord> getSelectionRecordByClientUserCode(SearchSelectionRecordParam param);
    
    /**
     * 
     * <p>根据产品编码、价格id查询选型记录的个数</p>
     * @param productCode
     * @param productPriceId
     * @return
     * @author 黄智聪  2018年11月29日 下午7:45:03
     */
    int countSelectionRecordByProductCodeAndProductPriceId(@Param("productCode")String productCode, @Param("productPriceId")Long productPriceId);
    
    /**
     * 
     * <p>查询个人选型记录的数量</p>
     * @param clientUserId
     * @return
     * @author 黄智聪  2018年12月18日 下午2:48:55
     */
    int countSelectionRecordByClientUserId(Long clientUserId);
}