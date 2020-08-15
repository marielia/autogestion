package com.asecor.extranet.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Conexion {
	//public static final String URL = "jdbc:sqlserver://DESKTOP-N70HT9N\\SQLEXPRESS:1433;databaseName=TEST";
	public static final String URL = "jdbc:sqlserver://172.16.9.226:1433;databaseName=AsecorR3;selectMethod=Direct";
	public static final String USERNAME = "sa";
	public static final String PASSWORD = "Argentina+12";
	public static void conectar() {
		try
		   {
		        Connection connection;
		        DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());   
		      //  connection = DriverManager.getConnection(Conexion.URL,Conexion.USERNAME,
		       	//	Conexion.PASSWORD);
		       connection = DriverManager.getConnection(Conexion.URL);
		        String query ="select * from tabla";
		        Statement statement = connection.createStatement();
		        ResultSet resultSet = statement.executeQuery(query);
		        while(resultSet.next())
		        {
		            System.out.print("First Name: " + resultSet.getString("first_name"));
		            System.out.println("  Last Name: " + resultSet.getString("last_name"));                
		        }
		   }catch(Exception ex)
		   {
		        ex.printStackTrace();
		   }

	}

}
