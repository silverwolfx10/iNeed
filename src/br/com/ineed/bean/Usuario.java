package br.com.ineed.bean;
import br.com.ineed.bean.Turma;

public class Usuario {

	private Integer id;
	private String 	nome;
	private String 	rm;
	private String 	senha;
	private Turma	turma_id;
	private Integer is_admin;
	
public Usuario(Integer usuario_id, String rm){
		
		if(usuario_id == 0){
			this.setId(0);
			this.setRm("");
		}else{
			this.setId(usuario_id);
			this.setRm(rm);
		}
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getRm() {
		return rm;
	}
	public void setRm(String rm) {
		this.rm = rm;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Turma getTurmaId() {
		return turma_id;
	}
	public void setTurmaId(Turma turma_id) {
		this.turma_id = turma_id;
	}
	public Integer getIsAdmin() {
		return is_admin;
	}
	public void setIsAdmin(Integer is_admin) {
		this.is_admin = is_admin;
	}
	
	
	
}
