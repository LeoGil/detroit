package ads.pipoca.controller;

import javax.servlet.http.HttpServletRequest;

import ads.pipoca.model.entity.Colaborador;

public class ParametrosHelper {
	private HttpServletRequest request;

	public ParametrosHelper(HttpServletRequest request) {
		this.request = request;
	}
	
	public String getAcao() {
		String acao = request.getParameter("acao");
		if (acao == null) {
			return "desconhecida";
		} else {
			return acao;
		}
	}
	
	public Colaborador getUsuario() {
		Colaborador usuario = new Colaborador();
		String matricula = request.getParameter("matricula");
		String senha = request.getParameter("senha");
		usuario.setMatricula(matricula);
		usuario.setSenha(senha);
		return usuario;
	}
}






