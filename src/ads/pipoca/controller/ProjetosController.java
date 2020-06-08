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
import javax.servlet.http.HttpSession;

import ads.pipoca.model.entity.Colaborador;
import ads.pipoca.model.entity.Comentario;
import ads.pipoca.model.entity.Contribuinte;
import ads.pipoca.model.entity.ContribuinteTarefa;
import ads.pipoca.model.entity.Objetivo;
import ads.pipoca.model.entity.Papel;
import ads.pipoca.model.entity.Projeto;
import ads.pipoca.model.entity.SituacaoProjeto;
import ads.pipoca.model.entity.SituacaoTarefa;
import ads.pipoca.model.entity.Tarefa;
import ads.pipoca.model.service.ColaboradorService;
import ads.pipoca.model.service.ComentarioService;
import ads.pipoca.model.service.ContribuinteService;
import ads.pipoca.model.service.ContribuinteTarefaService;
import ads.pipoca.model.service.ObjetivoService;
import ads.pipoca.model.service.PapelService;
import ads.pipoca.model.service.ProjetoService;
import ads.pipoca.model.service.SituacaoProjetoService;
import ads.pipoca.model.service.SituacaoTarefaService;
import ads.pipoca.model.service.TarefaService;

/**
 * Servlet implementation class ProjetosController
 */
