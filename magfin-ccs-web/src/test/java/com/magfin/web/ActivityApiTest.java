package com.magfin.web;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.*;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.List;
import java.util.zip.ZipInputStream;

/**
 * @Time 2019/4/20
 * @Author 佛祖保佑的最
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivityApiTest {
    ProcessEngine processEngine = null;
    @Before
    public void before(){
        processEngine = ProcessEngines.getDefaultProcessEngine();
    }
    /**
     * 部署流程定义
     * 方式一：读取单个的流程定义文件
     * 方式二：读取zip压缩文件
     */
    @Test
    public void test1(){
        DeploymentBuilder deployment = processEngine.getRepositoryService().createDeployment();
        //方式一：读取单个的流程定义文件
//        deployment.addClasspathResource("processes/firstProcess.bpmn");
//        deployment.addClasspathResource("processes/firstProcess.png");
//        Deployment deploy = deployment.deploy();
        //方式二：读取zip压缩文件
        ZipInputStream zipInputStream = new ZipInputStream(this.getClass()
                .getClassLoader().getResourceAsStream("processes/processes.zip"));
        deployment.addZipInputStream(zipInputStream);
        deployment.name("请假流程部署");
        Deployment deploy = deployment.deploy();
    }

    /**
     * 查询部署列表
     */
    @Test
    public void test2(){
        //部署查询对象，查询表ACT_RE_DEPLOYMENT
        DeploymentQuery deploymentQuery = processEngine.getRepositoryService().createDeploymentQuery();
        for(Deployment d :deploymentQuery.list()){
            System.out.println(d.getId());
        }


    }

    /**
     * 查询流程定义列表
     */
    @Test
    public void test3(){
        ProcessDefinitionQuery processDefinitionQuery = processEngine.getRepositoryService().createProcessDefinitionQuery();
        List<ProcessDefinition> list = processDefinitionQuery.list();
        for(ProcessDefinition p:list){
            System.out.println(p.getName());
        }
    }


    /**
     * 删除部署信息
     */
    @Test
    public void test4(){
        String deploymentId = "1";
        //简单的流程部署删除
        processEngine.getRepositoryService().deleteDeployment(deploymentId);
        //级联删除，如果该流程有在途流程，上面的删除会报错，下面的会全删了
        processEngine.getRepositoryService().deleteDeployment(deploymentId,true);
    }

    /**
     * 删除流程定义(通过删除部署信息来达到删除流程定义的目的)
     * 删除  ACT_RE_DEPLOYMENT流程部署表   同步删除  ACT_RE_PROCDEF流程定义表
     */
    @Test
    public void test5(){
        //test4()
    }

    /**
     * 查询一次部署对应的流程定义文件,和数据流(png，xml，bpmn)
     */
    @Test
    public void test6() throws IOException {
        String deploymentId = "27501";
        /*获取所有文件*/
        /*List<String> deploymentResourceNames = defaultProcessEngine.getRepositoryService().getDeploymentResourceNames(deploymentId);
        for(String name :deploymentResourceNames){
            //流程定义文件名称
            System.out.println(name);
            //获取流,下载文件
            InputStream in = defaultProcessEngine.getRepositoryService().getResourceAsStream(deploymentId, name);
            *//*OutputStream out = new FileOutputStream(new File("D:\\activity,chongxiangmengxiangjiaoyu\\"+name));
            byte[] bytes = new byte[1024];
            int read = 0;
            while ((read = in.read(bytes))!=-1){
                out.write(bytes,0,read);
            }
            out.close();*//*
            FileUtils.copyInputStreamToFile(in,new File("D:\\activity,chongxiangmengxiangjiaoyu\\"+name));
            in.close();
        }*/
        /*获取图片文件,传入的是流程定义的ID*/
        InputStream processDiagram = processEngine.getRepositoryService().getProcessDiagram("qjlc:7:27505");
        FileUtils.copyInputStreamToFile(processDiagram,new File("D:\\activity,chongxiangmengxiangjiaoyu\\my.png"));
    }


    /**
     * 启动流程
     * 方式一：根据流程定义的id启动
     * 方式二：根据流程定义的key启动(自动选择最新版本的流程定义启动流程实例)
     */
    @Test
    public void test7(){
        //根据流程定义的id启动
//        ProcessInstance processInstance = defaultProcessEngine.getRuntimeService().startProcessInstanceById("qjlc:7:27505");
//        System.out.println(processInstance.getId());
        //根据流程定义的key启动
        ProcessInstance qjlc = processEngine.getRuntimeService().startProcessInstanceByKey("qjlc");
        System.out.println(qjlc.getId());
    }

    /**
     * 查询流程实例列表,查询ACT_RU_EXECUTION
     */
    @Test
    public void test8(){
        ProcessInstanceQuery processInstanceQuery = processEngine.getRuntimeService().createProcessInstanceQuery();
        List<ProcessInstance> list = processInstanceQuery.list();
        for(ProcessInstance pi:list){
            System.out.println(pi.getId());
            System.out.println(pi.getActivityId());//act_id
        }
    }

    /**
     * 结束流程实例 (拒绝) 流程实例表ACT_RU_EXECUTION和流程任务表ACT_RU_TASK
     */
    @Test
    public void test9(){
        String processInstanceId="30007";
        String deleteReason="测试拒绝流程1";
        processEngine.getRuntimeService().deleteProcessInstance(processInstanceId,deleteReason);
    }



    /**
     * 查询任务列表
     */
    @Test
    public void test10(){
        TaskQuery taskQuery = processEngine.getTaskService().createTaskQuery();
        taskQuery.taskAssignee("lisi");
        List<Task> list = taskQuery.list();
        for(Task t:list){
            //任务id
            System.out.println("====="+t.getId());
        }
    }


    /**
     * 办理任务
     */
    @Test
    public void test11(){
        processEngine.getTaskService().complete("37502");

    }


    /**
     * 直接将流程向下执行一步
     */
    @Test
    public void test12(){
        String signalName = "经理审批";
        String executionId = "35002";
//        processEngine.getRuntimeService().signalEventReceived();

    }


}
