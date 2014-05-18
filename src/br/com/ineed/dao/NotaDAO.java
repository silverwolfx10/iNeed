package br.com.ineed.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




import br.com.ineed.bean.Avaliacao;
import br.com.ineed.bean.Materia;
import br.com.ineed.bean.Nota;
import br.com.ineed.bean.Usuario;
import br.com.ineed.factory.ConnectionFactory;
public class NotaDAO {

		Connection conn = null;
		
		public NotaDAO(){
			
			try{
				conn = ConnectionFactory.getConnection();
			}
			catch(SQLException ex){ }
		}
		
		public void insert(Nota n){

			String sql = "INSERT INTO nota (id, avaliacao_id, materia_id, nota, semestre, usuario_id) VALUES (NULL,?,?,?,?,?)";
			
			try{
				PreparedStatement stmt = conn.prepareStatement(sql);
				
				stmt.setInt(1, n.getAvaliacaoId().getId());
				stmt.setInt(2, n.getMateriaId().getId());
				stmt.setFloat(3, n.getNota());
				stmt.setFloat(4, n.getSemestre());
				stmt.setInt(5, n.getUsuarioId().getId());
				stmt.executeUpdate();

			}
			catch(SQLException ex){ 
				ex.printStackTrace();
			}
		}
		
		public void update(Nota n){
			String sql = "UPDATE nota SET  avaliacao_id = ?, materia_id = ?, nota = ?, semestre = ?, usuario_id = ? WHERE id = ?";
			try{
				PreparedStatement stmt = conn.prepareStatement(sql);
				
				stmt.setInt(1, n.getAvaliacaoId().getId());
				stmt.setInt(2, n.getMateriaId().getId());
				stmt.setFloat(3, n.getNota());
				stmt.setFloat(4, n.getSemestre());
				stmt.setInt(5, n.getUsuarioId().getId());
				stmt.setInt(6, n.getId());
				stmt.executeUpdate();
			}
			catch(SQLException ex){ 
				ex.printStackTrace();
			}
		}
		
