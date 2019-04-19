package com.magfin.web;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @Time 2019/4/2
 * @Author zlian
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /*@Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void whenQuerySuccess() throws Exception {
        //mock一个restful请求
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder =
                get("/user")
                        .param("userName","micheal")
                        .param("size","15")
                        .param("page","2")
                        .param("sort","age,desc")
                .contentType(MediaType.APPLICATION_JSON_UTF8);
        //发起这个请求，期望的值在andExpect内
        String result = mockMvc.perform(mockHttpServletRequestBuilder)
                //返回的是200
                .andExpect(status().isOk())
                //返回的是集合，长度为3
                .andExpect(jsonPath("$.length()").value(3))
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }

    @Test
    public void whenGetInfoSucess() throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder =
                get("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8);
        String result = mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value("micheal"))
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }

    @Test
    public void whenGetInfoFail() throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder =
                get("/user/a").contentType(MediaType.APPLICATION_JSON_UTF8);
        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void whenCreateSuccess() throws Exception {

        Date date = new Date();
        System.out.println(date.getTime());
        String content = "{\"userName\":\"micheal\",\"passWord\":null,\"birthday\":"+date.getTime()+"}";
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder =
                post("/user").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content);
        String result = mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }

    @Test
    public void whenUpdateSuccess() throws Exception {

        Date date = new Date(LocalDateTime.now().plusYears(1).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        System.out.println(date.getTime());
        String content = "{\"id\":\"1\",\"userName\":\"micheal\",\"passWord\":null,\"birthday\":"+date.getTime()+"}";
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder =
                put("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content);
        String result = mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }

    @Test
    public void whenDeleteSuccess() throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder =
                delete("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8);
        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(status().isOk());
    }*/

    /**
     * 部署流程定义（操作数据表:ACT_RE_DEPLOYMENT，ACT_RE_PROCDEF，ACT_GE_BYTEARRAY）
     */
    @Test
    public void process1(){
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = defaultProcessEngine.getRepositoryService();
        //部署构建器对象,用于加载流程定义文件(firstProcess.bpmn,firstProcess.xml,firstProcess.png),完成流程的部署
        DeploymentBuilder builder = repositoryService.createDeployment();
        builder.addClasspathResource("processes/firstProcess.bpmn");
        builder.addClasspathResource("processes/firstProcess.png");
        //部署流程定义
        Deployment deploy = builder.deploy();
        System.out.println(deploy.getId());
    }

    /**
     * 查询流程定义列表
     */
    @Test
    public void process2(){
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        {
            defaultProcessEngine.getRepositoryService().createDeploymentQuery().list();
            defaultProcessEngine.getRuntimeService().createProcessInstanceQuery().list();
            defaultProcessEngine.getTaskService().createTaskQuery().list();
        }
        RepositoryService repositoryService = defaultProcessEngine.getRepositoryService();
        //流程定义查询对象,用于查询ACT_RE_PROCDEF
        ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
        //添加过滤条件
        query.processDefinitionKey("qjlc");
        //添加排序
        query.orderByProcessDefinitionVersion().desc();
        //添加分页查询
        query.listPage(0,10);
        List<ProcessDefinition> list = query.list();
        for(ProcessDefinition pd:list){
            System.out.println(pd.getId());
        }
    }

    /**
     * 根据流程定义的ID启动一个流程实例
     */
    @Test
    public void process3(){
        String processId="qjlc:5:15004";
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        ProcessInstance processInstance = defaultProcessEngine.getRuntimeService().startProcessInstanceById(processId);
        System.out.println(processInstance.getId());
    }

    /**
     * 查询任务列表
     */
    @Test
    public void process4(){
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        TaskQuery taskQuery = defaultProcessEngine.getTaskService().createTaskQuery();
        String assignee = "lisi";
        taskQuery.taskAssignee(assignee);
        List<Task> list = taskQuery.list();
        for(Task task:list){
            System.out.println(task.getId()+"    "+task.getName() );

        }
    }

    /**
     * 办理任务
     */
    @Test
    public void process5(){
        String taskId="20002";
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        //办理任务
        defaultProcessEngine.getTaskService().complete(taskId);
    }
}
