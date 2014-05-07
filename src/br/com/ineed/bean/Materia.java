package br.com.ineed.bean;
import br.com.ineed.bean.Turma;

public class Materia {

	private Integer id;
	private String descricao;
	private Turma turma_id;
	
	
	
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
	public Turma getTurmaId() {
		return turma_id;
	}
	public void setTurmaId(Turma turma_id) {
		this.turma_id = turma_id;
	}
	
	
}
