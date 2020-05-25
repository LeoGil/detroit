package ads.pipoca.model.entity;

import java.util.Date;

public class Objetivo {
	private int id;
	private String descricao;
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
		return "Objetivo [id=" + id + ", descricao=" + descricao + ", dataCadastro=" + dataCadastro + ", ativo=" + ativo
				+ "]";
	}

}
