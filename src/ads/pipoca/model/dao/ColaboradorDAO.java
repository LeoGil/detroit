package ads.pipoca.model.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ads.pipoca.model.entity.Colaborador;

public class ColaboradorDAO {
	public int inserirColaborador(Colaborador colaborador) throws IOException {
		int id = -1;
		String sql = "INSERT INTO colaboradores (matricula, nome, email, senha) VALUES (?, ?, ?, ?);";

		try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement pst = conn.prepareStatement(sql);) {

			pst.setString(1, colaborador.getMatricula());
			pst.setString(2, colaborador.getNome());
			pst.setString(3, colaborador.getEmail());
			pst.setString(4, colaborador.getSenha());
			pst.execute();

			// obter o id criado
			String query = "select LAST_INSERT_ID()";
			try (PreparedStatement pst1 = conn.prepareStatement(query); ResultSet rs = pst1.executeQuery();) {

				if (rs.next()) {
					id = rs.getInt(1);
					colaborador.setId(id);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		return id;
	}

	public Colaborador buscarColaborador(int id) throws IOException {
		Colaborador colaborador = null;
		String sql = "SELECT matricula, nome, email, data_cadastro, ativo FROM colaboradores WHERE id = ?";

		try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement pst = conn.prepareStatement(sql);) {

			pst.setInt(1, id);
			try (ResultSet rs = pst.executeQuery();) {

				while (rs.next()) {
					colaborador = new Colaborador();
					colaborador.setId(id);
					colaborador.setMatricula(rs.getString("matricula"));
					colaborador.setNome(rs.getString("nome"));
					colaborador.setEmail(rs.getString("email"));
					colaborador.setDataCadastro(rs.getDate("data_cadastro"));
					colaborador.setAtivo(rs.getBoolean("ativo"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		return colaborador;
	}
	
	public ArrayList<Colaborador> listarColaboradores() throws IOException {
		Colaborador colaborador = null;
		ArrayList<Colaborador> lista = new ArrayList<>();
		String sql = "SELECT id, matricula, nome, email, data_cadastro, ativo FROM colaboradores";

		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql);
				ResultSet rs = pst.executeQuery();) {

			while (rs.next()) {
				colaborador = new Colaborador();
				colaborador.setId(rs.getInt("id"));
				colaborador.setMatricula(rs.getString("matricula"));
				colaborador.setNome(rs.getString("nome"));
				colaborador.setEmail(rs.getString("email"));
				colaborador.setDataCadastro(rs.getDate("data_cadastro"));
				colaborador.setAtivo(rs.getBoolean("ativo"));
				lista.add(colaborador);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		return lista;
	}

	public void atualizarColaborador(Colaborador colaborador) throws IOException {
		String sql = "UPDATE colaboradores set matricula=?, nome=?, email=?, senha=? where id=?";

		try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement pst = conn.prepareStatement(sql);) {

			pst.setString(1, colaborador.getMatricula());
			pst.setString(2, colaborador.getNome());
			pst.setString(3, colaborador.getEmail());
			pst.setString(4, colaborador.getSenha());
			pst.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}		
	}
	
	public void excluirColaborador(int id) throws IOException {
		String sql = "delete from colaboradores where id = ?";

		try (Connection conn = ConnectionFactory.getConnection(); 
				PreparedStatement pst = conn.prepareStatement(sql);) {

			pst.setInt(1, id);
			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
	}
	
	public boolean logar(Colaborador colaborador) throws IOException {
		String sqlSelect = "SELECT matricula, senha FROM colaboradores " + "WHERE matricula = ? and senha = ?";

		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setString(1, colaborador.getMatricula());
			stm.setString(2, colaborador.getSenha());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					return true;
				} else {
					return false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		} catch (SQLException e1) {
			e1.getStackTrace();
			throw new IOException(e1);
		}
	}
}
