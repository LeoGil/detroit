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
