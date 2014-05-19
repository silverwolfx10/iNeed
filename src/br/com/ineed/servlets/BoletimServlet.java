package br.com.ineed.servlets;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.ineed.bean.Avaliacao;
import br.com.ineed.bean.Materia;
import br.com.ineed.bean.Nota;
import br.com.ineed.bean.Turma;
import br.com.ineed.bean.Usuario;
import br.com.ineed.dao.AvaliacaoDAO;
import br.com.ineed.dao.MateriaDAO;
import br.com.ineed.dao.NotaDAO;
import br.com.ineed.dao.TurmaDAO;



@WebServlet("/boletim")
public class BoletimServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String pag;
	private Boolean redirect;
	private Usuario usuario;
	
	public BoletimServlet() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		verifyLogin(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		verifyLogin(request, response);
	}
	
	protected void verifyLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//pega da sessao
		HttpSession session = request.getSession(true);
		this.usuario = (Usuario) session.getAttribute("usuarioLogado");
		if(this.usuario == null)
			response.sendRedirect("login");
		else
			requestHandler(request, response);
	}
	
	protected void requestHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.pag = "/boletim/boletim.jsp";
		
		NotaDAO dao = new NotaDAO();
		ArrayList<Nota> boletim = null;
		
		AvaliacaoDAO avaliacaoDao = new AvaliacaoDAO();
		ArrayList<Avaliacao> avaliacoes = (ArrayList<Avaliacao>) avaliacaoDao.getAll();
		
		MateriaDAO materiaDao = new MateriaDAO();
		ArrayList<Materia> materias = (ArrayList<Materia>) materiaDao.getAllByTurmaId(this.usuario.getTurmaId().getId());
		
		boletim = (ArrayList<Nota>)dao.getBoletim(avaliacoes, materias);
		System.out.print(boletim.size());
		
		request.setAttribute("pagina", this.pag);
		request.setAttribute("boletim", boletim);
		RequestDispatcher dispatcher = request.getRequestDispatcher("layout/usuario.jsp");
		dispatcher.forward(request, response);
		
	}

	protected void cadastrar(HttpServletRequest request, HttpServletResponse response) throws IOException{
		this.pag = "/nota/formulario.jsp";
		NotaDAO dao = new NotaDAO();
		
		Float nota = null;
		Materia materia = null;
		Avaliacao avaliacao = null;
		Integer semestre = null;
		Integer id = null;
		
		
		if(request.getParameter("id") != null  && (!request.getParameter("id").equals("")))
			id = Integer.parseInt(request.getParameter("id"));
		
		if(request.getParameter("nota") != null && !request.getParameter("nota").equals(""))
			nota = Float.parseFloat(request.getParameter("nota"));
		
		if(request.getParameter("materia_id") != null && !request.getParameter("materia_id").equals("")){
			materia = new Materia();
			materia.setId(Integer.parseInt(request.getParameter("materia_id")));
		}
		
		if(request.getParameter("avaliacao_id") != null && !request.getParameter("avaliacao_id").equals("")){
			avaliacao = new Avaliacao();
			avaliacao.setId(Integer.parseInt(request.getParameter("avaliacao_id")));
		}
		
		if(request.getParameter("semestre") != null && !request.getParameter("semestre").equals(""))
			semestre = Integer.parseInt(request.getParameter("semestre"));
		
		
		ArrayList<Materia> materias = null;
		ArrayList<Avaliacao> avaliacoes = null;
		

		
		if(id != null && nota != null && materia != null && avaliacao != null && semestre != null){
			//update	
			Nota nt = new Nota(id, materia, avaliacao, nota, semestre, this.usuario);
			dao.update(nt);
			
			this.redirect = true;
			response.sendRedirect("nota?materia_id="+materia.getId());
			
		}else if(nota != null && materia != null && avaliacao != null && semestre != null){
			//cadastrar
			Nota nt = new Nota(materia, avaliacao, nota, semestre, this.usuario);
			dao.insert(nt);
		
			this.redirect = true;
			response.sendRedirect("nota?materia_id="+materia.getId());
			
		}else if(id != null){
			//popula o formulario
			Nota nt = dao.get(id);
			
			//dependencia do select no formulario
			MateriaDAO materiaDao = new MateriaDAO();
			materias 	= (ArrayList<Materia>)materiaDao.getAll();
			
			AvaliacaoDAO avaliacaoDao = new AvaliacaoDAO();
			avaliacoes 	= (ArrayList<Avaliacao>)avaliacaoDao.getAll();
			
			this.redirect = false;
			request.setAttribute("materias",materias);
			request.setAttribute("avaliacoes",avaliacoes);
			request.setAttribute("nota",nt);
			request.setAttribute("title", "Edicao de Nota");
		}else{
			
			//dependencia do select no formulario
			MateriaDAO materiaDao = new MateriaDAO();
			materias 	= (ArrayList<Materia>)materiaDao.getAll();
			
			AvaliacaoDAO avaliacaoDao = new AvaliacaoDAO();
			avaliacoes 	= (ArrayList<Avaliacao>)avaliacaoDao.getAll();
			
			request.setAttribute("materias",materias);
			request.setAttribute("avaliacoes",avaliacoes);
			request.setAttribute("title", "Cadastro de Nota");
		}
	}

	protected void listar(HttpServletRequest request, Integer materia_id){
		//define a pagina que vai ser carregado o conteudo	
		this.pag = "/nota/lista.jsp";
		
		NotaDAO dao = new NotaDAO();	
		ArrayList<Nota> notas = null;
		
		MateriaDAO materiaDao = new MateriaDAO();	
		ArrayList<Materia> materias = (ArrayList<Materia>) materiaDao.getAll();
		
		//consulta no banco todas as turmas
		if(this.usuario.getIsAdmin() == 1){
			notas = (ArrayList<Nota>)dao.getAllByMateriaId(materia_id);
		}else{
			notas = (ArrayList<Nota>)dao.getAllByMateriaIdAndUserId(materia_id, this.usuario.getId());
		}
		
		//seta variavel em escopo de requisicao		
		request.setAttribute("notas", notas);
		request.setAttribute("materia_id", materia_id);
		request.setAttribute("materias", materias);
		request.setAttribute("title", "Lista de Notas");
	}
	
	protected void excluir(HttpServletRequest request, HttpServletResponse response, Integer materia_id) throws IOException{
		NotaDAO dao = new NotaDAO();
		//casting
		Integer id = Integer.parseInt(request.getParameter("id"));
		dao.delete(id);
		
		this.redirect = true;
		response.sendRedirect("nota?materia_id="+materia_id);
	}

}
