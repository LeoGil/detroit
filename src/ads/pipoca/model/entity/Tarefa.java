package ads.pipoca.model.entity;

import java.util.Date;

public class Tarefa {
	private int id;
	private String titulo;
	private String descricao;
	private Projeto projeto;
	private Colaborador colaborador;
	private SituacaoTarefa situacaoTarefa;
	private Date dataCadastro;
	private Boolean ativo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
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
	
	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	public SituacaoTarefa getSituacaoTarefa() {
		return situacaoTarefa;
	}

	public void setSituacaoTarefa(SituacaoTarefa situacaoTarefa) {
		this.situacaoTarefa = situacaoTarefa;
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
		return "Tarefa [id=" + id + ", titulo=" + titulo + ", descricao=" + descricao + ", projeto=" + projeto.getNome() + ", colaborador=" + colaborador.getNome()
				+ ", situacaoTarefa=" + situacaoTarefa.getSituacao() + ", dataCadastro=" + dataCadastro + ", ativo=" + ativo + "]";
	}

}
