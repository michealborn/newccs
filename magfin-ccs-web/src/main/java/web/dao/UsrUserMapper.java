package web.dao;

import org.springframework.stereotype.Repository;
import web.model.UsrUser;

@Repository
public interface UsrUserMapper {
    int deleteByPrimaryKey(String userCode);

    int insert(UsrUser record);

    int insertSelective(UsrUser record);

    UsrUser selectByPrimaryKey(String userCode);

    int updateByPrimaryKeySelective(UsrUser record);

    int updateByPrimaryKey(UsrUser record);
}