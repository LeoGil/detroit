package ads.pipoca.model.entity;
import java.util.Date;
public class SituacaoProjeto {
		
		private int id;
		private String situacao;
		private String descricao;
		public String getDescricao() {
			return descricao;
		}
		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}
		private Date data_Cadastro;
		private Boolean ativo;
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getSituacao() {
			return situacao;
		}
		public void setSituacao(String situacao) {
			this.situacao = situacao;
		}
		public Date getData_Cadastro() {
			return data_Cadastro;
		}
		public void setData_Cadastro(Date data_Cadastro) {
			this.data_Cadastro = data_Cadastro;
		}
		public Boolean getAtivo() {
			return ativo;
		}
		public void setAtivo(Boolean ativo) {
			this.ativo = ativo;
		}
		
		
	}

