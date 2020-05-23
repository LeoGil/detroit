package ads.pipoca.model.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ads.pipoca.model.entity.*;

public class ComentarioDAO {
	public int inserirComentario(Comentario comentario) throws IOException {
		int id = -1;
		String sql = "insert into comentarios (comentario, projeto_id, colaborador_id, tarefa_id)" + "values (?,?,?,?)";

		try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement pst = conn.prepareStatement(sql);) {

			pst.setString(1, comentario.getComentario());
			pst.setInt(2, comentario.getProjeto().getId());
			pst.setInt(3, comentario.getColaborador().getId());
			pst.setInt(4, comentario.getTarefa().getId());
			pst.execute();

			// obter o id criado
			String query = "select LAST_INSERT_ID()";
			try (PreparedStatement pst1 = conn.prepareStatement(query); ResultSet rs = pst1.executeQuery();) {

				if (rs.next()) {
					id = rs.getInt(1);
					comentario.setId(id);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		return id;
	}
	
	public void excluirComentario(int id) throws IOException {
		String sql = "delete from comentarios where id = ?";

		try (Connection conn = ConnectionFactory.getConnection(); 
				PreparedStatement pst = conn.prepareStatement(sql);) {

			pst.setInt(1, id);
			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
	}
	
	public Comentario buscarComentario(int id) throws IOException {
		Comentario comentario = null;
		String sql = "select id, comentario, projeto_id , colaborador_id , data_cadastro,"
					+ "ativo, tarefa_id from comentarios"
					+ "where id = ?;";

		try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement pst = conn.prepareStatement(sql);) {

			pst.setInt(1, id);
			try (ResultSet rs = pst.executeQuery();) {

				while (rs.next()) {
					comentario = new Comentario();
					comentario.setId(id);
					comentario.setComentario(rs.getString("comentario"));
					Projeto projeto = new Projeto ();
					projeto.setId(rs.getInt("projeto_id"));
					comentario.setProjeto(projeto);
					Colaborador colaborador = new Colaborador();
					colaborador.setId(rs.getInt("colaborador_id"));
					comentario.setColaborador(colaborador);
					comentario.setDataCadastro(rs.getDate("data_lancamento"));
					comentario.setAtivo(rs.getBoolean("ativo"));
					Tarefa tarefa = new Tarefa();
					tarefa.setId(rs.getInt("tarefa_id"));
					comentario.setTarefa(tarefa);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		return comentario;
	}
	
	public ArrayList<Comentario> listarComentarios() throws IOException {
		Comentario comentario = null;
		ArrayList<Comentario> lista = new ArrayList<>();
		String sql = "SELECT comentarios.id, comentario, comentarios.projeto_id, projetos.nome, comentarios.colaborador_id, colaboradores.nome, tarefa_id, tarefas.descricao, comentarios.data_cadastro" 
				+ "FROM comentarios" 
				+ "INNER JOIN colaboradores ON colaboradores.id = comentarios.colaborador_id"
				+ "INNER JOIN projetos ON projetos.id = comentarios.projeto_id"
				+ "INNER JOIN tarefas ON tarefas.id = comentarios.tarefa_id;";

		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql);
				ResultSet rs = pst.executeQuery();) {

			while (rs.next()) {
				comentario = new Comentario();
				comentario.setId(rs.getInt("comentarios.id"));
				comentario.setComentario(rs.getString("comentario"));
				Projeto projeto = new Projeto();
				projeto.setId(rs.getInt("comentarios.projeto_id"));
				projeto.setNome(rs.getString("projetos.nome"));
				comentario.setProjeto(projeto);
				Colaborador colaborador = new Colaborador();
				colaborador.setId(rs.getInt("comentarios.colaborador_id"));
				colaborador.setNome(rs.getString("colaboradores.nome"));
				comentario.setColaborador(colaborador);
				Tarefa tarefa = new Tarefa();
				tarefa.setId(rs.getInt("tarefa_id"));
				tarefa.setDescricao(rs.getString("tarefas.descricao"));
				comentario.setTarefa(tarefa);
				comentario.setDataCadastro(rs.getDate("comentarios.data_cadastro"));
				lista.add(comentario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		return lista;
	}
	
}