		public void delete(Integer id){

			String sql = "DELETE FROM nota WHERE id = ?";
			try{
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1, id);
				stmt.executeUpdate();
			}
			catch(SQLException ex){ 
				ex.printStackTrace();
			}
		}
		
		public List<Nota> getAll(){

			String sql = "SELECT n.id, n.nota, n.semestre, av.id as avid, av.descricao as avdescricao,av.peso as avpeso, m.id as materia_id, m.descricao as materia_desc, "
					+ "u.id as usuario_id, u.rm as usuario_rm"
					+ " FROM nota n"
					+ " LEFT JOIN avaliacao av ON av.id = n.avaliacao_id "
					+ " LEFT JOIN materia m on m.id = n.materia_id"
					+ " LEFT JOIN usuario u on u.id = n.usuario_id"					
					+ " ORDER BY n.id";
			List<Nota> notas = null;
			try{
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();
				notas = new ArrayList<Nota>();
				
				while(rs.next()){
					Nota n = new Nota();
					n.setId(rs.getInt("id"));
					n.setNota(rs.getFloat("nota"));
					n.setSemestre(rs.getInt("semestre"));
					n.setAvaliacaoId(new Avaliacao(rs.getInt("avid"),rs.getString("avdescricao"), rs.getFloat("avpeso")));
					n.setMateriaId(new Materia(rs.getInt("materia_id"),rs.getString("materia_desc"), 0));
					n.setUsuarioId(new Usuario(rs.getInt("usuario_id"),rs.getString("usuario_rm")));					
					notas.add(n);
				}
			}
			catch(SQLException ex){ 
				ex.printStackTrace();
			}
			
			return notas;
		}
		
		public List<Nota> getAllByMateriaIdAndUserId(Integer materia_id, Integer usuario_id){

			String sql = "SELECT n.id, n.nota, n.semestre, av.id as avid, av.descricao as avdescricao,av.peso as avpeso, m.id as materia_id, m.descricao as materia_desc, "
					+ "u.id as usuario_id, u.rm as usuario_rm"
					+ " FROM nota n"
					+ " LEFT JOIN avaliacao av ON av.id = n.avaliacao_id "
					+ " LEFT JOIN materia m on m.id = n.materia_id"
					+ " LEFT JOIN usuario u on u.id = n.usuario_id"	
					+ " WHERE materia_id = ? AND usuario_id = ?"
					+ " ORDER BY n.id";
			List<Nota> notas = null;
			try{
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1, materia_id);
				stmt.setInt(2, usuario_id);
				ResultSet rs = stmt.executeQuery();
				notas = new ArrayList<Nota>();
				
				while(rs.next()){
					Nota n = new Nota();
					n.setId(rs.getInt("id"));
					n.setNota(rs.getFloat("nota"));
					n.setSemestre(rs.getInt("semestre"));
					n.setAvaliacaoId(new Avaliacao(rs.getInt("avid"),rs.getString("avdescricao"), rs.getFloat("avpeso")));
					n.setMateriaId(new Materia(rs.getInt("materia_id"),rs.getString("materia_desc"), 0));
					n.setUsuarioId(new Usuario(rs.getInt("usuario_id"),rs.getString("usuario_rm")));					
					notas.add(n);
				}
			}
			catch(SQLException ex){ 
				ex.printStackTrace();
			}
			
			return notas;
		}
		
		public List<Nota> getAllByMateriaId(Integer materia_id){

			String sql = "SELECT n.id, n.nota, n.semestre, av.id as avid, av.descricao as avdescricao,av.peso as avpeso, m.id as materia_id, m.descricao as materia_desc, "
					+ "u.id as usuario_id, u.rm as usuario_rm"
					+ " FROM nota n"
					+ " LEFT JOIN avaliacao av ON av.id = n.avaliacao_id "
					+ " LEFT JOIN materia m on m.id = n.materia_id"
					+ " LEFT JOIN usuario u on u.id = n.usuario_id"	
					+ " WHERE materia_id = ?"
					+ " ORDER BY n.id";
			List<Nota> notas = null;
			try{
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1, materia_id);
				ResultSet rs = stmt.executeQuery();
				notas = new ArrayList<Nota>();
				
				while(rs.next()){
					Nota n = new Nota();
					n.setId(rs.getInt("id"));
					n.setNota(rs.getFloat("nota"));
					n.setSemestre(rs.getInt("semestre"));
					n.setAvaliacaoId(new Avaliacao(rs.getInt("avid"),rs.getString("avdescricao"), rs.getFloat("avpeso")));
					n.setMateriaId(new Materia(rs.getInt("materia_id"),rs.getString("materia_desc"), 0));
					n.setUsuarioId(new Usuario(rs.getInt("usuario_id"),rs.getString("usuario_rm")));					
					notas.add(n);
				}
			}
			catch(SQLException ex){ 
				ex.printStackTrace();
			}
			
			return notas;
		}
		
		

		
		public Nota get(Integer id){

			String sql = "SELECT n.id, n.nota, n.semestre, av.id as avid, av.descricao as avdescricao, av.peso as avpeso, m.id as materia_id, m.descricao as materia_desc, "
					+ "u.id as usuario_id, u.rm as usuario_rm"
					+ " FROM nota n"
					+ " LEFT JOIN avaliacao av ON av.id = n.avaliacao_id "
					+ " LEFT JOIN materia m on m.id = n.materia_id"
					+ " LEFT JOIN usuario u on u.id = n.usuario_id"					
					+ " WHERE n.id = ?";
			Nota n = null;
			try{
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1, id);
				ResultSet rs = stmt.executeQuery();
				
				
				if(rs.next()){
					n = new Nota();
					n.setId(rs.getInt("id"));
					n.setNota(rs.getFloat("nota"));
					n.setSemestre(rs.getInt("semestre"));
					n.setAvaliacaoId(new Avaliacao(rs.getInt("avid"),rs.getString("avdescricao"), rs.getFloat("avpeso")));
					n.setMateriaId(new Materia(rs.getInt("materia_id"),rs.getString("materia_desc"), 0));
					n.setUsuarioId(new Usuario(rs.getInt("usuario_id"),rs.getString("usuario_rm")));	
				}
				
			}
			catch(SQLException ex){ 
				ex.printStackTrace();
			}
			
			return n;
		}
		

		
	}
