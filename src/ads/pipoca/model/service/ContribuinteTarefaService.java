package ads.pipoca.model.service;

import java.io.IOException;
import java.util.ArrayList;

import ads.pipoca.model.dao.ContribuinteTarefaDAO;
import ads.pipoca.model.entity.ContribuinteTarefa;
import ads.pipoca.model.entity.Tarefa;

public class ContribuinteTarefaService {
	ContribuinteTarefaDAO dao;
	
	public ContribuinteTarefaService() {
		this.dao = new ContribuinteTarefaDAO();
	}
	
	public int inserirContribuinteTarefa(ContribuinteTarefa contribuinteTarefa) throws IOException {
		return dao.inserirContribuinteTarefa(contribuinteTarefa);
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
	
	public ArrayList<Tarefa> listarTarefas(ArrayList<Integer> lista) throws IOException {
		ArrayList<Tarefa> tarefas = new ArrayList<>();
		for(int id:lista) {
			tarefas.add(buscarTarefa(id));
		}
		return tarefas;
	}
	
	public void editarTarefa(Tarefa tarefa) throws IOException {
		dao.editarTarefa(tarefa);
	}
	
	public void excluirTarefa(int id) throws IOException {
		dao.excluirTarefa(id);
	}
}
