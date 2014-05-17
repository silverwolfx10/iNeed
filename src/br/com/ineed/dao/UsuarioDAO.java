package br.com.ineed.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;





import br.com.ineed.bean.Turma;
import br.com.ineed.bean.Usuario;
import br.com.ineed.factory.ConnectionFactory;
public class UsuarioDAO {

		Connection conn = null;
		
		public UsuarioDAO(){
			
			try{
				conn = ConnectionFactory.getConnection();
			}
			catch(SQLException ex){ }
		}
		
		public void insert(Usuario usua){

			String sql = "INSERT INTO usuario (id, nome, rm, senha, turma_id, is_admin) VALUES (NULL,?,?,?,?,?)";
			
			try{
				PreparedStatement stmt = conn.prepareStatement(sql);
				
				stmt.setString(1, usua.getNome());
				stmt.setString(2, usua.getRm());
				stmt.setString(3, usua.getSenha());
				stmt.setInt(4, usua.getTurmaId().getId());
				stmt.setInt(5, usua.getIsAdmin());
				stmt.executeUpdate();

			}
			catch(SQLException ex){ 
				ex.printStackTrace();
			}
		}
		
		public void update(Usuario usua){
			String sql = "UPDATE usuario SET  nome = ?, rm = ?, senha = ?, turma_id = ?, is_admin = ? WHERE id = ?";
			try{
				PreparedStatement stmt = conn.prepareStatement(sql);
				
				stmt.setString(1, usua.getNome());
				stmt.setString(2, usua.getRm());
				stmt.setString(3, usua.getSenha());
				stmt.setInt(4, usua.getTurmaId().getId());
				stmt.setInt(5, usua.getIsAdmin());
				stmt.setInt(6, usua.getId());
				stmt.executeUpdate();
			}
			catch(SQLException ex){ 
				ex.printStackTrace();
			}
		}
		
		public void delete(Integer id){

			String sql = "DELETE FROM usuario WHERE id = ?";
			try{
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1, id);
				stmt.executeUpdate();
			}
			catch(SQLException ex){ 
				ex.printStackTrace();
			}
		}
		
		public List<Usuario> getAll(){

			String sql = "SELECT usua.id, usua.nome, usua.rm, usua.senha,  "
					+ "t.id as tid,  t.descricao as tdescricao, usua.is_admin "
					+ "FROM usuario usua"
					+ "LEFT JOIN turma t ON t.id = usua.turma_id ORDER BY usua.id";
			List<Usuario> usuarios = null;
			try{
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();
				usuarios = new ArrayList<Usuario>();
				
				while(rs.next()){
					Usuario usua = new Usuario();
					usua.setId(rs.getInt("id"));
					usua.setNome(rs.getString("nome"));
					usua.setRm(rs.getString("rm"));
					usua.setSenha(rs.getString("senha"));
					usua.setTurmaId(new Turma(rs.getInt("tid"),rs.getString("tdescricao")));
					usua.setIsAdmin(rs.getInt("is_admin"));		
					usuarios.add(usua);
				}
			}
			catch(SQLException ex){ 
				ex.printStackTrace();
			}
			
			return usuarios;
		}
		

		
		public Usuario get(Integer id){

			String sql = "SELECT usua.id, usua.nome, usua.rm, usua.senha,  "
					+ "t.id as tid,  t.descricao as tdescricao, usua.is_admin "
					+ "FROM usuario usua"
					+ "LEFT JOIN turma t ON t.id = usua.turma_id ORDER BY usua.id";
			Usuario usua = null;
			try{
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1, id);
				ResultSet rs = stmt.executeQuery();
				
				
				if(rs.next()){
					usua = new Usuario();
					usua.setId(rs.getInt("id"));
					usua.setNome(rs.getString("nome"));
					usua.setRm(rs.getString("rm"));
					usua.setSenha(rs.getString("senha"));
					usua.setTurmaId(new Turma(rs.getInt("tid"),rs.getString("tdescricao")));
					usua.setIsAdmin(rs.getInt("is_admin"));	
				}
				
			}
			catch(SQLException ex){ 
				ex.printStackTrace();
			}
			
			return usua;
		}
		

		
	}
