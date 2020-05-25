package ads.pipoca.model.service;

import java.io.IOException;
import java.util.ArrayList;

import ads.pipoca.model.dao.ObjetivoDAO;
import ads.pipoca.model.entity.Objetivo;

public class ObjetivoService {
	private ObjetivoDAO dao;
	
	public ObjetivoService () {
		this.dao = new ObjetivoDAO(); 
	}
	
	public ArrayList<Objetivo> listarObjetivos() throws IOException {
		return dao.listarObjetivos();
	}
	
	public Objetivo buscarObjetivo(int id) throws IOException {
		return dao.buscarObjetivos(id);
	}
}
