package ads.pipoca.model.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ads.pipoca.model.entity.Objetivo;

public class ObjetivoDAO {
	

	public ArrayList<Objetivo> listarObjetivos() throws IOException {
		Objetivo objetivo = null;
		ArrayList<Objetivo> lista = new ArrayList<>();
		String sql = "SELECT id, descricao, data_cadastro, ativo" 
				+ "FROM objetivos;";

		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql);
				ResultSet rs = pst.executeQuery();) {

			while (rs.next()) {
				objetivo = new Objetivo();
				objetivo.setId(rs.getInt("id"));
				objetivo.setDescricao(rs.getString("descricao"));
				objetivo.setDataCadastro(rs.getDate("data_cadastro"));
				objetivo.setAtivo(rs.getBoolean("ativo"));
				lista.add(objetivo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		return lista;
	}
	
	
	
}
