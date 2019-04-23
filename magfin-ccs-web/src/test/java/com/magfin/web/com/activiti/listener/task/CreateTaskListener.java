package com.magfin.web.com.activiti.listener.task;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * @Time 2019/4/23
 * @Author zlian
 */
public class CreateTaskListener implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        System.out.println("CreateTaskListener"+delegateTask.getId());
    }
}
