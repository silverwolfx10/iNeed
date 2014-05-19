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
		
		
		@SuppressWarnings("null")
		public List<Nota> getBoletim(ArrayList<Avaliacao> avaliacoes, ArrayList<Materia> materias){
				
			//prepara SQL para dar o BIND dentro do for			
			String sql = "SELECT AVG(nt.nota) nota from avaliacao av " +
					"LEFT JOIN nota nt ON nt.avaliacao_id = av.id " +
					"WHERE av.id = ? AND nt.materia_id = ? AND nt.semestre = ? AND usuario_id = 1;";
			
			
			List<Nota> notas = new ArrayList<Nota>();
			
			Integer contador = 0;
			Float nac = null;
			Float am = null;
			Float ps = null;
			Float mediaPrimeiro = null;
			
			try{
				PreparedStatement stmt = conn.prepareStatement(sql);
				//percorre todas as materias			
				for( Materia materia : materias )  
				{  
					
					stmt.setInt(2, materia.getId());
					
					//anda semestre				
					for(Integer i = 1; i <= 2; i++){
						stmt.setInt(3, i);
						
						contador = 0;
						//percorre todas os tipos de avaliacoes		
						for( Avaliacao avaliacao : avaliacoes)  
						{  
							
							
							stmt.setInt(1, avaliacao.getId());
							
								//executa consulta		
								ResultSet rs = stmt.executeQuery();
								Nota n = new Nota();
								if(rs.next() && !(i == 2 && contador == 2)){
									n.setNota(rs.getFloat("nota"));
									n.setSemestre(i);
									n.setAvaliacaoId(new Avaliacao(avaliacao.getId(),avaliacao.getDescricao(), avaliacao.getPeso()));
									n.setMateriaId(new Materia(materia.getId(),materia.getDescricao(), 0));
									notas.add(n);
									
								}else if(!(i == 2 && contador == 2)){
									//se nao teve retorno seta um objeto nota com a nota zerada	
									n.setNota(Float.valueOf("0"));
									n.setSemestre(i);
									n.setAvaliacaoId(new Avaliacao(avaliacao.getId(),avaliacao.getDescricao(), avaliacao.getPeso()));
									n.setMateriaId(new Materia(materia.getId(),materia.getDescricao(), 0));
									notas.add(n);
								}
								
								if(contador == 0){
									nac = (n.getNota() * n.getAvaliacaoId().getPeso());
								}else if(contador == 1){
									am = (n.getNota() * n.getAvaliacaoId().getPeso());
								}else if(contador == 2 && !(i == 2 && contador == 2)){
									ps = (n.getNota() * n.getAvaliacaoId().getPeso());
								}
								
								//se for primeiro semestre, calcula a media								
								if(i == 1 && contador == 2){
									Nota media = new Nota();
									media.setNota((nac + am + ps));
									notas.add(media);
									mediaPrimeiro = media.getNota();
								}else if(i == 2 && contador == 2){
									//calcula a nota que ele precisa
									Nota mediaSegundo = new Nota();
									mediaSegundo.setNota(((12 - (mediaPrimeiro + (am + nac))) * 2 ));
									notas.add(mediaSegundo);
								}
								
								contador++;
						} 
						
					}
					
					
				//	nota que ele precisa tirar						
//					Nota n = new Nota();
//					n.setNota(Float.valueOf("500"));
//					notas.add(n);
				}
				
			}
			catch(SQLException ex){ 
				ex.printStackTrace();
			}
		
				
			return notas;
		      
			
		}
		
	}
