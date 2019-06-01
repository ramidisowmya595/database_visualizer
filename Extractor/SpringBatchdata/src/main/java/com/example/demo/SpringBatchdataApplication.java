package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBatchdataApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchdataApplication.class, args);
	}
	public static Connection getMySqlConnection() throws SQLException {
		String url = "jdbc:mysql://localhost:3306";
		   String username = "root";
		   String password = "123456kvk";
		   Connection conn = DriverManager.getConnection(url, username, password);
		return conn;
		}

}
