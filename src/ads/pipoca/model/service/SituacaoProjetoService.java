package ads.pipoca.model.service;

import java.io.IOException;
import java.util.ArrayList;

import ads.pipoca.model.dao.SituacaoProjetoDAO;
import ads.pipoca.model.entity.SituacaoProjeto;

public class SituacaoProjetoService {
	private SituacaoProjetoDAO dao;
	
	public SituacaoProjetoService () {
		this.dao = new SituacaoProjetoDAO(); 
	}
	
	public ArrayList<SituacaoProjeto> listarSituacoes() throws IOException {
		return dao.listarSituacoes();
	}
	
	public SituacaoProjeto buscarSituacao(int id) throws IOException {
		return dao.buscarSituacao(id);
	}
}
