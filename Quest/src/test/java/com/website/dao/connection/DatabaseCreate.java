package com.website.dao.connection;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.io.FileUtils;

import com.website.dto.CreateAccountDTO;
import com.website.dto.CreateAccountTokenDTO;
import com.website.dto.MyAccountDTO;

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
		
		//read create and inject files
		public static String readToString(String fname) throws Exception {
			File file = new File(fname);
			String string = FileUtils.readFileToString(file, "utf-8");
			return string;
		}
		
		//get USERS from created database
		public CreateAccountDTO getUsers() throws SQLException {
			
			PreparedStatement pst = con.prepareStatement("SELECT * FROM Users");
	        pst.clearParameters();
	        ResultSet rs = pst.executeQuery();
	        
	        rs.next();
	        CreateAccountDTO info = new CreateAccountDTO();
	        info.setId(rs.getInt(1));
	        info.setEmail(rs.getString(2));
	        info.setPassword(rs.getString(3));
	        info.setRoles(rs.getString(4));
	        info.setEnabled(rs.getBoolean(5));

	        System.out.println(info);        
			return info;
		}
		
		//get USERS-DETAILS from created database
		public MyAccountDTO getUsersDetails() throws SQLException {
			
			PreparedStatement pst = con.prepareStatement("SELECT * FROM UsersDetails");
	        pst.clearParameters();
	        ResultSet rs = pst.executeQuery();
	        
	        rs.next();
	        MyAccountDTO info = new MyAccountDTO();
	        info.setId(rs.getInt(1));
	        info.setEmail(rs.getString(2));
	        info.setFirstname(rs.getString(3));
	        info.setSurname(rs.getString(4));
	        info.setAddress1(rs.getString(5));
	        info.setAddress2(rs.getString(6));
	        info.setTown(rs.getString(7));
	        info.setPostcode(rs.getString(8));
	        info.setPhonenumber(rs.getString(9));
	        info.setDob(rs.getString(10));

	        System.out.println(info);        
			return info;
		}
		
		//get USERS-DETAILS from created database
		public CreateAccountTokenDTO getVerificationTokens() throws SQLException {
			
			PreparedStatement pst = con.prepareStatement("SELECT * FROM VerificationTokens");
	        pst.clearParameters();
	        ResultSet rs = pst.executeQuery();
	        
	        rs.next();
	        CreateAccountTokenDTO info = new CreateAccountTokenDTO();
	        info.setId(rs.getInt(1));
	        info.setEmail(rs.getString(2));
	        info.setToken(rs.getString(3));
	        info.setExpiryDate(rs.getDate(4));

	        System.out.println(info);        
			return info;
		}
		
		//Close Connection
		public void close_connection() throws SQLException {
			con.close();
		}
		
	}

