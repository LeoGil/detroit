package ads.pipoca.model.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ads.pipoca.model.entity.Papel;

public class PapelDAO {
	public ArrayList<Papel> listarPapeis() throws IOException {
		ArrayList<Papel> papeis = new ArrayList<>();
		String sql = "select id, papel from papeis WHERE id != 1";

		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql);
				ResultSet rs = pst.executeQuery();) {

			while (rs.next()) {
				Papel papel = new Papel();
				papel.setId(rs.getInt("id"));
				papel.setPapel(rs.getString("papel"));
				papeis.add(papel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		return papeis;
	}
	
	public Papel buscarPapel(int id) throws IOException {
		Papel papel = null;
		String sql = "select id, papel from papeis where id=?";

		try (Connection conn = ConnectionFactory.getConnection(); 
				PreparedStatement pst = conn.prepareStatement(sql);) {

			pst.setInt(1, id);
			try (ResultSet rs = pst.executeQuery();) {

				if (rs.next()) {
					papel = new Papel();
					papel.setId(id);
					papel.setPapel(rs.getString("papel"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		return papel;
	}
}
