package ads.pipoca.model.service;

import java.io.IOException;
import java.util.ArrayList;

import ads.pipoca.model.dao.PapelDAO;
import ads.pipoca.model.entity.Papel;

public class PapelService {
	private PapelDAO dao;
	
	public PapelService () {
		this.dao = new PapelDAO(); 
	}
	
	public ArrayList<Papel> listarPapeis() throws IOException {
		return dao.listarPapeis();
	}
	
	public Papel buscarPapel(int id) throws IOException {
		return dao.buscarPapel(id);
	}
}
