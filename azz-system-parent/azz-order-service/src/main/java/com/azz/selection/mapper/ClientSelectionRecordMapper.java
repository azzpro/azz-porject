package com.azz.selection.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.azz.order.selection.ClientSelectionRecord;
import com.azz.order.selection.bo.SearchSelectionRecordParam;
import com.azz.order.selection.vo.SelectionRecord;

@Mapper
public interface ClientSelectionRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ClientSelectionRecord record);

    int insertSelective(ClientSelectionRecord record);

    ClientSelectionRecord selectByPrimaryKey(Integer id);

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
    
}