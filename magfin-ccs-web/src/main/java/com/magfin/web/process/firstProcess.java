package com.magfin.web.process;

import org.activiti.engine.*;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
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
        ProcessEngineConfiguration processEngineConfigurationFromResourceDefault =
                ProcessEngineConfiguration.createProcessEngineConfigurationFromResourceDefault();
        ProcessEngineConfiguration processEngineConfiguration =
                processEngineConfigurationFromResourceDefault.setDatabaseSchemaUpdate(ProcessEngineConfigurationImpl.DB_SCHEMA_UPDATE_CREATE);
        ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();

    }






}
