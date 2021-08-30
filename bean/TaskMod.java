package com.tasklist.bean;

public class TaskMod {
	private String task;
	//private String status;
	private String operation;
	
	public TaskMod(String operation, String task)
	{
		this.operation = operation;
		this.task = task;
		//this.status = status;
	}
	
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	//public void setTask(String task) {
	//	this.task = task;
	//}
	
	//public void setStatus(String status) {
	//	this.status = status;
	//}
	
	public String getOperation() {
		return operation;
	}
	
	public String getTask() {
		return task;
	}
	
	//public String getStatus() {
	//	return status;
	//}

}
