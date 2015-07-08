package activiti;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

public class HelloWorld {
    
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    
    /**
     * 部署流程定义
     */
    @Test
    public void deploymentProcessDefinition(){
	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	RepositoryService repositoryService = processEngine.getRepositoryService();
	DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
	deploymentBuilder.name("helloworl入门程序");
	deploymentBuilder.addClasspathResource("diagrams/helloworld.bpmn");
	deploymentBuilder.addClasspathResource("diagrams/helloworld.png");
	Deployment deployment = deploymentBuilder.deploy();
	System.out.println(deployment.getId());
	System.out.println(deployment.getName());
	System.out.println(deployment.getDeploymentTime());
	System.out.println(deployment.getCategory()); 
    }
    
    /**
     * 启动引擎实例
     */
    @Test
    public void startProcessInstance(){
	ProcessInstance processInstance = processEngine.getRuntimeService()
		.startProcessInstanceById("helloworld:6:2604") ;
//		.startProcessInstanceByKey("helloworld"); 
	System.out.println(processInstance.getId());
	System.out.println(processInstance.getActivityId());
	System.out.println(processInstance.getBusinessKey());
	System.out.println(processInstance.getProcessDefinitionId());
	System.out.println(processInstance.getProcessInstanceId());
	System.out.println(processInstance.getProcessVariables()); 
    }
    
    /**
     * 获取当前人的任务信息
     */
    @Test
    public void findMyTask(){
	TaskService taskService = processEngine.getTaskService();
	List<Task> tasks = taskService.createTaskQuery().taskAssignee("李四").list(); 
	if(tasks != null){
	    for (Task task : tasks) {
		System.out.println(task.getId() + "  " + task.getName() + "  " + task.getProcessDefinitionId()); 
	    }
	}
    }
    
    /**
     * 完成我的任务
     */
    @Test
    public void doTask(){
	TaskService taskService = processEngine.getTaskService();
	taskService.complete("2302"); 
	System.out.println("完成任务402");
    }

    /**
     * 流程实例状态查询
     */
    @Test
    public void findProcessInstanceStatus(){
	ProcessInstance processInstance =  processEngine.getRuntimeService().createProcessInstanceQuery().processInstanceId("1201").singleResult();
	if(processInstance == null){
	    System.out.println("流程结束");
	}else{
	    System.out.println("流程进行中");
	}
    }

    /**
     * 历史流程实例状态查询
     */
    @Test
    public void findHistoryProcessInstanceStatus(){
	HistoricProcessInstance processInstance =  processEngine.getHistoryService().createHistoricProcessInstanceQuery().processInstanceId("1201").singleResult();
	System.out.println(processInstance);
    }
    
    /**
     * 历史任务查询2
     */
    @Test
    public void findHistoryProcessInstanceStatus2(){
	List<HistoricTaskInstance> processInstance =   processEngine.getHistoryService().createHistoricTaskInstanceQuery().taskAssignee("张三").list();
	System.out.println(processInstance);
    }
     

}
