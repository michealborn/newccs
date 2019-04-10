package com.magfin.web.service;

import com.magfin.web.dao.UsrResourceMapper;
import com.magfin.web.model.UsrResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Time 2019/4/10
 * @Author 佛祖保佑的最
 */
@Service
public class UsrResourceService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UsrResourceMapper usrResourceMapper;


    public List<UsrResource> queryListByObject(UsrResource usrResource){
        List<UsrResource> usrResources = usrResourceMapper.queryListByObject(usrResource);
        return usrResources;
    }
}
