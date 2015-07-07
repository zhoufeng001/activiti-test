package activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.junit.Test;

public class HelloWorld {
    
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

}
