package com.magfin.web.com.activiti.spring;

import org.activiti.engine.*;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.ReadOnlyProcessDefinition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
import org.activiti.engine.impl.pvm.process.TransitionImpl;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Time 2019/4/25
 * @Author 佛祖保佑的最
 */
public class ChangeNode {


    static ProcessEngine processEngine =  ProcessEngines.getDefaultProcessEngine();
    static TaskService taskService = processEngine.getTaskService();
    static RepositoryService repositoryService = processEngine.getRepositoryService();
    static RuntimeService runtimeService = processEngine.getRuntimeService();

    /**
     * 流程转向操作
     *
     * @param taskId     当前任务ID
     * @param activityId 目标节点任务ID
     * @param variables  流程变量
     * @throws Exception
     */
    public static void turnTransition(String taskId, String activityId, Map<String, Object> variables) {
        // 当前节点
        ActivityImpl currActivity = findActivitiImpl(taskId, null);
        // 清空当前流向
        List<PvmTransition> oriPvmTransitionList = clearTransition(currActivity);

        // 创建新流向
        TransitionImpl newTransition = currActivity.createOutgoingTransition();
        // 目标节点
        ActivityImpl pointActivity = findActivitiImpl(taskId, activityId);
        // 设置新流向的目标节点
        newTransition.setDestination(pointActivity);
        // 执行转向任务
        taskService.complete(taskId, variables);
        // 删除目标节点新流入
        pointActivity.getIncomingTransitions().remove(newTransition);

        // 还原以前流向
        restoreTransition(currActivity, oriPvmTransitionList);
    }
    /**
     * 根据任务ID和节点ID获取活动节点 <br>
     *
     * @param taskId
     * @param activityId 活动节点ID <br>
     *                   如果为null或""，则默认查询当前活动节点 <br>
     *                   如果为"end"，则查询结束节点 <br>
     * @return
     * @throws Exception
     */
    public static ActivityImpl findActivitiImpl(String taskId, String activityId) {
        // 取得流程定义
        ProcessDefinitionEntity processDefinition = getProcDefEntityByTaskId(taskId);
        // 获取当前活动节点ID
        if (StringUtils.isEmpty(activityId)) {
            activityId = getTaskById(taskId).getTaskDefinitionKey();
        }
        // 根据流程定义，获取该流程实例的结束节点
        ProcessDefinitionImpl processDefinitionImpl = (ProcessDefinitionImpl) getReadOnlyProcessDefinitionByProcDefId(processDefinition.getId());
        if (activityId.toUpperCase().equals("END")) {
            for (ActivityImpl activityImpl : processDefinitionImpl.getActivities()) {
                List<PvmTransition> pvmTransitionList = activityImpl.getOutgoingTransitions();
                if (pvmTransitionList.isEmpty()) {
                    return activityImpl;
                }
            }
        }
        // 根据节点ID，获取对应的活动节点
        ActivityImpl activityImpl = processDefinitionImpl.findActivity(activityId);
        // 此处改动，无法获取到对应的活动节点，所以此处采用迂回的方式，如果前面可以取到则跳过，如果没有取到则自己取
        if (activityImpl == null) {
            List<ActivityImpl> activities = processDefinitionImpl.getActivities();
            for (ActivityImpl actImpl : activities) {
                if (actImpl.getId().equals(activityId)) {
                    activityImpl = actImpl;
                    break;
                }
            }
        }
        return activityImpl;
    }
    /**
     * 清空指定活动节点现有流向,且将清空的现有流向返回
     *
     * @param activityImpl 活动节点
     * @return 节点流向集合
     */
    public static List<PvmTransition> clearTransition(ActivityImpl activityImpl) {
        // 存储当前节点所有流向临时变量
        List<PvmTransition> oriPvmTransitionList = new ArrayList<>();
        // 获取当前节点所有流向，存储到临时变量，然后清空
        List<PvmTransition> pvmTransitionList = activityImpl.getOutgoingTransitions();
        for (PvmTransition pvmTransition : pvmTransitionList) {
            oriPvmTransitionList.add(pvmTransition);
        }
        pvmTransitionList.clear();

        return oriPvmTransitionList;
    }

    /**
     * 清空指定节点现有流向，且将新流向接入
     *
     * @param activityImpl         活动节点
     * @param oriPvmTransitionList 新流向节点集合
     */
    public static void restoreTransition(ActivityImpl activityImpl, List<PvmTransition> oriPvmTransitionList) {
        // 清空现有流向
        List<PvmTransition> pvmTransitionList = activityImpl.getOutgoingTransitions();
        pvmTransitionList.clear();
        // 还原以前流向
        for (PvmTransition pvmTransition : oriPvmTransitionList) {
            pvmTransitionList.add(pvmTransition);
        }
    }


    public static Task getTaskById(String taskId){
        TaskQuery taskQuery = taskService.createTaskQuery();
        taskQuery.taskId(taskId);
        Task task = taskQuery.singleResult();
        return task;
    }

    public static ProcessDefinitionEntity getProcDefEntityByTaskId(String taskId){
        Task taskById = getTaskById(taskId);
        String processDefinitionId = taskById.getProcessDefinitionId();
        ReadOnlyProcessDefinition deployedProcessDefinition = ((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(processDefinitionId);
        ProcessDefinitionEntity definition = (ProcessDefinitionEntity)deployedProcessDefinition;
        return definition;
    }

    public static ProcessDefinitionEntity getReadOnlyProcessDefinitionByProcDefId(String processDefinitionId){
        ReadOnlyProcessDefinition deployedProcessDefinition = ((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(processDefinitionId);
        ProcessDefinitionEntity definition = (ProcessDefinitionEntity)deployedProcessDefinition;
        return definition;

    }

}
