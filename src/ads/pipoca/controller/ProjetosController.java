package ads.pipoca.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ads.pipoca.model.entity.Colaborador;
import ads.pipoca.model.entity.Objetivo;
import ads.pipoca.model.entity.Projeto;
import ads.pipoca.model.entity.SituacaoProjeto;
import ads.pipoca.model.service.ColaboradorService;
import ads.pipoca.model.service.ObjetivoService;
import ads.pipoca.model.service.ProjetoService;
import ads.pipoca.model.service.SituacaoProjetoService;

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
		Projeto projeto = null;
		Objetivo objetivo = null;
		Colaborador colaborador = null;
		SituacaoProjeto situacaoProjeto = null;
		ProjetoService pService = new ProjetoService();
		ObjetivoService oService = new ObjetivoService();
		ColaboradorService cService = new ColaboradorService();
		SituacaoProjetoService spService = new SituacaoProjetoService();
		ArrayList<Objetivo> objetivos = null;
		String nome = null;
		String estimativa = null;
		String idObjetivo = null;
		String departamento = null;
		String resultado_esperado = null;
		String publico_beneficiario = null;
		String descricao = null;
		SimpleDateFormat formater = null;
		java.util.Date dataEstimativa = null;
		String saida = "index.jsp";
		ArrayList<Projeto> projetos = null;
		
		switch (acao) {
		case "listar":
			projetos = pService.listarProjetos();
			objetivos = oService.listarObjetivos();			
			request.setAttribute("projetos", projetos);
			request.setAttribute("objetivos", objetivos);
			saida = "ListaProjetos.jsp";
			break;
			
		case "inserir_projeto":
			nome = request.getParameter("nome");
			estimativa = request.getParameter("estimativa");
			idObjetivo = request.getParameter("objetivo");
			departamento = request.getParameter("departamento");
			resultado_esperado = request.getParameter("resultado_esperado");
			publico_beneficiario = request.getParameter("publico_beneficiario");
			descricao = request.getParameter("descricao");
			projeto = new Projeto();
			projeto.setNome(nome);
			formater = new SimpleDateFormat("yyyy-MM-dd");
			dataEstimativa = null;
			try {
				dataEstimativa = formater.parse(estimativa);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			projeto.setEstimativa(dataEstimativa);
			objetivo = oService.buscarObjetivo(Integer.parseInt(idObjetivo));
			projeto.setObjetivo(objetivo);
			projeto.setDepartamento(departamento);
			projeto.setResultadoEsperado(resultado_esperado);
			projeto.setPublicoBeneficiario(publico_beneficiario);
			colaborador = cService.buscarColaborador(1);
			projeto.setColaborador(colaborador);
			situacaoProjeto = spService.buscarSituacao(1);
			projeto.setSituacaoProjeto(situacaoProjeto);
			projeto.setDescricao(descricao);
			int id = pService.inserirProjeto(projeto);
			projeto.setId(id);
			
			System.out.println(projeto);
			//request.setAttribute("filme", filme);
			saida = "index.jsp";
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
