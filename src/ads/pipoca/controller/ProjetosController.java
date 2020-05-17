package ads.pipoca.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ads.pipoca.model.entity.Projeto;
import ads.pipoca.model.service.ProjetoService;

/**
 * Servlet implementation class ProjetosController
 */
@WebServlet("/projetos.do")
public class ProjetosController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String acao = request.getParameter("acao");
		ProjetoService pService = new ProjetoService();
		String saida = "index.jsp";
		ArrayList<Projeto> projetos = null;
		
		switch (acao) {
		case "listar":
			projetos = pService.listarProjetos();
			request.setAttribute("projetos", projetos);
			saida = "ListaProjetos.jsp";
			break;

		default:
			break;
		}
		RequestDispatcher view = request.getRequestDispatcher(saida);
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
