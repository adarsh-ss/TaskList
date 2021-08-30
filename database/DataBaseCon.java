package com.tasklist.database;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.tasklist.bean.LoginBean;
import com.tasklist.bean.TaskMod;

import java.sql.Connection;

public class DataBaseCon {
	private final String url = "jdbc:postgresql://localhost/TaskList";
	private final String user = "postgres";
	private final String password = "Postman@006";
	private final String driverDB = "org.postgresql.Driver";
	
	protected void loadDriver(String driverDB)
	{
		try {
			Class.forName(driverDB);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection connect()
	{
		Connection connection = null;
		try
		{
			connection = DriverManager.getConnection(url, user, password);
			if(connection != null)
			{
				System.out.println("Connected Successfully");
			}
			else
			{
				System.out.println("Connection Failed");
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return connection;
	}
	
	public boolean validate(LoginBean loginBean)
	{
		loadDriver(driverDB);
		Connection con = connect();
		boolean ifPresent = false;
		
		String sql = "select * from userinfo where username = ? and passcode = ?";
		
		PreparedStatement ps;
		try
		{
			ps = con.prepareStatement(sql);
			ps.setString(1, loginBean.getUsername());
			ps.setString(2, loginBean.getPassword());
			
			System.out.println(ps);
			
			ResultSet rs = ps.executeQuery();
			ifPresent = rs.next();
			//System.out.println(rs.getInt(3));
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return ifPresent;
	}

	public int getUserID(LoginBean loginBean) 
	{
		loadDriver(driverDB);
		Connection con = connect();
		int user_id = 0;
		
		String sql = "select * from userinfo where username = ?";
		
		PreparedStatement ps;
		try
		{
			ps = con.prepareStatement(sql);
			ps.setString(1, loginBean.getUsername());
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			user_id = rs.getInt(1);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return user_id;
	}
	
	public ResultSet getTask(int user_id)
	{
		loadDriver(driverDB);
		Connection con = connect();
		ResultSet rs = null;
		String sql = "select * from tasksdb where user_id = ?";
		
		PreparedStatement ps;
		try
		{
			ps = con.prepareStatement(sql);
			ps.setInt(1, user_id);
			
			//System.out.println(ps);
			
			rs = ps.executeQuery();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	
	public boolean delete(TaskMod task)
	{
		boolean ifDone = false;
		loadDriver(driverDB);
		Connection con = connect();
		String sql = "delete from tasksdb where task_name = ?";
		PreparedStatement ps;
		try
		{
			ps = con.prepareStatement(sql);
			ps.setString(1, task.getTask());
			
			//System.out.println(ps);
			
			ps.execute();
			ifDone = true;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		System.out.println(ifDone);
		return ifDone;
	}
	
	public boolean update(TaskMod task)
	{
		boolean ifDone = false;
		loadDriver(driverDB);
		Connection con = connect();
		String sql = "update tasksdb set task_status = 'Completed' where task_name = ?";
		PreparedStatement ps;
		try
		{
			ps = con.prepareStatement(sql);
			ps.setString(1, task.getTask());
			
			//System.out.println(ps);
			
			ps.execute();
			ifDone = true;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		System.out.println(ifDone);
		return ifDone;
	}
	
	
}
