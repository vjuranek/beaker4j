package org.fedorahosted.beaker4j.xmlrpc.client;

/**
 * Lists Beaker XML-RPC API
 * See {@link http://beaker-project.org/docs/server-api/xmlrpc.html} for details
 * 
 * @author vjuranek
 *
 */

public enum XmlRpcApi {
	
	AUTH_LOGIN_PASSWORD("auth.login_password"),
	AUTH_LOGOUT("auth.logout"),
	AUTH_WHO_AM_I("auth.who_am_i"),
	JOBS_STOP("jobs.stop"),
	JOBS_TO_XML("jobs.to_xml"),
	JOBS_UPLOAD("jobs.upload"),
	RECIPES_TASKS_EXTEND("recipes.tasks.extend"),
	RECIPES_TASKS_WATCHDOG("recipes.tasks.watchdog"),
	TASKACTIONS_TASK_INFO("taskactions.task_info"),
	TASKACTIONS_TO_XML("taskactions.to_xml");
	
	
	private String rpc;
	
	private XmlRpcApi(String rpc){
		this.rpc = rpc;
	}
	
	public String getRpc(){
		return rpc;
	}

}
