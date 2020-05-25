package ads.pipoca.model.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ads.pipoca.model.entity.SituacaoProjeto;

public class SituacaoProjetoDAO {
	public ArrayList<SituacaoProjeto> listarSituacoes() throws IOException {
		ArrayList<SituacaoProjeto> situacoes = new ArrayList<>();
		String sql = "select id, situacao from situacoes_projetos order by situacao";

		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql);
				ResultSet rs = pst.executeQuery();) {

			while (rs.next()) {
				SituacaoProjeto situacao = new SituacaoProjeto();
				situacao.setId(rs.getInt("id"));
				situacao.setSituacao(rs.getString("situacao"));
				situacoes.add(situacao);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		return situacoes;
	}
	
	public SituacaoProjeto buscarSituacao(int id) throws IOException {
		SituacaoProjeto situacao = null;
		String sql = "select id, situacao from situacoes_projetos where id=?";

		try (Connection conn = ConnectionFactory.getConnection(); 
				PreparedStatement pst = conn.prepareStatement(sql);) {

			pst.setInt(1, id);
			try (ResultSet rs = pst.executeQuery();) {

				if (rs.next()) {
					situacao = new SituacaoProjeto();
					situacao.setId(id);
					situacao.setSituacao(rs.getString("situacao"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		return situacao;
	}
}
