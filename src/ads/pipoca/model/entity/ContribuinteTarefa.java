package ads.pipoca.model.entity;
import java.util.Date;

public class ContribuinteTarefa {
	public int id;
	public Contribuinte contribuinte;
	public Date dataCadastro;
	public Boolean ativo;
	public Tarefa tarefa;
	
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
	public Tarefa getTarefa() {
		return tarefa;
	}
	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}
	
	
}
