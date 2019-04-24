package com.magfin.web.com.activiti.gateway;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @Time 2019/4/24
 * @Author zlian
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GateWayTest {
    ProcessEngine processEngine = null;
    @Before
    public void before(){
        processEngine = ProcessEngines.getDefaultProcessEngine();
    }


    /**
     * 部署带有排他网关的流程
     */
    @Test
    public void test1(){
        DeploymentBuilder deployment = processEngine.getRepositoryService().createDeployment();
        //方式一：读取单个的流程定义文件
        deployment.addClasspathResource("processes/PaiTaGateWay.bpmn");
        deployment.addClasspathResource("processes/PaiTaGateWay.png");
        Deployment deploy = deployment.deploy();
    }


    /**
     * 启动流程
     */
    @Test
    public void test2(){
        ProcessInstance paitagateway = processEngine.getRuntimeService().startProcessInstanceByKey("paitagateway");
        System.out.println(paitagateway.getId());
    }

    /**

     * 办理任务，设置流程变量
     */
    @Test
    public void test3(){
        Map<String, Object> variables = new HashMap<>();
        variables.put("bxje",800);
        processEngine.getTaskService().complete("72504",variables);
    }


    /**
     * 部署带有并行网关的流程
     */
    @Test
    public void test4(){
        DeploymentBuilder deployment = processEngine.getRepositoryService().createDeployment();
        //方式一：读取单个的流程定义文件
        deployment.addClasspathResource("processes/BingXingGateWay.bpmn");
        deployment.addClasspathResource("processes/BingXingGateWay.png");
        Deployment deploy = deployment.deploy();
    }

    /**
     * 启动流程
     */
    @Test
    public void test5(){
        ProcessInstance bingxinggateway = processEngine.getRuntimeService().startProcessInstanceByKey("bingxinggateway");
        System.out.println(bingxinggateway.getId());
    }

    /**
     * 办理任务
     */
    @Test
    public void test6(){
        processEngine.getTaskService().complete("85002");
    }
}
