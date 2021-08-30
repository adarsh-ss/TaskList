package com.tasklist.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.tasklist.bean.LoginBean;
import com.tasklist.database.DataBaseCon;


@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");  
		response.setCharacterEncoding("UTF-8");
		Gson json=new Gson();
		BufferedReader br=new BufferedReader(new InputStreamReader(request.getInputStream()));
		String param=br.readLine();
		System.out.println(param);
		
		HashMap<String,String> jsonfile=new HashMap<String,String>();
		PrintWriter out=response.getWriter();
		
		LoginBean login = json.fromJson(param, LoginBean.class);
		DataBaseCon db = new DataBaseCon();
		
		if(db.validate(login))
		{
			System.out.println("Authentication successful");
			jsonfile.put("status","Authentication successful");
		}
		else
		{
			System.out.println("Authentication failed");
			jsonfile.put("status","Authentication failed");
		}
		
		out.println(json.toJson(jsonfile));
		out.flush();
	}

}
