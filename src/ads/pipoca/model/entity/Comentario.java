package ads.pipoca.model.entity;

import java.util.Date;

public class Comentario {
	private int id;
	private String comentario;
	private Projeto projeto;
	private Tarefa tarefa;
//	private Colaborador colaborador;
	private Date dataCadastro;
	private Boolean ativo;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public Projeto getProjeto() {
		return projeto;
	}
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
	public Tarefa getTarefa() {
		return tarefa;
	}
	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	
	@Override
	public String toString() {
		return "Comentario [id=" + id + ", comentario=" + comentario + ", projeto=" + projeto + ", tarefa=" + tarefa
				+ ", dataCadastro=" + dataCadastro + ", ativo=" + ativo + "]";
	}
}
