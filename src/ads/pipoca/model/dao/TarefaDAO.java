package ads.pipoca.model.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ads.pipoca.model.entity.Projeto;
import ads.pipoca.model.entity.SituacaoTarefa;
import ads.pipoca.model.entity.Tarefa;

public class TarefaDAO {

	public int inserirTarefa(Tarefa tarefa) throws IOException {
		int id = -1;
		String sql = "INSERT INTO tarefas (descricao, projeto_id, situacao_id)"
					+ "values (?,?,?);";

		try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement pst = conn.prepareStatement(sql);) {

			pst.setString(1, tarefa.getDescricao());
			pst.setInt(2, tarefa.getProjeto().getId());
			pst.setInt(3, tarefa.getSituacaoTarefa().getId());
			pst.execute();

			// obter o id criado
			String query = "select LAST_INSERT_ID()";
			try (PreparedStatement pst1 = conn.prepareStatement(query); ResultSet rs = pst1.executeQuery();) {

				if (rs.next()) {
					id = rs.getInt(1);
					tarefa.setId(id);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		return id;
	}

	public Tarefa buscarTarefa(int id) throws IOException {
		Tarefa tarefa = null;
		String sql = "SELECT tarefas.descricao, projeto_id, projetos.nome, tarefas.situacao_id, situacoes_tarefas.situacao, tarefas.data_cadastro, tarefas.ativo"
					+	"FROM tarefas"
					+	"INNER JOIN projetos ON projetos.id = tarefas.projeto_id"
					+	"INNER JOIN situacoes_tarefas ON situacoes_tarefas.id = tarefas.situacao_id"
					+	"WHERE tarefas.id = ?;";
		
		try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement pst = conn.prepareStatement(sql);) {

			pst.setInt(1, id);
			try (ResultSet rs = pst.executeQuery();) {

				while (rs.next()) {
					tarefa = new Tarefa();
					tarefa.setId(id);
					tarefa.setDescricao(rs.getString("tarefas.descricao"));
					tarefa.setDataCadastro(rs.getDate("tarefas.data_cadastro"));
					tarefa.setAtivo(rs.getBoolean("tarefas.ativo"));
					Projeto projeto = new Projeto();
					projeto.setId(rs.getInt("projeto_id"));
					projeto.setNome(rs.getString("projetos.nome"));
					tarefa.setProjeto(projeto);
					SituacaoTarefa situacaoTarefa = new SituacaoTarefa();
					situacaoTarefa.setId(rs.getInt("tarefas.situcao_id"));
					situacaoTarefa.setSituacao(rs.getString("situacoes_tarefas.situacao"));
					tarefa.setSituacaoTarefa(situacaoTarefa);
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		return tarefa;
	}

	public ArrayList<Tarefa> listarTarefas() throws IOException {
		Tarefa tarefa = null;
		ArrayList<Tarefa> lista = new ArrayList<>();
		String sql = "SELECT tarefas.id, tarefas.descricao, projeto_id, projetos.nome, tarefas.situacao_id, situacoes_tarefas.situacao, tarefas.data_cadastro, tarefas.ativo"
				+	"FROM tarefas"
				+	"INNER JOIN projetos ON projetos.id = tarefas.projeto_id"
				+	"INNER JOIN situacoes_tarefas ON situacoes_tarefas.id = tarefas.situacao_id";

		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql);
				ResultSet rs = pst.executeQuery();) {

			while (rs.next()) {
				tarefa = new Tarefa();
				tarefa.setId(rs.getInt("tarefas.id"));
				tarefa.setDescricao(rs.getString("tarefas.descricao"));
				Projeto projeto = new Projeto();
				projeto.setId(rs.getInt("projeto_id"));
				projeto.setNome(rs.getString("projetos.nome"));
				tarefa.setProjeto(projeto);
				SituacaoTarefa situacaoTarefa = new SituacaoTarefa();
				situacaoTarefa.setId(rs.getInt("tarefas.situacao_id"));
				situacaoTarefa.setSituacao(rs.getString("situacoes_tarefas.situacao"));
				tarefa.setSituacaoTarefa(situacaoTarefa);
				tarefa.setDataCadastro(rs.getDate("tarefas.data_cadastro"));
				tarefa.setAtivo(rs.getBoolean("tarefas.ativo"));
				lista.add(tarefa);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		return lista;
	}
	
	public ArrayList<Tarefa> listarTarefas(int projeto_id) throws IOException {
		Tarefa tarefa = null;
		ArrayList<Tarefa> lista = new ArrayList<>();
		String sql = "SELECT tarefas.id, tarefas.titulo, tarefas.descricao, projeto_id, projetos.nome, tarefas.situacao_id, situacoes_tarefas.situacao, tarefas.data_cadastro, tarefas.ativo "
				+	"FROM tarefas "
				+	"INNER JOIN projetos ON projetos.id = tarefas.projeto_id "
				+	"INNER JOIN situacoes_tarefas ON situacoes_tarefas.id = tarefas.situacao_id WHERE projeto_id = ?";

		try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement pst = conn.prepareStatement(sql);) {

			pst.setInt(1, projeto_id);
			try (ResultSet rs = pst.executeQuery();) {

				while (rs.next()) {
					tarefa = new Tarefa();
					tarefa.setId(rs.getInt("tarefas.id"));
					tarefa.setTitulo(rs.getString("tarefas.titulo"));
					tarefa.setDescricao(rs.getString("tarefas.descricao"));
					Projeto projeto = new Projeto();
					projeto.setId(projeto_id);
					projeto.setNome(rs.getString("projetos.nome")); 
					tarefa.setProjeto(projeto);
					SituacaoTarefa situacao = new SituacaoTarefa();
					situacao.setId(rs.getInt("tarefas.situacao_id"));
					situacao.setSituacao(rs.getString("situacoes_tarefas.situacao"));
					tarefa.setSituacaoTarefa(situacao);
					tarefa.setDataCadastro(rs.getDate("tarefas.data_cadastro"));
					tarefa.setAtivo(rs.getBoolean("tarefas.ativo"));
					lista.add(tarefa);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		return lista;
	}
	
	public void editarTarefa(Tarefa tarefa) throws IOException {
		String sql = "UPDATE tarefas"
					+"SET descricao=?, projeto_id=?, situacao_id=?"
					+"WHERE id=?;";

		try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement pst = conn.prepareStatement(sql);) {

			pst.setString(1, tarefa.getDescricao());
			pst.setInt(2, tarefa.getProjeto().getId());
			pst.setInt(3, tarefa.getSituacaoTarefa().getId());
			pst.setInt(4, tarefa.getId());
			pst.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}		
	}
	
	public void excluirTarefa(int id) throws IOException {
		String sql = "DELETE FROM tarefas "
					+ "WHERE id = ?";

		try (Connection conn = ConnectionFactory.getConnection(); 
				PreparedStatement pst = conn.prepareStatement(sql);) {

			pst.setInt(1, id);
			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
	}
}
