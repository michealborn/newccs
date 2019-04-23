package com.magfin.web.com.activiti.listener.execution;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;

/**
 * @Time 2019/4/23
 * @Author zlian
 * 自定义执行监听器
 */
public class StartExecutionListener implements ExecutionListener {
    @Override
    public void notify(DelegateExecution execution/*流程实例代理对象*/) throws Exception {
        System.out.println("自定义的监听器执行了"+execution.getId());
    }
}
