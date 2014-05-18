package br.com.ineed.bean;
import br.com.ineed.bean.*;

public class Nota {
	
	private Integer id;
	private Materia materia_id;
	private Avaliacao avaliacao_id;
	private Float nota;
	private Integer semestre;
	private Usuario usuario_id;
	
	public Nota(){
		
	}
	public Nota(Integer id, Materia materia, Avaliacao avaliacao, Float nota, Integer semestre, Usuario usuario){
		this.id = id;
		this.materia_id = materia;
		this.avaliacao_id = avaliacao;
		this.nota = nota;
		this.semestre = semestre;
		this.usuario_id = usuario;
	}
	
	public Nota(Materia materia, Avaliacao avaliacao, Float nota, Integer semestre, Usuario usuario){
		this.id = id;
		this.materia_id = materia;
		this.avaliacao_id = avaliacao;
		this.nota = nota;
		this.semestre = semestre;
		this.usuario_id = usuario;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Materia getMateriaId() {
		return materia_id;
	}
	public void setMateriaId(Materia materia_id) {
		this.materia_id = materia_id;
	}
	public Avaliacao getAvaliacaoId() {
		return avaliacao_id;
	}
	public void setAvaliacaoId(Avaliacao avaliacao_id) {
		this.avaliacao_id = avaliacao_id;
	}
	public Float getNota() {
		return nota;
	}
	public void setNota(Float nota) {
		this.nota = nota;
	}
	public Integer getSemestre() {
		return semestre;
	}
	public void setSemestre(Integer semestre) {
		this.semestre = semestre;
	}
	public Usuario getUsuarioId() {
		return usuario_id;
	}
	public void setUsuarioId(Usuario usuario_id) {
		this.usuario_id = usuario_id;
	}
	
	
	

}
