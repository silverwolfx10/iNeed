package br.com.ineed.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import br.com.ineed.factory.ConnectionFactory;
import br.com.ineed.bean.Avaliacao;



public class AvaliacaoDAO {
	
Connection conn = null;
	
	public AvaliacaoDAO(){
		try{
		conn = ConnectionFactory.getConnection();
		}
		catch(SQLException ex){ }
		
	}

	public void insert(Avaliacao av){

		String sql = "INSERT INTO avaliacao (descricao, id, peso) VALUES (?,?,?)";
		
		try{
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, av.getDescricao());
			stmt.setInt(2, av.getId());
			stmt.setFloat(3, av.getPeso());
			stmt.executeUpdate();

		}
		catch(SQLException ex){ 
			ex.printStackTrace();
		}
	}
	
	
	public void update(Avaliacao av){
		String sql = "UPDATE avaliacao SET descricao = ?, id = ?, peso = ? WHERE id = ?";
		try{
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, av.getDescricao());
			stmt.setInt(2, av.getId());
			stmt.setFloat(3, av.getPeso());
			stmt.executeUpdate();
		}
		catch(SQLException ex){ 
			ex.printStackTrace();
		}
	}
	
	public void delete(Integer id){

		String sql = "DELETE FROM avaliacao WHERE id = ?";
		try{
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
		}
		catch(SQLException ex){ 
			ex.printStackTrace();
		}
	}
	
	public List<Avaliacao> getAll(){

		String sql = "SELECT id, descricao, peso FROM avaliacao";
		List<Avaliacao> avaliacoes = null;
		try{
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			avaliacoes = new ArrayList<Avaliacao>();
			
			while(rs.next()){
				Avaliacao av = new Avaliacao(rs.getInt("id"),rs.getString("descricao"));
				av.setPeso(rs.getFloat("peso"));
				avaliacoes.add(av);
			}
		}
		catch(SQLException ex){ 
			ex.printStackTrace();
		}
		
		return avaliacoes;
	}
	

	
	public Avaliacao get(Integer id){

		String sql = "SELECT id, descricao  peso FROM avaliacao";
		Avaliacao av = null;
		try{
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			
			if(rs.next()){
				av = new Avaliacao(rs.getInt("id"),rs.getString("descricao"));
				av.setPeso(rs.getFloat("peso"));
			}
			
		}
		catch(SQLException ex){ 
			ex.printStackTrace();
		}
		
		return av;
	}
	

	
}


