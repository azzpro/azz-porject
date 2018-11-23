package com.azz.selection.mapper;

import com.azz.order.selection.ClientSelectionRecord;

public interface ClientSelectionRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ClientSelectionRecord record);

    int insertSelective(ClientSelectionRecord record);

    ClientSelectionRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ClientSelectionRecord record);

    int updateByPrimaryKeyWithBLOBs(ClientSelectionRecord record);

    int updateByPrimaryKey(ClientSelectionRecord record);
}