package ads.pipoca.model.service;

import java.io.IOException;
import java.util.ArrayList;

import ads.pipoca.model.dao.ColaboradorDAO;
import ads.pipoca.model.entity.Colaborador;

public class ColaboradorService {
	ColaboradorDAO dao;

	public ColaboradorService() {
		this.dao = new ColaboradorDAO();
	}

	public ArrayList<Colaborador> listarColaboradores() throws IOException {
		return dao.listarColaboradores();
	}

	public int inserirColaborador(Colaborador colaborador) throws IOException {
		return dao.inserirColaborador(colaborador);
	}

	public Colaborador buscarColaborador(int id) throws IOException {
		return dao.buscarColaborador(id);
	}

	public void atualizarColaborador(Colaborador colaborador) throws IOException {
		dao.atualizarColaborador(colaborador);
	}

	public boolean logar(Colaborador colaborador) throws IOException {
		return dao.logar(colaborador);
	}

	public Colaborador excluirColaborador(int id) throws IOException {
		Colaborador colaborador = dao.buscarColaborador(id);
		if (colaborador != null) {
			dao.excluirColaborador(colaborador.getId());
			return colaborador;
		} else {
			return null;
		}
	}
}
