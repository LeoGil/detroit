package ads.pipoca.model.entity;
import java.util.Date;

public class Colaborador {
	public int id;
	public String matricula;
	public String nome;
	public String email;
	public String senha;
	public Date dataCadastro;
	public Boolean ativo;
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getMatricula() {
		return matricula;
	}


	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
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
		return "Colaborador [id=" + id + ", matricula =" + matricula + ", nome=" + nome + ", email=" + email
				+ ", senha=" + senha + ", dataCadastro=" + dataCadastro + ", ativo=" + ativo +"]";
	}

	
}
