package ads.pipoca.model.service;

import java.io.IOException;
import ads.pipoca.model.dao.ComentarioDAO;
import ads.pipoca.model.entity.Comentario;

public class ComentarioService {
	ComentarioDAO dao;
	
	public ComentarioService() {
		this.dao = new ComentarioDAO();
	}
	
	public int inserirComentario(Comentario comentario) throws IOException {
		return dao.inserirComentario(comentario);
	}
	
	public Comentario excluirComentario(int id) throws IOException {
		Comentario comentario = dao.buscarComentario(id);
		if (comentario != null) {
			dao.excluirComentario(comentario.getId());
			return comentario;
		} else {
			return null;
		}
	}
}
