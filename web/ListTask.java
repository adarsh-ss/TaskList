package com.tasklist.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;
import com.tasklist.bean.LoginBean;
import com.tasklist.bean.TaskBean;
import com.tasklist.database.DataBaseCon;

@WebServlet("/ListTask")
public class ListTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");  
		response.setCharacterEncoding("UTF-8");
		Gson json=new Gson();
		BufferedReader br=new BufferedReader(new InputStreamReader(request.getInputStream()));
		String param=br.readLine();
		System.out.println(param);
		
		HashMap<String,String> jsonfile=new HashMap<String,String>();
		jsonfile.put("check", "check");
		PrintWriter out=response.getWriter();
		
		LoginBean login = json.fromJson(param, LoginBean.class);
		DataBaseCon db = new DataBaseCon();
		
		int user_id = db.getUserID(login);
		System.out.println(user_id);
		
		ResultSet rs = db.getTask(user_id);
		ArrayList <TaskBean> ar = new ArrayList <TaskBean>();
		JSONArray jsonr=new JSONArray(); 
		String JsonString = "";
		
		//List<JSONObject> resultList = new ArrayList<JSONObject>();
		
		try
		{
			while(rs.next())
			{
				String task = rs.getString("task_name");
	            String status = rs.getString("task_status");
	                  
	            TaskBean cd = new TaskBean();
	            
	            cd.setTaskName(task);
	            cd.setTaskStatus(status);
	                      
	            ar.add(cd);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		for(int i=0; i < ar.size(); i++)
		{
			Gson g=new Gson();
			JsonString = g.toJson(ar.get(i));
			jsonr.put(JsonString);
		}
		
		//System.out.println(jsonr);
		/*for(int i = 0; i < resultList.size(); i++)
		{
			System.out.println(resultList.get(i));
		}*/
		
		//System.out.println(json.toJson(resultList));
		
		//jsonfile.put("check", "check");
		
		JSONObject jo=new JSONObject();
        jo.put("data",jsonr);
            
        String jsonFormattedString = jo.toString().replace("\\\"", "\"");
        String finalJSONString=jsonFormattedString.replace("\"{","{").replace("}\"","}");
        System.out.println(finalJSONString);
		
		out.println(finalJSONString);
		out.flush();
	}

}
