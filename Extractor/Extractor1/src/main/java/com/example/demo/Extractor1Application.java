package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Extractor1Application {
	public static Connection getMySqlConnection() throws Exception {
		   //String driver = "com.mysql.jdbc.Driver";
		    String url = "jdbc:mysql://localhost:3306";
		    String username = "root";
		    String password = "sowmyachoti@595";

		    
		    Connection conn = DriverManager.getConnection(url, username, password);
		    return conn;
		  }
	
//	public static Connection storeMySqlConnection() throws Exception {
//		   //String driver = "com.mysql.jdbc.Driver";
//		    String url = "jdbc:mysql://localhost:3306/visual_db";
//		    String username = "root";
//		    String password = "sowmyachoti@595";
//
//		    //Class.forName(driver);
//		    Connection conn = DriverManager.getConnection(url, username, password);
//		    return conn;
//		  }
	public static void main(String[] args) {
		SpringApplication.run(Extractor1Application.class, args);
	}

}
