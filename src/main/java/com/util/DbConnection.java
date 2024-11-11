package com.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection 
{
	public static Connection getConnection()
	{
		Connection conn = null;
		String dbUrl = "jdbc:mysql://localhost:3306/advjava";
		String name = "root";
		String password = "12345678";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbUrl, name, password);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return conn;
	}
}
