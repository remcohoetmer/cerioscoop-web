package nl.cerios.cerioscoop.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import com.mysql.jdbc.Connection;

public class DatabaseConnection {
	private static Connection con;
	
	public static Connection connectionDatabase() {
		try {
			Scanner pwInput = new Scanner(System.in);

			Class.forName("com.mysql.jdbc.Driver");
			
			final String url="jdbc:mysql://localhost/cerioscoop_db";
			final String user= "root";
			
			System.out.println("Enter your password:");
			final String password= pwInput.nextLine();
			
			int i= 0;
			while(i < 100){System.out.println(" "); i++;}
		 
			con=(Connection) DriverManager.getConnection(url, user, password);
			 
			System.out.println("Connected succesfully");
		}catch (SQLException | ClassNotFoundException e) {
			System.out.println("Something went wrong in the connection string" + e);
		}
		return con; 
	}
}
