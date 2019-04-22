package com.magfin.web.com.activiti.history;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.history.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Time 2019/4/22
 * @Author zlian
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HistoryDataTest {
    ProcessEngine processEngine = null;
    @Before
    public void before(){
        processEngine = ProcessEngines.getDefaultProcessEngine();
    }

    /**
     * 查询历史流程实例列表 ACT_HI_PROCINST
     */
    @Test
    public void test1(){
        HistoricProcessInstanceQuery query =
                processEngine.getHistoryService().createHistoricProcessInstanceQuery();
        List<HistoricProcessInstance> list = query.list();
        for (HistoricProcessInstance hpi : list) {
            System.out.println(hpi.getId());
        }
    }

    /**
     * 查询历史活动数据列表 ACT_HI_ACTINST
     */
    @Test
    public void test2(){
        HistoricActivityInstanceQuery query = processEngine.getHistoryService().createHistoricActivityInstanceQuery();
        query.orderByProcessInstanceId().asc();
        query.orderByHistoricActivityInstanceEndTime().asc();
        List<HistoricActivityInstance> list = query.list();
        for (HistoricActivityInstance hai : list) {
            System.out.println(hai.getActivityName());
        }

    }

    /**
     * 查询历史任务表数据  ACT_HI_TASKINST
     */
    @Test
    public void test3(){
        HistoricTaskInstanceQuery query = processEngine.getHistoryService().createHistoricTaskInstanceQuery();
        query.orderByProcessInstanceId().asc();
        query.orderByHistoricTaskInstanceEndTime().asc();
        List<HistoricTaskInstance> list = query.list();
        for (HistoricTaskInstance hti : list) {
            System.out.println(hti.getName());
        }
    }
}
