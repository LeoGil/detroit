package ads.pipoca.model.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ads.pipoca.model.entity.Colaborador;
import ads.pipoca.model.entity.Filme;
import ads.pipoca.model.entity.Genero;
import ads.pipoca.model.entity.Objetivo;
import ads.pipoca.model.entity.Projeto;
import ads.pipoca.model.entity.SituacaoProjeto;

public class ProjetoDAO {
	public int inserirProjeto(Projeto projeto) throws IOException {
		int id = -1;
		String sql = "INSERT INTO projetos (nome, descricao, estimativa, colaborador_id, "
				+ "situacao_id, objetivo_id, departamento, resultado_esperado, publico_beneficiario) values (?,?,?,?,?,?,?,?,?)";

		try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement pst = conn.prepareStatement(sql);) {

			pst.setString(1, projeto.getNome());
			pst.setString(2, projeto.getDescricao());
			pst.setDate(3, new java.sql.Date(projeto.getEstimativa().getTime()));
			pst.setInt(4, projeto.getColaborador().getId());
			pst.setInt(5, projeto.getSituacaoProjeto().getId());
			pst.setInt(6, projeto.getObjetivo().getId());
			pst.setString(7, projeto.getDepartamento());
			pst.setString(8, projeto.getResultadoEsperado());
			pst.setString(9, projeto.getPublicoBeneficiario());
			pst.execute();

			// obter o id criado
			String query = "select LAST_INSERT_ID()";
			try (PreparedStatement pst1 = conn.prepareStatement(query); ResultSet rs = pst1.executeQuery();) {

				if (rs.next()) {
					id = rs.getInt(1);
					projeto.setId(id);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		return id;
	}

	public Projeto buscarProjeto(int id) throws IOException {
		Projeto projeto = null;
		String sql = "SELECT projetos.nome, projetos.descricao, estimativa, colaborador_id, colaboradores.nome, situacao_id, situacoes_projetos.situacao, objetivo_id, objetivos.descricao, departamento, resultado_esperado, publico_beneficiario" + 
				"FROM projetos" + 
				"INNER JOIN colaboradores ON colaboradores.id = projetos.colaborador_id" + 
				"INNER JOIN situacoes_projetos ON situacoes_projetos.id = projetos.situacao_id" + 
				"INNER JOIN objetivos ON objetivos.id = projetos.objetivo_id" + 
				"WHERE projetos.id = ?";

		try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement pst = conn.prepareStatement(sql);) {

			pst.setInt(1, id);
			try (ResultSet rs = pst.executeQuery();) {

				while (rs.next()) {
					projeto = new Projeto();
					projeto.setId(id);
					projeto.setNome(rs.getString("projetos.nome"));
					projeto.setDescricao(rs.getString("projetos.descricao"));
					projeto.setEstimativa(rs.getDate("estimativa"));
					Colaborador colaborador = new Colaborador();
					colaborador.setId(rs.getInt("colaborador_id"));
					colaborador.setNome(rs.getString("colaboradores.nome"));
					projeto.setColaborador(colaborador);
					SituacaoProjeto situacaoProjeto = new SituacaoProjeto();
					situacaoProjeto.setId(rs.getInt("situacao_id"));
					situacaoProjeto.setSituacao(rs.getString("situacoes_projetos.situacao"));
					projeto.setSituacaoProjeto(situacaoProjeto);
					Objetivo objetivo = new Objetivo();
					objetivo.setId(rs.getInt("objetivo_id"));
					objetivo.setDescricao(rs.getString("objetivos.descricao"));
					projeto.setObjetivo(objetivo);
					projeto.setDepartamento(rs.getString("departamento"));
					projeto.setResultadoEsperado(rs.getString("resultado_esperado"));
					projeto.setPublicoBeneficiario(rs.getString("publico_beneficiario"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		return projeto;
	}
	
	public ArrayList<Projeto> listarProjetos() throws IOException {
		Projeto projeto = null;
		ArrayList<Projeto> lista = new ArrayList<>();
		String sql = "SELECT projetos.id, projetos.nome, projetos.descricao, estimativa, colaborador_id, colaboradores.nome, situacao_id, situacoes_projetos.situacao, objetivo_id, objetivos.descricao, departamento, resultado_esperado, publico_beneficiario" + 
				"FROM projetos" + 
				"INNER JOIN colaboradores ON colaboradores.id = projetos.colaborador_id" + 
				"INNER JOIN situacoes_projetos ON situacoes_projetos.id = projetos.situacao_id" + 
				"INNER JOIN objetivos ON objetivos.id = projetos.objetivo_id";

		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql);
				ResultSet rs = pst.executeQuery();) {

			while (rs.next()) {
				projeto = new Projeto();
				projeto.setId(rs.getInt("projetos.id"));
				projeto.setNome(rs.getString("projetos.nome"));
				projeto.setDescricao(rs.getString("projetos.descricao"));
				projeto.setEstimativa(rs.getDate("estimativa"));
				Colaborador colaborador = new Colaborador();
				colaborador.setId(rs.getInt("colaborador_id"));
				colaborador.setNome(rs.getString("colaboradores.nome"));
				projeto.setColaborador(colaborador);
				SituacaoProjeto situacaoProjeto = new SituacaoProjeto();
				situacaoProjeto.setId(rs.getInt("situacao_id"));
				situacaoProjeto.setSituacao(rs.getString("situacoes_projetos.situacao"));
				projeto.setSituacaoProjeto(situacaoProjeto);
				Objetivo objetivo = new Objetivo();
				objetivo.setId(rs.getInt("objetivo_id"));
				objetivo.setDescricao(rs.getString("objetivos.descricao"));
				projeto.setObjetivo(objetivo);
				projeto.setDepartamento(rs.getString("departamento"));
				projeto.setResultadoEsperado(rs.getString("resultado_esperado"));
				projeto.setPublicoBeneficiario(rs.getString("publico_beneficiario"));
				lista.add(projeto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		return lista;
	}
	
	public void inativarProjeto(int id) throws IOException {
		String sql = "UPDATE projetos SET situacao_id = 2 WHERE id = ?";

		try (Connection conn = ConnectionFactory.getConnection(); 
				PreparedStatement pst = conn.prepareStatement(sql);) {

			pst.setInt(1, id);
			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
	}
	
	public void reativarProjeto(int id) throws IOException {
		String sql = "UPDATE projetos SET situacao_id = 1 WHERE id = ?";

		try (Connection conn = ConnectionFactory.getConnection(); 
				PreparedStatement pst = conn.prepareStatement(sql);) {

			pst.setInt(1, id);
			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
	}
	
	public void concluirProjeto(int id) throws IOException {
		String sql = "UPDATE projetos SET situacao_id = 3 WHERE id = ?";

		try (Connection conn = ConnectionFactory.getConnection(); 
				PreparedStatement pst = conn.prepareStatement(sql);) {

			pst.setInt(1, id);
			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
	}

	public void atualizarProjeto(Projeto projeto) throws IOException {
		String sql = "UPDATE projetos set nome=?, descricao=?, estimativa=?, colaborador_id=?, situacao_id=?, objetivo_id=?, departamento=?, resultado_esperado=?, publico_beneficiario=? where id=?";

		try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement pst = conn.prepareStatement(sql);) {

			pst.setString(1, projeto.getNome());
			pst.setString(2, projeto.getDescricao());
			pst.setDate(3, new java.sql.Date(projeto.getEstimativa().getTime()));
			pst.setInt(4, projeto.getColaborador().getId());
			pst.setInt(5, projeto.getSituacaoProjeto().getId());
			pst.setInt(6, projeto.getObjetivo().getId());
			pst.setString(7, projeto.getDepartamento());
			pst.setString(8, projeto.getResultadoEsperado());
			pst.setString(9, projeto.getPublicoBeneficiario());
			pst.setInt(10, projeto.getId());
			pst.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}		
	}
}
