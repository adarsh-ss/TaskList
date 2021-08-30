package com.tasklist.bean;

public class TaskBean 
{
	private String task_name;
	private String task_status;
	
	/*public TaskBean(String task_name, String task_status)
	{
		this.task_name = task_name;
		this.task_status = task_status;
	}*/
	
	public void setTaskName(String task_name) {
		this.task_name = task_name;
	}
	
	public void setTaskStatus(String task_status) {
		this.task_status = task_status;
	}
	
	public String getTaskName() {
		return task_name;
	}
	
	public String getTaskStatus() {
		return task_status;
	}
		
}
