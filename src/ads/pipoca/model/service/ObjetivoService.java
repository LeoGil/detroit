package ads.pipoca.model.service;

import java.io.IOException;
import java.util.ArrayList;

import ads.pipoca.model.dao.ObjetivoDAO;
import ads.pipoca.model.entity.Objetivo;


public class ObjetivoService {
	ObjetivoDAO dao;
	
	public ArrayList<Objetivo> listarObjetivos() throws IOException {
		
		return dao.listarObjetivos();
	}
	
	
	
}
