package com.magfin.web.dao;

import com.magfin.web.model.UsrResource;

import java.util.List;

public interface UsrResourceMapper {
    int deleteByPrimaryKey(String resCode);

    int insert(UsrResource record);

    int insertSelective(UsrResource record);

    UsrResource selectByPrimaryKey(String resCode);

    int updateByPrimaryKeySelective(UsrResource record);

    int updateByPrimaryKey(UsrResource record);

    List<UsrResource> queryListByObject(UsrResource obj);
}