package ads.pipoca.model.entity;
import java.util.Date;
public class Papel {
	public int id;
	public Date dataCAdastro;
	public Boolean ativo;
	public String papel;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	public String getPapel() {
		return papel;
	}
	public void setPapel(String papel) {
		this.papel = papel;
	}
	public Date getDataCAdastro() {
		return dataCAdastro;
	}
	
	

}
