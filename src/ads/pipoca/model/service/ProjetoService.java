package ads.pipoca.model.service;
import java.io.IOException;
import java.util.ArrayList;

import ads.pipoca.model.dao.ProjetoDAO;
import ads.pipoca.model.entity.Projeto;

public class ProjetoService {
	ProjetoDAO dao;
	
	public ProjetoService() {
		this.dao = new ProjetoDAO();
	}
	
	public ArrayList<Projeto> listarProjetos() throws IOException {
		return dao.listarProjetos();
	}
	
	public ArrayList<Projeto> listarProjetos(ArrayList<Integer> lista) throws IOException {
		ArrayList<Projeto> projetos = new ArrayList<>();
		for(int id:lista) {
			projetos.add(buscarProjeto(id));
		}
		return projetos;
	}
	
	public int inserirProjeto(Projeto projeto) throws IOException {
		return dao.inserirProjeto(projeto);
	}
	
	public Projeto buscarProjeto(int id) throws IOException {
		return dao.buscarProjeto(id);
	}
	
	public void atualizarProjeto(Projeto projeto) throws IOException {
		dao.atualizarProjeto(projeto);
	}
	
	public Projeto inativarProjeto(int id) throws IOException {
		Projeto projeto = dao.buscarProjeto(id);
		if (projeto != null) {
			dao.inativarProjeto(projeto.getId());
			return projeto;
		} else {
			return null;
		}
	}
	
	public Projeto reativarProjeto(int id) throws IOException {
		Projeto projeto = dao.buscarProjeto(id);
		if (projeto != null) {
			dao.reativarProjeto(projeto.getId());
			return projeto;
		} else {
			return null;
		}
	}
	
	public Projeto concluirProjeto(int id) throws IOException {
		Projeto projeto = dao.buscarProjeto(id);
		if (projeto != null) {
			dao.concluirProjeto(projeto.getId());
			return projeto;
		} else {
			return null;
		}
	}
}
