package com.magfin.web.dao;

import com.magfin.web.model.UsrUser;

public interface UsrUserMapper {
    int deleteByPrimaryKey(String userCode);

    int insert(UsrUser record);

    int insertSelective(UsrUser record);

    UsrUser selectByPrimaryKey(String userCode);

    int updateByPrimaryKeySelective(UsrUser record);

    int updateByPrimaryKey(UsrUser record);
}