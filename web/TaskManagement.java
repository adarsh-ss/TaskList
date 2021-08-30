package com.tasklist.web;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.google.gson.Gson;
import com.tasklist.bean.TaskBean;
import com.tasklist.bean.TaskMod;
import com.tasklist.database.DataBaseCon;



@WebServlet("/TaskManagement")
public class TaskManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("application/json");  
		response.setCharacterEncoding("UTF-8");
		Gson json=new Gson();
		BufferedReader br=new BufferedReader(new InputStreamReader(request.getInputStream()));
		String param=br.readLine();
		System.out.println(param);
		
		HashMap<String,String> jsonfile=new HashMap<String,String>();
		PrintWriter out=response.getWriter();

		TaskMod task = json.fromJson(param, TaskMod.class);
		DataBaseCon db = new DataBaseCon();
		
		if(task.getOperation().equals("update"))
		{
			if(db.update(task))
			{
				jsonfile.put("status", "Update Successful");
			}
			else
			{
				jsonfile.put("status", "Update Failed");
			}
		}
		else if(task.getOperation().equals("delete"))
		{
			if(db.delete(task))
			{
				jsonfile.put("status", "Task Deleted");
			}
			else
			{
				jsonfile.put("status", "Operation Failed");
			}
		}
		
		out.println(json.toJson(jsonfile));
		out.flush();
	}

}
