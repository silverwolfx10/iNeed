package br.com.ineed.bean;

public class Turma {
	
	private Integer id;
	private String descricao;
	
	public Turma(Integer turma_id, String descricao){
		
		if(turma_id == 0){
			this.setId(0);
			this.setDescricao("");
		}else{
			this.setId(turma_id);
			this.setDescricao(descricao);
		}
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
