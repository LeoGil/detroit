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
		ArrayList<Objetivo> objetivos = new ArrayList<>();
		String sql = "select id, descricao from objetivos order by descricao";

		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql);
				ResultSet rs = pst.executeQuery();) {

			while (rs.next()) {
				Objetivo objetivo = new Objetivo();
				objetivo.setId(rs.getInt("id"));
				objetivo.setDescricao(rs.getString("descricao"));
				objetivos.add(objetivo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		return objetivos;
	}
	
	public Objetivo buscarObjetivos(int id) throws IOException {
		Objetivo objetivo = null;
		String sql = "select id, descricao from objetivos where id=?";

		try (Connection conn = ConnectionFactory.getConnection(); 
				PreparedStatement pst = conn.prepareStatement(sql);) {

			pst.setInt(1, id);
			try (ResultSet rs = pst.executeQuery();) {

				if (rs.next()) {
					objetivo = new Objetivo();
					objetivo.setId(id);
					objetivo.setDescricao(rs.getString("descricao"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		return objetivo;
	}
}
