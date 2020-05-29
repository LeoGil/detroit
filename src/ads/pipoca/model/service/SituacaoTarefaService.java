package ads.pipoca.model.service;

import java.io.IOException;
import java.util.ArrayList;

import ads.pipoca.model.dao.SituacaoTarefaDAO;
import ads.pipoca.model.entity.SituacaoTarefa;

public class SituacaoTarefaService {

	private SituacaoTarefaDAO dao;
	
	public SituacaoTarefaService () {
		this.dao = new SituacaoTarefaDAO(); 
	}
	
	public ArrayList<SituacaoTarefa> listarSituacoesTarefas() throws IOException {
		return dao.listarSituacoesTarefas();
	}
	
	public SituacaoTarefa buscarSituacaoTarefa(int id) throws IOException {
		return dao.buscarSituacaoTarefa(id);
	}
}
