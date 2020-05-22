package ads.pipoca.model.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
					Colaborador colaborador = new Colaborador();
					colaborador.setId(rs.getInt("colaborador_id"));
					comentario.setDataCadastro(rs.getDate("data_lancamento"));
					comentario.setAtivo(rs.getBoolean("ativo"));
					Tarefa tarefa = new Tarefa();
					tarefa.setId(rs.getInt("tarefa_id"));
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
	
}
