package ads.pipoca.model.entity;
import java.util.Date;

public class ContribuinteTarefa {
	public int id;
	public Contribuinte contribuinte;
	public Date dataCadastro;
	public Boolean ativo;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Contribuinte getContribuinte() {
		return contribuinte;
	}
	public void setContribuinte(Contribuinte contribuinte) {
		this.contribuinte = contribuinte;
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
	
	
}
