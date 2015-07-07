package activiti.test;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.junit.Test;

public class TestActiviti01 {


    @Test
    public void createTable(){
	ProcessEngineConfiguration config = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration() ;
	//连接数据库配置
	config.setJdbcDriver("com.mysql.jdbc.Driver");
	config.setJdbcUrl("jdbc:mysql://localhost:3306/activiti?useUnicode=true&characterEncoding=utf8");
	config.setJdbcUsername("root");
	config.setJdbcPassword("root");


	config.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_CREATE_DROP); 

	ProcessEngine engine = config.buildProcessEngine() ;

	System.out.println(engine);

    }

    @Test
    public void testCreateTable2(){
	ProcessEngineConfiguration config = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
	ProcessEngine engine = config.buildProcessEngine();
	System.out.println(engine); 
	
	engine = ProcessEngines.getDefaultProcessEngine();
	System.out.println(engine); 
	
	RepositoryService repositoryService = engine.getRepositoryService();
	
	HistoryService historyService = engine.getHistoryService();
	
	TaskService taskService = engine.getTaskService();
	 
	RuntimeService runtimeService = engine.getRuntimeService() ;
	
    }
    
}
