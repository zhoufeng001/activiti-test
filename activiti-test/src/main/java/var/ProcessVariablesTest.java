package var;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

public class ProcessVariablesTest {


    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    /**
     * 部署流程定义
     */
    @Test
    public void deploymentProcessDefinition(){
	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	RepositoryService repositoryService = processEngine.getRepositoryService();
	DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
	deploymentBuilder.name("请假流程");
	deploymentBuilder.addClasspathResource("diagrams/leaveProcess.bpmn");
	deploymentBuilder.addClasspathResource("diagrams/leaveProcess.png");
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
		.startProcessInstanceById("leaveProcess:1:1604") ;
	System.out.println(processInstance.getId());
	System.out.println(processInstance.getActivityId());
	System.out.println(processInstance.getBusinessKey());
	System.out.println(processInstance.getProcessDefinitionId());
	System.out.println(processInstance.getProcessInstanceId());
	System.out.println(processInstance.getProcessVariables()); 
    }
    
    /**
     * 设置流程变量
     */
    @Test
    public void setProcessVar(){
	TaskService taskService = processEngine.getTaskService() ;
	taskService.setVariableLocal("2302", "bbb", "3241"); 
    }
    
    /**
     * 获取流程变量
     */
    @Test
    public void getProcessVar(){
	TaskService taskService = processEngine.getTaskService() ;
	Object value = taskService.getVariable("2704" , "user");
	Person p = (Person)value ;
	System.out.println(p.getId() + " , " + p.getName());
	
	
//	System.out.println(processEngine.getHistoryService().createHistoricVariableInstanceQuery().taskId("2008").variableName("bbb").singleResult());
    }
    
    /**
     * 模拟设置和获取流程变量
     */
    @Test
    public void setAndGetProcessVar(){
	RuntimeService runtimeService = processEngine.getRuntimeService();
	
	Person p = new Person(3, "is_zhofeng");
	
	//设置流程变量
	runtimeService.setVariable("2701", "user", p ); 
	
	System.out.println("设置流程变量成功..");
    }

    
}
