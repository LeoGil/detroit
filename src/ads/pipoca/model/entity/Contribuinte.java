package ads.pipoca.model.entity;

import java.util.Date;

public class Contribuinte {
	public int id;
	public Colaborador colaborador;
	public Papel papel;
	public Date dataCadastro;
	public Boolean ativo;
	public Projeto projeto;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	public Papel getPapel() {
		return papel;
	}

	public void setPapel(Papel papel) {
		this.papel = papel;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	@Override
	public String toString() {
		return "Contribuinte [id=" + id + ", colaborador=" + colaborador + ", papel=" + papel + ", dataCadastro="
				+ dataCadastro + ", ativo=" + ativo + ", projeto=" + projeto + "]";
	}

}
