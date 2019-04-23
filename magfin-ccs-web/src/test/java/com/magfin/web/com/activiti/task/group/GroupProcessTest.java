package com.magfin.web.com.activiti.task.group;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.history.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
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
public class GroupProcessTest {
    ProcessEngine processEngine = null;
    @Before
    public void before(){
        processEngine = ProcessEngines.getDefaultProcessEngine();
    }

    /**
     * 部署多人执行一个节点的流程
     */
    @Test
    public void test1(){
        DeploymentBuilder deployment = processEngine.getRepositoryService().createDeployment();
        //方式一：读取单个的流程定义文件
        deployment.addClasspathResource("processes/groupProcess.bpmn");
        deployment.addClasspathResource("processes/groupProcess.png");
        Deployment deploy = deployment.deploy();
    }

    /**
     * 启动流程
     */
    @Test
    public void test2(){
        ProcessInstance qjlc = processEngine.getRuntimeService().startProcessInstanceByKey("groupTask");
        System.out.println(qjlc.getId());
    }


    /**
     * 办理个人任务
     */
    @Test
    public void test3(){
        processEngine.getTaskService().complete("37502");
    }

    /**
     * 查询公共任务列表 ACT_RU_IDENTITYLINK
     */
    @Test
    public void test4(){
        TaskQuery query = processEngine.getTaskService().createTaskQuery();
        //根据候选人过滤
        String candidateUser = "李四";
        List<Task> list = query.taskCandidateUser(candidateUser).list();
        for (Task task : list) {
            System.out.println(task.getName());
        }
    }

    /**
     * 拾取任务(公共任务变成个人任务，争抢任务)
     */
    @Test
    public void test5(){
        String taskId = "37502";
        String userId = "王五";
        processEngine.getTaskService().claim(taskId,userId);

    }

    /**
     * 退回任务(个人任务变成公共任务)
     */
    @Test
    public void test6(){
        String taskId = "37502";
        String userId = null;
        processEngine.getTaskService().setAssignee(taskId,userId);

    }



}
