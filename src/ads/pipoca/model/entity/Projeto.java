package ads.pipoca.model.entity;

import java.util.Date;

public class Projeto {
	private int id;
	private String nome;
	private String descricao;
	private Date estimativa;
	private Colaborador colaborador;
	private SituacaoProjeto situacaoProjeto;
	private Objetivo objetivo;
	private String departamento;
	private String resultadoEsperado;
	private String publicoBeneficiario;
	private Date dataCadastro;
	private Boolean ativo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getEstimativa() {
		return estimativa;
	}

	public void setEstimativa(Date estimativa) {
		this.estimativa = estimativa;
	}

	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	public SituacaoProjeto getSituacaoProjeto() {
		return situacaoProjeto;
	}

	public void setSituacaoProjeto(SituacaoProjeto situacaoProjeto) {
		this.situacaoProjeto = situacaoProjeto;
	}

	public Objetivo getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(Objetivo objetivo) {
		this.objetivo = objetivo;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getResultadoEsperado() {
		return resultadoEsperado;
	}

	public void setResultadoEsperado(String resultadoEsperado) {
		this.resultadoEsperado = resultadoEsperado;
	}

	public String getPublicoBeneficiario() {
		return publicoBeneficiario;
	}

	public void setPublicoBeneficiario(String publicoBeneficiario) {
		this.publicoBeneficiario = publicoBeneficiario;
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
		return "Projeto [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", estimativa=" + estimativa
				+ ", colaborador=" + colaborador.getNome() + ", situacaoProjeto=" + situacaoProjeto.getSituacao()
				+ ", objetivo=" + objetivo.getDescricao() + ", departamento=" + departamento + ", resultadoEsperado="
				+ resultadoEsperado + ", publicoBeneficiario=" + publicoBeneficiario + ", dataCadastro=" + dataCadastro
				+ ", ativo=" + ativo + "]";
	}

}
