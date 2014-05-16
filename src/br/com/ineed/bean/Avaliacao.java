package br.com.ineed.bean;

public class Avaliacao {
	private Integer id;
	private String descricao;
	private Float peso;
	
	public Avaliacao(){
	}
	
	public Avaliacao(Integer avaliacao_id, String descricao, Float Peso){
		
		if(avaliacao_id == 0){
			this.setId(0);
			this.setDescricao("");
			this.setPeso((float) 0);
		}else{
			this.setId(avaliacao_id);
			this.setDescricao(descricao);
			this.setPeso(Peso);
			
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
	public Float getPeso() {
		return peso;
	}
	public void setPeso(Float peso) {
		this.peso = peso;
	}
	
	
}
