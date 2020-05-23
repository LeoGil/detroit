package ads.pipoca.model.service;

import java.io.IOException;
import java.util.ArrayList;

import ads.pipoca.model.dao.ContribuinteDAO;
import ads.pipoca.model.entity.Contribuinte;


public class ContribuinteService {
	ContribuinteDAO dao;
	
	public ContribuinteService() {
		this.dao = new ContribuinteDAO();
	}
	
	public int inserirContribuinte(Contribuinte contribuinte) throws IOException {
		return dao.inserirContribuinte(contribuinte);
	}
	
	public Contribuinte buscarContribuinte(int id) throws IOException{
		return dao.buscarContribuinte(id);
	}
	
	public ArrayList<Contribuinte> listarContribuintes() throws IOException {
		return dao.listarContribuintes();
	}
	
	public ArrayList<Contribuinte> listarContribuintes(ArrayList<Integer> lista) throws IOException {
		ArrayList<Contribuinte> contribuintes = new ArrayList<>();
		for(int id:lista) {
			contribuintes.add(buscarContribuinte(id));
		}
		return contribuintes;
	}
	
	
	public void excluirContribuinte(int id) throws IOException {
		dao.excluirContribuinte(id);
	}
}
