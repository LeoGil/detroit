package ads.pipoca.model.entity;

import java.util.Date;

public class Tarefa {
	private int id;
	private String descricao;
	private Projeto projeto;
//	private SituacaoTarefa situacaoTarefa;
	private Date dataCadastro;
	private Boolean ativo;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Projeto getProjeto() {
		return projeto;
	}
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
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
		return "Tarefa [id=" + id + ", descricao=" + descricao + ", projeto=" + projeto + ", dataCadastro="
				+ dataCadastro + ", ativo=" + ativo + "]";
	}
}