@WebServlet("/projetos.do")
public class ProjetosController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession session = req.getSession();
		String acao = request.getParameter("acao");
		Projeto projeto = null;
		Objetivo objetivo = null;
		Colaborador colaborador = null;
		SituacaoProjeto situacaoProjeto = null;
		ProjetoService pService = new ProjetoService();
		ObjetivoService oService = new ObjetivoService();
		ColaboradorService cService = new ColaboradorService();
		ContribuinteService ctService = new ContribuinteService();
		SituacaoProjetoService spService = new SituacaoProjetoService();
		SituacaoTarefaService stService = new SituacaoTarefaService();
		ContribuinteTarefaService ContribuinteTarefaService = new ContribuinteTarefaService();
		TarefaService tService = new TarefaService();
		ComentarioService cmService = new ComentarioService();
		PapelService papelService = new PapelService();
		ArrayList<Objetivo> objetivos = null;
		ArrayList<Contribuinte> contribuintes = null;
		ArrayList<Tarefa> tarefasTodo = null;
		ArrayList<Tarefa> tarefasDoing = null;
		ArrayList<Tarefa> tarefasDone = null;
		ArrayList<Comentario> comentarios = null;
		ArrayList<Colaborador> colaboradoresLiberados = null;
		ArrayList<Papel> papeis = null;
		int totalTarefas = 0;
		int totalTarefasFeitas = 0;
		String id_projeto = null;
		String nome = null;
		String estimativa = null;
		String idObjetivo = null;
		String departamento = null;
		String resultado_esperado = null;
		String publico_beneficiario = null;
		String descricao = null;
		String idUsuarioLogado = null;
		SimpleDateFormat formater = null;
		java.util.Date dataEstimativa = null;
		String saida = "index.jsp";
		ArrayList<Projeto> projetos = null;
		int idProjeto = 0;

		// Inserir comentario
		Comentario ComentarioModel = null;
		String comentario = null;
		String idProjetoComentario = null;
		String idColaboradorComentario = null;

		// Inserir tarefa
		Tarefa TarefaModel = null;
		ContribuinteTarefa ContribuinteTarefa = null;
		SituacaoTarefa situacaoTarefa = null;
		Contribuinte contribuinte = null;
		String titulo = null;
		String idProjetoTarefa = null;
		String idContribuinteTarefa = null;

		// Inserir contribuinte
		Contribuinte ContribuinteModel = null;
		Papel papel = null;
		String idProjetoContribuinte = null;
		String idPapel = null;
		String idColaborador = null;

		// Excluir comentario
		String idComentario = null;

		// Excluir comentario
		String idContribuinte = null;

		// Avancar Tarefa
		String id_tarefa = null;

		switch (acao) {
		case "listar":
			projetos = pService.listarProjetos();
			objetivos = oService.listarObjetivos();
			request.setAttribute("projetos", projetos);
			request.setAttribute("objetivos", objetivos);
			request.setAttribute("usuarioLogado", (Colaborador)session.getAttribute("logado"));
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
			idUsuarioLogado = request.getParameter("id_colaborador");
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
			colaborador = cService.buscarColaborador(Integer.parseInt(idUsuarioLogado));
			projeto.setColaborador(colaborador);
			situacaoProjeto = spService.buscarSituacao(1);
			projeto.setSituacaoProjeto(situacaoProjeto);
			projeto.setDescricao(descricao);
			idProjeto = pService.inserirProjeto(projeto);
			projeto.setId(idProjeto);

			
			projeto = pService.buscarProjeto(idProjeto);
			request.setAttribute("projeto", projeto);

			contribuintes = ctService.listarContribuintesPorProjeto(idProjeto);
			request.setAttribute("contribuintes", contribuintes);

			tarefasTodo = tService.listarContribuintesPorProjetoToDo(idProjeto);
			request.setAttribute("tarefasTodo", tarefasTodo);

			tarefasDoing = tService.listarContribuintesPorProjetoDoing(idProjeto);
			request.setAttribute("tarefasDoing", tarefasDoing);

			tarefasDone = tService.listarContribuintesPorProjetoDone(idProjeto);
			request.setAttribute("tarefasDone", tarefasDone);

			comentarios = cmService.listarComentariosPorProjeto(idProjeto);
			request.setAttribute("comentarios", comentarios);

			colaboradoresLiberados = cService.listarColaboradoresLiberados(idProjeto);
			request.setAttribute("colaboradoresLiberados", colaboradoresLiberados);

			papeis = papelService.listarPapeis();
			request.setAttribute("papeis", papeis);

			totalTarefas = tService.totalTarefasPorProjeto(idProjeto);
			request.setAttribute("totalTarefas", totalTarefas);

			totalTarefasFeitas = tService.totalTarefasFeitasPorProjeto(idProjeto);
			request.setAttribute("totalTarefasFeitas", totalTarefasFeitas);
			
			request.setAttribute("usuarioLogado", (Colaborador)session.getAttribute("logado"));

			saida = "VisualizarProjeto.jsp";
			break;

		case "visualizar_projeto":
			id_projeto = request.getParameter("id_projeto");
			idProjeto = Integer.parseInt(id_projeto);

			projeto = pService.buscarProjeto(idProjeto);
			request.setAttribute("projeto", projeto);

			contribuintes = ctService.listarContribuintesPorProjeto(idProjeto);
			request.setAttribute("contribuintes", contribuintes);

			tarefasTodo = tService.listarContribuintesPorProjetoToDo(idProjeto);
			request.setAttribute("tarefasTodo", tarefasTodo);

			tarefasDoing = tService.listarContribuintesPorProjetoDoing(idProjeto);
			request.setAttribute("tarefasDoing", tarefasDoing);

			tarefasDone = tService.listarContribuintesPorProjetoDone(idProjeto);
			request.setAttribute("tarefasDone", tarefasDone);

			comentarios = cmService.listarComentariosPorProjeto(idProjeto);
			request.setAttribute("comentarios", comentarios);

			colaboradoresLiberados = cService.listarColaboradoresLiberados(idProjeto);
			request.setAttribute("colaboradoresLiberados", colaboradoresLiberados);

			papeis = papelService.listarPapeis();
			request.setAttribute("papeis", papeis);

			totalTarefas = tService.totalTarefasPorProjeto(idProjeto);
			request.setAttribute("totalTarefas", totalTarefas);

			totalTarefasFeitas = tService.totalTarefasFeitasPorProjeto(idProjeto);
			request.setAttribute("totalTarefasFeitas", totalTarefasFeitas);
			
			request.setAttribute("usuarioLogado", (Colaborador)session.getAttribute("logado"));

			saida = "VisualizarProjeto.jsp";
			break;

		case "inserir_comentario":
			comentario = request.getParameter("comentario");
			idProjetoComentario = request.getParameter("id_projeto");
			idUsuarioLogado = request.getParameter("id_colaborador");
			ComentarioModel = new Comentario();
			ComentarioModel.setComentario(comentario);
			projeto = pService.buscarProjeto(Integer.parseInt(idProjetoComentario));
			ComentarioModel.setProjeto(projeto);
			colaborador = cService.buscarColaborador(Integer.parseInt(idUsuarioLogado));
			ComentarioModel.setColaborador(colaborador);
			cmService.inserirComentario(ComentarioModel);

			id_projeto = request.getParameter("id_projeto");
			idProjeto = Integer.parseInt(id_projeto);

			projeto = pService.buscarProjeto(idProjeto);
			request.setAttribute("projeto", projeto);

			contribuintes = ctService.listarContribuintesPorProjeto(idProjeto);
			request.setAttribute("contribuintes", contribuintes);

			tarefasTodo = tService.listarContribuintesPorProjetoToDo(idProjeto);
			request.setAttribute("tarefasTodo", tarefasTodo);

			tarefasDoing = tService.listarContribuintesPorProjetoDoing(idProjeto);
			request.setAttribute("tarefasDoing", tarefasDoing);

			tarefasDone = tService.listarContribuintesPorProjetoDone(idProjeto);
			request.setAttribute("tarefasDone", tarefasDone);

			comentarios = cmService.listarComentariosPorProjeto(idProjeto);
			request.setAttribute("comentarios", comentarios);

			colaboradoresLiberados = cService.listarColaboradoresLiberados(idProjeto);
			request.setAttribute("colaboradoresLiberados", colaboradoresLiberados);

			papeis = papelService.listarPapeis();
			request.setAttribute("papeis", papeis);
			
			totalTarefas = tService.totalTarefasPorProjeto(idProjeto);
			request.setAttribute("totalTarefas", totalTarefas);

			totalTarefasFeitas = tService.totalTarefasFeitasPorProjeto(idProjeto);
			request.setAttribute("totalTarefasFeitas", totalTarefasFeitas);
			
			request.setAttribute("usuarioLogado", (Colaborador)session.getAttribute("logado"));

			saida = "VisualizarProjeto.jsp";
			break;

		case "inserir_tarefa":
			titulo = request.getParameter("titulo");
			descricao = request.getParameter("descricao");
			idProjetoTarefa = request.getParameter("id_projeto");
			idColaboradorComentario = "1";
			TarefaModel = new Tarefa();
			TarefaModel.setTitulo(titulo);
			TarefaModel.setDescricao(descricao);
			projeto = pService.buscarProjeto(Integer.parseInt(idProjetoTarefa));
			TarefaModel.setProjeto(projeto);
			situacaoTarefa = stService.buscarSituacaoTarefa(1);
			TarefaModel.setSituacaoTarefa(situacaoTarefa);
			int idTarefa = tService.inserirTarefa(TarefaModel);
			TarefaModel.setId(idTarefa);

			ContribuinteTarefa = new ContribuinteTarefa();
			ContribuinteTarefa.setTarefa(TarefaModel);
			idContribuinteTarefa = request.getParameter("contribuinte");
			contribuinte = ctService.buscarContribuinte(Integer.parseInt(idContribuinteTarefa));
			ContribuinteTarefa.setContribuinte(contribuinte);
			ContribuinteTarefaService.inserirContribuinteTarefa(ContribuinteTarefa);

			id_projeto = request.getParameter("id_projeto");
			idProjeto = Integer.parseInt(id_projeto);

			projeto = pService.buscarProjeto(idProjeto);
			request.setAttribute("projeto", projeto);

			contribuintes = ctService.listarContribuintesPorProjeto(idProjeto);
			request.setAttribute("contribuintes", contribuintes);

			tarefasTodo = tService.listarContribuintesPorProjetoToDo(idProjeto);
			request.setAttribute("tarefasTodo", tarefasTodo);

			tarefasDoing = tService.listarContribuintesPorProjetoDoing(idProjeto);
			request.setAttribute("tarefasDoing", tarefasDoing);

			tarefasDone = tService.listarContribuintesPorProjetoDone(idProjeto);
			request.setAttribute("tarefasDone", tarefasDone);

			comentarios = cmService.listarComentariosPorProjeto(idProjeto);
			request.setAttribute("comentarios", comentarios);

			colaboradoresLiberados = cService.listarColaboradoresLiberados(idProjeto);
			request.setAttribute("colaboradoresLiberados", colaboradoresLiberados);

			papeis = papelService.listarPapeis();
			request.setAttribute("papeis", papeis);
			
			totalTarefas = tService.totalTarefasPorProjeto(idProjeto);
			request.setAttribute("totalTarefas", totalTarefas);

			totalTarefasFeitas = tService.totalTarefasFeitasPorProjeto(idProjeto);
			request.setAttribute("totalTarefasFeitas", totalTarefasFeitas);
			
			request.setAttribute("usuarioLogado", (Colaborador)session.getAttribute("logado"));

			saida = "VisualizarProjeto.jsp";
			break;

		case "inserir_contribuinte":
			idProjetoContribuinte = request.getParameter("id_projeto");
			idPapel = request.getParameter("papel");
			idColaborador = request.getParameter("colaborador");

			ContribuinteModel = new Contribuinte();
			projeto = pService.buscarProjeto(Integer.parseInt(idProjetoContribuinte));
			ContribuinteModel.setProjeto(projeto);

			papel = papelService.buscarPapel(Integer.parseInt(idPapel));
			ContribuinteModel.setPapel(papel);

			colaborador = cService.buscarColaborador(Integer.parseInt(idColaborador));
			ContribuinteModel.setColaborador(colaborador);

			ctService.inserirContribuinte(ContribuinteModel);

			id_projeto = request.getParameter("id_projeto");
			idProjeto = Integer.parseInt(id_projeto);

			projeto = pService.buscarProjeto(idProjeto);
			request.setAttribute("projeto", projeto);

			contribuintes = ctService.listarContribuintesPorProjeto(idProjeto);
			request.setAttribute("contribuintes", contribuintes);

			tarefasTodo = tService.listarContribuintesPorProjetoToDo(idProjeto);
			request.setAttribute("tarefasTodo", tarefasTodo);

			tarefasDoing = tService.listarContribuintesPorProjetoDoing(idProjeto);
			request.setAttribute("tarefasDoing", tarefasDoing);

			tarefasDone = tService.listarContribuintesPorProjetoDone(idProjeto);
			request.setAttribute("tarefasDone", tarefasDone);

			comentarios = cmService.listarComentariosPorProjeto(idProjeto);
			request.setAttribute("comentarios", comentarios);

			colaboradoresLiberados = cService.listarColaboradoresLiberados(idProjeto);
			request.setAttribute("colaboradoresLiberados", colaboradoresLiberados);

			papeis = papelService.listarPapeis();
			request.setAttribute("papeis", papeis);
			
			totalTarefas = tService.totalTarefasPorProjeto(idProjeto);
			request.setAttribute("totalTarefas", totalTarefas);

			totalTarefasFeitas = tService.totalTarefasFeitasPorProjeto(idProjeto);
			request.setAttribute("totalTarefasFeitas", totalTarefasFeitas);
			
			request.setAttribute("usuarioLogado", (Colaborador)session.getAttribute("logado"));

			saida = "VisualizarProjeto.jsp";
			break;

		case "excluir_comentario":
			idComentario = request.getParameter("id_comentario");

			cmService.excluirComentario(Integer.parseInt(idComentario));

			id_projeto = request.getParameter("id_projeto");
			idProjeto = Integer.parseInt(id_projeto);

			projeto = pService.buscarProjeto(idProjeto);
			request.setAttribute("projeto", projeto);

			contribuintes = ctService.listarContribuintesPorProjeto(idProjeto);
			request.setAttribute("contribuintes", contribuintes);

			tarefasTodo = tService.listarContribuintesPorProjetoToDo(idProjeto);
			request.setAttribute("tarefasTodo", tarefasTodo);

			tarefasDoing = tService.listarContribuintesPorProjetoDoing(idProjeto);
			request.setAttribute("tarefasDoing", tarefasDoing);

			tarefasDone = tService.listarContribuintesPorProjetoDone(idProjeto);
			request.setAttribute("tarefasDone", tarefasDone);

			comentarios = cmService.listarComentariosPorProjeto(idProjeto);
			request.setAttribute("comentarios", comentarios);

			colaboradoresLiberados = cService.listarColaboradoresLiberados(idProjeto);
			request.setAttribute("colaboradoresLiberados", colaboradoresLiberados);

			papeis = papelService.listarPapeis();
			request.setAttribute("papeis", papeis);
			
			totalTarefas = tService.totalTarefasPorProjeto(idProjeto);
			request.setAttribute("totalTarefas", totalTarefas);

			totalTarefasFeitas = tService.totalTarefasFeitasPorProjeto(idProjeto);
			request.setAttribute("totalTarefasFeitas", totalTarefasFeitas);
			
			request.setAttribute("usuarioLogado", (Colaborador)session.getAttribute("logado"));

			saida = "VisualizarProjeto.jsp";
			break;

		case "excluir_contribuinte":
			idContribuinte = request.getParameter("id_comentario");

			ctService.excluirContribuinte(Integer.parseInt(idContribuinte));

			id_projeto = request.getParameter("id_projeto");
			idProjeto = Integer.parseInt(id_projeto);

			projeto = pService.buscarProjeto(idProjeto);
			request.setAttribute("projeto", projeto);

			contribuintes = ctService.listarContribuintesPorProjeto(idProjeto);
			request.setAttribute("contribuintes", contribuintes);

			tarefasTodo = tService.listarContribuintesPorProjetoToDo(idProjeto);
			request.setAttribute("tarefasTodo", tarefasTodo);

			tarefasDoing = tService.listarContribuintesPorProjetoDoing(idProjeto);
			request.setAttribute("tarefasDoing", tarefasDoing);

			tarefasDone = tService.listarContribuintesPorProjetoDone(idProjeto);
			request.setAttribute("tarefasDone", tarefasDone);

			comentarios = cmService.listarComentariosPorProjeto(idProjeto);
			request.setAttribute("comentarios", comentarios);

			colaboradoresLiberados = cService.listarColaboradoresLiberados(idProjeto);
			request.setAttribute("colaboradoresLiberados", colaboradoresLiberados);

			papeis = papelService.listarPapeis();
			request.setAttribute("papeis", papeis);
			
			totalTarefas = tService.totalTarefasPorProjeto(idProjeto);
			request.setAttribute("totalTarefas", totalTarefas);

			totalTarefasFeitas = tService.totalTarefasFeitasPorProjeto(idProjeto);
			request.setAttribute("totalTarefasFeitas", totalTarefasFeitas);
			
			request.setAttribute("usuarioLogado", (Colaborador)session.getAttribute("logado"));

			saida = "VisualizarProjeto.jsp";
			break;

		case "avancar_tarefa_doing":
			id_tarefa = request.getParameter("id_tarefa");

			tService.updateSituacaoTarefa(Integer.parseInt(id_tarefa), 2);

			id_projeto = request.getParameter("id_projeto");
			idProjeto = Integer.parseInt(id_projeto);

			projeto = pService.buscarProjeto(idProjeto);
			request.setAttribute("projeto", projeto);

			contribuintes = ctService.listarContribuintesPorProjeto(idProjeto);
			request.setAttribute("contribuintes", contribuintes);

			tarefasTodo = tService.listarContribuintesPorProjetoToDo(idProjeto);
			request.setAttribute("tarefasTodo", tarefasTodo);

			tarefasDoing = tService.listarContribuintesPorProjetoDoing(idProjeto);
			request.setAttribute("tarefasDoing", tarefasDoing);

			tarefasDone = tService.listarContribuintesPorProjetoDone(idProjeto);
			request.setAttribute("tarefasDone", tarefasDone);

			comentarios = cmService.listarComentariosPorProjeto(idProjeto);
			request.setAttribute("comentarios", comentarios);

			colaboradoresLiberados = cService.listarColaboradoresLiberados(idProjeto);
			request.setAttribute("colaboradoresLiberados", colaboradoresLiberados);

			papeis = papelService.listarPapeis();
			request.setAttribute("papeis", papeis);
			
			totalTarefas = tService.totalTarefasPorProjeto(idProjeto);
			request.setAttribute("totalTarefas", totalTarefas);

			totalTarefasFeitas = tService.totalTarefasFeitasPorProjeto(idProjeto);
			request.setAttribute("totalTarefasFeitas", totalTarefasFeitas);
			
			request.setAttribute("usuarioLogado", (Colaborador)session.getAttribute("logado"));

			saida = "VisualizarProjeto.jsp";
			break;

		case "retroceder_tarefa_todo":
			id_tarefa = request.getParameter("id_tarefa");

			tService.updateSituacaoTarefa(Integer.parseInt(id_tarefa), 1);

			id_projeto = request.getParameter("id_projeto");
			idProjeto = Integer.parseInt(id_projeto);

			projeto = pService.buscarProjeto(idProjeto);
			request.setAttribute("projeto", projeto);

			contribuintes = ctService.listarContribuintesPorProjeto(idProjeto);
			request.setAttribute("contribuintes", contribuintes);

			tarefasTodo = tService.listarContribuintesPorProjetoToDo(idProjeto);
			request.setAttribute("tarefasTodo", tarefasTodo);

			tarefasDoing = tService.listarContribuintesPorProjetoDoing(idProjeto);
			request.setAttribute("tarefasDoing", tarefasDoing);

			tarefasDone = tService.listarContribuintesPorProjetoDone(idProjeto);
			request.setAttribute("tarefasDone", tarefasDone);

			comentarios = cmService.listarComentariosPorProjeto(idProjeto);
			request.setAttribute("comentarios", comentarios);

			colaboradoresLiberados = cService.listarColaboradoresLiberados(idProjeto);
			request.setAttribute("colaboradoresLiberados", colaboradoresLiberados);

			papeis = papelService.listarPapeis();
			request.setAttribute("papeis", papeis);
			
			totalTarefas = tService.totalTarefasPorProjeto(idProjeto);
			request.setAttribute("totalTarefas", totalTarefas);

			totalTarefasFeitas = tService.totalTarefasFeitasPorProjeto(idProjeto);
			request.setAttribute("totalTarefasFeitas", totalTarefasFeitas);
			
			request.setAttribute("usuarioLogado", (Colaborador)session.getAttribute("logado"));

			saida = "VisualizarProjeto.jsp";
			break;

		case "avancar_tarefa_done":
			id_tarefa = request.getParameter("id_tarefa");

			tService.updateSituacaoTarefa(Integer.parseInt(id_tarefa), 3);

			id_projeto = request.getParameter("id_projeto");
			idProjeto = Integer.parseInt(id_projeto);

			projeto = pService.buscarProjeto(idProjeto);
			request.setAttribute("projeto", projeto);

			contribuintes = ctService.listarContribuintesPorProjeto(idProjeto);
			request.setAttribute("contribuintes", contribuintes);

			tarefasTodo = tService.listarContribuintesPorProjetoToDo(idProjeto);
			request.setAttribute("tarefasTodo", tarefasTodo);

			tarefasDoing = tService.listarContribuintesPorProjetoDoing(idProjeto);
			request.setAttribute("tarefasDoing", tarefasDoing);

			tarefasDone = tService.listarContribuintesPorProjetoDone(idProjeto);
			request.setAttribute("tarefasDone", tarefasDone);

			comentarios = cmService.listarComentariosPorProjeto(idProjeto);
			request.setAttribute("comentarios", comentarios);

			colaboradoresLiberados = cService.listarColaboradoresLiberados(idProjeto);
			request.setAttribute("colaboradoresLiberados", colaboradoresLiberados);

			papeis = papelService.listarPapeis();
			request.setAttribute("papeis", papeis);
			
			totalTarefas = tService.totalTarefasPorProjeto(idProjeto);
			request.setAttribute("totalTarefas", totalTarefas);

			totalTarefasFeitas = tService.totalTarefasFeitasPorProjeto(idProjeto);
			request.setAttribute("totalTarefasFeitas", totalTarefasFeitas);
			
			request.setAttribute("usuarioLogado", (Colaborador)session.getAttribute("logado"));

			saida = "VisualizarProjeto.jsp";
			break;
		default:
			break;
		}
		RequestDispatcher view = request.getRequestDispatcher(saida);
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
