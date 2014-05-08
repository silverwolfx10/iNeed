package br.com.ineed.dao;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.ineed.factory.ConnectionFactory;

public class TurmaDAO {
	
	Connection conn = null;
	
	public TurmaDAO(){
		try{
		conn = ConnectionFactory.getConnection();
		}
		catch(SQLException ex){ }
	}

	
	
	
	
	
	
}
