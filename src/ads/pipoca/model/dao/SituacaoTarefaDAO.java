package ads.pipoca.model.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ads.pipoca.model.entity.SituacaoTarefa;

public class SituacaoTarefaDAO {
	public ArrayList<SituacaoTarefa> listarSituacoesTarefas() throws IOException {
		ArrayList<SituacaoTarefa> situacoesTarefas = new ArrayList<>();
		String sql = "SELECT id" 
					+"FROM situacoes_tarefas"
					+"ORDER BY situacao;";

		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql);
				ResultSet rs = pst.executeQuery();) {

			while (rs.next()) {
				SituacaoTarefa situacaoTarefa = new SituacaoTarefa();
				situacaoTarefa.setId(rs.getInt("id"));
				situacaoTarefa.setSituacao(rs.getString("situacao"));
				situacoesTarefas.add(situacaoTarefa);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		return situacoesTarefas;
	}
	
	public SituacaoTarefa buscarSituacaoTarefa(int id) throws IOException {
		SituacaoTarefa situacaoTarefa = null;
		String sql = "SELECT id, situacao"
					+"FROM situacoes_TAREFAS"
					+"WHERE id=?";

		try (Connection conn = ConnectionFactory.getConnection(); 
				PreparedStatement pst = conn.prepareStatement(sql);) {

			pst.setInt(1, id);
			try (ResultSet rs = pst.executeQuery();) {

				if (rs.next()) {
					situacaoTarefa = new SituacaoTarefa();
					situacaoTarefa.setId(id);
					situacaoTarefa.setSituacao(rs.getString("situacao"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		return situacaoTarefa;
	}

}
