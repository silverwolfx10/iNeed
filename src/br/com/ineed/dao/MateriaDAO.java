package br.com.ineed.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import br.com.ineed.bean.Materia;
import br.com.ineed.bean.Turma;
import br.com.ineed.factory.ConnectionFactory;

public class MateriaDAO {
	
	Connection conn = null;
	
	public MateriaDAO(){
		
		try{
			conn = ConnectionFactory.getConnection();
		}
		catch(SQLException ex){ }
	}
	
	public void insert(Materia m){

		String sql = "INSERT INTO materia (id, descricao, turma_id) VALUES (NULL,?,?)";
		
		try{
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, m.getDescricao());
			stmt.setInt(2, m.getTurmaId().getId());
			stmt.executeUpdate();

		}
		catch(SQLException ex){ 
			ex.printStackTrace();
		}
	}
	
	public void update(Materia m){
		String sql = "UPDATE materia SET descricao = ?, turma_id = ? WHERE id = ?";
		try{
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, m.getDescricao());
			stmt.setInt(2, m.getTurmaId().getId());
			stmt.setInt(3, m.getId());
			stmt.executeUpdate();
		}
		catch(SQLException ex){ 
			ex.printStackTrace();
		}
	}
	
	public void delete(Integer id){

		String sql = "DELETE FROM materia WHERE id = ?";
		try{
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
		}
		catch(SQLException ex){ 
			ex.printStackTrace();
		}
	}
	
	public List<Materia> getAll(){

		String sql = "SELECT m.id, m.descricao, t.id as tid, t.descricao as tdescricao FROM materia m LEFT JOIN turma t ON t.id = m.turma_id ORDER BY m.descricao";
		List<Materia> materias = null;
		try{
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			materias = new ArrayList<Materia>();
			
			while(rs.next()){
				Materia m = new Materia(rs.getInt("id"),rs.getString("descricao"));
				m.setTurmaId(new Turma(rs.getInt("tid"),rs.getString("tdescricao")));
				materias.add(m);
			}
		}
		catch(SQLException ex){ 
			ex.printStackTrace();
		}
		
		return materias;
	}
	

	
	public Materia get(Integer id){

		String sql = "SELECT m.id, m.descricao, t.id as tid, t.descricao as tdescricao FROM materia m LEFT JOIN turma t ON t.id = m.turma_id  WHERE m.id = ?";
		Materia m = null;
		try{
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			
			if(rs.next()){
				m = new Materia(rs.getInt("id"),rs.getString("descricao"));
				m.setTurmaId(new Turma(rs.getInt("tid"),rs.getString("tdescricao")));
			}
			
		}
		catch(SQLException ex){ 
			ex.printStackTrace();
		}
		
		return m;
	}
	

	
}
