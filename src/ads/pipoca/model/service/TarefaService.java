package ads.pipoca.model.service;

import java.io.IOException;
import java.util.ArrayList;

import ads.pipoca.model.dao.TarefaDAO;
import ads.pipoca.model.entity.Tarefa;

public class TarefaService {
	TarefaDAO dao;
	
	public TarefaService() {
		this.dao = new TarefaDAO();
	}
	
	public int inserirTarefa(Tarefa tarefa) throws IOException {
		return dao.inserirTarefa(tarefa);
	}
	
	public Tarefa buscarTarefa(int id) throws IOException{
		return dao.buscarTarefa(id);
	}
	
	public ArrayList<Tarefa> listarTarefas() throws IOException {
		return dao.listarTarefas();
	}
	
	public ArrayList<Tarefa> listarContribuintesPorProjeto(int projeto_id) throws IOException {
		return dao.listarTarefas(projeto_id);
	}
	
	public ArrayList<Tarefa> listarContribuintesPorProjetoToDo(int projeto_id) throws IOException {
		return dao.listarTarefas(projeto_id, 1);
	}
	
	public ArrayList<Tarefa> listarContribuintesPorProjetoDoing(int projeto_id) throws IOException {
		return dao.listarTarefas(projeto_id, 2);
	}
	
	public ArrayList<Tarefa> listarContribuintesPorProjetoDone(int projeto_id) throws IOException {
		return dao.listarTarefas(projeto_id, 3);
	}
	
	public void updateSituacaoTarefa(int tarefa_id, int situacao_id) throws IOException {
		dao.updateSituacaoTarefa(tarefa_id, situacao_id);
	}
	
	public int totalTarefasPorProjeto(int projeto) throws IOException {
		return dao.totalTarefa(projeto);
	}
	
	public int totalTarefasFeitasPorProjeto(int projeto) throws IOException {
		return dao.totalTarefa(projeto, 3);
	}
	
	public void excluirTarefa(int id) throws IOException {
		dao.excluirTarefa(id);
	}
}
