package br.com.ineed.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import br.com.ineed.bean.Turma;
import br.com.ineed.factory.ConnectionFactory;

public class TurmaDAO {
	
	Connection conn = null;
	
	public TurmaDAO(){
		try{
		conn = ConnectionFactory.getConnection();
		}
		catch(SQLException ex){ }
		
	}

	public void insert(Turma t){

		String sql = "INSERT INTO turma (id, descricao) VALUES (NULL, ?)";
		
		try{
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, t.getDescricao());
			stmt.executeUpdate();

		}
		catch(SQLException ex){ 
			ex.printStackTrace();
		}
	}
	
	
	public void update(Turma t){
		String sql = "UPDATE turma SET descricao = ? WHERE id = ?";
		try{
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, t.getDescricao());
			stmt.setInt(2, t.getId());
			stmt.executeUpdate();
		}
		catch(SQLException ex){ 
			ex.printStackTrace();
		}
	}
	
	public void delete(Integer id){

		String sql = "DELETE FROM turma WHERE id = ?";
		try{
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
		}
		catch(SQLException ex){ 
			ex.printStackTrace();
		}
	}
	
	public List<Turma> getAll(){

		String sql = "SELECT id, descricao  FROM turma ORDER BY descricao ASC";
		List<Turma> turmas = null;
		try{
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			turmas = new ArrayList<Turma>();
			
			while(rs.next()){
				Turma t = new Turma(rs.getInt("id"),rs.getString("descricao"));
				t.setId(rs.getInt("id"));
				t.setDescricao(rs.getString("descricao"));
				turmas.add(t);
			}
		}
		catch(SQLException ex){ 
			ex.printStackTrace();
		}
		
		return turmas;
	}
	

	
	public Turma get(Integer id){

		String sql = "SELECT id, descricao  FROM turma WHERE id = ?";
		Turma t = null;
		try{
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			
			if(rs.next()){
				t = new Turma(rs.getInt("id"),rs.getString("descricao"));
				t.setId(rs.getInt("id"));
				t.setDescricao(rs.getString("descricao"));
			}
			
		}
		catch(SQLException ex){ 
			ex.printStackTrace();
		}
		
		return t;
	}
	

	
}