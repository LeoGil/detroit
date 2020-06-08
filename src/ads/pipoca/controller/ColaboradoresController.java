package ads.pipoca.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ads.pipoca.model.entity.Colaborador;
import ads.pipoca.model.service.ColaboradorService;

@WebServlet("/colaboradores.do")
public class ColaboradoresController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession session = req.getSession();
		String acao = request.getParameter("acao");
		Colaborador colaborador = null;
		ColaboradorService cService = new ColaboradorService();
		String nome = null;
		String email = null;
		String matricula = null;
		String senha = null;
		String saida = "index.jsp";
		ArrayList<Colaborador> colaboradores = null;
		String id_colaborador = null;
		int idColaborador = 0;
		
		switch (acao) {
		case "listar":
			colaboradores = cService.listarColaboradores();
			request.setAttribute("colaboradores", colaboradores);
			
			request.setAttribute("usuarioLogado", (Colaborador)session.getAttribute("logado"));
			
			saida = "ListaColaboradores.jsp";
			break;
			
		case "inserir_colaborador":
			nome = request.getParameter("nome");
			email = request.getParameter("email");
			matricula = request.getParameter("matricula");
			senha = request.getParameter("senha");
			colaborador = new Colaborador();
			colaborador.setNome(nome);
			colaborador.setEmail(email);
			colaborador.setMatricula(matricula);
			colaborador.setSenha(senha);
			int id = cService.inserirColaborador(colaborador);
			colaborador.setId(id);
			
			saida = "index.jsp";
			break;
			
		case "visualizar_colaborador":
			id_colaborador = request.getParameter("id_colaborador");
			idColaborador = Integer.parseInt(id_colaborador);
			colaborador = cService.buscarColaborador(idColaborador);
			request.setAttribute("colaborador", colaborador);
			request.setAttribute("usuarioLogado", (Colaborador)session.getAttribute("logado"));
			
			saida = "VisualizarColaborador.jsp";
			break;
		
		case "editar_colaborador":
			id_colaborador = request.getParameter("id_colaborador");
			idColaborador = Integer.parseInt(id_colaborador);
			colaborador = cService.buscarColaborador(idColaborador);
			request.setAttribute("colaborador", colaborador);
			request.setAttribute("usuarioLogado", (Colaborador)session.getAttribute("logado"));
			
			saida = "EditarColaborador.jsp";
			break;	
		
		case "salvar_colaborador":
			nome = request.getParameter("nome");
			email = request.getParameter("email");
			matricula = request.getParameter("matricula");
			senha = request.getParameter("senha");
			id_colaborador = request.getParameter("id_colaborador");
			idColaborador = Integer.parseInt(id_colaborador);
			colaborador = new Colaborador();
			colaborador.setNome(nome);
			colaborador.setEmail(email);
			colaborador.setMatricula(matricula);
			colaborador.setSenha(senha);
			colaborador.setId(idColaborador);
			cService.atualizarColaborador(colaborador);
			
			saida = "index.jsp";
			break;	
			
		case "deslogar":
			session.invalidate();
			
			saida = "Login.jsp";
			break;
			
		default:
			break;
		}
		RequestDispatcher view = request.getRequestDispatcher(saida);
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
