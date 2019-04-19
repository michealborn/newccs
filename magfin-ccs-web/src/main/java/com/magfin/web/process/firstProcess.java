package com.magfin.web.process;

import org.activiti.engine.*;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.impl.form.StringFormType;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Time 2019/4/15
 * @Author zlian
 */
public class firstProcess {

    public static void main(String[] args) throws ParseException {

        //创建流程引擎
        ProcessEngine processEngine = getProcessEngine();

        //部署流程定义文件
        ProcessDefinition processDefinition = deployment(processEngine);

        //启动运行流程
        ProcessInstance processInstance = getProcessInstance(processEngine, processDefinition);

        //处理流程任务
        processTask(processEngine, processInstance);
    }

    private static ProcessEngine getProcessEngine(){
        ProcessEngineConfiguration cfg =
                ProcessEngineConfiguration.createStandaloneInMemProcessEngineConfiguration();
        ProcessEngine processEngine = cfg.buildProcessEngine();
        String name = processEngine.getName();
        String version = processEngine.VERSION;
        System.out.println("流程引擎名称【{"+name+"}】,版本为【{"+version+"}】");
        return processEngine;
    }

    private static ProcessDefinition deployment(ProcessEngine processEngine){
        RepositoryService repositoryService = processEngine.getRepositoryService();
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
        deploymentBuilder.addClasspathResource("firstProcess.xml");
        Deployment deployment = deploymentBuilder.deploy();
        String id = deployment.getId();

        //获取流程定义对像
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .deploymentId(id)
                .singleResult();
        String processDefinitionName = processDefinition.getName();
        String processDefinitionId = processDefinition.getId();
        System.out.println("流程定义名称为【{"+processDefinitionName+"}】,id为【{"+processDefinitionId+"}】");
        return processDefinition;
    }

    private static ProcessInstance getProcessInstance(ProcessEngine processEngine, ProcessDefinition processDefinition){
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId());
        System.out.println("启动流程【{"+processInstance.getProcessDefinitionKey()+"}】");
        return processInstance;
    }

    private static void processTask(ProcessEngine processEngine, ProcessInstance processInstance) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        while (processInstance != null && !processInstance.isEnded()){
            TaskService taskService = processEngine.getTaskService();
            TaskQuery taskQuery = taskService.createTaskQuery();
            List<Task> list = taskQuery.list();
            System.out.println("待处理任务数为【{"+list.size()+"}】");
            for (Task task:list){
                System.out.println("待处理任务为【{"+task.getName()+"}】");
                FormService formService = processEngine.getFormService();
                TaskFormData taskFormData = formService.getTaskFormData(task.getId());
                List<FormProperty> formProperties = taskFormData.getFormProperties();
                Map<String, Object> variables = getStringObjectMap(scanner, formProperties);
                //提交表单
                taskService.complete(task.getId(),variables);
                //提交后更新流程实例
                processInstance = processEngine.getRuntimeService()
                        .createProcessInstanceQuery()
                        .processInstanceId(processInstance.getId())
                        .singleResult();
            }
        }
    }

    private static Map<String, Object> getStringObjectMap(Scanner scanner, List<FormProperty> formProperties) throws ParseException {
        Map<String,Object> variables = new HashMap();
        String line = null;
        for (FormProperty property : formProperties){
            //判断用户输入类型
            if(StringFormType.class.isInstance(property.getType())){
                System.out.println("请输入【{"+property.getName()+"}】?");
                line = scanner.nextLine();
                variables.put(property.getId(),line);
            }else{
                System.out.println("请输入时间，格式为yyyy-MM-dd【{"+property.getName()+"}】?");
                line = scanner.nextLine();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date parse = sdf.parse(line);
                System.out.println("您输入的内容是【{"+line+"}】");
                variables.put(property.getId(),parse);
            }
        }
        return variables;
    }



}
