package com.magfin.web.com.activiti.variable;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Time 2019/4/23
 * @Author zlian
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class VariableTest {
    ProcessEngine processEngine = null;
    @Before
    public void before(){
        processEngine = ProcessEngines.getDefaultProcessEngine();
    }
    /**
     * 设置流程变量
     * 1、启动流程实例时设置
     * 2、办理任务时设置
     * 3、使用RuntimeService时设置
     * 4、使用TaskService时设置
     */
    @Test
    public void test1(){
        //1、启动流程实例时设置
        String processDefinitionKey="qjlc";
        Map<String, Object> variables = new HashMap<>();
        variables.put("key1","value1");
        variables.put("key2",198);
        ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey(processDefinitionKey, variables);
        System.out.println(processInstance.getId());

        //2、办理任务时设置
        /*String taskId = "22503";
        Map<String, Object> variables = new HashMap<>();
        variables.put("key1","value999");
        variables.put("key2",888);
        variables.put("key3","恰银");
        processEngine.getTaskService().complete(taskId,variables);*/

        //3、使用RuntimeService时设置
        /*String executionId = "20001";
        String variableName = "key3";
        Object value = "恰两个银";
        processEngine.getRuntimeService().setVariable(executionId,variableName,value);*/

        //4、使用TaskService时设置
        /*String taskId = "22503";
        Map<String, Object> variables = new HashMap<>();
        variables.put("key4",0.028);
        processEngine.getTaskService().setVariables(taskId,variables);*/
    }

    /**
     * 获取流程变量
     * 1、使用RuntimeService时设置
     * 2、使用TaskService时设置
     */
    @Test
    public void test2(){
        String executionId = "30001";
        Object key1 = processEngine.getRuntimeService().getVariable(executionId, "key1");
        System.out.println(key1);

        String taskId = "30006";
        Object key2 = processEngine.getTaskService().getVariable(taskId, "key2");
        System.out.println(key2);
    }
}
