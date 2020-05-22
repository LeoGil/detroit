package ads.pipoca.model.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

	

}
