package com.magfin.web.com.activiti.listener.execution;

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

/**
 * @Time 2019/4/23
 * @Author zlian
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ExecutionListenerTest {
    ProcessEngine processEngine = null;
    @Before
    public void before(){
        processEngine = ProcessEngines.getDefaultProcessEngine();
    }

    /**
     * 部署带有监听器的流程
     */
    @Test
    public void test1(){
        DeploymentBuilder deployment = processEngine.getRepositoryService().createDeployment();
        //方式一：读取单个的流程定义文件
        deployment.addClasspathResource("processes/firstExceutionListener.bpmn");
        deployment.addClasspathResource("processes/firstExceutionListener.png");
        Deployment deploy = deployment.deploy();
    }

    /**
     * 启动流程
     */
    @Test
    public void test2(){
        ProcessInstance qjlc = processEngine.getRuntimeService().startProcessInstanceByKey("firstExceutionListener");
        System.out.println(qjlc.getId());
    }

    /**
     * 办理任务
     */
    @Test
    public void test3(){
        processEngine.getTaskService().complete("55002");
    }
}
