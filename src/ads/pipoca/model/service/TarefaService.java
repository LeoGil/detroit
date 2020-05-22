package ads.pipoca.model.service;

import java.io.IOException;

import ads.pipoca.model.dao.TarefaDAO;
import ads.pipoca.model.entity.Tarefa;

public class TarefaService {
	TarefaDAO dao;
	
	public int inserirTarefa(Tarefa tarefa) throws IOException {
		return dao.inserirTarefa(tarefa);
	}

}
