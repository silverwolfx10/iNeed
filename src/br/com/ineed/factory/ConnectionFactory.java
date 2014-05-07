package br.com.ineed.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public static Connection getConnection() throws SQLException{
		
		try {
			   Class.forName("com.mysql.jdbc.Driver");
		} 
		catch (ClassNotFoundException ex){ }
		
		String server = "localhost";
		String database = "ineed";
				
		String url = "jdbc:mysql://" + server + "/" + database;
		String user = "root";
		String senha = "";

		Connection conn = DriverManager.getConnection(url, user, senha);

		return conn;
	}
	
}
