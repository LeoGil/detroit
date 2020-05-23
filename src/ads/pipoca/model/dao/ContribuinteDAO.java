package ads.pipoca.model.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ads.pipoca.model.entity.Contribuinte;
import ads.pipoca.model.entity.Papel;
import ads.pipoca.model.entity.Projeto;

public class ContribuinteDAO {
	
	public int inserirContribuinte(Contribuinte contribuinte) throws IOException {
		int id = -1;
		String sql = "INSERT INTO contribuintes ( projeto_id, papel_id)"
					+"VALUES (?,?);";

		try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement pst = conn.prepareStatement(sql);) {

			pst.setInt(1, contribuinte.getProjeto().getId());
			pst.setInt(2, contribuinte.getPapel().getId());
			pst.execute();

			// obter o id criado
			String query = "select LAST_INSERT_ID()";
			try (PreparedStatement pst1 = conn.prepareStatement(query); ResultSet rs = pst1.executeQuery();) {

				if (rs.next()) {
					id = rs.getInt(1);
					contribuinte.setId(id);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		return id;
	}

	public Contribuinte buscarContribuinte(int id) throws IOException {
		Contribuinte contribuinte = null;
		String sql = "SELECT contribuintes.projeto_id, projetos.nome, contribuintes.papel_id, papeis.papel, contribuintes.data_cadastro, contribuintes.ativo"
					+"FROM contribuintes"
					+"INNER JOIN projetos ON projetos.id = contribuintes.projeto_id"
					+"INNER JOIN papeis ON papeis.id = contribuintes.papel_id"
					+"WHERE contribuintes.id = ?;";
		
		try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement pst = conn.prepareStatement(sql);) {

			pst.setInt(1, id);
			try (ResultSet rs = pst.executeQuery();) {

				while (rs.next()) {
					contribuinte = new Contribuinte();
					contribuinte.setId(id);
					Projeto projeto = new Projeto();
					projeto.setId(rs.getInt("contribuintes.projeto_id"));
					projeto.setNome(rs.getString("projetos.nome"));
					contribuinte.setProjeto(projeto);
					Papel papel = new Papel();
					papel.setId(rs.getInt("contribuintes.papel_id"));
					papel.setPapel(rs.getString("papeis.papel"));
					contribuinte.setPapel(papel);
					contribuinte.setDataCadastro(rs.getDate("tarefas.data_cadastro"));
					contribuinte.setAtivo(rs.getBoolean("tarefas.ativo"));
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		return contribuinte;
	}

	public ArrayList<Contribuinte> listarContribuintes() throws IOException {
		Contribuinte contribuinte = null;
		ArrayList<Contribuinte> lista = new ArrayList<>();
		String sql = "SELECT contribuintes.id, contribuintes.projeto_id, projetos.nome, contribuintes.papel_id, papeis.papel, contribuintes.data_cadastro, contribuintes.ativo"
					+"FROM contribuintes"
					+"INNER JOIN projetos ON projetos.id = contribuintes.projeto_id"
					+"INNER JOIN papeis ON papeis.id = contribuintes.papel_id";

		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql);
				ResultSet rs = pst.executeQuery();) {

			while (rs.next()) {
				contribuinte = new Contribuinte();
				contribuinte.setId(rs.getInt("contribuintes.id"));
				Projeto projeto = new Projeto();
				projeto.setId(rs.getInt("contribuintes.projeto_id"));
				projeto.setNome(rs.getString("projetos.nome"));
				contribuinte.setProjeto(projeto);
				Papel papel = new Papel();
				papel.setId(rs.getInt("contribuintes.papel_id"));
				papel.setPapel(rs.getString("papeis.papel"));
				contribuinte.setPapel(papel);
				contribuinte.setDataCadastro(rs.getDate("tarefas.data_cadastro"));
				contribuinte.setAtivo(rs.getBoolean("tarefas.ativo"));
				lista.add(contribuinte);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		return lista;
	}
	
	
	public void excluirContribuinte(int id) throws IOException {
		String sql = "DELETE FROM contribuintes "
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
