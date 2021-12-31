package com.website.dao.connection;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.io.FileUtils;

import com.website.dto.CreateAccountDTO;

//import com.website.dto.CreateAccount;

public class DatabaseCreate {

		static Connection con;

		static String connectionString = "jdbc:hsqldb:mem:db";
		
		public String populateDatabase;

		public void db(String create, String inject) throws Exception {

			String createDatabase = readToString(create);
			
			if(inject != "") {
			populateDatabase = readToString(inject);
			}
			
			System.out.println("Attempting to create DB ... ");

			// Loads DB driver
			// explained here:
			// http://stackoverflow.com/questions/5992126/loading-jdbc-driver
			try {
				Class.forName("org.hsqldb.jdbc.JDBCDriver");
			} 
			catch (ClassNotFoundException e) 
			{			
				throw e;
			}

			try {
				// Create DB
				// "SA" default user with hyperSQL
				// "" empty password
				con = DriverManager.getConnection(connectionString, "SA", "");

				// create table
				con.createStatement().executeUpdate(createDatabase);
				System.out.println("create");
				// inject data into table
				if(inject != "") {
				con.createStatement().executeUpdate(populateDatabase);
				System.out.println("insert");
				}			        
				
			} 
			catch (SQLException e) 
			{
				throw e;
			}
		}

		public static String readToString(String fname) throws Exception {
			File file = new File(fname);
			String string = FileUtils.readFileToString(file, "utf-8");
			return string;
		}
		
		public CreateAccountDTO getUserDetails() throws SQLException {
			
			PreparedStatement pst = con.prepareStatement("SELECT * FROM Users");
	        pst.clearParameters();
	        ResultSet rs = pst.executeQuery();
	        
	        rs.next();
	        CreateAccountDTO info = new CreateAccountDTO();
	        info.setId(rs.getInt(1));
	        info.setEmail(rs.getString(2));
	        info.setPassword(rs.getString(3));
	        info.setRole(rs.getString(4));
	        info.setEnabled(rs.getInt(5));

	        System.out.println(info);        
			return info;
		}
		
		//Close Connection
		public void close_connection() throws SQLException {
			con.close();
		}
		
	}

