package activiti;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.h2.util.IOUtils;
import org.junit.Test;

public class ProcessDifinitionTest {

    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    /**
     * 部署流程定义
     */
    @Test
    public void deploymentProcessDefinition(){
	Deployment deployment = processEngine.getRepositoryService()
		.createDeployment()
		.name("流程定义")
		.addClasspathResource("diagrams/helloworld.bpmn")
		.addClasspathResource("diagrams/helloworld.png")
		.deploy();

	System.out.println("部署ID：" + deployment.getId());
	System.out.println("部署名称：" + deployment.getName()); 

    }

    /**
     * 部署流程定义从zip文件
     */
    @Test
    public void deploymentProcessDefinitionZip(){
	Deployment deployment = processEngine.getRepositoryService()
		.createDeployment()
		.name("流程定义Zip222")
		.addZipInputStream(new ZipInputStream(this.getClass().getClassLoader().getResourceAsStream("diagrams/helloworld.zip")))
		.deploy();

	System.out.println("部署ID：" + deployment.getId()); 
	System.out.println("部署名称：" + deployment.getName()); 

    }

    /**
     * 查找流程定义
     */
    @Test
    public void findProcessDefinition(){
	ProcessDefinition processDefinition = processEngine.getRepositoryService().createProcessDefinitionQuery().deploymentId("901").singleResult();  
	System.out.println("流程定义ID：" + processDefinition.getId()); 
	System.out.println("流程定义资源文件：：" + processDefinition.getDiagramResourceName()); 
    }


    /**
     * 删除流程定义
     */
    @Test
    public void deleteProcessDefinition(){
	//只能删除没有启动的流程，如果已经启动就会抛出异常
	//	processEngine.getRepositoryService().deleteDeployment("1");



	//使用下面的删除方式，能强制删除流程， 并将关联的已启动的流程全部删除
	processEngine.getRepositoryService().deleteDeployment("1" , true);
	System.out.println("删除1成功");
    }


    /**
     * 查看流程图
     */
    @Test
    public void findProcessDiagram(){
	//获取图片资源名称
	List<String> resources = processEngine.getRepositoryService().getDeploymentResourceNames("601");
	System.out.println(resources); 
	//获取图片输入流
	InputStream is = processEngine.getRepositoryService().getResourceAsStream("601", resources.get(1)) ;
	try {
	    IOUtils.copyAndClose(is, new FileOutputStream("D:/aaa.png"));
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }


    /**
     * 查询最新版本的流程定义
     */
    @Test
    public void findNewProcessDefinition(){
	List<ProcessDefinition> processDefinitions = processEngine.getRepositoryService()
		.createProcessDefinitionQuery().processDefinitionKey("helloworld").orderByProcessDefinitionVersion().desc().list();
	for (ProcessDefinition processDefinition : processDefinitions) {
	    System.out.println(processDefinition.getKey() + "," + processDefinition);
	}
    }
}
